class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n];
        int INF = 1000000;
        for (int i = 0; i < n; i++) {
            minLen[i] = INF;
        }

        int left = 0;
        int sum = 0;
        int result = INF;
        int currentMin = INF;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            if (sum == target) {
                int len = right - left + 1;
                if (left > 0 && minLen[left - 1] != INF) {
                    result = Math.min(result, len + minLen[left - 1]);
                }
                currentMin = Math.min(currentMin, len);
            }

            minLen[right] = currentMin;
        }

        return result >= INF ? -1 : result;
    }
}
