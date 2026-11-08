class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[] rowCount = new int[m];
        int[] colCount = new int[n];
        
        // Step 1: Count servers in each row and column
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }
        
        int communicableServers = 0;
        
        // Step 2: Count servers that can communicate
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // If there's more than 1 server in its row or column, it can communicate
                    if (rowCount[i] > 1 || colCount[j] > 1) {
                        communicableServers++;
                    }
                }
            }
        }
        
        return communicableServers;
    }
}
