import java.util.Arrays;
import java.util.Collections;

class Solution {
    public int minimumCost(int[] cost) {
       
        Integer[] arr = new Integer[cost.length];
        for (int i = 0; i < cost.length; i++) {
            arr[i] = cost[i];
        }

     
        Arrays.sort(arr, Collections.reverseOrder());

        int total = 0;

      
        for (int i = 0; i < arr.length; i++) {
            if (i % 3 != 2) {      
                total += arr[i];
            }
        }

        return total;
    }
}
