public class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] counts = new int[26];
        
        // Count frequencies of each character in s
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }
        
        // Step 1: Match target prefix as much as possible
        int matchLen = 0;
        int[] tempCounts = counts.clone();
        for (int i = 0; i < n; i++) {
            int idx = target.charAt(i) - 'a';
            if (tempCounts[idx] > 0) {
                tempCounts[idx]--;
                matchLen++;
            } else {
                break;
            }
        }
        
        // Step 2: Backtrack from the matched prefix length to find the branch point
        for (int i = matchLen; i >= 0; i--) {
            // Reconstruct the frequency array up to current index i
            int[] currentCounts = counts.clone();
            for (int j = 0; j < i; j++) {
                currentCounts[target.charAt(j) - 'a']--;
            }
            
            // If we are at the end, we can't place a strictly greater character here
            if (i == n) continue;
            
            // Try to find a character strictly greater than target.charAt(i)
            int targetCharIdx = target.charAt(i) - 'a';
            int replacementIdx = -1;
            for (int c = targetCharIdx + 1; c < 26; c++) {
                if (currentCounts[c] > 0) {
                    replacementIdx = c;
                    break;
                }
            }
            
            // If a replacement character is found, build the result
            if (replacementIdx != -1) {
                StringBuilder sb = new StringBuilder();
                
                // Append the common prefix matching target
                for (int j = 0; j < i; j++) {
                    sb.append(target.charAt(j));
                }
                
                // Append the strictly greater character
                sb.append((char) ('a' + replacementIdx));
                currentCounts[replacementIdx]--;
                
                
                for (int c = 0; c < 26; c++) {
                    while (currentCounts[c] > 0) {
                        sb.append((char) ('a' + c));
                        currentCounts[c]--;
                    }
                }
                return sb.toString();
            }
        }
        
        return ""; 
    }
}
