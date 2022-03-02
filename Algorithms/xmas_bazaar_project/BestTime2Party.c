#include <stdio.h>
#include "simpio.h"
#include <string.h>

#define N 100

/*! Times should be integers, for example 18 or 22*/

int main()
{
    int i, n, k, max, index, s=0;
    char Bands[N][N]; // Names of the bands
    int ArTime[N], LeTime[N]; // Arriving time, Leaving time
    int Sum[] = {0,0,0,0,0,0}; // Present bands counter
    int Max[] = {0,0,0,0,0,0}; // Assisting array

    n = (printf("Give the number of the bands: "), GetInteger());
    for (i=0;i<n;i++)
    {
        printf("Give the name of the #%d band: ", i+1);
        strcpy(Bands[i], GetLine());
        ArTime[i] = (printf("Now give the Arriving Time: "), GetInteger());
        if (ArTime[i] < 18 || ArTime[i] > 24) Error("Not a valid time :("); // Exit if time is not valid
        LeTime[i] = (printf("And the Leaving Time: "), GetInteger());
        if ((LeTime[i] < 18 || ArTime[i] > 24) || LeTime[i] <= ArTime[i]) Error("Not a valid time :("); // Exit if time is not valid
    }
    //Filling the Sum array with the amount of bands that will be present each hour
    for (k=0; k<6; k++)
        for (i=0; i<n; i++)
            if (ArTime[i] <= k+18 && LeTime[i] > k+18) Sum[k]++;
    //Finding the max from the Sum array
    max = Sum[0];
    index = 0;
    for (k=1; k<6; k++)
        if (Sum[k] > max)
        {
            max = Sum[k];
            index = k;
        }
    //Spotting how many times does the max appear
    for (k=0; k<6; k++)
        if (Sum[k] == max)
        {
            Max[k]=1; //Updating assisting array
            s++;
        }
    //In case of 1 max appearance
    if (s == 1) printf("The most bands will be present at %d:00", index+18);
    else //In case of multiple max appearances
    {
        printf("Multiple hours will have the maximum amount of bands...\nPress any key to see the bands in each 'busy' hour");
        getchar();
        for (k=0; k<6; k++)
            if (Max[k]) //If the k hour is a "max" hour
            {
                printf("At %d:00 you will find:\n", k+18);
                for (i=0; i<n; i++) //Print all the bands that will appear each of the "max" hours
                {
                    if (ArTime[i] <= k+18 && LeTime[i] > k+18) printf("%s\n", Bands[i]);
                }
            }
    }
    return 0;
}
