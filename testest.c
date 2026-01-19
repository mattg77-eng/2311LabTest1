#include <stdio.h>
#include <stdlib.h>

int main(){

	int r, c;

	printf("Enter 2 integers: ");
	scanf("%d %d", &r, &c);


	int **A =malloc(sizeof(int*) * r);
	int **B =malloc(sizeof(int*) * r);

	if (A == NULL || B == NULL) {
		printf("Error \n");
		return 0;
	}
	
	for (int i = 0; i < r; i++) {
		A[i] =malloc(sizeof(int) * c); 
		B[i] =malloc(sizeof(int) * c);

		for (int j = 0; j < c; j++){
			printf("You are on row %d enter %d integers: ", i, c);
			scanf("%d %d", A[i][j]);
			scanf("%d %d", B[i][j]);
		}
	}
	
	for (int i = 0; i < r; i++) {
		int sumA = 0;
		int sumB = 0;

		for (int j = 0; j < c; j++){
			sumA += A[i][j];
			sumB += B[i][j];
		}
		if (sumA < sumB){
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
