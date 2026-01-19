#include <stdio.h>

struct time {
	int hour;
	int minute;
	int second;
};

void readTime(struct time *t) {
	printf("Enter hours: ");
	scanf("%d", &t ->hour);
	printf("Enter minutes: ");
        scanf("%d", &t -> minute);
	printf("Enter seconds: ");
        scanf("%d", &t -> second);
}

int main(void) {

	struct time currentTime;
	
	readTime(&currentTime);
	
	printf("current time: %02d:%02d:%02d \n", currentTime.hour, currentTime.minute, currentTime.second);


	return 0;
}

