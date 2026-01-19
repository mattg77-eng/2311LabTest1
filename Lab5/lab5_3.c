#include <stdio.h>

void applyPotion(int *age, int *strength, float *weight, int *wisdom) {
    if (*age <= 25) {
        *strength *= 2;
    } else if (*age >= 26 && *age <= 40) {
        *weight *= 0.9;
    } else if (*age >= 41) {
        *wisdom += 5;
    }
}

int main() {
    int age, strength, wisdom;
    float weight;

    printf("Enter age: ");
    scanf("%d", &age);
    printf("Enter strength: ");
    scanf("%d", &strength);
    printf("Enter weight: ");
    scanf("%f", &weight);
    printf("Enter wisdom: ");
    scanf("%d", &wisdom);

    applyPotion(&age, &strength, &weight, &wisdom);

    printf("\nAfter drinking the Reversal Potion:\n");
    printf("Age: %d\n", age);
    printf("Strength: %d\n", strength);
    printf("Weight: %.2f\n", weight);
    printf("Wisdom: %d\n", wisdom);

    return 0;
}


