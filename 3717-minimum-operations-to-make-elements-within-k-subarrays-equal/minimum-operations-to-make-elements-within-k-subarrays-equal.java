
public class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        
        // Step 1: Precompute costs for every valid sliding window of size x
        long[] windowCost = new long[n - x + 1];
        
        // Two TreeMaps to track numbers smaller/equal to median and greater than median
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();
        int leftSize = 0, rightSize = 0;
        long leftSum = 0, rightSum = 0;
        
        // Helper to balance the sizes of the two halves
        // 'left' will hold (x + 1) / 2 elements, 'right' will hold the rest.
        Runnable balance = () -> {
            int targetLeftSize = (x + 1) / 2;
            // Move from left to right if left is oversized
            // But we must capture variables safely or use fields. 
            // Writing it inside loops directly below for cleaner variable management.
        };

        for (int i = 0; i < n; i++) {
            // Add current element
            if (leftSize == 0 || nums[i] <= left.lastKey()) {
                left.put(nums[i], left.getOrDefault(nums[i], 0) + 1);
                leftSize++;
                leftSum += nums[i];
            } else {
                right.put(nums[i], right.getOrDefault(nums[i], 0) + 1);
                rightSize++;
                rightSum += nums[i];
            }
            
            // Remove outgoing element if window size exceeds x
            if (i >= x) {
                int out = nums[i - x];
                if (left.containsKey(out)) {
                    left.put(out, left.get(out) - 1);
                    if (left.get(out) == 0) left.remove(out);
                    leftSize--;
                    leftSum -= out;
                } else {
                    right.put(out, right.get(out) - 1);
                    if (right.get(out) == 0) right.remove(out);
                    rightSize--;
                    rightSum -= out;
                }
            }
            
            // Balance the multisets
            int targetLeftSize = (x + 1) / 2;
            while (leftSize > targetLeftSize) {
                int val = left.lastKey();
                left.put(val, left.get(val) - 1);
                if (left.get(val) == 0) left.remove(val);
                leftSize--;
                leftSum -= val;
                
                right.put(val, right.getOrDefault(val, 0) + 1);
                rightSize++;
                rightSum += val;
            }
            while (leftSize < targetLeftSize && rightSize > 0) {
                int val = right.firstKey();
                right.put(val, right.get(val) - 1);
                if (right.get(val) == 0) right.remove(val);
                rightSize--;
                rightSum -= val;
                
                left.put(val, left.getOrDefault(val, 0) + 1);
                leftSize++;
                leftSum += val;
            }
            
            // If we have a complete window, calculate its minimum absolute difference cost
            if (i >= x - 1) {
                long median = left.lastKey();
                long currentCost = (median * leftSize - leftSum) + (rightSum - median * rightSize);
                windowCost[i - x + 1] = currentCost;
            }
        }
        
        // Step 2: Dynamic Programming to select k non-overlapping windows
        // dp[j] holds the minimum operations to pick j subarrays
        long[] dp = new long[k + 1];
        Arrays.fill(dp, Long.MAX_VALUE / 2); // Avoid overflow
        dp[0] = 0;
        
        // To build iteratively and handle non-overlapping segments, we process index by index
        long[][] memo = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], Long.MAX_VALUE / 2);
        }
        memo[n][0] = 0;
        
        for (int i = n - 1; i >= 0; i--) {
            memo[i][0] = 0;
            for (int j = 1; j <= k; j++) {
                // Option 1: Skip index i
                memo[i][j] = memo[i + 1][j];
                
                // Option 2: Pick a window starting at index i (if valid)
                if (i <= n - x) {
                    memo[i][j] = Math.min(memo[i][j], windowCost[i] + memo[i + x][j - 1]);
                }
            }
        }
        
        return memo[0][k];
    }
}
