
class Solution {
    static class Node {
        int prod;
        int[] remain;

        Node(int k) {
            this.prod = 1;
            this.remain = new int[k];
        }
    }

    private int n;
    private int K;
    private Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.K = k;
        this.tree = new Node[4 * n];
        
        build(nums, 0, 0, n - 1);

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int xi = queries[i][3];

            // 1. Update the index persistently
            update(0, 0, n - 1, idx, val);

            // 2. Query the subarray starting from 'start' up to 'n - 1'
            Node resNode = query(0, 0, n - 1, start, n - 1);
            
            result[i] = resNode != null ? resNode.remain[xi] : 0;
        }

        return result;
    }

    private void build(int[] nums, int cur, int left, int right) {
        tree[cur] = new Node(K);
        if (left == right) {
            int valMod = nums[left] % K;
            tree[cur].prod = valMod;
            tree[cur].remain[valMod] = 1;
            return;
        }
        int mid = left + (right - left) / 2;
        build(nums, 2 * cur + 1, left, mid);
        build(nums, 2 * cur + 2, mid + 1, right);
        tree[cur] = merge(tree[2 * cur + 1], tree[2 * cur + 2]);
    }

    private void update(int cur, int left, int right, int idx, int val) {
        if (left == right) {
            int valMod = val % K;
            Arrays.fill(tree[cur].remain, 0);
            tree[cur].prod = valMod;
            tree[cur].remain[valMod] = 1;
            return;
        }
        int mid = left + (right - left) / 2;
        if (idx <= mid) {
            update(2 * cur + 1, left, mid, idx, val);
        } else {
            update(2 * cur + 2, mid + 1, right, idx, val);
        }
        tree[cur] = merge(tree[2 * cur + 1], tree[2 * cur + 2]);
    }

    private Node query(int cur, int left, int right, int ql, int qr) {
        if (ql <= left && right <= qr) {
            return tree[cur];
        }
        int mid = left + (right - left) / 2;
        Node leftRes = null, rightRes = null;
        
        if (ql <= mid) {
            leftRes = query(2 * cur + 1, left, mid, ql, qr);
        }
        if (qr > mid) {
            rightRes = query(2 * cur + 2, mid + 1, right, ql, qr);
        }

        if (leftRes == null) return rightRes;
        if (rightRes == null) return leftRes;
        
        return merge(leftRes, rightRes);
    }

    private Node merge(Node left, Node right) {
        Node parent = new Node(K);
        parent.prod = (left.prod * right.prod) % K;

        // Copy prefix counts from the left child directly
        for (int i = 0; i < K; i++) {
            parent.remain[i] = left.remain[i];
        }

        // For the right child, prefix products are multiplied by the total product of the left child
        for (int j = 0; j < K; j++) {
            if (right.remain[j] > 0) {
                int combinedMod = (left.prod * j) % K;
                parent.remain[combinedMod] += right.remain[j];
            }
        }
        
        return parent;
    }
}
