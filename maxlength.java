import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maximumLength(int[] nums) {
        int maxNum = 0;
        for (int num : nums) {
            if (num > maxNum) {
                maxNum = num;
            }
        }
        
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        
        int ans = 1;
        if (count.containsKey(1)) {
            int countOnes = count.get(1);
            if (countOnes % 2 == 0) {
                ans = Math.max(ans, countOnes - 1);
            } else {
                ans = Math.max(ans, countOnes);
            }
        }
        
        for (int num : nums) {
            if (num == 1) {
                continue;
            }
            
            int length = 0;
            long x = num;
            
            while (x <= maxNum && count.containsKey((int) x) && count.get((int) x) >= 2) {
                length += 2;
                x *= x;
            }
            
            if (x <= maxNum && count.containsKey((int) x)) {
                ans = Math.max(ans, length + 1);
            } else {
                ans = Math.max(ans, length - 1);
            }
        }
        
        return ans;
    }
}
