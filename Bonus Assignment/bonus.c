#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define MAX_NAME 100
#define MAX_DEPT 50

struct employee {
    char name[MAX_NAME];
    int id;
    float salary;
    char dept[MAX_DEPT];
    int yrs_exp;
    float performance;
};

struct employee* addEmployee(struct employee *arr, int *num) {
    arr = realloc(arr, ((*num)+1) * sizeof(struct employee));
    if (arr == NULL) {
        printf("Memory allocation FAILED in addEmployee function\n");
        exit(1);
    }

    printf("\nEnter name of employee: ");
    fgets(arr[*num].name, MAX_NAME, stdin);
    arr[*num].name[strcspn(arr[*num].name, "\n")] = '\0';

    printf("Enter employee ID: ");
    scanf("%d", &arr[*num].id);

    printf("Enter salary: ");
    scanf("%f", &arr[*num].salary);

    while(getchar() != '\n'); 

    printf("Enter department: ");
    fgets(arr[*num].dept, MAX_DEPT, stdin);
    arr[*num].dept[strcspn(arr[*num].dept, "\n")] = '\0';

    printf("Enter years of experience: ");
    scanf("%d", &arr[*num].yrs_exp);
    while(getchar() != '\n'); 

    arr[*num].performance = 0.3 * arr[*num].yrs_exp + 0.7 * arr[*num].salary/1000;
    (*num)++;
    printf("\n");
    
    return arr;
}

void updateEmployee(struct employee *arr, int num) {
    int search_id, i;
    bool found = false;
    
    printf("Enter employee ID to update: ");
    scanf("%d", &search_id);
    while(getchar() != '\n'); 

    for (i = 0; i < num; i++) {
        if (arr[i].id == search_id) {
            found = true;
            break;
        }
    }
    
    if (!found) {
        printf("Employee with ID %d not found!\n\n", search_id);
        return;
    }

    printf("Enter new salary: ");
    scanf("%f", &arr[i].salary);
    
    while(getchar() != '\n'); 

    printf("Enter new department: ");
    fgets(arr[i].dept, MAX_DEPT, stdin);
    arr[i].dept[strcspn(arr[i].dept, "\n")] = '\0';

    arr[i].performance = 0.3 * arr[i].yrs_exp + 0.7 * arr[i].salary/1000;
    printf("Employee information updated successfully!\n\n");
}

void displayEmployees(struct employee *arr, int num) {
    if (num == 0) {
        printf("No employees to display!\n\n");
        return;
    }

    for (int i = 0; i < num-1; i++) {
        for (int j = 0; j < num-i-1; j++) {
            if (arr[j].performance < arr[j+1].performance) {
                struct employee temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
    }

    printf("\nEmployee Records (sorted by Performance Score):\n");
    for(int k = 0; k < num; k++) {
        printf("\nName: %s\n", arr[k].name);
        printf("Employee ID: %d\n", arr[k].id);
        printf("Salary: %.2f\n", arr[k].salary);
        printf("Department: %s\n", arr[k].dept);
        printf("Years of Experience: %d\n", arr[k].yrs_exp);
        printf("Performance Score: %.2f\n", arr[k].performance);
    }
    printf("\n");
}

void searchEmployeeByName(struct employee *arr, int num) {
    char name_search[MAX_NAME];
    bool found = false;
    
    printf("Enter name to search: ");
    fgets(name_search, MAX_NAME, stdin);
    name_search[strcspn(name_search, "\n")] = '\0';

    printf("\nSearch Results:\n");
    for (int i = 0; i < num; i++) {
        if (strstr(arr[i].name, name_search)) {
            printf("\nName: %s\n", arr[i].name);
            printf("Employee ID: %d\n", arr[i].id);
            printf("Salary: %.2f\n", arr[i].salary);
            printf("Department: %s\n", arr[i].dept);
            printf("Years of Experience: %d\n", arr[i].yrs_exp);
            printf("Performance Score: %.2f\n", arr[i].performance);
            found = true;
        }
    }
    
    if (!found) {
        printf("No employees found matching '%s'\n", name_search);
    }
    printf("\n");
}

int main() {
    int flag = 1;
    int num_of_employees = 0;
    
    struct employee *employee_arr = malloc(sizeof(struct employee));
    if(employee_arr == NULL) {
        printf("Memory allocation FAILED for employee_arr in main method");
        exit(1);
    }

    while(flag) {
        int choice;
        printf("Employee Performance Management System\n"
               "1. Add Employee\n"
               "2. Update Employee\n"
               "3. Display All Employees\n"
               "4. Search Employee by Name\n"
               "5. Exit\n"
               "Enter your choice: ");
        scanf("%d", &choice);
        while(getchar() != '\n'); 

        if (choice == 1) {
            employee_arr = addEmployee(employee_arr, &num_of_employees);
        }
        else if(choice == 2) {
            updateEmployee(employee_arr, num_of_employees);
        }
        else if(choice == 3) {
            displayEmployees(employee_arr, num_of_employees);
        }
        else if(choice == 4) {
            searchEmployeeByName(employee_arr, num_of_employees);
        }
        else if(choice == 5) {
            printf("Exiting...\n");
            free(employee_arr);
            flag = 0;
        }
        else {
            printf("\nPlease ensure you enter a valid choice (1-5).\n");
        }
    }
    return 0;
}
