
public class Solution {
    private int[] cnt = new int[26];
    private char[] ans;
    private int n;
    private int midKey = -1;
    private String targetStr;

    public String lexPalindromicPermutation(String s, String target) {
        this.n = s.length();
        this.targetStr = target;
        this.ans = new char[n];
        
        // Count frequencies of each character
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        // Validate if a palindrome can be formed
        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 != 0) {
                if (n % 2 == 0 || midKey >= 0) {
                    return ""; // More than one odd frequency character
                }
                midKey = i;
            }
        }

        // Place the middle character if the length is odd
        if (n % 2 != 0) {
            ans[n / 2] = (char) ('a' + midKey);
            cnt[midKey]--;
        }

        // Greedily build the palindrome
        if (backtrack(0, true)) {
            return new String(ans);
        }

        return "";
    }

    private boolean backtrack(int idx, boolean isSamePrefix) {
        // Base case: successfully filled the left half
        if (idx == n / 2) {
            if (!isSamePrefix) return true;
            // If it matches target prefix exactly, check if the whole palindrome is strictly greater
            return new String(ans).compareTo(targetStr) > 0;
        }

        int startChar = isSamePrefix ? (targetStr.charAt(idx) - 'a') : 0;

        for (int c = startChar; c < 26; c++) {
            if (cnt[c] >= 2) {
                cnt[c] -= 2;
                ans[idx] = (char) ('a' + c);
                ans[n - 1 - idx] = (char) ('a' + c);

                boolean nextSamePrefix = isSamePrefix && (c == (targetStr.charAt(idx) - 'a'));
                
                if (backtrack(idx + 1, nextSamePrefix)) {
                    return true;
                }

                // Backtrack
                cnt[c] += 2;
            }
        }

        return false;
    }
}
