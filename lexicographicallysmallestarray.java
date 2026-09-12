import java.util.*;

class Solution {
    private static class Interval {
        int l, r, weight, id;

        Interval(int l, int r, int weight, int id) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.id = id;
        }
    }

    private static class Result {
        long weight;
        List<Integer> indices;

        Result(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);
            arr[i] = new Interval(interval.get(0), interval.get(1), interval.get(2), i);
        }

    
        Arrays.sort(arr, (a, b) -> Integer.compare(a.l, b.l));

      
        int[] nextIdx = new int[n];
        for (int i = 0; i < n; i++) {
            int target = arr[i].r;
            int low = i + 1, high = n, ans = n;
            while (low < high) {
                int mid = (low + high) >>> 1;
                if (arr[mid].l > target) {
                    ans = mid;
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            nextIdx[i] = ans;
        }

      
        Result[][] dp = new Result[n + 1][5];

      
        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new Result(0, new ArrayList<>());
        }

        for (int i = n - 1; i >= 0; i--) {
            dp[i][0] = new Result(0, new ArrayList<>());

            for (int k = 1; k <= 4; k++) {
               
                Result best = dp[i + 1][k];

             
                Result nextRes = dp[nextIdx[i]][k - 1];
                long takeWeight = arr[i].weight + nextRes.weight;

                List<Integer> takeIndices = new ArrayList<>();
                takeIndices.add(arr[i].id);
                takeIndices.addAll(nextRes.indices);
                Collections.sort(takeIndices);

                Result takeResult = new Result(takeWeight, takeIndices);

            
                if (takeResult.weight > best.weight) {
                    best = takeResult;
                } else if (takeResult.weight == best.weight) {
                    if (isLexicographicallySmaller(takeResult.indices, best.indices)) {
                        best = takeResult;
                    }
                }

                dp[i][k] = best;
            }
        }

       
        List<Integer> resIndices = dp[0][4].indices;
        int[] result = new int[resIndices.size()];
        for (int i = 0; i < resIndices.size(); i++) {
            result[i] = resIndices.get(i);
        }

        return result;
    }

    private boolean isLexicographicallySmaller(List<Integer> a, List<Integer> b) {
        if (a.isEmpty() && !b.isEmpty()) return false;
        if (!a.isEmpty() && b.isEmpty()) return true;

        int minLen = Math.min(a.size(), b.size());
        for (int i = 0; i < minLen; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }
        return a.size() < b.size();
    }
}
