#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#define N 12
#define M 12

///Functions for printing all the possible permutations
void ToZero(int s, int arr[s]);
int Check(int s, int tab[s][s], int row, int cols, int temp[row][cols]);
unsigned int factorial(unsigned int n);
void combinationUtil(int arr[], int data[], int start, int end, int index, int r, int temp[][r], int* k);

///Functions for computing the max amount of people able to come to the party
void RowSum(int s, int array[s][s], int sum[s]);
int Check2(int s, int tab[s][s], int list[s]);
int MaxIndex(int s, int array[s]);
void EverybodyIsNotInvited(int s, int list[s]);
void Append(int s, int tab[s][s], int list[s], int sum[s]);
int MinIndex(int s, int array[s], int list[s]);
int MaxPeople(int s, int list[s]);
int GraphMaxVertices(int s, int tab[s][s]);

int main()
{
    char names[N][M]={
        {"Nikos"},
        {"Lydia"},
        {"Giannhs"},
        {"Akhs"},
        {"Manos"},
        {"Eua"},
        {"Elenh"},
        {"Petros"},
        {"Dhmhtrhs"},
        {"Maria"},
        {"Aleksandros"},
        {"Anna"}};
    int tab[N][N]={
        {0, 1,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0},
        {1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
        {0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0},
        {1,	0,	1,	0,	1,	0,	0,	0,	0,	0,	0,	0},
        {0,	0,	0,	1,	0,	1,	1,	0,	0,	0,	0,	0},
        {0,	0,	0,	0,	1,	0,	0,	0,	0,  0,	0,	0},
        {0,	0,	0,	0,	1,	0,	0,	1,	1,	1,	0,	0},
        {0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0},
        {0,	0,	0,	0,	0,	0,	1,	0,	0,	1,	0,	0},
        {0,	0,	0,	0,	0,	0,	1,	0,	1,	0,	1,	0},
        {0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	1},
        {0,	0,	0,	0,	0,	0,	0,	0,	0,  0,	1,	0}};
    int arr[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    int r = GraphMaxVertices(N, tab);
    int temp_rows=factorial(N)/(factorial(r)*factorial(N-r));
    int temp[temp_rows][r];
    int k=0,i,j;
    int data[r];
    int matrix[temp_rows][r];

    combinationUtil(arr, data, 0, N-1, 0, r, temp, &k);

    int h=0;
    for (i=0; i<temp_rows; i++)
        if (Check(N, tab, i, r, temp)==1)
        {
            for (j=0; j<r; j++)
                matrix[h][j]=temp[i][j];
            h++;
        }

    printf("The maximum amount of people, Stathhs is able to invite\nwithout turning the party into a warzone is %d people.\n", r+1);
    printf("He can choose between the following combinations:\n");
    for (i=0; i<h; i++)
    {
        printf("\n");
        printf("%2d|", i+1);
        printf(" %s, ","Zwh");
        for (j=0; j<r; j++)
        {
            if (j==r-1) printf("%s.", names[matrix[i][j]]);
            else printf("%s, ", names[matrix[i][j]]);
        }
        printf("\n");
    }

    return 0;
}
/**This function checks if the invitation list is possible, similar to Check2 function but with a slight adjustment**/
int Check(int s, int tab[s][s], int row, int cols, int temp[row][cols])
{
    int i,j;
    int list[s];

    ToZero(s, list);
    for (i=0; i<cols; i++)
        list[temp[row][i]]=1;

    for (i=0; i<s; i++)
        if (list[i]==1) // if invited
            for (j=0; j<s; j++)
                if (list[j]==1 && tab[i][j]==1) // if they are both invited and they both dislike each other
                    return 0;
    return 1;
}
/**This function initializes an array with zero**/
void ToZero(int s, int arr[s])
{
    int i;
    for (i=0; i<s; i++)
        arr[i]=0;
}
/**This function finds all the possible permutations using a recursive method**/
void combinationUtil(int arr[], int data[], int start, int end, int index, int r, int temp[][r], int* k)
{
    int i;

    if (index == r)
    {
        for (int j=0; j<r; j++)
            temp[*k][j]=data[j];
        (*k)++;
        return;
    }

    for (i=start; i<=end && end-i+1 >= r-index; i++)
    {
        data[index] = arr[i];
        combinationUtil(arr, data, i+1, end, index+1, r, temp, &(*k));
    }
}
/**This function computes the factorial of a number**/
unsigned int factorial(unsigned int n)
{
    if (n == 0)
        return 1;
    return n * factorial(n - 1);
}
/**Row Sum-Initializing with 0 first**/
void RowSum(int s, int array[s][s], int sum[s])
{
    int i,j;

    ToZero(s, sum);
    for (i=0; i<s; i++)
        for (j=0; j<s; j++)
            if (array[i][j] == 1)
                sum[j]++;
}
/**This function checks if the invitation list is possible**/
int Check2(int s, int tab[s][s], int list[s])
{
    int i,j;

    for (i=0; i<s; i++)
        if (list[i]==1) // if invited
            for (j=0; j<s; j++)
                if (list[j]==1 && tab[i][j]==1) // if they are both invited and they both dislike each other
                    return 0;
    return 1;
}
/**This function finds the index of the maximum element**/
int MaxIndex(int s, int array[s])
{
    int i, index, max;

    max=array[0];
    index=0;
    for (i=1; i<s; i++)
        if (array[i]>max)
            {
                index=i;
                max=array[i];
            }
    return index;
}
/**This function adds the maximum amount of people in the list. Firstly it adds all the people with 0 dislikes,
then it adds all the people with the next minimum of dislikes(one or more), only if each addition results in a valid list.
It keeps going until it has checked the person with the maximum dislikes**/
void Append(int s, int tab[s][s], int list[s], int sum[s])
{
    int i, k;

    for (i=0; i<s; i++)
    {
        if (sum[i]==0) // no dislikes
            list[i]=1; // invite
    }
    for (k=1; k<sum[MaxIndex(s, sum)]; k++) // from 1 to max(sum) dislikes
        for (i=0; i<s; i++)
            if (sum[i]== sum[MinIndex(s, sum, list)]) // people with the minimum of dislikes
                if (list[i]==0) // not invited
                {
                    list[i]=1; // add in order to check
                    if (!(Check2(s, tab, list))) // check==false
                        list[i]=0; // delete
                }
}
/**This function finds the minimum for only those who are not already invited**/
int MinIndex(int s, int array[s], int list[s])
{
    int i,min,index;

    min=s; // s-1 is the max degree of a vertex(κορυφή)
    index=-1;
    for (i=0; i<s; i++)
        if (list[i]==0)
            if (array[i]<min)
                {
                    min=array[i];
                    index=i;
                }
    return index;
}
/**This function finds the maximum amount of people who are invited**/
int MaxPeople(int s, int list[s])
{
    int i,k=0;

    for (i=0; i<s; i++)
        if (list[i]==1)
            k++;
    return k;
}
/**This function combines the RowSum, the ToZero and the Append function
to return the max amount of people able to come to the party**/
int GraphMaxVertices(int s, int tab[s][s])
{
    int sum[s];
    int list[s];
    int max;

    RowSum(s, tab, sum);
    ToZero(s, list);// everybody is not invited
    Append(s, tab, list, sum);
    max=MaxPeople(s, list);
    return max;
}
