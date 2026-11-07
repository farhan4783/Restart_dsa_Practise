import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];
        
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        
        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[] { servers[i], i });
        }
        
        PriorityQueue<long[]> busyServers = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        
        long currentTime = 0;
        for (int j = 0; j < m; j++) {
            currentTime = Math.max(currentTime, j);
            
            if (freeServers.isEmpty()) {
                currentTime = Math.max(currentTime, busyServers.peek()[0]);
            }
            
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                long[] busy = busyServers.poll();
                int idx = (int) busy[1];
                int weight = (int) busy[2];
                freeServers.offer(new int[] { weight, idx });
            }
            
            int[] server = freeServers.poll();
            ans[j] = server[1];
            busyServers.offer(new long[] { currentTime + tasks[j], server[1], server[0] });
        }
        
        return ans;
    }
}
