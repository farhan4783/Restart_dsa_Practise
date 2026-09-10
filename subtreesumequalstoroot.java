/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
*/


class Solution {
    private int matchingNodeCount = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return matchingNodeCount;
    }

  
    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

      
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int currentSum = node.val + left[0] + right[0];
        int currentCount = 1 + left[1] + right[1];

       
        if (currentSum / currentCount == node.val) {
            matchingNodeCount++;
        }

        return new int[]{currentSum, currentCount};
    }
}
