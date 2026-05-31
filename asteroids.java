import java.util.Arrays;

class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long current = mass;
        for (int a : asteroids) {
            if (current < a) return false;
            current += a;
        }
        return true;
    }
}
