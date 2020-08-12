package siyu.leetcode.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class No133 {

    public static void main(String[] args) {
        Node start = new Node(1);

        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);


        start.neighbors.add(node2);
        start.neighbors.add(node3);


        node2.neighbors.add(start);
        node2.neighbors.add(node4);

        node3.neighbors.add(start);
        node3.neighbors.add(node4);

        node4.neighbors.add(node2);
        node4.neighbors.add(node3);


        Node result = new No133().cloneGraph(start);

        System.out.println(result);


    }

    public Node cloneGraph(Node node) {

        if (node == null) {
            return null;
        }

        Node clonedStartNode = new Node(node.val);

        for (Node oriNeighbor : node.neighbors) {

            Node nextClonedNeighbor = new Node(oriNeighbor.val);

            doCloneGraph(clonedStartNode, nextClonedNeighbor, oriNeighbor);
        }


        return clonedStartNode;

    }

    private void doCloneGraph(Node lastNode, Node cloningNode, Node cloningOriNode) {
        if (cloningOriNode.neighbors.isEmpty()) {
            cloningNode.neighbors.add(lastNode);
            return;
        }

        for (Node oriNeighBor : cloningOriNode.neighbors) {
            if (lastNode.val == oriNeighBor.val) {
                cloningNode.neighbors.add(lastNode);
                continue;
            }
             Node nextClonedNeighbor = new Node(oriNeighBor.val);
            cloningNode.neighbors.add(nextClonedNeighbor);
            doCloneGraph(cloningNode, nextClonedNeighbor, oriNeighBor);

        }

    }

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    }
}
