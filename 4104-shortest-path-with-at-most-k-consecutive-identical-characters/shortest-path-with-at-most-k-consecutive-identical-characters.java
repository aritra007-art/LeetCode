
class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // 1. Build the adjacency list for the graph
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj.get(u).add(new int[]{v, w});
        }

        // 2. Initialize the 2D distance matrix: dist[node][consec]
        // consec ranges from 1 to k. We use size (k + 1) for easier indexing.
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // 3. Min-Heap Priority Queue sorted by distance
        // Elements in PQ: {current_distance, current_node, consecutive_count}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Base case: Start at node 0, initial consecutive count is 1
        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0];
            int u = curr[1];
            int consec = curr[2];

            // If we found a shorter path to this state already, skip it
            if (d > dist[u][consec]) {
                continue;
            }

            // If we reached the target node, we can return immediately (Dijkstra property)
            if (u == n - 1) {
                return d;
            }

            // 4. Traverse neighbors
            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int w = neighbor[1];

                int nextConsec = (labels.charAt(u) == labels.charAt(v)) ? consec + 1 : 1;

                // Only proceed if the next state doesn't violate the K-consecutive constraint
                if (nextConsec <= k) {
                    if (d + w < dist[v][nextConsec]) {
                        dist[v][nextConsec] = d + w;
                        pq.offer(new int[]{dist[v][nextConsec], v, nextConsec});
                    }
                }
            }
        }

        // 5. If node n-1 is unreachable within the given constraints
        return -1;
    }
}
