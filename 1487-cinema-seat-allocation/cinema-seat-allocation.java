class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMap = new HashMap<>();
        
        // Map seats using 0-indexing layout (seat - 1)
        for (int[] r : reservedSeats) {
            int row = r[0];
            int seat = r[1];
            rowMap.put(row, rowMap.getOrDefault(row, 0) | (1 << (seat - 1)));
        }
        
        // Treat completely empty rows as hosting 2 groups each
        int count = (n - rowMap.size()) * 2;
        
        // Explicit bitmasks based on 0-indexing layout:
        // Seats 2,3,4,5 -> indices 1,2,3,4   -> 0b0000011110
        // Seats 6,7,8,9 -> indices 5,6,7,8   -> 0b0111100000
        // Seats 4,5,6,7 -> indices 3,4,5,6   -> 0b0001111000
        int leftMask = 0b0000011110; 
        int rightMask = 0b0111100000;
        int middleMask = 0b0001111000;
        
        for (int mask : rowMap.values()) {
            boolean leftFree = (mask & leftMask) == 0;
            boolean rightFree = (mask & rightMask) == 0;
            boolean middleFree = (mask & middleMask) == 0;
            
            if (leftFree && rightFree) {
                count += 2; // Both left and right blocks are valid
            } else if (leftFree || rightFree || middleFree) {
                count += 1; // Only one block can be occupied
            }
        }
        
        return count;
    }
}
