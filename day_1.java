import java.util.Scanner;

public class day_1{
    public static Scanner scn = new Scanner(System.in);
    public static void printArr(int[] arr){
        for(int ele : arr){
            System.out.print(ele + " ");
        }
        System.out.println();
    }
    
    /**
        Dynamic Programming
        1. Climbing Stairs with jumps   
        2. Climbing Stairs with minimum moves
        3. a) Print Board Path
           b) Minimum cost path 
        4. Goldmine problem 
        5. Path with maximum gold
        6. Target Sum subsets

     */

    // 1. Climbing Stairs with jumps  
    // https://leetcode.com/problems/climbing-stairs/
    public static int climbStairs_memo(int n, int[] dp){
        if(n < 0)
            return 0;
        
        if(n == 0)
            return dp[n] = 1;
        
        if(dp[n] != 0)
            return dp[n];
        
        int count = 0;
        for(int i = 1; i <= 2; i++){
            count += climbStairs_memo(n - i, dp);
        }
        return dp[n] = count;
    }
    
    
    public static int climbStairs(int n) {
        if( n == 1)     
            return 1;
        
        int a = 1;
        int b = 1;
        int ans = 0;
        
        for(int i = 2; i <= n; i++ ){
            ans = a + b;
            a = b;
            b = ans;
        }
        return ans;  
    }

    public static void climbStairs_() {
        int n = scn.nextInt();
        // int ans = climbStairs_memo(n, new int[n + 1]);
        int ans = climbStairs(n);
        System.out.print(ans);
    }
    
    //  2. Climbing Stairs with minimum moves
    // https://leetcode.com/problems/min-cost-climbing-stairs/
    public static int minCost_recursion(int[] cost, int[] dp, int n) {
        if(n <= 1){
            return dp[n] = cost[n];
        }
        if(dp[n] != 0) 
            return dp[n];
        return dp[n] = Math.min(minCost_recursion(cost, dp, n - 1), minCost_recursion(cost, dp, n - 2)) +(n != cost.length ? cost[n] : 0); 
    }

    public static int minCost_Tabu(int[] cost, int[] dp){
        int N = cost.length;
        for (int n = 0; n <= N; n++) {
            if(n <= 1){
                dp[n] = cost[n];
                continue;
            }
             
            dp[n] = Math.min(dp[n - 1],dp[n - 2]) +(n != cost.length ? cost[n] : 0); 
        }
        return dp[N];
    }

    public static int minCost_opti(int[] cost) {
        int n = cost.length;
        int a = cost[0], b = cost[1];
        for (int i = 2; i <= n; i++) {
            int minVal = Math.min(a, b) + i != cost.length ? cost[i] : 0;
            a = b;
            b = minVal;
        }
        return b;
    }

    public static void minCostClimbingStairs() {
        int query = scn.nextInt();
        for (int i = 0; i < query; i++) {
            int n = scn.nextInt();
            
            int[] arr= new int[n];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = scn.nextInt();
            }

            int[] dp = new int[n + 1];
            
            // int ans = minCost_recursion(arr, dp, n);
            int ans = minCost_Tabu(arr, dp);
            printArr(dp);
            System.out.println((i + 1) + ": " + ans);
        }
    }


    // 3. a) Print Board Path
    public static int printBoardPath_memo(int n, int[] dp){
        if(n == 0){
            return dp[n] = 1;
        }

        if(dp[n] != 0) {
            return dp[n];
        }

        int count = 0;
        for (int i = 1; i <= 6 && n - i >= 0; i++) {
            count += printBoardPath_memo(n - i, dp);
        }

        return dp[n] = count;
    }
    
    public static void boardPathProblem(){
        int n = scn.nextInt();
        int[] dp = new int[n + 1];
        System.out.println(printBoardPath_memo(n, dp));
    }



    public static void main(String[] args) {
        // climbStairs_();
        // minCostClimbingStairs();
        boardPathProblem();
    }

}