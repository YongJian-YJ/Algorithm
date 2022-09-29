#include <stdio.h>
#include <stdlib.h>
#include <time.h>
int static InsertSortKeyComparisons=0;
int static MergeSortKeyComparisons=0;


void insertionSort(int arr[],int l, int n)
{
    int temp;

    for(int i=l;i<=n;i++)
    {

        for(int j=i;j>l;j--)
        {
            InsertSortKeyComparisons++;
            if(arr[j]<arr[j-1]){

                temp=arr[j-1];
                arr[j-1]=arr[j];
                arr[j]=temp;
            }
            else{
                break;
            }
        }
    }

}
void merge(int arr[], int l, int m, int r,int cutoff)
{
    if(r-l <= cutoff-1){
        return;
    }


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
        MergeSortKeyComparisons++;
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
void mergeInsertionSort(int arr[], int l, int r,int cutoff)
{
    int m = (r+l)/2;
    if (l < r) {
        // Same as (l+r)/2, but avoids overflow for
        // large l and h
        //int m = l + (r - l) / 2;

        // Sort first and second halves
        if(r-l <cutoff)
        {
            insertionSort(arr,l,r);
        }
        else{
        mergeInsertionSort(arr, l, m,cutoff);
        mergeInsertionSort(arr, m+1, r,cutoff);
        }
    }
    merge(arr, l, m, r,cutoff);
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
    //int k=0;
    int optimalS = 0;
    double a = 0.0,b = 0.0;
    int number=0;
    double time_spent = 0.0;
    writeFile("excelType8.csv", "Algorithm,Array Size,Threshold Size,Key Comparisons,Execution Time,Optimal S\n");
    printf("Please enter the number of array you want: ");
    scanf("%d",&number);
    for(int k=0;k<=100;k++){
        //for(int k=32;k<=64;k++){
        printf("Please enter the cutoff number you want: %d\n",k);
        //printf("Please enter the cutoff number you want: \n");
        //scanf("%d",&cutoff);
        //scanf("%d",&k);
        
        
        int *array=(int *)malloc((number+1)*sizeof(int));
        
        array[0]=9999999;
        srand((unsigned int)time(NULL));
        for(int i=1;i<=number;i++)//generating array.
        {
            array[i]= rand()%(number+1-1)+1;
            //printf("%d\n",array[i]);
        }
        //array[number+1]=999999999;
        /*array[0]=1;*/
        /*array[1]=45;
         array[2]=29;
         array[3]=6;
         array[4]=64;
         array[5]=12;
         array[6]=16;*/
        
        clock_t begin = 0;
        clock_t end = 0;
        begin = clock();
        mergeInsertionSort(array,1,number,k);
        end = clock();
        
        //printArray(array, number);
        
        time_spent += (double)(end-begin)/CLOCKS_PER_SEC;
        
        
        writeStringOutput("excelType8.csv","Merge-Insertion Sort",",");
        writeIntOutput("excelType8.csv", number, ",");
        writeIntOutput("excelType8.csv", k, ",");
        writeLongOutput("excelType8.csv", InsertSortKeyComparisons+MergeSortKeyComparisons, ",");
        writeDoubleOutput("excelType8.csv", time_spent, ",");
        writeIntOutput("excelType8.csv", optimalS, "\n");
        //FILE *fp;
        //fp=fopen("excelType.csv","w");
        
        //fprintf(fp,"%f%s",time_spent,"\n");
        
        //fclose(fp);
        
        printf("The elapsed time is %f seconds\n",time_spent);
        printf("The number of comparison for insertion sort only is %d ",InsertSortKeyComparisons);
        printf("\nThe number of comparison for merge sort only is %d ",MergeSortKeyComparisons);
        printf("\nThe number of comparison for combination of insertion + merge sort only is %d \n\n",MergeSortKeyComparisons+InsertSortKeyComparisons);
        
        if (k==0)
            a = time_spent;
        
        b = time_spent;
        
        if (b<a) {
            a = b;
            optimalS = k;
        }
    
    
    

    free(array);
    time_spent = 0.0;
    begin=0;
    end=0;
    InsertSortKeyComparisons=0;
    MergeSortKeyComparisons=0;
    }
    
    
    return 0;
}
