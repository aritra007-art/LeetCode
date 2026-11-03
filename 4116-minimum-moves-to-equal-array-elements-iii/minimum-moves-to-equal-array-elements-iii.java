class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        int maxVal = nums[0];
        int sum = 0;
        
        // Find the maximum value and calculate the sum of the array
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
            sum += num;
        }
        
        // Formula: (max * total elements) - current sum
        return (maxVal * n) - sum;
    }
}
