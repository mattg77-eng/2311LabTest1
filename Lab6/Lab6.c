#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void generate_random_numbers(int n) {
    FILE *file = fopen("random_numbers.txt", "w");
    if (file == NULL) {
        perror("Error opening file");
        return;
    }

    srand(time(NULL));

    for (int i = 0; i < n; i++) {
        int num = rand() % 100;
        fprintf(file, "%d\n", num);
    }

    fclose(file);
}

void read_numbers(int *arr, int n) {
    FILE *file = fopen("random_numbers.txt", "r");
    if (file == NULL) {
        perror("Error opening file");
        return;
    }

    for (int i = 0; i < n; i++) {
        fscanf(file, "%d", &arr[i]);
    }

    fclose(file);
}

double calculate_mean(int *arr, int n) {
    int sum = 0;
    for (int i = 0; i < n; i++) {
        sum += arr[i];
    }
    return (double)sum / n;
}

double calculate_median(int *arr, int n) {
    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            if (arr[i] > arr[j]) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }

    if (n % 2 == 0) {
        return (arr[n / 2 - 1] + arr[n / 2]) / 2.0;
    } else {
        return arr[n / 2];
    }
}

int calculate_mode(int *arr, int n) {
    int frequency[100] = {0};

    for (int i = 0; i < n; i++) {
        frequency[arr[i]]++;
    }

    int mode = arr[0];
    int max_count = frequency[arr[0]];

    for (int i = 1; i < n; i++) {
        if (frequency[arr[i]] > max_count) {
            max_count = frequency[arr[i]];
            mode = arr[i];
        }
    }

    return mode;
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Enter the number of random integers to generate.\n");
        return 1;
    }

    int n = atoi(argv[1]);
    if (n <= 0 || n > 100) {
        printf("Please enter a valid number of random integers (1 to 100).\n");
        return 1;
    }

    generate_random_numbers(n);

    int *arr = malloc(n * sizeof(int));
    if (arr == NULL) {
        perror("Error allocating memory");
        return 1;
    }

    read_numbers(arr, n);

    double mean = calculate_mean(arr, n);
    double median = calculate_median(arr, n);
    int mode = calculate_mode(arr, n);

    printf("Mean: %.2f\n", mean);
    printf("Median: %.2f\n", median);
    printf("Mode: %d\n", mode);

    free(arr);

    return 0;
}

