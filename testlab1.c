#include <stdio.h>
#include <stdlib.h>

int main () {
	
	int r, c;

printf("Enter two integers: ");
scanf("%d %d", &r, &c);

int **A =malloc(sizeof(int*) * r);
int **B =malloc(sizeof(int*) * r);

if (A == NULL || B == NULL) {
	printf("Memory allocation error \n");
	return 1;
}

for (int i = 0; i < r; i++) {
	A[i] = malloc(sizeof(int) * c);
	B[i] = malloc(sizeof(int) * c);

for (int j = 0; j < c; j++) {
	printf("You are on row %d, enter %d integers: ", i, c);
	scanf("%d", &A[i][j]);
	scanf("%d", &B[i][j]);
}
}

	for (int i = 0; i < r; i++) {
		int sumA = 0;
		int sumB = 0;

		for (int j = 0; j < c; j++){
			sumA += A[i][j];
			sumB += B[i][j];
		}
		if (sumA < sumB) {
		printf("NO \n");
		return 0;
		}
	}
printf("YES \n");

for (int i = 0; i < r; i++){
	free(A[i]);
	free(B[i]);
}

free(A);
free(B);
return 0;
}

