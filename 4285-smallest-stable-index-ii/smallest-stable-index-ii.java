
class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        
        // Array to store the minimum value from index i to n-1
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.min(right[i + 1], nums[i]);
        }
        
        // Variable to track the running maximum from index 0 to i
        int left = Integer.MIN_VALUE; 
        for (int i = 0; i < n; i++) {
            left = Math.max(left, nums[i]);
            
            // Check if the current instability score is stable
            if (left - right[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}
