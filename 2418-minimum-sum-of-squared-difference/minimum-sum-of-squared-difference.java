
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        
        // The maximum possible difference given constraints is 100,000
        int[] freq = new int[100_002]; 
        long totalDiff = 0;
        int maxDiff = 0;
        
        // Step 1: Calculate absolute differences and populate the frequency array
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            if (diff > 0) {
                freq[diff]++;
                totalDiff += diff;
                maxDiff = Math.max(maxDiff, diff);
            }
        }
        
        // Edge Case: If our total budget k can reduce all gaps to 0, return 0
        if (totalDiff <= k) {
            return 0;
        }
        
        // Step 2: Greedily reduce the largest differences from maxDiff down to 1
        for (int d = maxDiff; d > 0 && k > 0; d--) {
            if (freq[d] > 0) {
                // Determine how many elements we can afford to demote from 'd' to 'd - 1'
                long take = Math.min(k, (long) freq[d]);
                
                freq[d] -= take;
                freq[d - 1] += take;
                k -= take;
            }
        }
        
        // Step 3: Compute the final minimum sum of squared differences
        long minSumSquare = 0;
        for (int d = 1; d <= maxDiff; d++) {
            if (freq[d] > 0) {
                minSumSquare += (long) freq[d] * d * d;
            }
        }
        
        return minSumSquare;
    }
}
