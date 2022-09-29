#include <stdio.h>
#include <stdlib.h>
#include <time.h>
int static mergeSortKeyComparisons=0;

void merge(int arr[], int l, int m, int r)
{
    int i, j, k;
    int n1 = m - l + 1;
    int n2 = r - m;

    /* create temp arrays */
    int *L =(int *)malloc((n1+1)*sizeof(int));
    int *R =(int *)malloc((n2+1)*sizeof(int));

    /* Copy data to temp arrays L[] and R[] */
    for (i = 0; i < n1; i++)
        L[i] = arr[l + i];
    for (j = 0; j < n2; j++)
        R[j] = arr[m + 1 + j];

    /* Merge the temp arrays back into arr[l..r]*/
    i = 0; // Initial index of first subarray
    j = 0; // Initial index of second subarray
    k = l; // Initial index of merged subarray
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i];
            i++;
        }
        else {
            arr[k] = R[j];
            j++;
        }
        mergeSortKeyComparisons++;
        k++;
    }

    /* Copy the remaining elements of L[], if there
    are any */
    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }

    /* Copy the remaining elements of R[], if there
    are any */
    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }

    free(L);
    free(R);
}

/* l is for left index and r is right index of the
sub-array of arr to be sorted */
void mergeSort(int arr[], int l, int r)
{
    int m = (r+l)/2;
    if (l < r) {
        
        mergeSort(arr, l, m);
        mergeSort(arr, m+1, r);
        
    }
    merge(arr, l, m, r);
}

/* UTILITY FUNCTIONS */
/* Function to print an array */
void printArray(int A[], int size)
{
    int i;
    for (i = 1; i <= size; i++)
        printf("%d ", A[i]);
    printf("\n");
}

void writeFile(char *name, char *header)
{
    FILE *f = fopen(name, "a");
    fprintf(f, "%s", header);
    fclose(f);
}

// Function to write a string to the output file
int writeStringOutput(char *name, char *text, char *character)
{
    FILE *f = fopen(name, "a");
    if (f == NULL)
    {
        printf("Error opening file!\n");
        exit(1);
    }

    fprintf(f, "%s%s", text, character);

    fclose(f);

    return 1;
}

// Function to write an integer to the output file
int writeIntOutput(char *name, long text, char *character)
{
    FILE *f = fopen(name, "a");
    if (f == NULL)
    {
        printf("Error opening file!\n");
        exit(1);
    }

    fprintf(f, "%ld%s", text, character);

    fclose(f);

    return 1;
}

// Function to write a long long integer to the output file
int writeLongOutput(char *name, long long text, char *character)
{
    FILE *f = fopen(name, "a");
    if (f == NULL)
    {
        printf("Error opening file!\n");
        exit(1);
    }

    fprintf(f, "%lld%s", text, character);

    fclose(f);

    return 1;
}

// Function to write a float value to the output file
int writeDoubleOutput(char *name, double text, char *character)
{
    FILE *f = fopen(name, "a");
    if (f == NULL)
    {
        printf("Error opening file!\n");
        exit(1);
    }

    fprintf(f, "%lf%s", text, character);

    fclose(f);

    return 1;
}

int main()
{
  
  
    double a = 0.0,b = 0.0;
    int number=0;
    double time_spent = 0.0;
    writeFile("excelType8.csv", "Algorithm,Array Size,Threshold Size,Key Comparisons,Execution Time,Optimal S\n");
    printf("Please enter the number of array you want: ");
    scanf("%d",&number);        
        int *array=(int *)malloc((number+1)*sizeof(int));
        
        array[0]=9999999;
        srand((unsigned int)time(NULL));
        for(int i=1;i<=number;i++)//generating array.
        {
            array[i]= rand()%(number+1-1)+1;
    
        }
        clock_t begin = 0;
        clock_t end = 0;
        begin = clock();
        mergeSort(array,1,number);
        end = clock();
        //printArray(array, number);
        time_spent += (double)(end-begin)/CLOCKS_PER_SEC;

        writeStringOutput("excelType8.csv","Merge-Insertion Sort",",");
        writeIntOutput("excelType8.csv", number, ",");
        writeDoubleOutput("excelType8.csv", time_spent, ",");
      
        printf("The elapsed time is %f seconds",time_spent);
        printf("\nThe number of comparison for merge sort only is %d ",mergeSortKeyComparisons);
       
        free(array);
       
    
    
    
    return 0;
}
