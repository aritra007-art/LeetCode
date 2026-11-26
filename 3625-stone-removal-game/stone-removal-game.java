
class Solution {
    public boolean canAliceWin(int n) {
        int stonesToRemove = 10;
        int turns = 0;

        while (n >= stonesToRemove) {
            n -= stonesToRemove;
            stonesToRemove--;
            turns++;
        }

        // Alice goes first (odd turns: 1st, 3rd, 5th...).
        // If total successful turns are odd, Bob couldn't make the next move.
        return turns % 2 == 1;
    }
}

