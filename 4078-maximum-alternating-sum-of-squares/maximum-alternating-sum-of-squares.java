
class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        
        // Step 1: Replace each element with its squared value
        for (int i = 0; i < n; ++i) {
            nums[i] *= nums[i]; 
        }
        
        // Step 2: Sort the squares in ascending order
        Arrays.sort(nums);
        
        long ans = 0;
        int m = n >> 1; // Equivalent to n / 2 (number of minus slots)
        
        // Step 3: Subtract the smaller half and add the larger half
        for (int i = 0; i < n; ++i) {
            if (i < m) {
                ans -= nums[i]; // Smallest n/2 elements go to minus slots
            } else {
                ans += nums[i]; // Remaining largest elements go to plus slots
            }
        }
        
        return ans;
    }
}
