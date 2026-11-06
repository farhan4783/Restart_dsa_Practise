class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[][] qs = new int[m][2];
        for (int i = 0; i < m; i++) {
            qs[i][0] = queries[i];
            qs[i][1] = i;
        }
        Arrays.sort(qs, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        int[] ans = new int[m];
        Map<Integer, Integer> countMap = new HashMap<>();
        int i = 0, j = 0;
        
        for (int[] q : qs) {
            int time = q[0];
            int idx = q[1];
            int left = time - x;
            
            while (i < logs.length && logs[i][1] <= time) {
                countMap.put(logs[i][0], countMap.getOrDefault(logs[i][0], 0) + 1);
                i++;
            }
            
            while (j < logs.length && logs[j][1] < left) {
                int server = logs[j][0];
                countMap.put(server, countMap.get(server) - 1);
                if (countMap.get(server) == 0) {
                    countMap.remove(server);
                }
                j++;
            }
            
            ans[idx] = n - countMap.size();
        }
        
        return ans;
    }
}
