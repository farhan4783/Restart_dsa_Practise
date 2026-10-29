import java.util.*;

class Solution {
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        int[] parent = new int[n];
        int[] depth = new int[n];
        int[] sz = new int[n];
        int[] heavy = new int[n];
        Arrays.fill(heavy, -1);

       
        int[] bfsOrder = new int[n];
        int headQueue = 0, tailQueue = 0;
        boolean[] visited = new boolean[n];
        
        bfsOrder[tailQueue++] = 0;
        visited[0] = true;
        parent[0] = -1;

        while (headQueue < tailQueue) {
            int u = bfsOrder[headQueue++];
            for (int v : adj[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    parent[v] = u;
                    depth[v] = depth[u] + 1;
                    bfsOrder[tailQueue++] = v;
                }
            }
        }

       
        Arrays.fill(sz, 1);
        for (int i = n - 1; i >= 0; i--) {
            int u = bfsOrder[i];
            int maxSz = 0;
            for (int v : adj[u]) {
                if (v != parent[u]) {
                    sz[u] += sz[v];
                    if (sz[v] > maxSz) {
                        maxSz = sz[v];
                        heavy[u] = v;
                    }
                }
            }
        }

       
        int[] head = new int[n];
        int[] pos = new int[n];
        int curPos = 0;

        int[] stU = new int[n];
        int[] stH = new int[n];
        int stSize = 0;
        stU[stSize] = 0;
        stH[stSize] = 0;
        stSize++;

        while (stSize > 0) {
            stSize--;
            int u = stU[stSize];
            int h = stH[stSize];
            
            head[u] = h;
            pos[u] = curPos++;
            
            if (heavy[u] != -1) {
                for (int v : adj[u]) {
                    if (v != parent[u] && v != heavy[u]) {
                        stU[stSize] = v;
                        stH[stSize] = v;
                        stSize++;
                    }
                }
                stU[stSize] = heavy[u];
                stH[stSize] = h;
                stSize++;
            } else {
                for (int v : adj[u]) {
                    if (v != parent[u]) {
                        stU[stSize] = v;
                        stH[stSize] = v;
                        stSize++;
                    }
                }
            }
        }

       
        char[] charArr = s.toCharArray();
        int[] treeVals = new int[n];
        for (int i = 0; i < n; i++) {
            treeVals[pos[i]] = 1 << (charArr[i] - 'a');
        }

        int[] tree = new int[4 * n];
        buildTree(1, 0, n - 1, treeVals, tree);

        List<Boolean> ans = new ArrayList<>();

       
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                charArr[u] = c;
                int newVal = 1 << (c - 'a');
                updateTree(1, 0, n - 1, pos[u], newVal, tree);
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int pathXor = 0;
                
                while (head[u] != head[v]) {
                    if (depth[head[u]] < depth[head[v]]) {
                        int temp = u; u = v; v = temp;
                    }
                    pathXor ^= queryTree(1, 0, n - 1, pos[head[u]], pos[u], tree);
                    u = parent[head[u]];
                }
                if (depth[u] > depth[v]) {
                    int temp = u; u = v; v = temp;
                }
                pathXor ^= queryTree(1, 0, n - 1, pos[u], pos[v], tree);

                
                if (pathXor == 0 || (pathXor & (pathXor - 1)) == 0) {
                    ans.add(true);
                } else {
                    ans.add(false);
                }
            }
        }
        return ans;
    }

    private void buildTree(int node, int l, int r, int[] vals, int[] tree) {
        if (l == r) {
            tree[node] = vals[l];
            return;
        }
        int mid = (l + r) / 2;
        buildTree(2 * node, l, mid, vals, tree);
        buildTree(2 * node + 1, mid + 1, r, vals, tree);
        tree[node] = tree[2 * node] ^ tree[2 * node + 1];
    }

    private void updateTree(int node, int l, int r, int idx, int val, int[] tree) {
        if (l == r) {
            tree[node] = val;
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) {
            updateTree(2 * node, l, mid, idx, val, tree);
        } else {
            updateTree(2 * node + 1, mid + 1, r, idx, val, tree);
        }
        tree[node] = tree[2 * node] ^ tree[2 * node + 1];
    }

    private int queryTree(int node, int l, int r, int ql, int qr, int[] qdTree) {
        if (ql > r || qr < l) {
            return 0;
        }
        if (ql <= l && r <= qr) {
            return qdTree[node];
        }
        int mid = (l + r) / 2;
        return queryTree(2 * node, l, mid, ql, qr, qdTree) ^ queryTree(2 * node + 1, mid + 1, r, ql, qr, qdTree);
    }
}
