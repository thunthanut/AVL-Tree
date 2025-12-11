package avl_tree;

public class LinkedListAVL_Tree {

    LinkedListNode root;

    int height(LinkedListNode N) {
        return (N == null) ? 0 : N.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    LinkedListNode rightRotate(LinkedListNode y) {
        LinkedListNode x = y.left;
        LinkedListNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    LinkedListNode leftRotate(LinkedListNode x) {
        LinkedListNode y = x.right;
        LinkedListNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    int getBalance(LinkedListNode N) {
        return (N == null) ? 0 : height(N.left) - height(N.right);
    }

    LinkedListNode insert(LinkedListNode node, int value) {
        if (node == null) {
            return new LinkedListNode(value);
        }

        if (value < node.value) {
            node.left = insert(node.left, value);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        } else {
            return node; // Duplicates not allowed
        }

        node.height = 1 + max(height(node.left), height(node.right));
        int balance = getBalance(node);

        if (balance > 1 && value < node.left.value) {
            return rightRotate(node);
        }
        if (balance < -1 && value > node.right.value) {
            return leftRotate(node);
        }
        if (balance > 1 && value > node.left.value) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && value < node.right.value) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    LinkedListNode minValueNode(LinkedListNode node) {
        LinkedListNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    LinkedListNode deleteNode(LinkedListNode root, int value) {
        if (root == null) {
            return root;
        }

        if (value < root.value) {
            root.left = deleteNode(root.left, value);
        } else if (value > root.value) {
            root.right = deleteNode(root.right, value);
        } else {
            if ((root.left == null) || (root.right == null)) {
                LinkedListNode temp = (root.left != null) ? root.left : root.right;
                return temp; // Return the non-null child or null
            } else {
                LinkedListNode temp = minValueNode(root.right);
                root.value = temp.value;
                root.right = deleteNode(root.right, temp.value);
            }
        }

        if (root == null) {
            return root;
        }

        root.height = max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    boolean find(LinkedListNode node, int value) {
        if (node == null) {
            return false;
        }
        if (node.value == value) {
            return true;
        }
        return (value < node.value) ? find(node.left, value) : find(node.right, value);
    }

    String inOrder(LinkedListNode node) {
        StringBuilder sb = new StringBuilder();
        inOrderHelper(node, sb);
        return sb.toString().trim();
    }

    void inOrderHelper(LinkedListNode node, StringBuilder sb) {
        if (node != null) {
            inOrderHelper(node.left, sb);
            sb.append(node.value).append(" ");
            inOrderHelper(node.right, sb);
        }
    }

    String preOrder(LinkedListNode node) {
        StringBuilder sb = new StringBuilder();
        preOrderHelper(node, sb);
        return sb.toString().trim();
    }

    void preOrderHelper(LinkedListNode node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.value).append(" ");
            preOrderHelper(node.left, sb);
            preOrderHelper(node.right, sb);
        }
    }

    String postOrder(LinkedListNode node) {
        StringBuilder sb = new StringBuilder();
        postOrderHelper(node, sb);
        return sb.toString().trim();
    }

    void postOrderHelper(LinkedListNode node, StringBuilder sb) {
        if (node != null) {
            postOrderHelper(node.left, sb);
            postOrderHelper(node.right, sb);
            sb.append(node.value).append(" ");
        }
    }

    String printTree() {
        return "Pre-order: " + preOrder(root) + "\n"
                + "In-order: " + inOrder(root) + "\n"
                + "Post-order: " + postOrder(root);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Base base = new Base();
            javax.swing.JFrame frame = new javax.swing.JFrame("AVL Tree");
            frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(base);
            frame.pack();
            frame.setVisible(true);
        });
    }
}