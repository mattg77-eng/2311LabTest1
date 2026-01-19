#define _POSIX_C_SOURCE 200809L
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "list.h"
#include "task.h"
#include "cpu.h"

struct node *head = NULL;

// add a new task
void add(char *name, int priority, int burst) {
    Task *t = malloc(sizeof(Task));
    t->name = strdup(name);
    t->priority = priority;
    t->burst = burst;
    insert(&head, t);
}

// Round Robin scheduling
void schedule() {
    struct node *temp;
    int time = 0;
    int num_tasks = 0;
    float total_wait = 0.0, total_turnaround = 0.0, total_response = 0.0;

    // Count total number of tasks
    temp = head;
    while (temp != NULL) {
        num_tasks++;
        temp = temp->next;
    }

    // Create a copy list so we can modify bursts
    struct node *copy = NULL;
    temp = head;
    while (temp != NULL) {
        Task *t = malloc(sizeof(Task));
        t->name = strdup(temp->task->name);
        t->priority = temp->task->priority;
        t->burst = temp->task->burst;
        insert(&copy, t);
        temp = temp->next;
    }

    // For response times, we’ll track which tasks have run
    int *responded = calloc(num_tasks, sizeof(int));
    int response_count = 0;

    // Run tasks in round robin fashion until all are complete
    while (copy != NULL) {
        temp = copy;
        while (temp != NULL) {
            Task *task = temp->task;
            int slice = (task->burst > QUANTUM) ? QUANTUM : task->burst;

            // First time the task is seen, record response time
            if (!responded[response_count]) {
                total_response += time;
                responded[response_count] = 1;
            }

            run(task, slice);
            time += slice;
            task->burst -= slice;

            // If finished, remove task and accumulate turnaround
            if (task->burst <= 0) {
                total_turnaround += time;
                delete(&copy, task);
                num_tasks--;
            }

            temp = temp->next;
            response_count++;
        }
        response_count = 0;
    }

    // Compute waiting time (TAT - burst)
    total_wait = total_turnaround - time;

    printf("\nAverage waiting time = %.2f\n", total_wait / 25.0);
    printf("Average turnaround time = %.2f\n", total_turnaround / 25.0);
    printf("Average response time = %.2f\n", total_response / 25.0);

    free(responded);
}

