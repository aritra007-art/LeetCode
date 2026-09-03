class Solution {
    public boolean uniformArray(int[] nums1) {
        int mn = Integer.MAX_VALUE;
        
        // Step 1: Find the minimum odd number in the array
        for (int x : nums1) {
            if (x % 2 != 0) { // odd number
                mn = Math.min(mn, x);
            }
        }
        
        // If there are no odd numbers, the array is already uniformly even
        if (mn == Integer.MAX_VALUE) {
            return true;
        }
        
        // Step 2: Check if any even number is smaller than the minimum odd number
        for (int x : nums1) {
            if (x % 2 == 0 && x < mn) {
                return false; 
            }
        }
        
        return true;
    }
}
