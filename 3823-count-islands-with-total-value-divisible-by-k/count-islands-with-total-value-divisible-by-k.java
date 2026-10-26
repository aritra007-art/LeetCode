
class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If we find an unvisited land cell, explore the entire island
                if (grid[i][j] > 0) {
                    long islandSum = dfs(grid, i, j, m, n);
                    // Check if the total sum of the island is divisible by k
                    if (islandSum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private long dfs(int[][] grid, int r, int c, int m, int n) {
        // Base case: check grid boundaries and if the cell is water (0)
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 0) {
            return 0;
        }

        // Add the current cell's value and sink the land to mark it visited
        long currentVal = grid[r][c];
        grid[r][c] = 0;

        // Explore 4-directional neighbors
        long sum = currentVal;
        sum += dfs(grid, r + 1, c, m, n); // Down
        sum += dfs(grid, r - 1, c, m, n); // Up
        sum += dfs(grid, r, c + 1, m, n); // Right
        sum += dfs(grid, r, c - 1, m, n); // Left

        return sum;
    }
}
