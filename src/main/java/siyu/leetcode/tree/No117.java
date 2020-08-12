package siyu.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No117 {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);


        root.right.right = new Node(7);

        Node connect = new No117().connect(root);
        System.out.println(connect);

    }

    public Node connect(Node root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }


        Queue<Node> queue = new ArrayDeque<>();
        List<Node> nextLevelQueue = new ArrayList<>();
        if (root.left != null) {
            queue.add(root.left);
        }
        if (root.right != null) {
            queue.add(root.right);
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.left != null) {
                nextLevelQueue.add(node.left);
            }
            if (node.right != null) {
                nextLevelQueue.add(node.right);
            }

            Node next = queue.peek();

            if (next != null) {
                node.next = next;
            } else {
                node.next = null;
                if (!nextLevelQueue.isEmpty()) {
                    queue.addAll(nextLevelQueue);
                    nextLevelQueue.clear();
                }
            }
        }
        return root;
    }


//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/solution/tian-chong-mei-ge-jie-dian-de-xia-yi-ge-you-ce-15/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            Node last = null;
            for (int i = 1; i <= n; ++i) {
                Node f = queue.poll();
                if (f.left != null) {
                    queue.offer(f.left);
                }
                if (f.right != null) {
                    queue.offer(f.right);
                }
                if (i != 1) {
                    last.next = f;
                }
                last = f;
            }
        }
        return root;
    }


    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

}

