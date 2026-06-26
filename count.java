class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int[] tree = new int[2 * n + 2];
        int s = n + 1;
        update(tree, s, 1);
        long ans = 0;
        for (int x : nums) {
            s += (x == target) ? 1 : -1;
            ans += query(tree, s - 1);
            update(tree, s, 1);
        }
        return ans;
    }

    private void update(int[] tree, int x, int delta) {
        for (; x < tree.length; x += x & -x) {
            tree[x] += delta;
        }
    }

    private int query(int[] tree, int x) {
        int s = 0;
        for (; x > 0; x -= x & -x) {
            s += tree[x];
        }
        return s;
    }
}
