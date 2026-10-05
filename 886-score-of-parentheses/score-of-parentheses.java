class Solution {
    public int scoreOfParentheses(String s) {
        int ans = 0;
        int d = 0; // Tracks the current nesting depth

        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                ++d; // Step inside a layer
            } else {
                --d; // Step outside a layer
                // If it forms a primitive "()" group, compute its score contribution
                if (s.charAt(i - 1) == '(') {
                    ans += 1 << d; // 1 << d evaluates to 2^d
                }
            }
        }
        return ans;
    }
}
