class Solution {
    public int maxSubArray(int[] nums) {
        int max = nums[0], cur = 0; for (int n : nums) max = Math.max(max, cur = Math.max(n, cur + n)); return max;

    }
}