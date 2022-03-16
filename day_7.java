import java.util.Scanner;
public class day_7 {
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
     * Catalan Set
     * 1. Catalan Number
     * 2. Number of Bsts
     * 3. Count of Valleys and Mountains
     * 4. Count Brackets
     * 5. Circle and Chords
     * 6. Number of Ways of Triangulation
     * 7. Catalan number Variation 
     * 
     */

    // Catalan Number
    // Catalan Series : 1 1 2 5 ....
    public static void catalanNumber() {
        int n  = scn.nextInt();
        
        if(n <= 1){
            System.out.println(1);
            return;
        }
        
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        
        for (int i = 2; i < dp.length; i++) {
            int idx = i - 1;
            for (int j = 0; j < i && idx >= 0; j++, idx--) {
                dp[i] += dp[j] * dp[idx];
            }
        }
        printArr(dp);
        System.out.println(dp[n]);
    }

    // 2. Number of Bsts 
    //         or
    // Count Number of Binary Search Trees with N Nodes
    public static void numberOfBsts() {
        int n = scn.nextInt();
        if(n <= 1){
            System.out.println(1);
           return;
       }
       
       int[] dp = new int[n + 1];
       dp[0] = 1;
       dp[1] = 1;
       
       for (int i = 2; i < dp.length; i++) {
           int idx = i - 1;
           for (int j = 0; j < i && idx >= 0; j++, idx--) {
               dp[i] += dp[j] * dp[idx];
           }
       }
       System.out.println(dp[n]);       
    } 

    public static void main(String[] args) {
        catalanNumber();
    }
}
