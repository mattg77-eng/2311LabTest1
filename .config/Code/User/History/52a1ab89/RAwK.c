#include <stdio.h>
#include <stdlib.h>

#define PAGE_SIZE 256
#define NUM_PAGES 256
#define TLB_SIZE 16

typedef struct {
    int page;
    int frame;
    int valid;
} TLBEntry;

// Global structures
static int page_table[NUM_PAGES]; // page -> frame, or -1 if not loaded
static TLBEntry tlb[TLB_SIZE];
static int tlb_next_to_replace = 0;

static signed char *physical_memory = NULL;

// For page replacement (Phase 2)
static int *frame_page = NULL;      // frame_page[f] = which page is currently in frame f, or -1
static int *frame_last_used = NULL; // for LRU: "time" of last use of each frame
static int time_counter = 0;

// Stats
static int num_page_faults = 0;
static int num_tlb_hits = 0;
static int num_addresses = 0;

// Look up a page in the TLB. Return frame or -1 if miss.
int tlb_lookup(int page) {
    for (int i = 0; i < TLB_SIZE; i++) {
        if (tlb[i].valid && tlb[i].page == page) {
            return tlb[i].frame;
        }
    }
    return -1;
}

// Insert/Update TLB using FIFO replacement policy
void tlb_add(int page, int frame) {
    tlb[tlb_next_to_replace].page = page;
    tlb[tlb_next_to_replace].frame = frame;
    tlb[tlb_next_to_replace].valid = 1;

    tlb_next_to_replace = (tlb_next_to_replace + 1) % TLB_SIZE;
}

// Invalidate a page in TLB (used when that page is evicted from memory)
void tlb_invalidate_page(int page) {
    for (int i = 0; i < TLB_SIZE; i++) {
        if (tlb[i].valid && tlb[i].page == page) {
            tlb[i].valid = 0;
        }
    }
}

// Choose a frame to load a page into: first free frame, otherwise LRU victim
int select_frame(int num_frames) {
    // First look for free frame
    for (int f = 0; f < num_frames; f++) {
        if (frame_page[f] == -1) {
            return f;
        }
    }

    // No free frame: use LRU
    int lru_frame = 0;
    int min_time = frame_last_used[0];
    for (int f = 1; f < num_frames; f++) {
        if (frame_last_used[f] < min_time) {
            min_time = frame_last_used[f];
            lru_frame = f;
        }
    }
    return lru_frame;
}

// Handle page fault: load page from BACKING_STORE into a frame and update structures
int handle_page_fault(int page, FILE *backing_store, int num_frames) {
    num_page_faults++;

    int frame = select_frame(num_frames);

    // If this frame already has some page, evict it
    int old_page = frame_page[frame];
    if (old_page != -1) {
        // Mark old page as not present and invalidate it in TLB
        page_table[old_page] = -1;
        tlb_invalidate_page(old_page);
    }

    // Read page from backing store
    if (fseek(backing_store, page * PAGE_SIZE, SEEK_SET) != 0) {
        perror("fseek on backing store failed");
        exit(1);
    }

    size_t read_count = fread(physical_memory + frame * PAGE_SIZE,
                              sizeof(signed char),
                              PAGE_SIZE,
                              backing_store);
    if (read_count != PAGE_SIZE) {
        fprintf(stderr, "Error: could not read a full page from backing store\n");
        exit(1);
    }

    // Update page table and frame metadata
    page_table[page] = frame;
    frame_page[frame] = page;
    // LRU timestamp for this frame will be updated once in main() per access

    // Add to TLB
    tlb_add(page, frame);

    return frame;
}

