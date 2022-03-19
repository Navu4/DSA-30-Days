import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
public class day_9 {

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

    public static void print2ArrString(String[][] arr){
        for (String[] a : arr) {
            for (String b : a) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
        System.out.println();
    }



    /**
     * String Set-2
     * 1. Minimum Palindromic Cut
     * 2. Longest Common Subsequence
     * 3. Longest Palindromic Subsequence
     * 4. Count Palindromic Subsequence
     * 5. Count Palindromic Substring
     * 6. Longest Palindromic Substring
     * 
     *  IMPORTANT 
     *  :: In string set dry run from forward and code in terms of length from backward :: 
     */



     // 2. Longest Common Subsequence
     public static void LCSS() {
        String str1 = scn.nextLine();
        String str2 = scn.nextLine();
        
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        // for (int[] a : dp) {
        //     Arrays.fill(a, -1);
        // }

        int ans = longestCommonSubsequence_tabu(str1, str2, str1.length(), str2.length(), dp);
        print2Arr(dp);
        System.out.println(ans);
     }

     // Recursive and Memo 
     public static int longestCommonSubsequence_memo(String str1, String str2, int n, int m, int[][] dp){
        if(n == 0 || m == 0)
            return dp[n][m] = 0;

        if(dp[n][m] != 0)
            return dp[n][m];

        if(str1.charAt(n - 1) == str2.charAt(m - 1)){
            return dp[n][m] = longestCommonSubsequence_memo(str1, str2, n - 1, m - 1, dp) + 1;
        } else {
            return dp[n][m] = Math.max(longestCommonSubsequence_memo(str1, str2, n - 1, m, dp), 
                                    longestCommonSubsequence_memo(str1, str2, n, m - 1, dp));
        }
     }

     // Tabulation
     public static int longestCommonSubsequence_tabu(String str1, String str2, int N, int M, int[][] dp){
         for (int n = 0; n <= N; n++) {
             for (int m = 0; m <= M; m++) {
                if(n == 0 || m == 0){
                    dp[n][m] = 0;
                    continue;
                }

                if(str1.charAt(n - 1) == str2.charAt(m - 1)){
                    dp[n][m] =  dp[n - 1][m - 1] + 1; // longestCommonSubsequence_memo(str1, str2, n - 1, m - 1, dp) + 1;
                } else {
                    dp[n][m] =   Math.max(dp[n - 1][m], dp[n][m - 1]);
                    //  Math.max(longestCommonSubsequence_memo(str1, str2, n - 1, m, dp), 
                    //                         longestCommonSubsequence_memo(str1, str2, n, m - 1, dp));
                }
             }
         }

         return dp[N][M];
     }

     // Longest Common Subsequence String
     public static class Pair {
         int i;
         int j;
         String s;

         public Pair(int i, int j, String s){
             this.i = i;
             this.j = j;
             this.s = s;
         }
     }
     public static void longestCommonSubsequence(){
        String str1 = scn.nextLine();
        String str2 = scn.nextLine();

        int N = str1.length();
        int M = str2.length();

        int[][] dp = new int[N + 1][M + 1];

        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if(n == 0 || m == 0){
                    dp[n][m] = 0;
                    continue;
                }

                if(str1.charAt(n - 1) == str2.charAt(m - 1)){
                    dp[n][m] = dp[n - 1][m - 1] + 1;
                } else {
                    dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
                }
            }
        }
        // print2Arr(dp);
        System.out.println(dp[N][M]);
        LinkedList<Pair> que = new LinkedList<>();
        que.addLast(new Pair(N, M, ""));

