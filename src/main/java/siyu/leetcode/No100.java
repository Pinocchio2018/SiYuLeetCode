package siyu.leetcode;

public class No100 {

    public static void main(String[] args) {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);

        TreeNode q = new TreeNode(1);
        q.left = new TreeNode(2);
        q.right = new TreeNode(5);

        System.out.println(new No100().isSameTree(p,q));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q != null) {
            return false;
        }

        if (p != null && q == null) {
            return false;
        }

        if (p == null && q == null) {
            return true;
        }

        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
