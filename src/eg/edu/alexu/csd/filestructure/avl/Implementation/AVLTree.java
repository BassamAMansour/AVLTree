package eg.edu.alexu.csd.filestructure.avl.Implementation;

import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.INode;

public class AVLTree<T extends Comparable<T>> implements IAVLTree<T> {

    private int numberOfNodes;
    private int height;
    private Node<T> root;


    public AVLTree() {
    }

    @Override
    public void insert(T key) {

        boolean inserted = false;

        if (key == null) {
            return;
        }

        if (getNumberOfNodes() == 0 || getRoot() == null) {
            setRoot(new Node<>(key, 0, null));
            inserted = true;
        } else {
            inserted = insertNonParentNode(getRoot(), key);
        }

        if (inserted) {
            setNumberOfNodes(getNumberOfNodes() + 1);
            updateTreeHeight();
        }
    }

    @Override
    public boolean delete(T key) {
        if (search(key)) {

            //TODO: Akromty
            //TODO: delete the key after it's found in the tree

            setNumberOfNodes(getNumberOfNodes() - 1);
            updateTreeHeight();
            return true;
        }
        return false;
    }

    @Override
    public boolean search(T key) {

        //TODO: Akromty
        //TODO: search for that key
        return false;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public INode<T> getTree() {
        return getRoot();
    }

    private boolean insertNonParentNode(Node<T> currentNode, T key) {

        int compareValue = key.compareTo(currentNode.getValue());

        if (compareValue < 0) {

            if (currentNode.getLeftChild() == null) {
                insertAsLeftChild(currentNode, key);
            } else {

                return insertNonParentNode((Node<T>) currentNode.getLeftChild(), key);
            }
        } else if (compareValue == 0) {
            return false;
        } else {
            if (currentNode.getRightChild() == null) {
                insertAsRightChild(currentNode, key);
            } else {
                return insertNonParentNode((Node<T>) currentNode.getRightChild(), key);
            }
        }

        int maxLeftDepth = getMaxDepth((Node<T>) currentNode.getLeftChild());
        int maxRightDepth = getMaxDepth((Node<T>) currentNode.getRightChild());

        int depth = maxLeftDepth - maxRightDepth;

        if (Math.abs(depth) > 1) {
            rotate(getUnbalancedNode(currentNode));
        }

        return true;
    }

    private void rotate(Node<T> unbalancedNode) {

        //TODO: Akromty
        //TODO: decide rotate left or right or double rotation

    }

    private void insertAsRightChild(Node<T> currentNode, T key) {
        Node<T> nodeToInsert = new Node<>(key, currentNode.getNodeDepth() + 1, currentNode);
        currentNode.setRightChild(nodeToInsert);
    }

    private void insertAsLeftChild(Node<T> currentNode, T key) {
        Node<T> nodeToInsert = new Node<>(key, currentNode.getNodeDepth() + 1, currentNode);
        currentNode.setLeftChild(nodeToInsert);
    }

    private void updateTreeHeight() {

        //TODO: Akromty
        //TODO: Update the tree height
        //Called after an insertion or deletion

    }

    private Node<T> getUnbalancedNode(Node<T> parentNode) {

        //TODO: Akromty or Bassam
        //TODO: balance the tree by making rotations at the non balanced branches
        //Called after an insertion or deletion

        int maxLeftDepth = getMaxDepth((Node<T>) parentNode.getLeftChild());
        int maxRightDepth = getMaxDepth((Node<T>) parentNode.getRightChild());

        int depth = maxLeftDepth - maxRightDepth;

        if (depth >= 2) {
            return getUnbalancedNode((Node<T>) parentNode.getLeftChild());
        } else if (depth <= -2) {
            return getUnbalancedNode((Node<T>) parentNode.getRightChild());
        }

        return parentNode.getParent();
    }

    private int getMaxDepth(Node<T> parentNode) {
        int maxLeftDepth;
        int maxRightDepth;

        if (parentNode.getLeftChild() == null) {
            maxLeftDepth = parentNode.getNodeDepth();
        }
        if (parentNode.getRightChild() == null) {
            maxRightDepth = parentNode.getNodeDepth();
        }

        maxLeftDepth = getMaxDepth((Node<T>) parentNode.getLeftChild());
        maxRightDepth = getMaxDepth((Node<T>) parentNode.getRightChild());

        return Math.max(maxLeftDepth, maxRightDepth);
    }


    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    private void setNumberOfNodes(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
    }

    private void setHeight(int height) {
        this.height = height;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

}
