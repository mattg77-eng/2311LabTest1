/**
 * Representation of a task in the system.
 */

#ifndef TASK_H
#define TASK_H

// representation of a task
typedef struct task {
    char *name;         // pointer to name string (we strdup in add())
    int tid;            // input order id (for stable tie-breaking)
    int priority;
    int burst;          // remaining burst (modified during RR)
    int original_burst; // original burst value (for stats)
    int arrival;        // arrival time (assignment uses 0 for all)
    int start_time;     // first time run (for response time)
    int completion_time;// completion time (for turnaround)
} Task;

#endif

