import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> map = new HashMap<>();
        Set<Integer> children = new HashSet<>();

        for (int[] d : descriptions) {
            int parentVal = d[0];
            int childVal = d[1];
            boolean isLeft = d[2] == 1;

            map.putIfAbsent(parentVal, new TreeNode(parentVal));
            map.putIfAbsent(childVal, new TreeNode(childVal));

            if (isLeft) {
                map.get(parentVal).left = map.get(childVal);
            } else {
                map.get(parentVal).right = map.get(childVal);
            }

            children.add(childVal);
        }

        for (int parentVal : map.keySet()) {
            if (!children.contains(parentVal)) {
                return map.get(parentVal);
            }
        }

        return null;
    }
}
