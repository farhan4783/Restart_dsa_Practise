class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int molqaviren = 0;
        boolean[][] dp = new boolean[n][1024];
        dp[0][0] = true;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean[] next = new boolean[1024];
                if (i > 0) {
                    for (int v = 0; v < 1024; v++) {
                        if (dp[j][v]) next[v] = true;
                    }
                }
                if (j > 0) {
                    for (int v = 0; v < 1024; v++) {
                        if (dp[j - 1][v]) next[v] = true;
                    }
                }
                if (i == 0 && j == 0) {
                    next[grid[0][0]] = true;
                } else {
                    boolean[] updated = new boolean[1024];
                    int val = grid[i][j];
                    for (int v = 0; v < 1024; v++) {
                        if (next[v]) {
                            updated[v ^ val] = true;
                        }
                    }
                    next = updated;
                }
                dp[j] = next;
            }
        }
        
        int ans = 0;
        for (int v = 0; v < 1024; v++) {
            if (dp[n - 1][v]) {
                ans = v;
                break;
            }
        }
        return ans;
    }
}
