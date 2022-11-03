def printknapSack(W, wt, val, n):
    K = [[0 for i in range(n + 1)]
            for w in range(W + 1)]
			
	# Build table K[][] in bottom up manner
    for w in range(W + 1):
        for i in range(n + 1):
            if i == 0 or w == 0:
                K[w][i] = 0
            elif wt[i-1] <= w:
                K[w][i] = max(val[i - 1]+ K[w - wt[i-1]][i], K[w][i-1])               
            else:
                K[w][i] = K[w][i-1]

    #print k table
    for w in range(W + 1):
        for i in range(n + 1):
            print(K[w][i],end=" ")
        print()


    # stores object taken
    i=W
    j=n
    idxes_list = []
    while(i>0 and j>0):
        if(j>0 and K[i][j]!=K[i][j-1]):
            idxes_list.append(j)
            i=i-wt[j-1]
        elif(j==0 and i>wt[j]):
            idxes_list.append(j)
            i=i-wt[j-1]
        else:
            j=j-1
    
    print ("\nHighest possible profit is: ", K[W][n], " and the objects to be taken are: ", *idxes_list)
    
    
  


# Driver code
val = [7, 6, 9]
wt = [4, 6, 8]
W = 14
n = len(val)

val2 = [7 ,6 ,9]
wt2 = [5 ,6 , 8]

print ("Question 3: ")
printknapSack(W, wt, val, n)

print()
print()

print ("Question 4: ")
printknapSack(W, wt2, val2, n)
