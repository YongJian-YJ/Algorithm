def unboundedKnapSack(W, wt, val, n):
   
   # code here
   dp_table = [[0 for j in range(W+1)]for i in range(len(wt)+1)]
   for i in range(1,len(dp_table)):
      for j in range(1, len(dp_table[0])):
          if wt[i-1] <= j:
             dp_table[i][j] = max(val[i-1]+dp_table[i][j-wt[i-1]],dp_table[i-1][j])
          elif wt[i-1] > j:
             dp_table[i][j] = dp_table[i-1][j]
    
   # print table
   print("Table as follows:")
   for i in range(len(dp_table)):
        print(dp_table[i])
   print ()
   return dp_table[-1][-1]
 
# Driver program
W = 14
val = [7, 6, 9]
wt = [4, 6, 8]
n = len(val)
 
print(unboundedKnapSack(W, wt, val, n))