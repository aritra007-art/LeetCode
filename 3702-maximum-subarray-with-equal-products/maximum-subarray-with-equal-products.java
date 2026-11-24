class Solution {
    public int maxLength(int[] nums) {
        int maxVal = 0;
        int totalLcm = 1;
        
        // Calculate the maximum possible LCM * GCD upper bound
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
            totalLcm = lcm(totalLcm, num);
        }
        int maxPossibleProduct = totalLcm * maxVal;
        
        int n = nums.length;
        int maxLen = 0;
        
        for (int i = 0; i < n; i++) {
            int prod = 1;
            int g = 0;
            int l = 1;
            
            for (int j = i; j < n; j++) {
                prod *= nums[j];
                
                // Prune early: once prod exceeds the max possible (LCM * GCD),
                // it can never match again since prod only grows or stays the same.
                if (prod > maxPossibleProduct) {
                    break;
                }
                
                g = gcd(g, nums[j]);
                l = lcm(l, nums[j]);
                
                if (prod == g * l) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        
        return maxLen;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }
}
