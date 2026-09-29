class Solution {
    private int m, n;
    private char[][] grid;
    private boolean[][][] visited;

    public boolean hasValidPath(char[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;

        // 1. Base check: The length of the path from (0,0) to (m-1, n-1) is m + n - 1.
        // A valid parenthesis string must have an even length.
        if ((m + n - 1) % 2 != 0) return false;

        // 2. Base check: Path cannot start with ')' or end with '('
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;

        // 3. Max possible open brackets can't exceed path length, but let's bound it safely by m + n
        this.visited = new boolean[m][n][m + n];

        return dfs(0, 0, 0);
    }

    private boolean dfs(int r, int c, int balance) {
        // Accumulate balance based on the current cell
        balance += (grid[r][c] == '(') ? 1 : -1;

        // If balance goes negative, it means a closing bracket has no opening pair
        // If balance exceeds the remaining steps left in the grid, we can't close them all
        if (balance < 0 || balance > (m - r + n - c)) return false;

        // Base Case: If we reach the bottom-right corner, check if all brackets match up
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        // If we've already evaluated this exact sub-problem, skip it
        if (visited[r][c][balance]) return false;
        visited[r][c][balance] = true;

        // Move Right
        if (c + 1 < n && dfs(r, c + 1, balance)) {
            return true;
        }

        // Move Down
        if (r + 1 < m && dfs(r + 1, c, balance)) {
            return true;
        }

        return false;
    }
}