int main(int argc, char *argv[]) {
    if (argc != 4) {
        fprintf(stderr, "Usage: %s <num_frames> <backing_store> <addresses_file>\n", argv[0]);
        return 1;
    }

    int num_frames = atoi(argv[1]);
    const char *backing_store_name = argv[2];
    const char *addresses_name = argv[3];

    if (num_frames <= 0 || num_frames > 256) {
        fprintf(stderr, "Error: num_frames must be between 1 and 256\n");
        return 1;
    }

    int phys_mem_size = num_frames * PAGE_SIZE;

    // Allocate memory
    physical_memory = (signed char *)malloc(phys_mem_size * sizeof(signed char));
    frame_page = (int *)malloc(num_frames * sizeof(int));
    frame_last_used = (int *)malloc(num_frames * sizeof(int));

    if (!physical_memory || !frame_page || !frame_last_used) {
        fprintf(stderr, "Error: memory allocation failed\n");
        return 1;
    }

    // Initialize page table
    for (int i = 0; i < NUM_PAGES; i++) {
        page_table[i] = -1;
    }

    // Initialize TLB
    for (int i = 0; i < TLB_SIZE; i++) {
        tlb[i].valid = 0;
        tlb[i].page = -1;
        tlb[i].frame = -1;
    }

    // Initialize frame metadata
    for (int f = 0; f < num_frames; f++) {
        frame_page[f] = -1;
        frame_last_used[f] = 0;
    }

    // Open backing store
    FILE *backing_store = fopen(backing_store_name, "rb");
    if (!backing_store) {
        perror("Error opening backing store");
        return 1;
    }

    // Open addresses file
    FILE *addresses = fopen(addresses_name, "r");
    if (!addresses) {
        perror("Error opening addresses file");
        return 1;
    }

    // Determine output file name
    char output_name[64];
    if (num_frames == 256) {
        snprintf(output_name, sizeof(output_name), "output256.csv");
    } else if (num_frames == 128) {
        snprintf(output_name, sizeof(output_name), "output128.csv");
    } else {
        // Generic, just in case
        snprintf(output_name, sizeof(output_name), "output%d.csv", num_frames);
    }

    FILE *output = fopen(output_name, "w");
    if (!output) {
        perror("Error opening output file");
        return 1;
    }

    int logical_address;
    while (fscanf(addresses, "%d", &logical_address) == 1) {
        num_addresses++;

        unsigned int logical16 = (unsigned int)logical_address & 0xFFFF;
        int page = (logical16 >> 8) & 0xFF;
        int offset = logical16 & 0xFF;

        int frame;

        // 1) TLB lookup
        frame = tlb_lookup(page);
        if (frame != -1) {
            // TLB hit
            num_tlb_hits++;
        } else {
            // 2) Page table lookup
            frame = page_table[page];
            if (frame == -1) {
                // Page fault: load from backing store (also updates TLB)
                frame = handle_page_fault(page, backing_store, num_frames);
            } else {
                // Page table hit & TLB miss: update TLB
                tlb_add(page, frame);
            }
        }

        int physical_address = frame * PAGE_SIZE + offset;
        signed char value = physical_memory[physical_address];

        // Update LRU timestamp ONCE per address for the frame we actually used
        frame_last_used[frame] = time_counter++;

        // Output CSV line: logical,physical,value
        fprintf(output, "%d,%d,%d\n", logical_address, physical_address, (int)value);
    }

    // Compute statistics as PERCENTAGES (like "24.40%")
    double page_fault_rate = 0.0;
    double tlb_hit_rate = 0.0;

    if (num_addresses > 0) {
        page_fault_rate = ((double)num_page_faults / (double)num_addresses) * 100.0;
        tlb_hit_rate = ((double)num_tlb_hits / (double)num_addresses) * 100.0;
    }

    // Print statistics EXACTLY like correct*.csv
    // Example from your files:
    // Page Faults Rate,24.40%, 
    // TLB Hits Rate,5.40%,
    fprintf(output, "Page Faults Rate,%.2f%%,\n", page_fault_rate);
    fprintf(output, "TLB Hits Rate,%.2f%%,\n", tlb_hit_rate);

    // Clean up
    fclose(output);
    fclose(addresses);
    fclose(backing_store);

    free(physical_memory);
    free(frame_page);
    free(frame_last_used);

    return 0;
}
