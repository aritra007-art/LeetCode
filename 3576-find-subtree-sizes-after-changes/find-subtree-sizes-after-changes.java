
class Solution {
    private List<Integer>[] adj;
    private int[] ans;
    private int[] lastSeen;
    private String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        this.ans = new int[n];
        this.lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        // Build adjacency list from parent array
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        // Start DFS from root (node 0)
        dfs(0, -1);
        return ans;
    }

    private void dfs(int node, int currParent) {
        int charIdx = s.charAt(node) - 'a';
        
        // Save the previous closest ancestor with the same character
        int prevAncestor = lastSeen[charIdx];
        
        // Update closest ancestor for descendants of this node
        lastSeen[charIdx] = node;
        
        // Initialize subtree size with the current node itself
        ans[node] = 1;

        for (int child : adj[node]) {
            dfs(child, node);
        }

        // Backtrack: restore the previous ancestor state
        lastSeen[charIdx] = prevAncestor;

        // Determine the final parent (closest matching ancestor if it exists, else original parent)
        int targetParent = (prevAncestor != -1) ? prevAncestor : currParent;
        if (targetParent != -1) {
            ans[targetParent] += ans[node];
        }
    }
}
