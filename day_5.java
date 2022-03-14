import java.util.Scanner;
import java.util.LinkedList;

public class day_5 {


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
     * Print Path using Reverse Engineering
     * 1. Largest Square Submatrix of all 1's
     * 2. Print all path in min cost
     * 3. Print all path with minimum jumps
     * 4. Print all path with max gold
     * 5. Print Subsets with Target Sum 
     * 6. Paths in 0-1 knapsack 
     * 7. 2 Keys Keyboard
     * 
     * 
     */

    // 1. Largest Square Submatrix of all 1's
    public static int largestSquareSubmatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        
        int[][] dp = new int[n][m];
        
        int ans = 0, min = 0;
    
        for(int i = n - 1; i >= 0; i--){
            for(int j = m - 1; j >= 0; j--){
                if(i == n - 1 || j == m - 1){
                    dp[i][j] = matrix[i][j];
                } else {
                    if(matrix[i][j] == 0){
                        dp[i][j] = matrix[i][j];
                    } else {
                        min = Math.min(dp[i][j + 1], dp[i + 1][j]);
                        min = Math.min(min, dp[i+ 1][j + 1]) + 1;
                        dp[i][j] = min;
                    }
                }
                
                if(dp[i][j] > ans){
                    ans = dp[i][j];
                }
                
            }
        }
        
