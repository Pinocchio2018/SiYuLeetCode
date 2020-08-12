package siyu.leetcode.tree;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 */
public class No106 {

    public static void main(String[] args) {
        int[] inOrder = new int[]{9, 3, 15, 20, 7};
        int[] postorder = new int[]{9, 15, 7, 20, 3};


        TreeNode treeNode = new No106().buildTree(inOrder, postorder);
        System.out.println(treeNode);

    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0) {
            return null;
        }

        if (inorder.length == 1) {
            return new TreeNode(inorder[0]);
        }

        int root = postorder[postorder.length - 1];//后序遍历的最后一位是根

        for (int i = 0; i < inorder.length; i++) {
            //找到根在先序遍历的位置
            if (root == inorder[i]) {
                TreeNode node = new TreeNode(root);
                TreeNode left = buildTree(Arrays.copyOfRange(inorder, 0, i), Arrays.copyOfRange(postorder, 0, i));
                TreeNode right = buildTree(Arrays.copyOfRange(inorder, i + 1, inorder.length), Arrays.copyOfRange(postorder, i, postorder.length - 1));
                node.left = left;
                node.right = right;
                return node;
            }
        }
        throw new RuntimeException("有问题");
    }
}
