class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int i = 0, j = 0, cnt = 0;
        int n = s.length();
        String ans = "";
        
        while (j < n) {
            cnt += s.charAt(j) - '0';
            
            
            while (cnt > k || (i < j && s.charAt(i) == '0')) {
                cnt -= s.charAt(i) - '0';
                ++i;
            }
            
            if (cnt == k) {
                String t = s.substring(i, j + 1);
                if ("".equals(ans) || t.length() < ans.length() || 
                    (t.length() == ans.length() && t.compareTo(ans) < 0)) {
                    ans = t;
                }
            }
            ++j;
        }
        
        return ans;
    }
}
