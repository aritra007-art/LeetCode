class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startR = 0, startC = 0;
        int litterCount = 0;
        int[][] litterId = new int[m][n];
        
        // Map each litter 'L' to a unique bit index
        for (int i = 0; i < m; i++) {
            Arrays.fill(litterId[i], -1);
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                if (ch == 'S') {
                    startR = i;
                    startC = j;
                } else if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }
        
        // Target mask when all litter pieces are collected
        int targetMask = (1 << litterCount) - 1;
        if (targetMask == 0) return 0;
        
        // Visited array tracker: [row][col][remaining_energy][litter_mask]
        boolean[][][][] visited = new boolean[m][n][energy + 1][1 << litterCount];
        
        // Queue elements: {row, col, remaining_energy, current_mask}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startR, startC, energy, 0});
        visited[startR][startC][energy][0] = true;
        
        int moves = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                int e = curr[2];
                int mask = curr[3];
                
                // Explore 4 directions
                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    
                    // Out of bounds check or hitting obstacle 'X'
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n || classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }
                    
                    int nextEnergy = e - 1;
                    if (nextEnergy < 0) continue; // Out of juice
                    
                    char nextCell = classroom[nr].charAt(nc);
                    
                    // Re-calculate energy if it's a reset station
                    if (nextCell == 'R') {
                        nextEnergy = energy;
                    }
                    
                    // Update litter bitmask if landing on uncollected litter
                    int nextMask = mask;
                    if (nextCell == 'L' && litterId[nr][nc] != -1) {
                        nextMask |= (1 << litterId[nr][nc]);
                    }
                    
                    // Goal check
                    if (nextMask == targetMask) {
                        return moves + 1;
                    }
                    
                    // Standard BFS state deduping
                    if (!visited[nr][nc][nextEnergy][nextMask]) {
                        visited[nr][nc][nextEnergy][nextMask] = true;
                        queue.offer(new int[]{nr, nc, nextEnergy, nextMask});
                    }
                }
            }
            moves++;
        }
        
        return -1;
    }
}
