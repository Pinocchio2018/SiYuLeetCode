package siyu.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
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
        System.out.println(new No337().rob(root));


        //[4,1,null,2,null,3]
        root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(3);

        System.out.println(new No337().rob(root));


        root = new TreeNode(1);
        System.out.println(new No337().rob(root));
    }

    public static final int method(int i) {
        i++;
        return i;
    }

    public static class TreeNodeWrapper {
        public TreeNodeWrapper(TreeNode node) {
            this.node = node;
        }

        TreeNode node;
        int maxValExclude;
        int maxValInclude;
    }

    public int rob(TreeNode root) {
        TreeNodeWrapper rootWrap = solveRob(root);

        if (rootWrap == null) {
            return 0;
        }

        int result = Math.max(rootWrap.maxValExclude, rootWrap.maxValInclude);
        return result == 0 ? root.val : result;

    }

    private TreeNodeWrapper solveRob(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNodeWrapper wrap = new TreeNodeWrapper(root);

        // 如果该节点左右都有
        if (nonNull(root.left) && nonNull(root.right)) {

            // 如果该节点的左右节点是叶子
            if (isLeaf(root.left) && isLeaf(root.right)) {
                //该节点不包含它自己的最大值是左右叶子之和
                wrap.maxValExclude = root.left.val + root.right.val;
                // 该节点包含它自己的最大值是它自己
                wrap.maxValInclude = root.val;
                return wrap;
            }


            // 如果该节点的左右节点都不是叶子
            if (!isLeaf(root.left) && !isLeaf(root.right)) {

                TreeNodeWrapper leftChild = solveRob(root.left);
                TreeNodeWrapper rightChild = solveRob(root.right);
                // 该节点不包含它自己的最大值 = max{左孩子包含它自己的最大值，左孩子不包含它自己的最大值 }+ max {右孩子包含它自己的最大值，右孩子不包含它自己的最大值}
                wrap.maxValExclude = Math.max(leftChild.maxValExclude, leftChild.maxValInclude) + Math.max(rightChild.maxValExclude, rightChild.maxValInclude);
                // 该节点包含它自己的最大值 = 该节点的值 + 左孩子不包含它自己的最大值 + 右孩子不包含它自己的最大值
                wrap.maxValInclude = root.val + leftChild.maxValExclude + rightChild.maxValExclude;
                return wrap;
            }

            // 如果该节点的左边是叶子，右边不是
            if (isLeaf(root.left) && !isLeaf(root.right)) {
                TreeNodeWrapper rightChild = solveRob(root.right);

                // 当前节点包含它自己的最大值 = 当前节点自己的值 + 右子树不包含右孩子的最大值
                wrap.maxValInclude = root.val + rightChild.maxValExclude;

                // 当前节点不包含它自己的最大值 = 左孩子的值 +  max{右子树不包含右孩子的最大值，右子树包含右孩子的最大值}
                wrap.maxValExclude = root.left.val + Math.max(rightChild.maxValExclude, rightChild.maxValInclude);

                return wrap;
            }

            // 如果该节点的右边是叶子，左边不是
            if (isLeaf(root.right) && !isLeaf(root.left)) {
                TreeNodeWrapper leftChild = solveRob(root.left);

                // 当前节点包含它自己的最大值 = 当前节点自己的值 + 左子树不包含左孩子的最大值
                wrap.maxValInclude = root.val + leftChild.maxValExclude;

                // 当前节点不包含它自己的最大值 = 右孩子的值 +  max{左子树不包含右孩子的最大值，左子树包含右孩子的最大值}
                wrap.maxValExclude = root.right.val + Math.max(leftChild.maxValExclude, leftChild.maxValInclude);

                return wrap;
            }


        }

        // 该节点只有左子树
        if (nonNull(root.left) && isNull(root.right)) {

            if (isLeaf(root.left)) {
                wrap.maxValInclude = root.val;
                wrap.maxValExclude = root.left.val;
            } else {
                TreeNodeWrapper leftChild = solveRob(root.left);

                // 不包含当前节点的最大值 = max{左孩子包含其本身的最大值，左孩子不包含其本身的最大值}
                wrap.maxValExclude = Math.max(leftChild.maxValExclude, leftChild.maxValInclude);

                // 包含当前节点的最大值 = 左孩子不包含其本身的最大值+ 当前节点的值
                wrap.maxValInclude = root.val + leftChild.maxValExclude;
            }
        }

        // 该节点只有右子树
        if (isNull(root.left) && nonNull(root.right)) {

            if (isLeaf(root.right)) {
                wrap.maxValInclude = root.val;
                wrap.maxValExclude = root.right.val;
                return wrap;
            } else {
                TreeNodeWrapper rightChild = solveRob(root.right);

                // 不包含当前节点的最大值 = max{右孩子包含其本身的最大值，右孩子不包含其本身的最大值}
                wrap.maxValExclude = Math.max(rightChild.maxValExclude, rightChild.maxValInclude);

                // 包含当前节点的最大值 = 右孩子不包含其本身的最大值 + 当前节点的值
                wrap.maxValInclude = root.val + rightChild.maxValExclude;

                return wrap;
            }
        }


        return wrap;
    }

    private boolean isNull(TreeNode node) {

        return node == null;
    }

    private boolean nonNull(TreeNode node) {
        return node != null;
    }

    private boolean notNull(TreeNode node) {
        return node != null;
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
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
