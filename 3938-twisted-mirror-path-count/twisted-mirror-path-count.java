
public class Solution {
    private int m, n;
    private int[][] grid;
    private int[][][] memo;
    private final int MOD = 1_000_000_007;

    // Directions: 0 = Moving Right, 1 = Moving Down
    public int uniquePaths(int[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        
        // If the start or end cell itself is invalidly blocked or out of structuralbounds
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return 0;
        }

        // memo[r][c][dir] -> stores the number of paths from (r, c) given we entered it facing 'dir'
        memo = new int[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        // We start at (0, 0). From the start, we can choose to go either Right or Down.
        // We simulate moving to the next adjacent cell directly.
        long totalPaths = 0;
        
        // Try moving Right to (0, 1)
        totalPaths = (totalPaths + solve(0, 1, 0)) % MOD;
        
        // Try moving Down to (1, 0)
        totalPaths = (totalPaths + solve(1, 0, 1)) % MOD;

        return (int) totalPaths;
    }

    private int solve(int r, int c, int dir) {
        // 1. Boundary check: Out of bounds means this path fails
        if (r < 0 || r >= m || c < 0 || c >= n) {
            return 0;
        }

        // 2. Base Case: Safely reached the destination
        if (r == m - 1 && c == n - 1) {
            // Note: problem states if destination is a mirror, it's checked beforehand, 
            // but we already verified grid[m-1][n-1] == 0 in the main method.
            return 1;
        }

        // 3. Return cached result if already calculated
        if (memo[r][c][dir] != -1) {
            return memo[r][c][dir];
        }

        // 4. Handle Mirror Cells (1) -> Deterministic continuous deflection
        if (grid[r][c] == 1) {
            int nextAns;
            if (dir == 0) {
                // Entered moving Right -> Deflected DOWN to (r + 1, c)
                nextAns = solve(r + 1, c, 1);
            } else {
                // Entered moving Down -> Deflected RIGHT to (r, c + 1)
                nextAns = solve(r, c + 1, 0);
            }
            memo[r][c][dir] = nextAns;
            return nextAns;
        }

        // 5. Handle Empty Cells (0) -> Standard choice of moving Right or Down
        long paths = 0;
        // Move Right -> Next cell is (r, c + 1), direction is Right (0)
        paths = (paths + solve(r, c + 1, 0)) % MOD;
        // Move Down -> Next cell is (r + 1, c), direction is Down (1)
        paths = (paths + solve(r + 1, c, 1)) % MOD;

        memo[r][c][dir] = (int) paths;
        return memo[r][c][dir];
    }
}
