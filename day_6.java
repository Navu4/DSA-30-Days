import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
public class day_6 {


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
     * String Set (Most Important Set)
     * 1. Longest Increasing Subsequence (LIS)
     * 2. Print All LIS
     * 3. Maximum Sum Increasing Subsequence
     * 4. Longest Bitonic Subsequence
     * 5. Maximum Non-Overlapping 
     * 6. Russian Doll Envelopes
     * 7. Min Squares
     */

    //  1. Longest Increasing Subsequence (LIS)
    public static void LIS() {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }

        int[] dp = new int[n];

        int ans = LIS(arr, n, dp);
        System.out.println(ans);
    }

    public static int LIS(int[] arr, int n, int[] dp){
        if(n == 0){
            return 1;
        }
        dp[0] = 1;

        int ans = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxLength = 0;
            for(int j = 0; j < i; j++){
                if(arr[j] < arr[i]){
                    maxLength = Math.max(maxLength, dp[j]);
                }
            }

            dp[i] = maxLength + 1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // Longest Increasing Subsequence (LIS) Path 
    public static class Pair {
        int l;
        int i;
        int v;
        String psf;
        
        Pair(int l, int i, int v, String psf){
            this.l = l;
            this.i = i;
            this.v = v;
            this.psf = psf;
        }
    }

    public static void LIS_Path(int[] arr, int n, int[] dp){
        if(n == 0){
            System.out.println(1);
            return;
        }
        dp[0] = 1;

        int ans = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxLength = 0;
            for(int j = 0; j < i; j++){
                if(arr[j] < arr[i]){
                    maxLength = Math.max(maxLength, dp[j]);
                }
            }

            dp[i] = maxLength + 1;
            if(ans < dp[i]){
                ans = dp[i];
            }
        }
        System.out.println(ans);

        LinkedList<Pair> que = new LinkedList<>();
        for (int i = 0; i < dp.length; i++) {
            if(dp[i] == ans)
                que.addLast(new Pair(ans, i, arr[i], arr[i] + ""));
        }
    
        while (que.size() != 0) {
            Pair rem = que.removeFirst();

            int len = rem.l;
            int idx = rem.i;
            int val = rem.v;
            String psf = rem.psf;

            if(len == 1){   // compare it with length 1 ONLY
                System.out.println(psf);
            }

            for (int j = idx - 1; j >= 0; j--) {
                if(dp[j] == len - 1 && arr[j] <= val){
                    que.addLast(new Pair(dp[j], j, arr[j], psf + " -> " + arr[j] ));
                }
            }
        }
    }
    
    // 2. Maximum Sum Increasing Subsequence
    public static void maximumSumIncreasingSubsequence() {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = scn.nextInt();
        }

        int[] dp = new int[n];
        int ans = maximumSumIncreasingSubsequence(arr, n, dp);
        System.out.println(ans);
    
    }

    public static int maximumSumIncreasingSubsequence(int[] arr, int n, int[] dp){
        dp[0] = arr[0];
        int ans = arr[0];

        for (int i = 1; i < n; i++) {
            int maxValue = 0;
            for (int j = 0; j < i; j++) {
                if(arr[j] <= arr[i])
                    maxValue = Math.max(maxValue, dp[j]);
            }

            dp[i] = maxValue + arr[i];
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }

    // 4. Longest Bitonic Subsequence
    public static void longestBitonicSubsequence() {
        int n = scn.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }

        int ans = longestBitonicSubsequence(arr, n);
        System.out.println(ans);
    }

    public static int longestBitonicSubsequence(int[] nums, int n) {
        int[] LIS = new int[n];
        int[] LDS = new int[n];
        
        LIS[0] = 1;
        LDS[n - 1] = 1;
        
        for(int i = 1; i < n; i++){
            int maxLength = 0;
            
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i])
                    maxLength = Math.max(maxLength, LIS[j]);
            }
            
            LIS[i] = maxLength + 1;
        }
        
        for(int i = n - 2; i >= 0; i--){
            int maxLength = 0;
            
            for(int j = n - 1; j > i; j--){
                if(nums[j] < nums[i])
                    maxLength = Math.max(maxLength, LDS[j]);
            }
            
            LDS[i] = maxLength + 1;
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, LIS[i] + LDS[i] - 1);
        }

        return ans;
    
    }

    // 5. Maximum Non-Overlapping 
    public static class Bridge {
        int n;
        int s;

        public Bridge(int n, int s){
            this.n = n;
            this.s = s;
        }
    }
    public static void maximumNonOverlappingBridges() {
        int n = scn.nextInt();
        Bridge[] arr = new Bridge[n];
        for (int i = 0; i < n; i++) {
            int x = scn.nextInt();
            int y = scn.nextInt();

            arr[i] = new Bridge(x,y);
        }
        Arrays.sort(arr, (a, b) -> {
            if(a.n != b.n)
                return a.n - b.n;
            else 
                return a.s - b.s;
        });

        int[] dp = new int[n];

        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < n; i++) {
            int maxLength = 0;

            for (int j = 0; j < i; j++) {
                if(arr[j].s <= arr[i].s){
                    maxLength = Math.max(maxLength, dp[j]);
                }
            }

            dp[i] = maxLength + 1;
            ans = Math.max(ans, dp[i]);
        }

        System.out.println(ans);
    }


    // 6. Russian Doll Envelopes
    public static class Envolope{
        int n;
        int s;
        public Envolope(int n, int s){
            this.n = n;
            this.s = s;
        }
    }
   
    public static void russianDollEnvelopes(){
        int n = scn.nextInt();
        Envolope[] arr = new Envolope[n];
        for (int i = 0; i < n; i++) {
            int x = scn.nextInt();
            int y = scn.nextInt();

            arr[i] = new Envolope(x,y);
        }
        Arrays.sort(arr, (a, b) -> {
                return a.n - b.n;
        });

        int[] dp = new int[n];

        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < n; i++) {
            int maxLength = 0;

            for (int j = 0; j < i; j++) {
                if(arr[j].s < arr[i].s && arr[j].n < arr[i].n){
                    maxLength = Math.max(maxLength, dp[j]);
                }
            }

            dp[i] = maxLength + 1;
            ans = Math.max(ans, dp[i]);
        }

        System.out.println(ans);
 
    }

    // 354. Russian Doll Envelopes 
    // https://leetcode.com/problems/russian-doll-envelopes/
    // Same Question but optimized with binary search algorithm
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        if(n == 0)
            return 0;
        Arrays.sort(envelopes, (arr1,arr2) -> {
            if(arr1[0] == arr2[0])
                return arr2[1] - arr1[1];
            else
                return arr1[0] - arr2[0]; 
        });

        int[] dp = new int[n];

        dp[0] = 1;
        int len = 0;
        for(int[] envelope : envelopes){
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            if(index < 0)
                index = -(index + 1);
            dp[index] = envelope[1];
            if(index == len)
                len++;
        }
        return len;
    }

    // 279. Perfect Squares
    // https://leetcode.com/problems/perfect-squares/
    public int numSquares(int n) {
        if(n <= 1)
            return n;
        int[] dp = new int[n + 1];
        
        dp[0] = 0;
        dp[1] = 1;
        
        for(int i = 2; i < dp.length; i++){
            int min = (int)1e9;
            
            for(int j = 1; j * j <= i; j++){
                min = Math.min(min, dp[i - (j * j)]);
            }
            
            dp[i] = min + 1;
        }
        return dp[n];
    }

     public static void main(String[] args) {
        LIS();
     }
}
