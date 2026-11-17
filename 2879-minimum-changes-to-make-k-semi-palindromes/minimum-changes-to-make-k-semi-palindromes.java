class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        
        // 1. Precompute proper divisors for every possible substring length
        List<Integer>[] divisors = new ArrayList[n + 1];
        for (int len = 1; len <= n; len++) {
            divisors[len] = new ArrayList<>();
            for (int d = 1; d < len; d++) {
                if (len % d == 0) {
                    divisors[len].add(d);
                }
            }
        }

        // 2. Precompute minimum changes to make s[i..j] a semi-palindrome
        int INF = 1_000_000;
        int[][] cost = new int[n][n];
        for (int[] row : cost) {
            Arrays.fill(row, INF);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) { // Substring length must be >= 2
                int len = j - i + 1;
                int minChanges = INF;
                
                for (int d : divisors[len]) {
                    int changes = 0;
                    // Check each of the d modulo groups independently
                    for (int rem = 0; rem < d; rem++) {
                        int left = i + rem;
                        int right = j - (d - 1 - rem);
                        while (left < right) {
                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }
                            left += d;
                            right -= d;
                        }
                    }
                    minChanges = Math.min(minChanges, changes);
                }
                cost[i][j] = minChanges;
            }
        }

        // 3. DP: dp[i][p] = min changes for prefix of length i split into p parts
        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            for (int i = 2 * p; i <= n; i++) { // Each partition needs at least 2 chars
                for (int j = 2 * (p - 1); j <= i - 2; j++) {
                    if (dp[j][p - 1] != INF && cost[j][i - 1] != INF) {
                        dp[i][p] = Math.min(dp[i][p], dp[j][p - 1] + cost[j][i - 1]);
                    }
                }
            }
        }

        return dp[n][k];
    }
}
