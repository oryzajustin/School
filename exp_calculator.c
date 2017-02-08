#include <stdio.h> 
#include <math.h>
#include <stdlib.h>

float calcval(float base, int exponent)
{
	if(exponent == 0)
	{
		return 1;
	}
	else
	{
		return base * calcval(base, exponent - 1);
	}
}

int main(void)
{
	float base;
	printf("Please enter the number that you would like the base to be:\n");
	scanf("%f", &base);
	
	int exp;
	printf("Please enter the positive number that you would like the exponent to be:\n");
	scanf("%d", &exp);
	if(exp < 0)
	{
		printf("Please enter a positive integer for the exponent.\n");
	}
	else
	{
		float ans = calcval(base, exp);
		printf("This is the answer:\n");
		printf("%f", ans);
	}
}