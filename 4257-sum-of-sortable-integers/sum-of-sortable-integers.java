
public class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int sum = 0;
        
        // 1. Get the target sorted array
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        
        // 2. Iterate through all possible factors k of n
        for (int k = 1; k <= n; k++) {
            if (n % k == 0) {
                if (canSortWithBlockSize(nums, sortedNums, k)) {
                    sum += k;
                }
            }
        }
        
        return sum;
    }
    
    private boolean canSortWithBlockSize(int[] nums, int[] sortedNums, int k) {
        int n = nums.length;
        
        // Check every block of size k
        for (int i = 0; i < n; i += k) {
            // Check if nums[i ... i+k-1] can be cyclically shifted to match sortedNums[i ... i+k-1]
            if (!isCyclicShift(nums, sortedNums, i, k)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isCyclicShift(int[] nums, int[] sortedNums, int start, int k) {
        // Double the target block to check for a cyclic shift match
        int[] doubledTarget = new int[2 * k];
        for (int i = 0; i < k; i++) {
            doubledTarget[i] = sortedNums[start + i];
            doubledTarget[i + k] = sortedNums[start + i];
        }
        
        // Find if nums[start ... start+k-1] exists as a contiguous subarray in doubledTarget
        for (int shift = 0; shift <= k; shift++) {
            boolean match = true;
            for (int i = 0; i < k; i++) {
                if (nums[start + i] != doubledTarget[shift + i]) {
                    match = false;
                    break;
                }
            }
            if (match) return true;
        }
        
        return false;
    }
}
