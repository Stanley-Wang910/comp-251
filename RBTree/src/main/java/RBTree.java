public abstract class RBTree {
    Node nil;
    Node root;

    static class Node {
        Node left, right, parent;
        int val;
        Color color;


        public Node(int val, Node parent, Color color) {
            this.val = val;
            this.parent = parent;
            this.color = color;
        }
    }

    public abstract void insert(int val);

    public abstract boolean isValidRBT();
}
