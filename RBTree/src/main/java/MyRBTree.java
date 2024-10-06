public class MyRBTree extends RBTree {
    public MyRBTree() {
        // Initialize nil node
        nil = new Node(0, null, Color.BLACK); // Value doesn't matter
        nil.left = nil;
        nil.right = nil;
        nil.parent = nil;
        root = nil;
    }

    public void insert(int val) {
        Node parent = nil;
        Node node = root;

        while (node != nil) {
            parent = node;
            if (val < parent.val) {
                node = node.left;
            } else if (val > parent.val) {
                node = node.right;
            } else {
                return; // Value already exists
            }
        }

        // Reached the proper insertion location in RB Tree
        Node newNode = new Node(val, parent, Color.RED);
        newNode.left = nil;  // Initialize left child
        newNode.right = nil; // Initialize right child

        if (parent == nil) {
            root = newNode;
        } else if (val < parent.val) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        newNode.parent = parent;

        fixRBT(newNode);
    }

    private void fixRBT(Node node) {
        while (node.parent.color == Color.RED) {
            if (node.parent == node.parent.parent.left) {
                Node uncle = node.parent.parent.right;

                if (uncle.color == Color.RED) {
                    // Case 1: Uncle is red
                    node.parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        // Case 2: Node is right child
                        node = node.parent;
                        rotateLeft(node);
                    }
                    // Case 3: Node is left child
                    node.parent.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    rotateRight(node.parent.parent);
                }
            } else {
                // Symmetric cases
                Node uncle = node.parent.parent.left;

                if (uncle.color == Color.RED) {
                    // Case 1: Uncle is red
                    node.parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        // Case 2: Node is left child
                        node = node.parent;
                        rotateRight(node);
                    }
                    // Case 3: Node is right child
                    node.parent.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    rotateLeft(node.parent.parent);
                }
            }
        }
        root.color = Color.BLACK;
    }

    private void rotateLeft(Node node){
        Node rightChild = node.right;

        node.right = rightChild.left;
        if (rightChild.left != nil) {
            rightChild.left.parent = node;
        }
        rightChild.parent = node.parent;

        if (node.parent == nil) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }

        rightChild.left = node;
        node.parent = rightChild;
    }

    private void rotateRight(Node node){
        Node leftChild = node.left;

        node.left = leftChild.right;
        if (leftChild.right != nil) {
            leftChild.right.parent = node;
        }
        leftChild.parent = node.parent;

        if (node.parent == nil) {
            root = leftChild;
        } else if (node == node.parent.right) {
            node.parent.right = leftChild;
        } else {
            node.parent.left = leftChild;
        }

        leftChild.right = node;
        node.parent = leftChild;
    }

    public boolean isValidRBT() {

        // Property 2: Root is black
        if (root.color != Color.BLACK) {
            return false;
        }

        // Validate the tree starting from the root
        try {
            validateRBT(root);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    private int validateRBT(Node node) throws Exception {
        if (node == null) {
            throw new Exception("Null node encountered");
        }

        if (node.left == null || node.right == null) {
            throw new Exception("Null child encountered");
        }

        if (node == nil) {
            // All leaf nodes are black
            if (node.color != Color.BLACK) {
                throw new Exception("Nil node is not black");
            }
            return 1;
        }

        if (node.color == Color.RED) {
            if (node.left.color == Color.RED || node.right.color == Color.RED) {
                throw new Exception("Red node has red child");
            }
        }

        int leftBlackHeight = validateRBT(node.left);
        int rightBlackHeight = validateRBT(node.right);

        if (leftBlackHeight != rightBlackHeight) {
            throw new Exception("Black height mismatch");
        }

        int blackHeight = leftBlackHeight;
        if (node.color == Color.BLACK) {
            blackHeight += 1;
        }

        return blackHeight;
    }
}
