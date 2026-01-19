#include <stdio.h>

void backwardArray(int arr[6]) {
    int temp;
    for (int i = 0; i < 3; i++) {
        temp = arr[i];
        arr[i] = arr[5 - i];
        arr[5 - i] = temp;
    }
}

int main() {
    int arr[6];

    printf("Enter 6 integers: ");
    for (int i = 0; i < 6; i++) {
        scanf("%d", &arr[i]);
    }
    backwardArray(arr);

    printf("Reversed array: ");
    for (int i = 0; i < 6; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");

    return 0;
}

