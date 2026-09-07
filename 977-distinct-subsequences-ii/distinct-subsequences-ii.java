class Solution {
    public int distinctSubseqII(String s) {
        int mod = 1_000_000_007;
        int[] dp = new int[26];
        int total = 0;

        for (char c : s.toCharArray()) {
            int index = c - 'a';
            int add = (total + 1 - dp[index] + mod) % mod;
            total = (total + add) % mod;
            dp[index] = (dp[index] + add) % mod;
        }

        return total;
    }
}
