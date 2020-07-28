package siyu.leetcode;

public class No104 {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(5);
        System.out.println(new No104().maxDepth(root));
    }

    public int maxDepth(TreeNode root) {
        if (root==null){
            return 1;
        }


        int leftDepth = 1;
        if (root.left != null) {
            leftDepth = maxDepth(root.left) + 1;
        }
        int rightDepth = 1;
        if (root.right != null) {
            rightDepth = maxDepth(root.right) + 1;
        }
        return Math.max(leftDepth, rightDepth);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
