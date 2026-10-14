
public class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // A single digit can be at most 9. If the target sum exceeds 9 * num, 
        // it is mathematically impossible to form such a number.
        if (sum > 9L * num) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        
        // Greedily fill positions from left to right
        for (int i = 0; i < num; i++) {
            if (sum >= 9) {
                sb.append('9');
                sum -= 9;
            } else if (sum > 0) {
                sb.append(sum);
                sum = 0;
            } else {
                sb.append('0');
            }
        }
        
        return sb.toString();
    }
}
