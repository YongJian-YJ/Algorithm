def printknapSack(W, wt, val, n):
    K = [[0 for i in range(n + 1)]
            for w in range(W + 1)]
			
	# Build table K[][] in bottom
	# up manner
    for w in range(W + 1):
        for i in range(n + 1):
            if i == 0 or w == 0:
                K[w][i] = 0
            elif wt[i-1] <= w:
                K[w][i] = max(val[i - 1]+ K[w - wt[i-1]][i-1], K[w][i-1])               
            else:
                K[w][i] = K[w][i-1]

    ##print k table
    for w in range(W + 1):
        for i in range(n + 1):
            print(K[w][i],end=" ")
        print()
    

	# stores the result of Knapsack
    res = K[W][n]
    print ()
    print("Max profit is:", res)      #max profit
	
    print("Objects to be selected are:")

    w = W   #capacity
    for i in range(n, 0, -1):
        if res <= 0:
            break

        if res == K[W][i-1]:
            continue
        else:

            print("Object",i)    #weight of item
			
            res = res - val[i - 1]
            w = w - wt[i - 1]


	



# Driver code
val = [7, 6, 9]
wt = [4, 6, 8]
W = 14
n = len(val)


printknapSack(W, wt, val, n)


# This code is contributed by Aryan Garg.
