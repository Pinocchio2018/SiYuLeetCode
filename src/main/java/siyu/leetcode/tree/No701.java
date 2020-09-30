package siyu.leetcode.tree;

public class No701 {

    public static void main(String[] args) {
        //      4
        // 2        7
        //1 3     5
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        new No701().insertIntoBST(root, 5);
    }


    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        doinsertIntoBST(root, val);

        return root;
    }

    public TreeNode doinsertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val < root.val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
                return root;
            }
            return doinsertIntoBST(root.left, val);
        } else if (val > root.val) {
            if (root.right == null) {
                root.right = new TreeNode(val);
                return root;
            }
            return doinsertIntoBST(root.right, val);
        }

        return root;
    }
}
