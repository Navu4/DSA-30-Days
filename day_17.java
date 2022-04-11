import java.util.LinkedList;

public class day_17 {
    
    public int orangesRotting(int[][] grid) {
        int n = grid.length, m = grid[0].length;

        LinkedList<Integer> que = new LinkedList<>();
        int time = 0, freshOrangesCount = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(grid[i][j] == 1){
                    freshOrangesCount++;
                } else if(grid[i][j] == 2){
                    que.add(i * m + j);
                }
            }
        }

        if(freshOrangesCount == 0)
            return 0;


        int[][] dir = {
            {0, 1}, 
            {0 , -1},
            {1, 0},
            {-1, 0}
        };

        while (que.size() != 0) {
            int size = que.size();

            while (size-- > 0) {
                int idx = que.removeFirst();
                int i = idx / m, j = idx % m;

                for (int  d = 0; d < dir.length; d++) {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];

                    if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1){
                        if(--freshOrangesCount == 0){
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
}
