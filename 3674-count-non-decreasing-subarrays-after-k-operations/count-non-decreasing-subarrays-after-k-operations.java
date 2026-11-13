class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        long ans = 0;
        long cost = 0;
        // Stores indices of elements in non-increasing order of their values
        Deque<Integer> dq = new ArrayDeque<>();
        int j = nums.length - 1;

        for (int i = nums.length - 1; i >= 0; i--) {
            // Merge smaller peaks to the right into the new element nums[i]
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                int l = dq.pollLast();
                int r = dq.isEmpty() ? j + 1 : dq.peekLast();
                cost += (long) (r - l) * (nums[i] - nums[l]);
            }
            dq.offerLast(i);

            // Shrink the window from the right if operations exceed k
            while (cost > k) {
                cost -= nums[dq.peekFirst()] - nums[j];
                if (dq.peekFirst() == j) {
                    dq.pollFirst();
                }
                j--;
            }

            // All subarrays starting at i and ending between i and j are valid
            ans += j - i + 1;
        }

        return ans;
    }
}
