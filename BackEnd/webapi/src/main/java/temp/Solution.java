package temp;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by desair4 on 2/26/2017.
 */
public class Solution {
    private static int[] coins;
    private static int result = 0;
    private static int level = 1;

    public static void main(String[] args) {
        System.out.println("\n\n" + new Solution().change(5, new int[]{1, 2, 5}));
    }

    public int change(int amount, int[] coins1) {
        coins = coins1;
        Node rootNode = new Node(-1);    //Dummy Data
        for (int i = 0; i < coins.length; i++) {
            Node n = new Node(coins[i]);
            boolean b = doAddNode(n, amount);

            if (b) {
                System.out.println(coins[i]);
                rootNode.nodes.add(n);
                level = 1;
                build(n, amount - coins[i]);
            }
        }
        // build(rootNode,amount);
//        System.out.println(rootNode);
//        for (Node n2 : rootNode.nodes) {
//            System.out.println(n2.data);
//            System.out.print(" ");
//            traverse(n2, true);
//        }

        return result;
    }

    public static void build1() {

    }

    public static void build(Node parentNode, int amount) {

        if (parentNode == null) return;
        if (amount < 1) return;
        // System.out.print(parentNode.data+" ");

        for (int i = 0; i < coins.length; i++) {
            int nextValue = coins[i];

            Node newNode = new Node(nextValue);
            boolean b = doAddNode(newNode, amount);
            if (b) {
                System.out.print(newNode.data + " ");
                int updatedAmount = amount - nextValue;
                parentNode.nodes.add(newNode);
                build(newNode, updatedAmount);
                //         level++;
                if (newNode.nodes.size() == 0 && updatedAmount == 0) {    //it's a leaf so we have reached an end to the sequence
                    result++;
                    System.out.println("");
                    for (int j = 0; j < level; j++) {
                        System.out.print(" ");
                    }
                }
            }
            // else{
            // System.out.println("");
            // result++;
            // }
        }
    }

    public static boolean doAddNode(Node n, int amountLeft) {
        //n.data=1,amountLeft=5->true : n.data=3,amountLeft=4->false : n.data=5,amountLeft=5->true
        return (amountLeft - n.data >= n.data || (amountLeft - n.data == 0));
    }

    public static void traverse(Node n, boolean isRoot) {
        if (n == null) return;
        if (!isRoot) {
            System.out.print(n.data + " ");
        }
        List<Node> nodes = n.nodes; //1st Level
        for (Node n1 : nodes) {
            traverse(n1, false);
        }
        if (n.nodes.size() == 0) {
            result++;
            System.out.println("");
            System.out.print(" ");
        }

    }

    // public static void traverseDelegate(Node n){
    //     if(n==null) return;
    //     List<Node> nodes = n.nodes;
    //     for(int i=0; i<coins.length; i++){
    //         System.out.print(n.data);
    //         traverseDelegate(nodes.get(i));
    //     }
    // }
}


class Node {
    public final List<Node> nodes = new LinkedList<Node>();
    public final int data;

    public Node(int data1) {
        data = data1;
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodes=" + nodes +
                ", data=" + data +
                '}';
    }
}