class Solution {
    public int totalNumbers(int[] digits) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        int n = digits.length;
        
        // Loop through all possible indices for the hundreds, tens, and units positions
        for (int k = 0; k < n; ++k) {
            // Rule 1: The hundreds digit cannot be zero
            if (digits[k] == 0) {
                continue;
            }
            
            for (int j = 0; j < n; ++j) {
                // Rule 2: Tens index must be distinct from hundreds index
                if (j == k) {
                    continue;
                }
                
                for (int i = 0; i < n; ++i) {
                    // Rule 3: Units index must be completely unique
                    if (i == k || i == j) {
                        continue;
                    }
                    
                    // Rule 4: The final number must be even (units digit is even)
                    if (digits[i] % 2 == 0) {
                        int num = digits[k] * 100 + digits[j] * 10 + digits[i];
                        uniqueNumbers.add(num);
                    }
                }
            }
        }
        
        // The size of the set represents the number of unique 3-digit even numbers
        return uniqueNumbers.size();
    }
}
