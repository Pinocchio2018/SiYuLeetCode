package siyu.leetcode;

import java.util.LinkedList;
import java.util.List;

public class No95 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;


        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<TreeNode> myGenerateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }

        for (int i = 0; i <n ; i++) {


        }

        return null;
    }
}
