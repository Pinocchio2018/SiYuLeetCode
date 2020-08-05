package siyu.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class No337 {

    /**
     * 0
     * 1       2
     * 3    4   5     6
     * 7  8  9 10
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(5);

        root.right = new TreeNode(6);
        root.right.left = new TreeNode(1);


        //[3,4,5,1,3,null,1]
        root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        System.out.println(new No337().getLevelOrders(root));


        //[4,1,null,2,null,3]
        root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(3);

        System.out.println(new No337().getLevelOrders(root));
    }

    public static final int method(int i) {
        i++;
        return i;
    }

    public int rob(TreeNode root) {
//        List<Integer> levelOrders = getLevelOrders(root);
        return 0;
    }

    private int getLevelOrders(TreeNode root) {
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();

        if (root == null) {
            return 0;
        }
        queue.add(root);
        boolean isEvenLevel = true;
        int oddNums = 0;
        int evenNums = 0;
        TreeNode node = queue.poll();
        List<TreeNode> nextLevelNodes = new ArrayList<>();
        while (node != null) {
            if (isEvenLevel) {
                evenNums += node.val;
            } else {
                oddNums += node.val;
            }


            if (node.left != null) {
                nextLevelNodes.add(node.left);
            }
            if (node.right != null) {
                nextLevelNodes.add(node.right);
            }


            if (nextLevelNodes.isEmpty() && queue.isEmpty()) {
                break;
            }

            node = queue.poll();

            if (node == null) {
                isEvenLevel = !isEvenLevel;
                queue.addAll(nextLevelNodes);
                node = queue.poll();
                nextLevelNodes.clear();
            }
        }
        return Math.max(oddNums, evenNums);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
