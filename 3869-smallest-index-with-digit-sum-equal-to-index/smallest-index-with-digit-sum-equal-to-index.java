class Solution {
    public int smallestIndex(int[] nums) {
        // Iterate through the array from left to right
        for (int i = 0; i < nums.length; ++i) {
            if (getDigitSum(nums[i]) == i) {
                return i; // Return immediately on the first match
            }
        }
        return -1; // Return -1 if no such index exists
    }

    // Helper method to compute the sum of digits of a number
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10; // Extract the last digit
            num /= 10;       // Remove the last digit
        }
        return sum;
    }
}
