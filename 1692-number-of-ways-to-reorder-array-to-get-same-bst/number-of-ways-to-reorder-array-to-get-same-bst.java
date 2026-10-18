
class Solution {
    private int[][] c;
    private final int MOD = 1_000_000_007;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        c = new int[n][n];
        
        // Step 1: Precompute Combinations using Pascal's Triangle
        for (int i = 0; i < n; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= i; ++j) {
                c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % MOD;
            }
        }

        // Convert array to List for easier sublist partitions
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        // Step 2: Calculate combinations via DFS recursively
        // Subtract 1 at the end to exclude the original configuration
        return (dfs(list) - 1 + MOD) % MOD;
    }

    private int dfs(List<Integer> nums) {
        if (nums.size() <= 2) {
            return 1;
        }

        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        // Group elements into left and right subtrees
        for (int i = 1; i < nums.size(); ++i) {
            int val = nums.get(i);
            if (val < root) {
                left.add(val);
            } else {
                right.add(val);
            }
        }

        // Recursive combinations count for children subtrees
        long leftWays = dfs(left);
        long rightWays = dfs(right);

        // Total available spaces to interleave left and right elements
        int totalSpaces = left.size() + right.size();
        long currentComb = c[totalSpaces][left.size()];

        // Combine using modular arithmetic
        long totalWays = (currentComb * leftWays) % MOD;
        totalWays = (totalWays * rightWays) % MOD;

        return (int) totalWays;
    }
}
