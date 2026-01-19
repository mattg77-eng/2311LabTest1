#define _POSIX_C_SOURCE 200809L
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "list.h"
#include "task.h"
#include "cpu.h"
#include "schedulers.h"

struct node *head = NULL;

void append(struct node **head, Task *task) {
    struct node *newNode = malloc(sizeof(struct node));
    newNode->task = task;
    newNode->next = NULL;
    if (*head == NULL) {
        *head = newNode;
    } else {
        struct node *temp = *head;
        while (temp->next)
            temp = temp->next;
        temp->next = newNode;
    }
}

void add(char *name, int priority, int burst) {
    Task *t = malloc(sizeof(Task));
    t->name = strdup(name);
    t->priority = priority;
    t->burst = burst;
    append(&head, t);
}

void schedule() {
    int time = 0;
    float total_wait = 0.0, total_turnaround = 0.0, total_response = 0.0;
    int total_tasks = 0;

    struct node *tmp = head;
    while (tmp) {
        total_tasks++;
        tmp = tmp->next;
    }

    for (int p = MAX_PRIORITY; p >= MIN_PRIORITY; p--) {
        struct node *plist = NULL;
        tmp = head;
        while (tmp) {
            if (tmp->task->priority == p) {
                Task *t = malloc(sizeof(Task));
                t->name = strdup(tmp->task->name);
                t->priority = tmp->task->priority;
                t->burst = tmp->task->burst;
                append(&plist, t);
            }
            tmp = tmp->next;
        }

        while (plist) {
            struct node *curr = plist;
            struct node *next;
            while (curr) {
                next = curr->next;
                Task *task = curr->task;
                int slice = (task->burst > QUANTUM) ? QUANTUM : task->burst;

                run(task, slice);
                time += slice;
                task->burst -= slice;

                if (task->burst <= 0) {
                    total_turnaround += time;
                    delete(&plist, task);
                }
                curr = next;
            }
        }
    }

    total_wait = total_turnaround - time;
    printf("\nAverage waiting time = %.2f\n", total_wait / total_tasks);
    printf("Average turnaround time = %.2f\n", total_turnaround / total_tasks);
    printf("Average response time = %.2f\n", (total_turnaround - time) / total_tasks);
}
