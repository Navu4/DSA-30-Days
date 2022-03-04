import java.util.Arrays;
import java.util.Scanner;

public class day_1{
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
    
    /**
        Dynamic Programming
        1. Climbing Stairs with jumps   
        2. Climbing Stairs with minimum moves
        3. a) Print Board Path
           b) Minimum cost path 
           c) Multiple jumps
        4. Goldmine problem 
        5. Path with maximum gold
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

    // b) Minimum cost path 
    public static int mazePath_recursive(int sr, int sc, int er, int ec, int[][] dir){
        if(sr == er && sc == ec)
            return 1;

        int count = 0;
        for (int i = 0; i < dir.length; i++) {
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];

            if(r >= 0 && c >= 0 && r <= er && c <= ec){
                count += mazePath_recursive(r, c, er, ec, dir);
            }
        }
        return count;
    }

    public static int mazePath_memo(int sr, int sc, int er, int ec, int[][] dir, int[][] dp){
        if(sr == er && sc == ec)
            return dp[sr][sc] = 1;

        if(dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        for (int i = 0; i < dir.length; i++) {
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];

            if(r >= 0 && c >= 0 && r <= er && c <= ec){
                count += mazePath_memo(r, c, er, ec, dir, dp);
            }
        }
        return dp[sr][sc] = count;
    }

    public static int mazePath_tabu(int er, int ec, int[][] dir, int[][] dp){
      
        for (int sr = er; sr >= 0; sr--) {
            for (int sc = ec; sc >= 0; sc--) {
                if(sr == er && sc == ec){
                    dp[sr][sc] = 1;
                    continue;
                }
        
                int count = 0;
                for (int i = 0; i < dir.length; i++) {
                    int r = sr + dir[i][0];
                    int c = sc + dir[i][1];

                    if(r >= 0 && c >= 0 && r <= er && c <= ec){
                        count += dp[r][c];
                    }
                }
                
                dp[sr][sc] = count;
            }
        }
        return dp[0][0];
    }

    public static int mazePath_withJumps_memo(int sr, int sc, int er, int ec, int[][] dir, int[][] dp){
        if(sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        if(dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        for(int d = 0; d < dir.length; d++){
            for(int rad = 1; rad <= Math.max(er,ec); rad++){
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if(r >= 0 && c >= 0 && r <= er && c <= ec)
                    count += mazePath_memo(r, c, er, ec, dir, dp);
                else 
                    break;
            }
        }
        return dp[sr][sc] = count;
    }


    public static int mazePath_withJumps_tabu(int SR, int SC, int er, int ec, int[][] dir, int[][] dp){
        for (int sr = er; sr >= 0; sr--) {
            for(int sc = ec; sc >= 0; sc--){
                if(sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for(int d = 0; d< dir.length; d++){
                    for(int rad = 1; rad <= Math.max(er,ec); rad++){
                        int r = sr + rad * dir[d][0];
                        int c = sc + rad * dir[d][1];
                        
                        if(r >= 0 && c >= 0 && r <= er && c <= ec)
                            count += dp[r][c];
                        else 
                            break;
                    }
                }
                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];

    }

    public static void mazePath() {
        /**
         * Calculate the number of paths from src to dest 
         */
        int n = scn.nextInt();
        int m = scn.nextInt();

        int[][] dp = new int[n][m];
        int[][] dir = { { 0, 1 }, { 1, 1 }, { 1, 0} };
        // int ans = mazePath_recursive(0, 0, n - 1, m  - 1, dir);
        // int ans = mazePath_memo(0, 0, n - 1, m  - 1, dir, dp);
        int ans = mazePath_tabu(n - 1, m - 1, dir, dp);
        print2Arr(dp);
        System.out.println(ans);
    }

    // Minimum cost in a maze
    // https://leetcode.com/problems/minimum-path-sum/

    public static int minPathSum_memo(int sr, int sc, int er, int ec, int[][] grid, int[][] dir, int[][] dp){
        if(sr == er && sc == ec){
            return dp[sr][sc] = grid[sr][sc];
        }

        if(dp[sr][sc] != 0)
            return dp[sr][sc];

        int cost = (int)1e9;
        for(int d = 0; d < dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if(r >= 0 && c >= 0 && r <= er && c <= ec){
                cost = Math.min(cost, minPathSum_memo(r, c, er, ec, grid, dir, dp));
            }
        }
        return dp[sr][sc] = cost + grid[sr][sc];
    }

    public static void minPathSum() {
        int n = scn.nextInt();
        int m = scn.nextInt();

        int[][] grid = new int[n][m];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = scn.nextInt();
            }
        }
        int[][] dir = { {0, 1}, {1, 0} };
        int[][] dp = new int[n][m];
        int ans = minPathSum_memo(0, 0, n - 1, m - 1, grid, dir, dp);
        print2Arr(dp);
        System.out.println(ans);
    }


    // 4. Goldmine problem 
    public static int goldmineProblem_memo(int[][] grid, int r, int c, int[][] dp, int[][] dir){
        if(c == grid[0].length - 1){
            return dp[r][c] = grid[r][c];
        }
        if(dp[r][c] != -1){
            return dp[r][c];
        }

        int maxGold = 0;
        for (int d = 0; d < dir.length; d++) {
            int x = r + dir[d][0];
            int y = c + dir[d][1];

            if(x >= 0 && y >= 0 && x <  grid.length && y < grid[0].length){
                maxGold = Math.max(maxGold, goldmineProblem_memo(grid, x, y, dp, dir) + grid[r][c]);
            }
        }

        return dp[r][c] = maxGold;
    }

    public static int goldmineProblem_tabu(int[][] grid, int R, int C, int[][] dp, int[][] dir){

        int n = grid.length, m = grid[0].length;
        for (int c = grid[0].length; c >= 0; c--) {
            for (int r = grid.length - 1; r >= 0; r--) {
                
                if(c == grid[0].length - 1){
                     dp[r][c] = grid[r][c];
                     continue;
                }

                int maxGold = 0;
                for (int d = 0; d < dir.length; d++) {
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];

                    if(x >= 0 && y >= 0 && x <  grid.length && y < grid[0].length){
                        maxGold = Math.max(maxGold, dp[x][y] + grid[r][c]);
                    }
                }

                dp[r][c] = maxGold;
            }
        }
        return dp[R][C];
    }

    public static void goldmineProblem() {
        int n = scn.nextInt();
        int m = scn.nextInt();

        int[][] grid = new int[n][m];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = scn.nextInt();
            }
        }
        int[][] dir = { {-1, 1}, {1, 1}, {0, 1} };
        int[][] dp = new int[n][m];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }

        int maxGold = 0;
        for (int i = 0; i < n; i++) {
            maxGold = Math.max(maxGold, goldmineProblem_memo(grid, i, 0, dp, dir));
        }

        print2Arr(dp);
        System.out.println(maxGold);
    }



    public static void main(String[] args) {
        // climbStairs_();
        // minCostClimbingStairs();
        // boardPathProblem();
        // mazePath();
        // minPathSum();
        goldmineProblem();
    }
}