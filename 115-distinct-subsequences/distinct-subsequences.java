
class Solution {
    public int numDistinct(String s, String t) {
        // Standard signature is numDistinct(String s, String t)
        // Correct implementation below:
        int n = t.length();
        int[] dp = new int[n + 1];
        dp[0] = 1; // Base case: an empty string 't' has 1 match

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            // Traverse backwards to avoid overwriting values needed from the previous state
            for (int j = n; j >= 1; j--) {
                char c2 = t.charAt(j - 1);
                if (c1 == c2) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[n];
    }
}
