def printknapSack(C, weight, profit, n):
    K = [[0 for i in range(n + 1)]
            for C in range(C + 1)]
			
	# Build table K[][] in bottom up manner
    for C in range(C + 1):
        for i in range(n + 1):
            if i == 0 or C == 0:
                K[C][i] = 0
            elif weight[i-1] <= C:
                K[C][i] = max(profit[i - 1]+ K[C - weight[i-1]][i], K[C][i-1])               
            else:
                K[C][i] = K[C][i-1]

    #print k table
    for C in range(C + 1):
        for i in range(n + 1):
            print(K[C][i],end=" ")
        print()


    # stores object taken
    i=C
    j=n
    objectArray = []
    while(i>0 and j>0):
        if(j>0 and K[i][j]!=K[i][j-1]):
            objectArray.append(j)
            i=i-weight[j-1]
        elif(j==0 and i>weight[j]):
            objectArray.append(j)
            i=i-weight[j-1]
        else:
            j=j-1
    
    print ("\nHighest possible profit is: ", K[C][n], " and the objects to be taken are: ", *objectArray)
    
    
  


# Driver code
profit = [7, 6, 9]
weight = [4, 6, 8]
C = 14
n = len(profit)

profit2 = [7 ,6 ,9]
weight2 = [5 ,6 , 8]

print ("Question 3: ")
printknapSack(C, weight, profit, n)

print()
print()

print ("Question 4: ")
printknapSack(C, weight2, profit2, n)
