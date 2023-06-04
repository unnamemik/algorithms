package seminar3;

import java.util.*;

public class LLRBT<Key extends Comparable<Key>, Value> {
    public static final String reset = "\u001B[0m";
    public static final String red = "\u001B[41m";
    public static final String black = "\u001B[40m";
    public static final String frame = "\u001B[51m";
    private static final boolean RED = true;
    private static final boolean Black = false;
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        boolean color;

        public Node(Key k, Value v, boolean col) {
            key = k;
            val = v;
            color = col;
        }
    }

    private Node add(Node n, Key k, Value v) {
        if (n == null) return new Node(k, v, RED);
        int t = k.compareTo(n.key);
        if (t < 0) n.left = add(n.left, k, v);
        else if (t > 0) n.right = add(n.right, k, v);
        else n.val = v;
        if (!isRed(n.left) && isRed(n.right)) n = leftRotate(n);
        if (isRed(n.left) && isRed(n.left.left)) n = rightRotate(n);
        if (isRed(n.left) && isRed(n.right)) swapColors(n);
        return n;
    }

    private boolean isRed(Node n) {
        if (n == null)
            return false;

        return n.color == RED;
    }

    public Value get(Key k) {
        return get(root, k);
    }

    public int height() {
        return height(root);
    }

    private int height(Node n) {
        if (n == null) return 0;
        return 1 + Math.max(height(n.left), height(n.right));
    }

    public Value get(Node n, Key k) {
        if (n == null) return null;
        int t = n.key.compareTo(k);
        if (t > 0) return get(n.left, k);
        if (t < 0) return get(n.right, k);
        return n.val;
    }

    private Node leftRotate(Node n) {
        Node x = n.right;
        n.right = x.left;
        x.left = n;
        x.color = n.color;
        n.color = RED;
        return x;
    }

    private Node rightRotate(Node n) {
        Node x = n.left;
        n.left = x.right;
        x.right = n;
        x.color = n.color;
        n.color = RED;
        return x;
    }

    private void swapColors(Node n) {
        n.color = !n.color;
        n.left.color = !n.left.color;
        n.right.color = !n.right.color;

    }

    public void add(Key k, Value v) {
        root = add(root, k, v);
        root.color = Black;
    }

    private Node moveLeft(Node n) {
        swapColors(n);
        if (isRed(n.right.left)) {
            n.right = rightRotate(n.right);
            n = leftRotate(n);
            swapColors(n);
        }
        return n;
    }

    private Node moveRight(Node n) {
        swapColors(n);
        if (isRed(n.left.left)) {
            n = rightRotate(n);
            swapColors(n);
        }
        return n;
    }

    private Node nodeBalance(Node n) {
        if (isRed(n.right))
            n = leftRotate(n);
        if (isRed(n.left) && isRed(n.left.left))
            n = rightRotate(n);
        if (isRed(n.left) && isRed(n.right))
            swapColors(n);
        return n;
    }

    public Node min() {
        if (root == null) return null;
        return min(root.left);
    }

    public Node min(Node n) {
        if (n.left == null)
            return n;
        return min(n.left);
    }

    public void delete(Key key) {
        root = delete(root, key);
        root.color = Black;
    }

    private Node delete(Node n, Key key) {
        int t = n.key.compareTo(key);
        if (t > 0) {
            if (!isRed(n.left) && !isRed(n.left.left))
                n = moveLeft(n);
            n.left = delete(n.left, key);
        } else {
            if (isRed(n.left))
                n = rightRotate(n);
            if (t == 0 && n.right == null)
                return null;
            if(!isRed(n.right) && !isRed(n.right.left))
                n = moveRight(n);

            if (t == 0) {
                Node target = min(n.right);
                n.key = target.key;
                n.val = target.val;
            }
            else {
                n.right = delete(n.right, key);
            }
        }
        return nodeBalance(n);
    }

    private void printRows(int n, String str) {
        for (int i = 0; i < n; i++)
            System.out.print(str);
    }

    public void print() {
        class nodeIndex {
            Node node;
            int idx;

            nodeIndex(Node node, int idx) {
                this.node = node;
                this.idx = idx;
            }
        }
        Queue<nodeIndex> queue = new LinkedList<>();
        nodeIndex rootIndex = new nodeIndex(root, 1);
        queue.offer(rootIndex);
        int len = (int) Math.pow(2, height());
        String[] arr = new String[len];
        Arrays.fill(arr, "□");
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                nodeIndex cur = queue.poll();
                arr[cur.idx] = cur.node.color ? red + frame + cur.node.val + reset : black + frame + cur.node.val +reset;
                if (cur.node.left != null) {
                    nodeIndex temp = new nodeIndex(cur.node.left, cur.idx * 2);
                    queue.offer(temp);
                }
                if (cur.node.right != null) {
                    nodeIndex temp = new nodeIndex(cur.node.right, cur.idx * 2 + 1);
                    queue.offer(temp);
                }
            }
        }

        int size = len - 1;
        for (int i = 0; i < height(); i++) {
            int curLen = (int) Math.pow(2, i);
            int leftPaddingSize = size / ((int) Math.pow(2, i + 1));
            int middlePaddingSize = i == 0 ? 0 : (size - leftPaddingSize * 2 - curLen) / (curLen - 1);
            printRows(leftPaddingSize, "□");
            for (int j = (int) Math.pow(2, i); j < (int) Math.pow(2, i + 1); j++) {
                System.out.print(arr[j]);
                if (j == (int) Math.pow(2, i + 1) - 1)
                    continue;
                printRows(middlePaddingSize, "□");
            }
            printRows(leftPaddingSize, "□");
            System.out.println();
        }
    }
}