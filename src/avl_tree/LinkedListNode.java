package avl_tree;

public class LinkedListNode {
    int value;
    int height;
    LinkedListNode next; // Reference to the next node in the list
    LinkedListNode left, right; // References for AVL Tree structure

    public LinkedListNode(int d) {
        value = d;
        height = 1;
        next = null; // Initialize next as null
    }
}
