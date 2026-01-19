#define _POSIX_C_SOURCE 200809L
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"
#include "task.h"
#include "cpu.h"

struct node *head = NULL;

void add(char *name, int priority, int burst) {
    Task *task = malloc(sizeof(Task));
    task->name = strdup(name);
    task->priority = priority;
    task->burst = burst;
    insert(&head, task);
}

void schedule() {
    struct node *temp;
    int time = 0; // track elapsed time
    float total_wait = 0, total_turnaround = 0, total_response = 0;
    int n = 0;

    // Copy tasks to a new list so we can modify bursts
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

    while (copy != NULL) {
        temp = copy;
        while (temp != NULL) {
            Task *task = temp->task;
            int slice = (task->burst > QUANTUM) ? QUANTUM : task->burst;
            run(task, slice);
            time += slice;
            task->burst -= slice;

            // If finished, remove from list
            if (task->burst <= 0) {
                total_turnaround += time;
                delete(&copy, task);
                n++;
            }
            temp = temp->next;
        }
    }

    total_wait = total_turnaround - time;
    total_response = total_wait / n;
    printf("\nAverage waiting time = %.2f\n", total_wait / n);
    printf("Average turnaround time = %.2f\n", total_turnaround / n);
    printf("Average response time = %.2f\n", total_response);
}
