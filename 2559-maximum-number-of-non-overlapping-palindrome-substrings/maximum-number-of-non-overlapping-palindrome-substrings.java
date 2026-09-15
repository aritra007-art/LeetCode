class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        // dp[i] stores the max non-overlapping palindromes in the substring s[0...i-1]
        int[] dp = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            // By default, carry forward the result from the previous character
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
            
            // Check for odd-length palindromes centering at i
            expand(s, i, i, k, dp);
            
            // Check for even-length palindromes centering between i and i+1
            expand(s, i, i + 1, k, dp);
        }
        
        return dp[n];
    }
    
    private void expand(String s, int left, int right, int k, int[] dp) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            int length = right - left + 1;
            
            // If we found a valid palindrome of length >= k
            if (length >= k) {
                // Update the DP state at the end index of this palindrome
                dp[right + 1] = Math.max(dp[right + 1], dp[left] + 1);
                // Greedy choice: stop expanding further since a longer palindrome 
                // overlapping here would only restrict future matches.
                break;
            }
            left--;
            right++;
        }
    }
}
