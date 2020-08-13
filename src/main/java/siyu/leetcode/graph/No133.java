package siyu.leetcode.graph;

import java.util.*;

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

        Set<Node> alreadyClonedNodes = new HashSet<>();
        Set<Node> alreadyClonedOriNodes = new HashSet<>();


        alreadyClonedNodes.add(clonedStartNode);
        alreadyClonedOriNodes.add(node);
        doCloneGraph(alreadyClonedNodes, alreadyClonedOriNodes, clonedStartNode, node);


        return clonedStartNode;

    }

    private void doCloneGraph(Set<Node> alreadyClonedNodes, Set<Node> alreadyClonedOriNodes, Node cloningNode, Node cloningOriNode) {

        for (Node oriNeighBor : cloningOriNode.neighbors) {
            if (alreadyClonedOriNodes.contains(oriNeighBor)) {
                Node clonedNode = alreadyClonedNodes.stream().filter(item -> item.val == oriNeighBor.val).findFirst().get();

                if (!clonedNode.neighbors.contains(cloningNode)) {
                    clonedNode.neighbors.add(cloningNode);
                }
                if (!cloningNode.neighbors.contains(clonedNode)) {
                    cloningNode.neighbors.add(clonedNode);
                }
                continue;
            }


            Node nextClonedNeighbor = new Node(oriNeighBor.val);

            nextClonedNeighbor.neighbors.add(cloningNode);

            if (!cloningNode.neighbors.contains(nextClonedNeighbor)) {
                cloningNode.neighbors.add(nextClonedNeighbor);
            }
            alreadyClonedNodes.add(nextClonedNeighbor);
            alreadyClonedOriNodes.add(oriNeighBor);

            doCloneGraph(alreadyClonedNodes, alreadyClonedOriNodes, nextClonedNeighbor, oriNeighBor);

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
