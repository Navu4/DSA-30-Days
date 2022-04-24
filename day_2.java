import java.util.ArrayList;
import java.util.Scanner;

public class day_2 {
    public static Scanner scn = new Scanner(System.in);
    public static void printArr(int[] arr){
        for(int ele : arr){
            System.out.print(ele + " ");
        }
        System.out.println();
        
    }
    public static void print2Arr(int[][] arr){
        for (int[] a : arr) {
            printArr(a);
        }
        System.out.println();
    }

    public static void print2ArrBool(boolean[][] arr){
        for (boolean[] a : arr) {
            for (boolean b : a) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Target Sum Set & Coin Change 
        1. Target Sum Subset
        2. K subset with equal Sum
        3. Coin Change ( recursive and dp )
            a) Combination
            b) Permutation
        4. 0-1 Knapsack
        5. Unbounded knapsack
     */

    // 1. Target Sum Subset 
    public static void targetSumSubset_recursion(int[] arr, int idx, int tar, ArrayList<Integer> ans) {
        if(tar == 0 || idx == arr.length){
            if(tar == 0){
                System.out.println(ans);
            }
            return;
        }

        targetSumSubset_recursion(arr, idx + 1, tar, ans);
        ans.add(arr[idx]);
        targetSumSubset_recursion(arr, idx + 1, tar - arr[idx], ans);
        ans.remove(ans.size() - 1);
    }

    
    public static void targetSumSubset_DP() {
        /**
         * Boolean Value if target is acheive or not
         * True or False
         */

        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }

