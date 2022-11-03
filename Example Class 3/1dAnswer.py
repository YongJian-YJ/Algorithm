def unboundedKnapsack(C, n, profit, weight):
 
    #C: capacity of knapsack
    #n: number of items
    # dp[i] is going to store maximum value with knapsack capacity i
    dp = [0 for i in range(C + 1)]

    # Recursive Formula for finding the maximum profit
    for i in range(C + 1):
        for j in range(n):
            if (weight[j] <= i):
                dp[i] = max(dp[i], dp[i - weight[j]] + profit[j])
 
    return dp[C]
 
# Driver program
C = 14
profit = [7, 6, 9]
weight = [4, 6, 8]
n = len(profit)
 
print(unboundedKnapsack(C, n, profit, weight))


