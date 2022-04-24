import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class day_17 {

    /**
     * BFS and DFS (IMPORTANT CONCEPT AND QUESTIONS)
     * 
     * 1. Number of Islands
     * 2. Clone Graph
     * 3. Max Area of Island
     * 4. Pacific Atlantic Waterflow
     * 5. Surrounded Regions
     * 6. Rotting Oranges
     * 7. Walls and Gates
     * 8. Redundant Connection
     * 11. Number of Connected Componenents in Graph
     * 12. Graph Valid Tree
     * 13. Word Ladder
     * 
     */

    // 1. Number of Islands
    // https://leetcode.com/problems/number-of-islands/
    /**
     * Constraints And Info
     * '1' -> Land
     * '0' -> Water
     */
    public void dfs_numIslands(char[][] grid, int i, int j, boolean[][] vis, int[][] dir) {

        vis[i][j] = true;
        for (int d = 0; d < dir.length; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && !vis[r][c] && grid[r][c] == '1') {
                dfs_numIslands(grid, r, c, vis, dir);
            }
        }
    }

    public int numIslands(char[][] grid) {
        int n = grid.length, m = grid[0].length;

        int[][] dir = {
                { 0, 1 },
                { 0, -1 },
                { 1, 0 },
                { -1, 0 }
        };

        int noOfIsland = 0;

        boolean[][] vis = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!vis[i][j] && grid[i][j] == '1') {
                    noOfIsland++;
                    dfs_numIslands(grid, i, j, vis, dir);
                }
            }
        }

        return noOfIsland;
    }

    // 3. Max Area of Island
    // https://leetcode.com/problems/max-area-of-island/
    public void dfs_maxAreaOfIsland(int[][] grid, int i, int j, int[][] dir, int area[]) {

        area[0]++;
        grid[i][j] = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1) {
                dfs_maxAreaOfIsland(grid, r, c, dir, area);
            }
        }
    }

    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length;

        int[][] dir = {
                { 0, 1 },
                { 0, -1 },
                { 1, 0 },
                { -1, 0 }
        };

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int[] area = { 0 };
                    dfs_maxAreaOfIsland(grid, i, j, dir, area);
                    maxArea = Math.max(maxArea, area[0]);
                }
            }
        }

        return maxArea;
    }

    // 4. Pacific Atlantic Waterflow
    // https://leetcode.com/problems/pacific-atlantic-water-flow/
    public void dfs_pacificAtlantic(int[][] heights, int i, int j, int[][] dir, boolean[][] vis) {

        vis[i][j] = true;
        for (int d = 0; d < dir.length; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && c >= 0 && r < heights.length && c < heights[0].length && heights[r][c] >= heights[i][j]
                    && !vis[r][c]) {
                dfs_pacificAtlantic(heights, r, c, dir, vis);
            }
        }

    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int n = heights.length, m = heights[0].length;

        List<List<Integer>> res = new ArrayList<>();
        if (heights == null || (heights.length == 0 && heights[0].length == 0))
            return res;

        int[][] dir = {
                { 0, 1 },
                { 0, -1 },
                { 1, 0 },
                { -1, 0 }
        };

        boolean[][] pacific = new boolean[n][m];
        boolean[][] atlantic = new boolean[n][m];

        // dfs;
        for (int i = 0; i < m; i++) {
            dfs_pacificAtlantic(heights, 0, i, dir, pacific);
            dfs_pacificAtlantic(heights, n - 1, i, dir, atlantic);
        }

        for (int i = 0; i < n; i++) {
            dfs_pacificAtlantic(heights, i, 0, dir, pacific);
            dfs_pacificAtlantic(heights, i, m - 1, dir, atlantic);
        }

        // calculating res
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    // 5. Surrounded Regions
    // https://leetcode.com/problems/surrounded-regions/
    public void solve(char[][] board) {
        int n = board.length, m = board[0].length;

        LinkedList<Integer> que = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i == 0 || j == 0 || i == n - 1 || j == m - 1) && board[i][j] == 'O') {
                    que.add(i * m + j);
                }
            }
        }

        int[][] dir = {
                { 0, 1 },
                { 0, -1 },
                { 1, 0 },
                { -1, 0 }
        };

        while (que.size() != 0) {
            int idx = que.removeFirst();
            int i = idx / m;
            int j = idx % m;

            board[i][j] = '$';
            for (int d = 0; d < dir.length; d++) {
                int r = i + dir[d][0];
                int c = j + dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m && board[r][c] == 'O') {
                    que.add(r * m + c);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '$') {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    // 6. Rotting Oranges
    // https://leetcode.com/problems/rotting-oranges/
    public int orangesRotting(int[][] grid) {
        int n = grid.length, m = grid[0].length;

        LinkedList<Integer> que = new LinkedList<>();
        int time = 0, freshOrangesCount = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    freshOrangesCount++;
                } else if (grid[i][j] == 2) {
                    que.add(i * m + j);
                }
            }
        }

        if (freshOrangesCount == 0)
            return 0;

        int[][] dir = {
                { 0, 1 },
                { 0, -1 },
                { 1, 0 },
                { -1, 0 }
        };

        while (que.size() != 0) {
            int size = que.size();

            while (size-- > 0) {
                int idx = que.removeFirst();
                int i = idx / m, j = idx % m;

                for (int d = 0; d < dir.length; d++) {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                        if (--freshOrangesCount == 0) {
                            return time + 1;
                        }
                        grid[r][c] = 2;
                        que.add(r * m + c);
                    }
                }
            }
            time++;
        }

        return -1;
    }

    // 7. Walls and Gates
    // https://www.lintcode.com/problem/663/
    // Leetcode 286
    public void wallsAndGates(int[][] rooms) {
        int n = rooms.length, m = rooms[0].length;
        LinkedList<Integer> que = new LinkedList<>();

        int[][] dir = {
                { 0, 1 },
                { 0, -1 },
                { 1, 0 },
                { -1, 0 }
        };

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (rooms[i][j] == 0)
                    que.addLast(i * m + j);

        while (que.size() != -1) {
            int size = que.size();

            while (size-- > 0) {
                int idx = que.removeFirst();
                int sr = idx / m, sc = idx % m;
                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];

                    if (r >= 0 && c >= 0 && r < n && c < m && rooms[r][c] == 2147483647) {
                        rooms[r][c] = rooms[sr][sc] + 1;
                        que.addLast(r * m + c);
                    }
                }
            }
        }
    }
}
