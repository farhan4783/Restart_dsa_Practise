import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
       
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }
        
       
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] result = new int[n];
        int i = 0;
        
     
        while (i < n) {
            int j = i;
            List<Integer> indices = new ArrayList<>();
            List<Integer> values = new ArrayList<>();
            
     
            while (j < n && (j == i || pairs[j][0] - pairs[j - 1][0] <= limit)) {
                values.add(pairs[j][0]);
                indices.add(pairs[j][1]);
                j++;
            }
            
            
            Collections.sort(indices);
            
           
            for (int k = 0; k < indices.size(); k++) {
                result[indices.get(k)] = values.get(k);
            }
            
           
            i = j;
        }
        
        return result;
    }
}
