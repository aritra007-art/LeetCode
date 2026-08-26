class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String ans = "";
        
        for (int i = 0; i < n; ++i) {
            for (int j = i + k; j <= n; ++j) {
                String t = s.substring(i, j);
                int cnt = 0;
                for (char c : t.toCharArray()) {
                    cnt += c - '0';
                }
                
                // Check if it has exactly k ones
                if (cnt == k) {
                    if ("".equals(ans) || j - i < ans.length() || 
                        (j - i == ans.length() && t.compareTo(ans) < 0)) {
                        ans = t;
                    }
                }
            }
        }
        return ans;
    }
}
