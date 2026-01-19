#include <stdio.h>

int main() {
    int n, m;

    scanf("%d %d", &n, &m);

    int matrix[n * m];

    for (int i = 0; i < n * m; i++) {
        scanf("%d", &matrix[i]);
    }

    for (int i = 0; i < n; i++) {
        int isNonDescending = 1;
        
        for (int j = 0; j < m - 1; j++) {
            if (*(matrix + i * m + j) > *(matrix + i * m + j + 1)) {
                isNonDescending = 0;
                break;
            }
        }
        
        if (isNonDescending) {
            printf("%5d\n", i + 1);
        }
    }

    return 0;
}

