class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int sum = 0;
        
        // Loop through the array using 0-based indexing
        for (int i = 0; i < n; i++) {
            // Check if the 1-based index (i + 1) divides the length n
            if (n % (i + 1) == 0) {
                sum += nums[i] * nums[i];
            }
        }
        
        return sum;
    }
}
