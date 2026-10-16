
public class Solution {
    private static final int MOD = 1_000_000_007;

    // Segment Tree Nodes
    private long[] sum;   // Tracks the sum of L[j]
    private long[] sqSum; // Tracks the sum of L[j]^2
    private long[] lazy;  // Lazy propagation tag

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum = new long[4 * n];
        sqSum = new long[4 * n];
        lazy = new long[4 * n];

        // Track the last seen index of each element.
        // Elements are up to 10^5 based on constraints.
        int[] lastSeen = new int[100001];
        Arrays.fill(lastSeen, -1);

        long totalAns = 0;
        long currentRunningSum = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int prev = lastSeen[x];
            
            // Subarrays starting from (prev + 1) to i will have their distinct count incremented by 1
            int left = prev + 1;
            int right = i;

            // Update the segment tree and return the increment to add to the running sum of squares
            currentRunningSum = (currentRunningSum + update(1, 0, n - 1, left, right, 1)) % MOD;
            totalAns = (totalAns + currentRunningSum) % MOD;
            
            lastSeen[x] = i;
        }

        return (int) (totalAns + MOD) % MOD;
    }

    private void pushDown(int node, int start, int end) {
        if (lazy[node] == 0) return;

        long val = lazy[node];
        int mid = start + (end - start) / 2;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;

        // Apply lazy value to left child
        long leftLen = mid - start + 1;
        sqSum[leftChild] = (sqSum[leftChild] + 2 * val * sum[leftChild] % MOD + leftLen * val % MOD * val % MOD) % MOD;
        sum[leftChild] = (sum[leftChild] + leftLen * val) % MOD;
        lazy[leftChild] = (lazy[leftChild] + val) % MOD;

        // Apply lazy value to right child
        long rightLen = end - mid;
        sqSum[rightChild] = (sqSum[rightChild] + 2 * val * sum[rightChild] % MOD + rightLen * val % MOD * val % MOD) % MOD;
        sum[rightChild] = (sum[rightChild] + rightLen * val) % MOD;
        lazy[rightChild] = (lazy[rightChild] + val) % MOD;

        lazy[node] = 0;
    }

    private void pushUp(int node) {
        sum[node] = (sum[2 * node] + sum[2 * node + 1]) % MOD;
        sqSum[node] = (sqSum[2 * node] + sqSum[2 * node + 1]) % MOD;
    }

    private long update(int node, int start, int end, int l, int r, long val) {
        if (r < start || end < l) {
            return 0;
        }

        if (l <= start && end <= r) {
            long len = end - start + 1;
            long oldSqSum = sqSum[node];
            
            // (L + val)^2 = L^2 + 2*val*L + val^2
            sqSum[node] = (sqSum[node] + 2 * val * sum[node] % MOD + len * val % MOD * val % MOD) % MOD;
            sum[node] = (sum[node] + len * val) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
            
            return (sqSum[node] - oldSqSum + MOD) % MOD;
        }

        pushDown(node, start, end);
        int mid = start + (end - start) / 2;
        
        long leftChange = update(2 * node, start, mid, l, r, val);
        long rightChange = update(2 * node + 1, mid + 1, end, l, r, val);
        
        pushUp(node);
        return (leftChange + rightChange) % MOD;
    }
}
