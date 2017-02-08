#include <stdio.h> 
#include <math.h>
#include <stdlib.h>

float korp (char option);
float lorg(char option);
float cori(char option);
float corf(char option);

int main (void)
{
  int action;
  char option;
  printf("Please type the corresponding number to continue:\n");
  printf("1: for conversion between Kilogram and Pound\n");
  printf("2: for conversion between Litre and Gallon (liquid gallon or US gallon)\n");
  printf("3: for conversion between Centimetre and Inch\n");
  printf("4: for conversion between Celsius and Fahrenheit\n");
  printf("5: to quit\n");

  scanf("%d", &action);
  switch(action)
  {
    case 1:
      printf("Which conversion?\n");
      printf("K: Kilogram to Pound\n");
      printf("P: Pound to Kilogram\n");
      scanf(" %c", &option);
      if(option == 'K' || option == 'k' || option == 'p' || option == 'P')
      {
          float ans = korp(option);
          printf("The result is: %f", ans);
      }
      else
      {
          printf("Invalid input, please choose either K or P\n");  
      }    
        break;
    case 2:
      printf("Which conversion?\n");
      printf("L: Litre to Gallon\n");
      printf("G: Gallon to Litre\n");
      scanf(" %c", &option);
      if(option == 'L' || option == 'l' || option == 'g' || option == 'G')
      {
          float ans = lorg(option);
          printf("The result is: %f", ans);
      }
      else
      {
          printf("Invalid input, please choose either L or G\n");  
      }   
      break;
    case 3:
      printf("Which conversion\n");
      printf("C: Centimetre to Inch\n");
      printf("I: Inch to Centimetre\n");
      scanf(" %c", &option);
      if(option == 'C' || option == 'c' || option == 'i' || option == 'I')
      {
          float ans = cori(option);
          printf("The result is: %f", ans);
      }
      else
      {
          printf("Invalid input, please choose either C or I\n");  
      }   
      break;
    case 4:
      printf("Which conversion\n");
      printf("C: Celsius to Fahrenheit\n");
      printf("F: Fahrenheit to Celsius\n");
    scanf(" %c", &option);
    if(option == 'C' || option == 'c' || option == 'f' || option == 'F')
      {
          float ans = corf(option);
          printf("The result is: %f", ans);
      }
      else
      {
          printf("Invalid input, please choose either C or F\n");  
      }   
      break;
    case 5:
      exit(0);
    default:
      printf("Invalid input, please choose 1 - 5.\n");

  }

  return 0;
}

float korp (char option)
{
  float val;
  if(option == 'k' || option == 'K')
  {
    printf("Please input the value you want converted to lbs\n");
    scanf("%f", &val);
    return val * 2.2;
  }
  if(option == 'p' || option == 'P')
  {
    printf("Please input the value you want converted to kilograms\n");
    scanf("%f", &val);
    return val*0.4535;
  }
}

float lorg(char option)
{
    float val;
  if(option == 'l' || option == 'L')
  {
    printf("Please input the value you want converted to gallons\n");
    scanf("%f", &val);
    return val*0.2641;
  }
  if(option == 'g' || option == 'G')
  {
    printf("Please input the value you want converted to litres\n");
    scanf("%f", &val);
    return val*3.785;
  }
}

float cori(char option)
{
    float val;
  if(option == 'c' || option == 'C')
  {
    printf("Please input the value you want converted to inches\n");
    scanf("%f", &val);
    return val*0.3937;
  }
  if(option == 'i' || option == 'I')
  {
    printf("Please input the value you want converted to centimetres\n");
    scanf("%f", &val);
    return val*2.54;
  }
}

float corf(char option)
{
    float val;
  if(option == 'c' || option == 'C')
  {
    printf("Please input the value you want converted to fahrenheit\n");
    scanf("%f", &val);
    return val*(9/5) + 32;
  }
  if(option == 'f' || option == 'F')
  {
    printf("Please input the value you want converted to celsius\n");
    scanf("%f", &val);
    return (val - 32) * (5/9);
  }
}