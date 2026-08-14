class Solution {
    public int rob(int[] nums) {
        int sum = 0;
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];

        int rob2 = 0; // max money from two houses ago
        int rob1 = 0; // max money from previous house

        for (int num : nums) {
            int current = Math.max(rob1, rob2 + num);
            rob2 = rob1;
            rob1 = current;
        }

        return rob1;
    }
}