package siyu.leetcode.tree;

import java.util.LinkedList;
import java.util.List;

public class No145 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        System.out.println(new No145().postorderTraversal(root));
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();

        doPostorderTraversal(root, ans);
        return ans;
    }

    private void doPostorderTraversal(TreeNode node, List<Integer> ans) {

        if (node == null) {
            return;
        }

        if (node.left != null) {
            doPostorderTraversal(node.left, ans);
        }

        if (node.right != null) {
            doPostorderTraversal(node.right, ans);
        }

        ans.add(node.val);
    }
}