        int tar = scn.nextInt();
        boolean[][] dp = new boolean[n + 1][tar + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {

                if(i == 0 && j == 0)
                    dp[i][j] = true;
                 else if(i == 0)
                    dp[i][j] = false;
                 else if(j == 0)
                    dp[i][j] = true;
                 else {
                    if(dp[i - 1][j] == true)
                        dp[i][j] = true;
                    else {
                        int val = arr[i - 1];
                        if(j >= val){
                            if(dp[i - 1][j - val] == true){
                                dp[i][j] = true;
                            }
                        }
                    }
                }
            }
        }
        print2ArrBool(dp);
        System.out.println(dp[arr.length][tar]);
    }

    public static void targetSumSubset() {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }

        int tar = scn.nextInt();
        targetSumSubset_recursion(arr, 0, tar, new ArrayList<>());
    }

    // 2. Kth subset with equal sum
    public static void print2EqualSumSubset(int[] arr, int idx, int sum1, int sum2, ArrayList<Integer> arr1, ArrayList<Integer> arr2){

        if(idx == arr.length || sum1 == sum2){
            if(sum1 == sum2 && idx == arr.length){
                System.out.println(arr1 + " " + arr2);
            }
            return;
        }

        int val = arr[idx];
        arr1.add(val);
        print2EqualSumSubset(arr, idx + 1, sum1 + val, sum2, arr1, arr2);
        arr1.remove(arr1.size() - 1);

        arr2.add(val);
        print2EqualSumSubset(arr, idx + 1, sum1, sum2 + val, arr1, arr2);
        arr2.remove(arr2.size() - 1);
    }
    
    public static void kthSubset(){
        int n = scn.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
        ArrayList<Integer> sum1 = new ArrayList<>();
        ArrayList<Integer> sum2 = new ArrayList<>();
        sum1.add(arr[0]);

        print2EqualSumSubset(arr, 1, arr[0], 0, sum1, sum2);
        
    }

    // 3. Coin Change with infinite coin
    // a) Recursion
    public static int coinChange_recursion(int[] arr, int idx, int tar, String ans) {
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if(tar - arr[i] >= 0){
               count += coinChange_recursion(arr, i, tar - arr[i], ans + arr[i]); // important for recursive solution
            }
        }

        return count;
    }

    public static int coinChange_DP(int[] arr, int tar) {
        int[] dp = new int[tar + 1];
        dp[0] = 1;
        for (int i = 0; i < arr.length; i++) {   // IMPORT: firstly coins loop then dp traversal
            for (int j = arr[i]; j < dp.length; j++) {
                dp[j] += dp[j - arr[i]];  // Sexy but important dry run 
            }
        }

        return dp[tar];
    }

    public static void coinChange() {
        int n = scn.nextInt();
        int[] arr=  new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }

        int tar = scn.nextInt();
        System.out.println(coinChange_recursion(arr, 0, tar, ""));
        // System.out.println(coinChange_DP(arr, tar));
    }

    // Coin Change Permutation
    public static int coinChange_Per_recursion(int[] arr, int idx, int tar, String ans) {
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }

        if(idx == arr.length)
            return 0;

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if(tar - arr[i] >= 0){
                count += coinChange_Per_recursion(arr, idx, tar - arr[i], ans + arr[i]);
            }
        }
        return count;
    }

    public static int coinChange_Per_DP(int[] coins, int tar) {
        int[] dp = new int[tar + 1];
        dp[0] = 1;
        for (int amt = 1; amt < dp.length; amt++) {
            for (int coin : coins) {
                if(coin <= amt){ // IMPORT: firstly Dp loop then coins traversal for Permutation
                    int ramt = amt - coin;
                    dp[amt] += dp[ramt];  // Sexy but important dry run 
                } 
            }
        }

        return dp[tar];
    }

    // https://leetcode.com/problems/coin-change/
    public class Pair {
        int val = 0;
        int count = (int)1e9 ;
        
        Pair(int val, int count){
            this.val = val;
            this.count = count;
        }
    }
    
    public int coinChange(int[] arr, int tar) {
        int n = arr.length;
        Pair[] dp = new Pair[tar + 1];
        
        for(int i = 1; i < dp.length; i++){
            dp[i] = new Pair(0, (int)1e9);
        }
        
        dp[0] = new Pair(1, 0);
        
        for(int i = 0; i < n; i++){
            for(int j = arr[i]; j < dp.length; j++){
                dp[j].val = dp[j].val + dp[j - arr[i]].val;
                dp[j].count = Math.min(dp[j].count, dp[j - arr[i]].count + 1);
            }
        }
        
        
        return dp[tar].count == (int)1e9 ? -1 : dp[tar].count ;
    }

    // https://leetcode.com/problems/coin-change-2/
     public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        
        for(int i = 0; i < coins.length; i++){
            for(int j = coins[i]; j < dp.length; j++){
                dp[j] += dp[j - coins[i]];
            }
        }

        return dp[amount];
    }

    public static void coinChange_Per(){

        int n = scn.nextInt();
        int[] arr=  new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }

        int tar = scn.nextInt();
        // System.out.println(coinChange_Per_recursion(arr, 0, tar, ""));
        System.out.println(coinChange_Per_DP(arr, tar));
    }

    // 4. 0-1 Knapsack
    public static void knapsack(){
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
        int[] weight = new int[n];
        for (int i = 0; i < weight.length; i++) {
            weight[i] = scn.nextInt();
        }

        int capacity = scn.nextInt();
        int[][] dp = new int[n + 1][capacity + 1];


        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if(j >= weight[i - 1]){
                    int rCap = j - weight[i - 1];
                    if(dp[i - 1][rCap] + arr[i - 1] > dp[i - 1][j]){
                        dp[i][j] = dp[i - 1][rCap] + arr[i - 1];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[n][capacity]);
    }

    // Unbounded Knapsack
    public static void unBoundedKnapSack(){
        int n = scn.nextInt();
        int[] vals = new int[n];
        int[] wts = new int[n];

        for (int i = 0; i < vals.length; i++) {
            vals[i] = scn.nextInt();
        }
        
        for (int i = 0; i < wts.length; i++) {
            wts[i] = scn.nextInt();
        }

        int capacity = scn.nextInt();
        
        int[] dp = new int[capacity + 1];
        for (int cap = 1; cap < dp.length; cap++) {
            for (int j = 0; j < n; j++) {
            
                if(cap >= wts[j]){
                    int rCap = cap - wts[j];
                    if(dp[cap] < vals[j] + dp[rCap]){
                        dp[cap] = vals[j] + dp[rCap];
                    }
                }
            }
        }
        
        System.out.println(dp[capacity]);
    }

    public static void main(String[] args) {
        // targetSumSubset();
        // targetSumSubset_DP();
        // kthSubset();
        // coinChange();
        unBoundedKnapSack();
    }
}
