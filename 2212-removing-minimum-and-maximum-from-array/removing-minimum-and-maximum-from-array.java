class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }

        int minIdx = 0;
        int maxIdx = 0;

        // Step 1: Find the indices of the minimum and maximum elements
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        // Step 2: Establish leftmost and rightmost boundaries
        int left = Math.min(minIdx, maxIdx);
        int right = Math.max(minIdx, maxIdx);

        // Step 3: Calculate cost for the three different strategies
        int removeFromFront = right + 1;
        int removeFromBack = n - left;
        int removeFromBoth = (left + 1) + (n - right);

        // Return the minimum deletions among all three cases
        return Math.min(removeFromFront, Math.min(removeFromBack, removeFromBoth));
    }
}
