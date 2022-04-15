import java.util.Arrays;

public class day_18 {
    /**
     * Recursion Level 2 (Practice)
     * 1. Unique Path II 
     * 2. 
     * 
     */


    // https://leetcode.com/problems/unique-paths-ii/
    public int uniquePathsWithObstacles(int[][] obstacleGrid, int sr, int sc, int er, int ec, int[][] dir, int[][] dp) {
        if(sr == er && sc == ec)
            return obstacleGrid[sr][sc] != 1 ? 1 : 0;

        if(dp[sr][sc] != -1)
            return dp[sr][sc];

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if(r >= 0 && c >= 0 && r <= er && c <= ec && obstacleGrid[r][c] != 1){
                count += uniquePathsWithObstacles(obstacleGrid, r, c, er, ec, dir, dp);
            }
        }

        return dp[sr][sc] = count;
    }
    
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        if(obstacleGrid[0][0] == 1 || obstacleGrid[n - 1][m - 1] == 1)
            return 0;
        
        int[][] dir = { {0, 1}, {1, 0} };
        int[][] dp = new int[n][m];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }

        return uniquePathsWithObstacles(obstacleGrid, 0, 0, n - 1, m - 1, dir, dp);
    }


    public static void main(String[] args) {
        
    }
}
