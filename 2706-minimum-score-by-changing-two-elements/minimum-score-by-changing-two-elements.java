
class Solution {
    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        
        // Option 1: Change the two smallest elements
        int changeTwoSmallest = nums[n - 1] - nums[2];
        
        // Option 2: Change the two largest elements
        int changeTwoLargest = nums[n - 3] - nums[0];
        
        // Option 3: Change one smallest and one largest element
        int changeOneEach = nums[n - 2] - nums[1];
        
        return Math.min(changeTwoSmallest, Math.min(changeTwoLargest, changeOneEach));
    }
}
