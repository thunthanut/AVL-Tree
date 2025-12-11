package avl_tree;

import javax.swing.*;
import java.awt.*;

public class TreeDisplayPanel extends JPanel {
    private LinkedListNode root;

    public void setTree(LinkedListNode root) {
        this.root = root;
        repaint(); // Refresh the panel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (root != null) {
            drawTree(g, root, getWidth() / 2, 30, 100);
        }
    }

    private void drawTree(Graphics g, LinkedListNode node, int x, int y, int xOffset) {
        if (node != null) {
            g.drawOval(x - 15, y - 15, 30, 30);
            g.drawString(String.valueOf(node.value), x - 5, y + 5);

            if (node.left != null) {
                g.drawLine(x, y, x - xOffset, y + 50);
                drawTree(g, node.left, x - xOffset, y + 50, xOffset / 2);
            }

            if (node.right != null) {
                g.drawLine(x, y, x + xOffset, y + 50);
                drawTree(g, node.right, x + xOffset, y + 50, xOffset / 2);
            }
        }
    }
}
