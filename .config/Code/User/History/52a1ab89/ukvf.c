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

static int page_table[NUM_PAGES];
static TLBEntry tlb[TLB_SIZE];
static int tlb_next_to_replace = 0;

static signed char *physical_memory = NULL;

static int *frame_page = NULL;
static int *frame_last_used = NULL;
static int time_counter = 0;

// Stats
static int num_page_faults = 0;
static int num_tlb_hits = 0;
static int num_addresses = 0;

int tlb_lookup(int page) {
    for (int i = 0; i < TLB_SIZE; i++) {
        if (tlb[i].valid && tlb[i].page == page) {
            return tlb[i].frame;
        }
    }
    return -1;
}

void tlb_add(int page, int frame) {
    tlb[tlb_next_to_replace].page = page;
    tlb[tlb_next_to_replace].frame = frame;
    tlb[tlb_next_to_replace].valid = 1;

    tlb_next_to_replace = (tlb_next_to_replace + 1) % TLB_SIZE;
}

void tlb_invalidate_page(int page) {
    for (int i = 0; i < TLB_SIZE; i++) {
        if (tlb[i].valid && tlb[i].page == page) {
            tlb[i].valid = 0;
        }
    }
}

int select_frame(int num_frames) {
    for (int f = 0; f < num_frames; f++) {
        if (frame_page[f] == -1) {
            return f;
        }
    }

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

int handle_page_fault(int page, FILE *backing_store, int num_frames) {
    num_page_faults++;

    int frame = select_frame(num_frames);

    int old_page = frame_page[frame];
    if (old_page != -1) {
        page_table[old_page] = -1;
        tlb_invalidate_page(old_page);
    }

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

    page_table[page] = frame;
    frame_page[frame] = page;

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

    physical_memory = (signed char *)malloc(phys_mem_size * sizeof(signed char));
    frame_page = (int *)malloc(num_frames * sizeof(int));
    frame_last_used = (int *)malloc(num_frames * sizeof(int));

    if (!physical_memory || !frame_page || !frame_last_used) {
        fprintf(stderr, "Error: memory allocation failed\n");
        return 1;
    }

    for (int i = 0; i < NUM_PAGES; i++) {
        page_table[i] = -1;
    }

    for (int i = 0; i < TLB_SIZE; i++) {
        tlb[i].valid = 0;
        tlb[i].page = -1;
        tlb[i].frame = -1;
    }

    for (int f = 0; f < num_frames; f++) {
        frame_page[f] = -1;
        frame_last_used[f] = 0;
    }

    FILE *backing_store = fopen(backing_store_name, "rb");
    if (!backing_store) {
        perror("Error opening backing store");
        return 1;
    }

    FILE *addresses = fopen(addresses_name, "r");
    if (!addresses) {
        perror("Error opening addresses file");
        return 1;
    }

    char output_name[64];
    if (num_frames == 256) {
        snprintf(output_name, sizeof(output_name), "output256.csv");
    } else if (num_frames == 128) {
        snprintf(output_name, sizeof(output_name), "output128.csv");
    } else {
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

        frame = tlb_lookup(page);
        if (frame != -1) {
            num_tlb_hits++;
        } else {
            frame = page_table[page];
            if (frame == -1) {
                frame = handle_page_fault(page, backing_store, num_frames);
            } else {
                tlb_add(page, frame);
            }
        }

        int physical_address = frame * PAGE_SIZE + offset;
        signed char value = physical_memory[physical_address];

        frame_last_used[frame] = time_counter++;

        fprintf(output, "%d,%d,%d\n", logical_address, physical_address, (int)value);
    }

    double page_fault_rate = 0.0;
    double tlb_hit_rate = 0.0;

    if (num_addresses > 0) {
        page_fault_rate = ((double)num_page_faults / (double)num_addresses) * 100.0;
        tlb_hit_rate = ((double)num_tlb_hits / (double)num_addresses) * 100.0;
    }

    fprintf(output, "Page Faults Rate,%.2f%%,\n", page_fault_rate);
    fprintf(output, "TLB Hits Rate,%.2f%%,\n", tlb_hit_rate);

    fclose(output);
    fclose(addresses);
    fclose(backing_store);

    free(physical_memory);
    free(frame_page);
    free(frame_last_used);

    return 0;
}
