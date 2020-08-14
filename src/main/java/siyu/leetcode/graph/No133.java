package siyu.leetcode.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class No133 {
    public Node cloneGraph(Node node) {
        Set<Node> clonedNodes = new HashSet<>();

        if (node == null) {
            return null;
        }

        Node clonedStartNode = new Node(node.val);

        clonedNodes.add(clonedStartNode);

        doCloneGraph(clonedNodes, clonedStartNode, node);
        return clonedStartNode;

    }

    private void doCloneGraph(Set<Node> clonedOriNodes, Node cloneNode, Node oriNode) {
        if (oriNode.neighbors.isEmpty()) {
            return;
        }

        for (Node oriNeighBor : oriNode.neighbors) {
            if (clonedOriNodes.contains(oriNeighBor)) {
                continue;
            }
            Node item = new Node(oriNeighBor.val);
            cloneNode.neighbors.add(item);
            clonedOriNodes.add(oriNeighBor);
            doCloneGraph(clonedOriNodes, item, oriNeighBor);
        }

    }

    class Node {
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
    }
}
