#include <stdio.h>

int main() {
    int l, m;

    printf("Enter the left margin (l): ");
    scanf("%d", &l);
    printf("Enter the number of rows (m): ");
    scanf("%d", &m);

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= l - i; j++) {
            printf(" ");
        }

        for (int j = 1; j <= i; j++) {
            printf("*");
        }
        printf("\n");
    }

    return 0;
}