        while (que.size() != 0) {
            Pair rem =  que.removeFirst();
            int r = rem.i;
            int c = rem.j;
            String s = rem.s;

            if(r == 0 || c == 0){
                System.out.println(s);
                continue;
            }
            

            if(str1.charAt(r - 1) == str2.charAt(c - 1)){
                que.addLast(new Pair(r - 1, c - 1, str1.charAt(r - 1) + s));
            } else {
                if(dp[r - 1][c] == dp[r][c - 1]){
                    que.addLast(new Pair(r - 1, c, s));
                } else if (dp[r][c] == dp[r - 1][c]) {
                    que.addLast(new Pair(r - 1, c, s));
                } else if (dp[r][c] == dp[r][c - 1]) {
                    que.addLast(new Pair(r, c - 1, s));
                }
            }
        }
     }


    //  3. Longest Palindromic Subsequence
    public static void LPSS(){
        String str = scn.nextLine();
        int n = str.length();
        
        int[][] dp = new int[n][n];

        // int ans = longestPalindromicSubsequence_memo(str, 0, n - 1, dp);
        int ans = longestPalindromicSubsequence_tabu(str, 0, n - 1, dp);
        print2Arr(dp);
        System.out.println(ans);
    }
    public static int longestPalindromicSubsequence_memo(String str, int i, int j, int[][] dp){
        if(i >= j){
            return dp[i][j] = i == j ? 1 : 0;
        }

        if(dp[i][j] != 0)
            return dp[i][j];


        if(str.charAt(i) == str.charAt(j)){
            return dp[i][j] = longestPalindromicSubsequence_memo(str, i + 1, j - 1, dp) + 2;
        } else {
            return dp[i][j] = Math.max(longestPalindromicSubsequence_memo(str, i + 1, j, dp), 
                                    longestPalindromicSubsequence_memo(str, i, j - 1, dp));
        }
    }

    public static int longestPalindromicSubsequence_tabu(String str, int I, int J, int[][] dp){
        int n = str.length();
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n && i < n; i++, j++) {
                if(i >= j){
                    dp[i][j] = i == j ? 1 : 0;
                    continue;
                }

                if(str.charAt(i) == str.charAt(j)){
                    dp[i][j] =  dp[i + 1][j - 1] + 2; // longestPalindromicSubsequence_memo(str, i + 1, j - 1, dp) + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    // Math.max(longestPalindromicSubsequence_memo(str, i + 1, j, dp), 
                    //                         longestPalindromicSubsequence_memo(str, i, j - 1, dp));
                }
            }
        }

        return dp[I][J];
    }

    // Longest Palindromic Subsequence String
    public static void longestPalindromicSubsequence() {
        String str = scn.nextLine();

        int n = str.length();
        String[][] dp = new String[n][n];

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; i < n && j < n; j++, i++) {
                if(i >= j){
                    dp[i][j] = i == j ? str.charAt(i) + "" : "";
                    continue;
                }

                if(str.charAt(i) == str.charAt(j)){
                    dp[i][j] = str.charAt(i) +  dp[i + 1][j - 1] + str.charAt(j);
                } else {
                    dp[i][j] = dp[i][ j -1].length() > dp[i + 1][j].length() ? dp[i][ j -1] : dp[i + 1][j];
                }
            }
        }

        System.out.println(dp[0][n - 1]);
        print2ArrString(dp);
    }



    // * 5. Count Palindromic Substring
    // * 6. Longest Palindromic Substring
    /**
     * Count All Palindromic Substring
     * Length of Longest Palindromic SubString
     * String of Longest Palindromic SubString
     */
    // All in One 
     public static void LongestPalindromicSubString(){
         String str = scn.nextLine();

         int n = str.length();
         boolean[][] dp = new boolean[n][n];
         int count = 0;
         int len = 0;

         for (int gap = 0; gap < dp.length; gap++) {
             for (int i = 0, j = gap; j < dp.length && i < dp.length; j++, i++) {
                if(i == j){
                     dp[i][j] = true;
                     count++;
                } else if(j - i == 1){
                    if(str.charAt(i) == str.charAt(j)){
                        dp[i][j] = true;
                        count++;
                    }
                } else {
                    if(str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1]){
                        dp[i][j] = true;
                        count++;
                    }
                }
                len = dp[i][j] && (j - i + 1) > len ? (j - i + 1) : len;
            }
        }
        
        System.out.print("Count: " + count + "\nLength: " + len + "\nString: ");
        
        for (int i = 0, j = len - 1; j < dp.length && i < dp.length; j++, i++) {
            if(dp[i][j]){
                System.out.print(str.substring(i, j + 1) + " ");
            }
        }
     }

     public static void main(String[] args) {
        //  LCSS();
        // longestCommonSubsequence();
        // LPSS();
        // longestPalindromicSubsequence();
        LongestPalindromicSubString();
     }
}
