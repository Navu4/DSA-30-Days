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
        3. Minimum cost path 
        4. Goldmine problem 
        5. Path with maximum gold
        6. Target Sum subsets

     */

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
    
    // https://leetcode.com/problems/min-cost-climbing-stairs/
    public static int minCost_recursion(int[] cost, int idx) {
        return 0; 
    }

    public static void minCostClimbingStairs() {
        int query = scn.nextInt();
        for (int i = 0; i < query; i++) {
            int n = scn.nextInt();
            int[] arr= new int[n];
            
            int ans = minCost_recursion(arr, 0);
            System.out.println(ans);
        }
    }

    public static void main(String[] args) {
        climbStairs_();
    }

}