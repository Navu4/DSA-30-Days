import java.util.Arrays;
import java.util.Scanner;

public class day_3 {

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
     * 
     * Include Exclude (Set 3)  
        1. Count Binary String
        2. Arrange Buildings 
        3. Decode Ways 
        4. Maximum Sum Not Adjacent
        5. Paint House
           a) 1
           b) 2
           c) Paint Fence 
        6. Tiling with Dominoes
        7. Tiling with M x 1 Tiles
        8. Friends Pairing
        9. Partition
     */

     /**
      * Remaining question this set 
        1. Decode way II
        2. Paint Fence 
      */

    // 1. Count Binary String | Binary Strings with no consecutive 0's |
    public static void countBinary() {
        int n = scn.nextInt();
        
        // int[] dp0 = new int[n + 1];
        // int[] dp1 = new int[n + 1];

        // dp0[1] = 1;
        // dp1[1] = 1;

        // for (int i = 2; i < dp1.length; i++) {
        //     dp1[i] = dp0[i - 1] + dp1[i - 1];
        //     dp0[i] = dp1[i - 1];
        // }
        // System.out.println(dp1[n] + dp0[n]);

        int cozeros = 1;
        int coones = 1;
        for (int i = 2; i < n + 1; i++) {
            int newZeros = coones;
            int newOnes = coones + cozeros;

            cozeros = newZeros;
            coones = newOnes;
        }
        System.out.println(coones + cozeros);
    }

    // 3. Decode Ways 
    public static int decodeWayI_memo(String s, int n, int[] dp){
        if(n == 0){
            return dp[n] = 1;
        }

        if(dp[n] != -1)
            return dp[n];

        int count = 0;
        if(s.charAt(n - 1) != '0'){
            count += decodeWayI_memo(s, n - 1, dp);
        }

        if(n > 1 && Integer.parseInt(s.substring(n - 2, n)) >= 10 && Integer.parseInt(s.substring(n - 2, n)) <= 26){
            count += decodeWayI_memo(s, n - 2, dp);
        }

        return dp[n] = count;
    }

    public static int decodeWayI_tabu(String s, int N, int[] dp){
        for (int n = 0; n <= N; n++) {
            if(n == 0){
                dp[n] = 1;
                continue;
            }
    
            int count = 0;
            if(s.charAt(n - 1) != '0'){
                count +=  dp[n -1]; // decodeWayI_memo(s, n - 1, dp);
            }
    
            if(n > 1 && Integer.parseInt(s.substring(n - 2, n)) >= 10 && Integer.parseInt(s.substring(n - 2, n)) <= 26){
                count +=  dp[n - 2]; // decodeWayI_memo(s, n - 2, dp);
            }
    
            dp[n] = count;
        }
        return dp[N];
    }

    public static void decodeWayI(){
        String str = scn.nextLine();

        int n = str.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        System.out.println(decodeWayI_tabu(str, n, dp));
    }

    // Decode ways II
    public static int mod = (int)1e9 + 7;
    // Recursive - memo 
    // Tabulation 
    // Optimization

    // * => 1, 2, 3, 4, 5, 6, 7, 8, 9

    // Edge Cases 
    // * * 
    // n * 
    // * n 
    // n n
    // public static int decodeWayII_memo(String s, int idx, int[] dp){
    //     if(idx == s.length()){
    //         return dp[idx] = 1;
    //     }

    //     if(dp[idx] != -1)
    //         return dp[idx];

    //     int count = 0;
    //     if(s.charAt(idx) == '*'){
    //         count = (count + decodeWayII_memo(s, idx + 1, dp) % mod) % mod;
    //         if(idx + 1 < s.length()){
    //             if(s.charAt(idx + 1) == '*'){

    //             } else {

    //             }
    //         }
    //     } else {
    //         count = (count + decodeWayII_memo(s, idx + 1, dp) ) % mod;
    //         if(idx + 1 < s.length()){
    //             if(s.charAt(idx + 1) == '*'){

    //             } else if(s.charAt(idx + 1) ){

    //             } 
    //         }
    //     }
    
    
    //     return dp[idx] = count;
    // }
    public static void decodeWayII(){
        String str = scn.nextLine();

        int n = str.length(); 
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);


    }
    
    

    // 4. Maximum Sum Not Adjacent
    public static int sumNotAdjacent_tabu(int[] arr){
        int[][] dp = new int[2][arr.length];

        // elm     5  10  10   110   4      6
        // -------------------------------------- 
        // inc     5  10  15   120   19     126
        // exc     0  5   10   15    120    120

        dp[0][0] = arr[0];

        for(int i = 1; i < arr.length; i++){
            dp[0][i] = dp[1][i - 1] + arr[i];
            dp[1][i] = Math.max(dp[0][i - 1], dp[1][i - 1]);
        }

        return Math.max(dp[0][arr.length], dp[1][arr.length]);
    }

    public static int sumNotAdjacent_optiz(int[] arr){
        int n = arr.length;
        int inc = arr[0];
        int exc = 0;

        for(int i = 1; i < n; i++){
            int newInc = exc + arr[i];
            int newExc = Math.max(inc, exc);

            inc = newInc;
            exc = newExc;
        }

        int ans = Math.max(inc, exc);
        return ans;

    }


    public static void sumNotAdjacent() {

        int n = scn.nextInt();
        int[] arr = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = scn.nextInt();
        }

        System.out.println();
    }


    //  5. a) Paint House I
    public static void paintHouseI(){
        int n = scn.nextInt();
        int[][] arr = new int[n][3];

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                arr[i][j] = scn.nextInt();
            }
        }

        long[][] dp = new long[n][3];

        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];

        for (int i = 1; i < arr.length; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + arr[i][1];
            dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][0]) + arr[i][2];
        }

        long ans = Math.min(Math.min(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]);
        System.out.println(ans);
    
    }

    // 5. b) Paint House II
    public static void paintHouseII_Opti(){
        int noOfHouses = scn.nextInt();
        int colors = scn.nextInt();

        int[][] arr = new int[noOfHouses][colors];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] =  scn.nextInt();
            }
        }

       
        int min = Integer.MAX_VALUE;
        int smin = Integer.MAX_VALUE;
        for(int j = 0; j < arr[0].length; j++){
            if(arr[0][j] <= min){
                smin = min;
                min = arr[0][j];
            } else if(arr[0][j] <= smin){
                smin = arr[0][j];
            }
        }

        for (int i = 1; i < arr.length; i++) {
            int cmin = Integer.MAX_VALUE;
            int csmin = Integer.MAX_VALUE;

            for(int j = 0; j < arr[i].length; j++){
                if(arr[i - 1][j] != min){
                    arr[i][j] += min;
                } else {
                    arr[i][j] += smin;
                }

                if(arr[i][j] <= cmin){
                    csmin = cmin;
                    cmin = arr[i][j];
                } else if(arr[i][j] <= csmin){
                    csmin = arr[i][j];
                }
            }

            min = cmin;
            smin = csmin;
        }

        System.out.println(min);
    
    }



    public static void paintHouseII_DP_Tabu(){
        int noOfHouses = scn.nextInt();
        int colors = scn.nextInt();

        int[][] arr = new int[noOfHouses][colors];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] =  scn.nextInt();
            }
        }

        int[][] dp = new int[arr.length][arr[0].length];
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = arr[0][j];
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                int min = (int)1e9;

                for (int k = 0; k < dp[0].length; k++) {
                    if(k != j)
                        min = Math.min(min, dp[i - 1][k]);
                }

                dp[i][j] = arr[i][j] + min;
            }
        }

        int ans = (int)1e9;
        for (int i = 0; i < dp[0].length; i++) {
            ans = Math.min(ans, dp[dp.length - 1][i]);
        }

        System.out.println(ans);
    
    }



    public static void main(String[] args) {
        // countBinary();
        decodeWayI();
    }
}
