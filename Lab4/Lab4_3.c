#include <stdio.h>

int main() {
    int M, i, j;
    scanf("%d %d %d", &M, &i, &j);
    
    int x = 1, y = 1;
    for (int k = 0; k < i; k++) {
        x *= 10;
    }
    for (int k = 0; k <= j; k++) {
        y *= 10;
    }
    
    int result = (M % y) / x;
    printf("%d\n", result);
    
    return 0;
}

