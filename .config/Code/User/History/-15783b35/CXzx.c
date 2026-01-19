#include <stdio.h>
#include <stdlib.h>

#include "task.h"
#include "list.h"
#include "cpu.h"

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

static int count_tasks() { int c=0; struct node *p=head; while(p){c++; p=p->next;} return c; }

void schedule() {
    int total_tasks = count_tasks();
    int finished_tasks = 0;
    int current_time = 0;
    int quantum = QUANTUM;

    /* determine priority range in list */
    int maxp = -1000, minp = 100000;
    struct node *p = head;
    while (p) {
        Task *t = p->task;
        if (t->priority > maxp) maxp = t->priority;
        if (t->priority < minp) minp = t->priority;
        p = p->next;
    }

    for (int pr = maxp; pr >= minp && finished_tasks < total_tasks; --pr) {
        /* count tasks with this priority */
        int count_pr = 0;
        p = head;
        while (p) { if (p->task->priority == pr) count_pr++; p = p->next; }
        if (count_pr == 0) continue;

        int finished_pr = 0;
        while (finished_pr < count_pr) {
            p = head;
            while (p) {
                Task *t = p->task;
                if (t->priority == pr && t->completion_time == -1) {
                    int slice = (t->burst <= quantum) ? t->burst : quantum;
                    if (t->start_time == -1) t->start_time = current_time;
                    run(t, slice);
                    current_time += slice;
                    t->burst -= slice;
                    if (t->burst == 0) {
                        t->completion_time = current_time;
                        finished_pr++;
                        finished_tasks++;
                    }
                }
                p = p->next;
            }
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
