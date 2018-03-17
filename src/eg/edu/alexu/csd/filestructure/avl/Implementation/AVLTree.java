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
        INode deleteNode= searchForTheNode(key);
        if (deleteNode != null) {
            Node successorNode = this.successor((Node<T>) deleteNode);
            ((Node<T>) deleteNode).setValue((T) successorNode.getValue());
            if(successorNode.isARightChild()){
                successorNode.getParent().setRightChild((Node) successorNode.getRightChild());
                ((Node) successorNode.getRightChild()).setParent(successorNode.getParent());
            }
            else if (successorNode.isALeftChild()){
                successorNode.getParent().setLeftChild((Node) successorNode.getRightChild());
                ((Node) successorNode.getRightChild()).setParent(successorNode.getParent());
            }
            //TODO: Akromty
            //TODO: hena elmfrod n3mel call 3shan nzbt el balance

            setNumberOfNodes(getNumberOfNodes() - 1);
            updateTreeHeight();
            return true;
        }
        return false;
    }

    private Node<T> searchForTheNode(T key) {
        INode currentNode = root;
        while (currentNode != null){
            int compareValue = key.compareTo((T) currentNode.getValue());
            if (compareValue < 0){
                if (currentNode.getLeftChild() == null){
                    return null;
                }
                currentNode = currentNode.getLeftChild();
            }
            else if (compareValue == 0){
                return (Node<T>) currentNode;
            }
            else {
                if (currentNode.getRightChild() == null){
                    return null;
                }
                currentNode = currentNode.getRightChild();
            }
        }
        return null;
    }

    @Override
    public boolean search(T key) {
        INode currentNode = root;
        while (currentNode != null){
            int compareValue = key.compareTo((T) currentNode.getValue());
            if (compareValue < 0){
                if (currentNode.getLeftChild() == null){
                    return false;
                }
                currentNode = currentNode.getLeftChild();
            }
            else if (compareValue == 0){
                return true;
            }
            else {
                if (currentNode.getRightChild() == null){
                    return false;
                }
                currentNode = currentNode.getRightChild();
            }
        }
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
        int maxLeftDepth = getMaxDepth((Node<T>) unbalancedNode.getLeftChild());
        int maxRightDepth = getMaxDepth((Node<T>) unbalancedNode.getRightChild());
        if (maxLeftDepth-maxRightDepth > 0){
            if (unbalancedNode.getLeftChild().getRightChild() != null){
                rotateLeft((Node<T>) unbalancedNode.getLeftChild());
            }
            rotateRight(unbalancedNode);
        }
        else if(maxLeftDepth-maxRightDepth < 0){
            if (unbalancedNode.getRightChild().getLeftChild() != null){
                rotateRight((Node<T>) unbalancedNode.getLeftChild());
            }
            rotateLeft(unbalancedNode);
        }
    }
    private void rotateLeft(Node<T> node){
        Node<T> nodeRightChild = (Node<T>) node.getRightChild();
        nodeRightChild.setParent(node.getParent());
        if(node.isALeftChild()){
            node.getParent().setLeftChild(nodeRightChild);
        }
        else if(node.isARightChild()){
            ((Node<T>) node.getParent()).setRightChild(nodeRightChild);
        }
        nodeRightChild.setLeftChild(node);
        node.setParent(nodeRightChild);
        node.setRightChild(null);
    }

    private void rotateRight(Node<T> node){
        Node<T> nodeLeftChild = (Node<T>) node.getLeftChild();
        nodeLeftChild.setParent(node.getParent());
        if(node.isALeftChild()){
            ((Node<T>) node.getParent()).setLeftChild(nodeLeftChild);
        }
        else if(node.isARightChild()){
            ((Node<T>) node.getParent()).setRightChild(nodeLeftChild);
        }
        nodeLeftChild.setRightChild(node);
        node.setParent(nodeLeftChild);
        node.setLeftChild(null);
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

    private Node<T> successor(Node<T> node){
        if (node.getLeftChild() != null){
            successor((Node<T>) node.getLeftChild());
        }
        return node;
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
