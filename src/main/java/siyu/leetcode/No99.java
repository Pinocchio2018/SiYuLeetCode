package siyu.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class No99 {

    public static void main(String[] args) {


        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);

        new No99().recoverTree(root);


        root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        new No99().recoverTree(root);

    }

    class TreeNodeWrapper {

        TreeNodeWrapper parent;
        TreeNodeWrapper left;
        TreeNodeWrapper right;

        TreeNode node;

        public TreeNodeWrapper() {

        }

        public TreeNodeWrapper(TreeNode node) {
            this.node = node;
        }
    }

    public void recoverTree(TreeNode root) {
        List<Integer> orderVal = new LinkedList<>();

        TreeNodeWrapper rootWrap = queryTree(root, orderVal);

        Queue<Integer> queue = new LinkedBlockingQueue<>(orderVal);

        reArrange(rootWrap, queue);

        queue.clear();

        List<Integer> result = new ArrayList<>();

        Queue<TreeNodeWrapper> leverOrderQueue = new LinkedBlockingQueue<>();
        leverOrderQueue.add(rootWrap);

        while (!leverOrderQueue.isEmpty()) {
            TreeNodeWrapper nodeWrap = leverOrderQueue.peek();

            if (nodeWrap.node != null) {
                result.add(nodeWrap.node.val);
            } else {
                result.add(null);
            }

            if (nodeWrap.left == null && nodeWrap.right == null) {
                leverOrderQueue.poll();
                continue;
            }

            if (nodeWrap.left != null) {
                leverOrderQueue.add(nodeWrap.left);
            } else {
                leverOrderQueue.add(new TreeNodeWrapper());
            }
            if (nodeWrap.right != null) {
                leverOrderQueue.add(nodeWrap.right);
            } else {
                leverOrderQueue.add(new TreeNodeWrapper());
            }
            leverOrderQueue.poll();
        }
        System.out.println(result);

    }

    private void reArrange(TreeNodeWrapper nodeWrap, Queue<Integer> queue) {
        if (nodeWrap.left != null) {

            reArrange(nodeWrap.left, queue);
        }


        nodeWrap.node.val = queue.poll();


        if (nodeWrap.right != null) {
            reArrange(nodeWrap.right, queue);
        }

    }

    private TreeNodeWrapper queryTree(TreeNode root, List<Integer> orderVal) {
        TreeNodeWrapper rootWrap = new TreeNodeWrapper(root);

        doQueryTree(rootWrap, orderVal);

        return rootWrap;
    }

    private void doQueryTree(TreeNodeWrapper nodeWrap, List<Integer> orderVal) {

        int nodeVal = nodeWrap.node.val;

        boolean added = false;

        for (int i = 0; i < orderVal.size(); i++) {
            if (nodeVal <= orderVal.get(i)) {
                orderVal.add(i, nodeVal);
                added = true;
                break;
            }
        }

        if (!added) {
            orderVal.add(nodeVal);
        }

        if (nodeWrap.node.left != null) {
            TreeNodeWrapper leftChild = new TreeNodeWrapper(nodeWrap.node.left);

            leftChild.parent = nodeWrap;
            nodeWrap.left = leftChild;

            doQueryTree(leftChild, orderVal);
        }

        if (nodeWrap.node.right != null) {
            TreeNodeWrapper rightChild = new TreeNodeWrapper(nodeWrap.node.right);

            rightChild.parent = nodeWrap;
            nodeWrap.right = rightChild;

            doQueryTree(rightChild, orderVal);
        }

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


