#define _POSIX_C_SOURCE 200809L
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

#include "task.h"
#include "list.h"
#include "cpu.h"
#include "cpu.h" /* for QUANTUM define via cpu.h included by CPU.c compile */

static struct node *head = NULL;
static int next_tid = 0;

void add(char *name, int priority, int burst) {
    Task *t = malloc(sizeof(Task));
    t->name = strdup(name);
    t->tid = next_tid++;
    t->priority = priority;
    t->burst = burst;
    t->original_burst = burst;
    t->arrival = 0;
    t->start_time = -1;
    t->completion_time = -1;
    insert(&head, t);
}

static int count_tasks() { int c = 0; struct node *p = head; while (p) { c++; p = p->next; } return c; }

void schedule() {
    int n = count_tasks();
    int finished = 0;
    int current_time = 0;
    int quantum = QUANTUM;

    while (finished < n) {
        struct node *p = head;
        while (p) {
            Task *t = p->task;
            if (t->completion_time == -1) {
                int slice = (t->burst <= quantum) ? t->burst : quantum;
                if (t->start_time == -1) t->start_time = current_time;
                run(t, slice);
                current_time += slice;
                t->burst -= slice;
                if (t->burst == 0) {
                    t->completion_time = current_time;
                    finished++;
                }
            }
            p = p->next;
        }
    }

    double total_turn = 0.0, total_wait = 0.0, total_resp = 0.0;
    int total = 0;
    struct node *q = head;
    while (q) {
        Task *t = q->task; total++;
        int turnaround = t->completion_time - t->arrival;
        int waiting = turnaround - t->original_burst;
        int response = t->start_time - t->arrival;
        total_turn += turnaround; total_wait += waiting; total_resp += response;
        q = q->next;
    }

    printf("\nAverage waiting time = %.2f\n", total_wait / total);
    printf("Average turnaround time = %.2f\n", total_turn / total);
    printf("Average response time = %.2f\n", total_resp / total);
}
