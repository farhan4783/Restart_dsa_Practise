import java.util.PriorityQueue;

class Solution {
    private int[][] fMax;
    private int[][] fMin;
    private int[] lg;

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        lg = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            lg[i] = lg[i >> 1] + 1;
        }

        int maxLog = lg[n] + 1;
        fMax = new int[n][maxLog];
        fMin = new int[n][maxLog];

        for (int i = 0; i < n; i++) {
            fMax[i][0] = nums[i];
            fMin[i][0] = nums[i];
        }

        for (int j = 1; j < maxLog; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                fMax[i][j] = Math.max(fMax[i][j - 1], fMax[i + (1 << (j - 1))][j - 1]);
                fMin[i][j] = Math.min(fMin[i][j - 1], fMin[i + (1 << (j - 1))][j - 1]);
            }
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(b[0], a[0]));

        for (int l = 0; l < n; l++) {
            long val = (long) queryMax(l, n - 1) - queryMin(l, n - 1);
            pq.offer(new long[]{val, l, n - 1});
        }

        long ans = 0;
        for (int i = 0; i < k; i++) {
            long[] curr = pq.poll();
            long val = curr[0];
            int l = (int) curr[1];
            int r = (int) curr[2];

            ans += val;

            if (r > l) {
                long nextVal = (long) queryMax(l, r - 1) - queryMin(l, r - 1);
                pq.offer(new long[]{nextVal, l, r - 1});
            }
        }

        return ans;
    }

    private int queryMax(int l, int r) {
        int len = r - l + 1;
        int j = lg[len];
        return Math.max(fMax[l][j], fMax[r - (1 << j) + 1][j]);
    }

    private int queryMin(int l, int r) {
        int len = r - l + 1;
        int j = lg[len];
        return Math.min(fMin[l][j], fMin[r - (1 << j) + 1][j]);
    }
}