        return ans;
    }

    public static void largestSquareSubmatrix() {
        int n = scn.nextInt();
        int m = scn.nextInt();

        int[][] arr = new int[n][m];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = scn.nextInt();
            }
        }

        System.out.println(largestSquareSubmatrix(arr));
    }
	

    // 2. Print all path in min cost
    public static class Pair{
        int i;
        int j;
        String psf;

        Pair(String psf, int i, int j){
            this.i = i;
            this.j = j;
            this.psf =psf;
        }
    }

    public static void minCostPath(int[][] arr, int n, int m){
        int[][] dp = new int[n][m];
  
        for(int i = n - 1; i >= 0; i--){
           for(int j = m - 1; j >= 0; j--){
              if(i == n - 1 && j == m - 1){
                 dp[i][j] = arr[i][j];
              } else if(i == n - 1){
                 dp[i][j] = dp[i][j + 1] + arr[i][j];
              } else if(j == m - 1){
                 dp[i][j] = dp[i + 1][j] + arr[i][j];
              } else {
                 dp[i][j] = arr[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
              }
           }
        }
  
        System.out.println(dp[0][0]);
        // display(dp);
  
  
        LinkedList<Pair> que = new LinkedList<>();
        que.add(new Pair("", 0, 0));
  
        while(que.size() != 0){
           Pair rIdx = que.removeFirst();
  
           if(rIdx.i == n - 1 && rIdx.j == m - 1){
              System.out.println(rIdx.psf);
           } else if(rIdx.i == n - 1){
              que.addLast(new Pair( rIdx.psf + "H", rIdx.i, rIdx.j + 1));
           } else if(rIdx.j == m - 1){
              que.addLast(new Pair( rIdx.psf + "V", rIdx.i + 1, rIdx.j));
           } else {
              if(dp[rIdx.i + 1][rIdx.j] < dp[rIdx.i][rIdx.j + 1]) {
                 que.addLast(new Pair( rIdx.psf + "V", rIdx.i + 1, rIdx.j));
              } else if(dp[rIdx.i + 1][rIdx.j] > dp[rIdx.i][rIdx.j + 1]){
                 que.addLast(new Pair( rIdx.psf + "H", rIdx.i, rIdx.j + 1));
              } else {
                 que.addLast(new Pair( rIdx.psf + "V", rIdx.i + 1, rIdx.j));
                 que.addLast(new Pair( rIdx.psf + "H", rIdx.i, rIdx.j + 1));
              }
              
           }
  
        }
     }
  

    public static void minCostPath() {
        int n = scn.nextInt();
        int m = scn.nextInt();

        int[][] arr= new int[n][m];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = scn.nextInt();
            }
        }

        minCostPath(arr, n, m);
    }

    // 3. Print all path with minimum jumps
    public static class PairMin {
        int i; // index
        int j; // jumps
        int s; // state
        String psf;

        PairMin(int i, int s, int j, String psf){
            this.i = i;
            this.s = s;
            this.j = j;
            this.psf = psf;
        }
    }

    public static void minJumps(int arr[]){
        // write your code here
        int n = arr.length;

        Integer[] dp = new Integer[n];
        dp[n - 1] = 0;

        for(int i = n - 2; i >= 0; i--){
            int steps = arr[i];

            int min = Integer.MAX_VALUE;
            for(int j = 1; j <= steps && i + j < n; j++){
                if(dp[i + j] != null && dp[i + j] < min){
                    min = dp[i + j];
                }
            }
            if(min != Integer.MAX_VALUE){
                dp[i] = min + 1;
            } else {
                dp[i] = null;
            }
        }
        System.out.println(dp[0]);

        LinkedList<PairMin> que = new LinkedList<>();
        que.add(new PairMin(0, arr[0], dp[0],0 + ""));
    
        while ( que.size() != 0 ) {
            PairMin rem = que.removeFirst();

            if(rem.j == 0){
                System.out.println(rem.psf + " .");
            }

            int steps = rem.s;
            
            for(int j = 1; j <= steps && rem.i + j < arr.length; j++){

                int curIdx = rem.i + j;
                if(dp[curIdx] != null && dp[curIdx] == (rem.j - 1)){
                    que.addLast(new PairMin(curIdx, arr[curIdx], dp[curIdx], rem.psf + " -> " + curIdx));
                }
            }
        }
    }

    public static void minJumps(){
        int n = scn.nextInt();

        int arr[] = new int[n];
        for(int i = 0 ; i < n ; i++)
            arr[i] = scn.nextInt();

        minJumps(arr);
    }
 
    // 5. Print Subsets with Target Sum 
    public static class PairTar {
        int i;
        int j;
        String psf;

        public PairTar(int i, int j, String psf){
            this.i = i;
            this.j = j;
            this.psf = psf;
        }
    }

    public static void targetSumSubsetPath(int[] arr, int n, int tar){
        boolean[][] dp = new boolean[n + 1][tar + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= tar; j++) {
                if(j == 0){
                    dp[i][j] = true;
                } else if(i == 0) {
                    dp[i][j] = false;
                } else {
                    
                    if(dp[i - 1][j] == true){
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        if(j - arr[i - 1] >= 0){
                            dp[i][j] = dp[i - 1][j - arr[i - 1]];
                        }
                    }
                }
            }
        }

        System.out.println(dp[n][tar]);

        LinkedList<PairTar> que = new LinkedList<>();
        que.add(new PairTar(n, tar, ""));

        while (que.size() != 0) {
            PairTar rem = que.removeFirst();

            int r = rem.i;
            int c = rem.j;
            String psf = rem.psf;

            if(r == 0 || c == 0){
                System.out.println(psf);
            } else {
                if(dp[r - 1][c] == true){
                    que.addLast(new PairTar(r - 1, c, psf));
                }           

                if(c - arr[r - 1] >= 0 && dp[r - 1][c - arr[r - 1]] == true){
                    que.addLast(new PairTar(r - 1, c - arr[r - 1], (r - 1) + " " + psf));    
                }
            }

        }
    }

    public static void targetSumSubsetPath(){
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }

        int tar = scn.nextInt();

        targetSumSubsetPath(arr, n, tar);
    }



    // 6. Paths in 0-1 knapsack 
    public static class Pair01 {
        int i;
        int j;
        String psf;

        public Pair01(int i, int j, String psf){
            this.i = i;
            this.j = j;
            this.psf = psf;
        }
    }
    public static void knapSack(int[] values, int[] wts, int cap, int n){
        int[][] dp = new int[n + 1][cap + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= cap; j++) {
                dp[i][j] = dp[i - 1][j];

                if(j - wts[i - 1] >= 0 && dp[i - 1][j] < (dp[i - 1][j - wts[i - 1]] + values[i - 1])){
                    dp[i][j] = dp[i - 1][j - wts[i - 1]] +  values[i - 1];
                }
            }
        }
        System.out.println(dp[n][cap]);

        LinkedList<Pair01> que = new LinkedList<>();
        que.addLast(new Pair01(n, cap, ""));

        while ( que.size() != 0 ) {
            Pair01 rem = que.removeFirst();

            int r = rem.i;
            int c = rem.j;
            String psf = rem.psf;

            if(r == 0 || c == 0){
                System.out.println(psf);
            } else {
                int exc = dp[r - 1][c]; // no value add
                
                if(c - wts[r - 1] >= 0){
                    int inc = dp[r - 1][c - wts[r - 1]] + values[r - 1];
                    if(dp[r][c] == inc){
                        que.add(new Pair01(r - 1, c - wts[r - 1],  (r - 1) + " " + psf));
                    }
                }

                if(dp[r][c] == exc){
                    que.add(new Pair01(r - 1, c, psf));
                }
            }
        }
    }

    public static void knapSack() {
        int n = scn.nextInt();

        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scn.nextInt();
        }

        int[] wts = new int[n];
        for (int i = 0; i < n; i++) {
            wts[i] = scn.nextInt();
        }

        int cap = scn.nextInt();

        //write your code here
        knapSack(values, wts, cap, n);
    }

    // 7. 2 Keys Keyboard
    public static void Keys2Keyboard() {
        int n = scn.nextInt();

        if(n == 1){
            System.out.println(0);
            return;
        }
        
        int ans = minSteps_(n);
        System.out.println(ans);
    }
    
    public static int minSteps_(int n) {
        int ans = 0;
        
        for(int i = 2; i*i <= n;){
            if(n % i == 0){
                ans += i;
                n /= i;
                
            } else {
                i++;
            }
        }
        if(n != 1){
            ans += n;
        }
            
        return ans;
    }

    public static void main(String[] args) {
        targetSumSubsetPath();
    }
}
