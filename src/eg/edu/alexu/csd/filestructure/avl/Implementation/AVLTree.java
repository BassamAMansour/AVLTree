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
        INode deleteNode = searchForTheNode(key);
        if (deleteNode != null) {
            Node successorNode = null;
            if (deleteNode.getRightChild() != null) {
               successorNode = this.successor((Node<T>) deleteNode.getRightChild());
            }
            if (successorNode != null) {
                ((Node<T>) deleteNode).setValue((T) successorNode.getValue());
                this.deleteSuccessor(successorNode);
            } else {
                this.deleteSuccessor((Node<T>) deleteNode);
            }
            setNumberOfNodes(getNumberOfNodes() - 1);
            updateTreeHeight();
            return true;
        }
        return false;
    }

    private void deleteSuccessor(Node<T> successorNode) {
        if (successorNode.getRightChild() != null) {
            ((Node<T>) successorNode.getRightChild()).setParent(successorNode.getParent());
            if (successorNode.isALeftChild()) {
                successorNode.getParent().setLeftChild((Node<T>) successorNode.getRightChild());
            } else {
                successorNode.getParent().setRightChild((Node<T>) successorNode.getRightChild());
            }
        }
        else if (successorNode.getLeftChild() != null) {
            ((Node<T>) successorNode.getLeftChild()).setParent(successorNode.getParent());
                if (successorNode.isALeftChild()) {
                    successorNode.getParent().setLeftChild((Node<T>) successorNode.getLeftChild());
                } else {
                    successorNode.getParent().setLeftChild((Node<T>) successorNode.getLeftChild());
                }
        }

        fix(successorNode.getParent());
    }

    private void fix(Node<T> node) {
        if (node == null)
            return;
        int maxLeftDepth = getMaxDepth((Node<T>) node.getLeftChild());
        if (maxLeftDepth == 0){
            maxLeftDepth = node.getNodeDepth();
        }
        int maxRightDepth = getMaxDepth((Node<T>) node.getRightChild());
        if (maxRightDepth == 0){
            maxRightDepth = node.getNodeDepth();
        }
        int depth = maxLeftDepth - maxRightDepth;

        if (Math.abs(depth) > 1) {
            rotate(node);
        }
        fix(node.getParent());

    }

    private Node<T> searchForTheNode(T key) {
        INode currentNode = root;
        while (currentNode != null) {
            int compareValue = key.compareTo((T) currentNode.getValue());
            if (compareValue < 0) {
                if (currentNode.getLeftChild() == null) {
                    return null;
                }
                currentNode = currentNode.getLeftChild();
            } else if (compareValue == 0) {
                return (Node<T>) currentNode;
            } else {
                if (currentNode.getRightChild() == null) {
                    return null;
                }
                currentNode = currentNode.getRightChild();
            }
        }
        return null;
    }

    @Override
    public boolean search(T key) {
        INode currentNode = searchForTheNode(key);
        return currentNode != null;
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
        return true;
    }

    private void rotate(Node<T> unbalancedNode) {
        int maxLeftDepth = getMaxDepth((Node<T>) unbalancedNode.getLeftChild());
        if (maxLeftDepth == 0){
            maxLeftDepth = unbalancedNode.getNodeDepth();
        }
        int maxRightDepth = getMaxDepth((Node<T>) unbalancedNode.getRightChild());
        if (maxRightDepth == 0){
            maxRightDepth = unbalancedNode.getNodeDepth();
        }
        if (maxLeftDepth - maxRightDepth > 0) {
            checkLeft((Node<T>) unbalancedNode.getLeftChild());
            rotateRight(unbalancedNode);
        } else if (maxLeftDepth - maxRightDepth < 0) {
            checkRight((Node<T>) unbalancedNode.getRightChild());
            rotateLeft(unbalancedNode);
        }
        if (unbalancedNode == this.getRoot()){
            setRoot(unbalancedNode.getParent());
        }
        updateHeight(unbalancedNode.getParent());
    }

    private void checkRight(Node<T> rightChild) {
        int maxLeftDepth = getMaxDepth((Node<T>) rightChild.getLeftChild());
        if (maxLeftDepth == 0){
            maxLeftDepth = rightChild.getNodeDepth();
        }
        int maxRightDepth = getMaxDepth((Node<T>) rightChild.getRightChild());
        if (maxRightDepth == 0){
            maxRightDepth = rightChild.getNodeDepth();
        }
        if (maxLeftDepth - maxRightDepth >0){
            rotateRight(rightChild);
        }
    }

    private void checkLeft(Node<T> leftChild) {
        int maxLeftDepth = getMaxDepth((Node<T>) leftChild.getLeftChild());
        if (maxLeftDepth == 0){
            maxLeftDepth = leftChild.getNodeDepth();
        }
        int maxRightDepth = getMaxDepth((Node<T>) leftChild.getRightChild());
        if (maxRightDepth == 0){
            maxRightDepth = leftChild.getNodeDepth();
        }
        if (maxLeftDepth - maxRightDepth <0){
            rotateLeft(leftChild);
        }
    }

    private void rotateLeft(Node<T> node) {
        Node<T> nodeRightChild = (Node<T>) node.getRightChild();
        nodeRightChild.setParent(node.getParent());
        if (node.isALeftChild()) {
            node.getParent().setLeftChild(nodeRightChild);
        } else if (node.isARightChild()) {
            node.getParent().setRightChild(nodeRightChild);
        }
        node.setRightChild((Node<T>) nodeRightChild.getLeftChild());
        if (nodeRightChild.getLeftChild()!= null){
            ((Node<T>) nodeRightChild.getLeftChild()).setParent(node);
        }
        nodeRightChild.setLeftChild(node);
        node.setParent(nodeRightChild);
    }

    private void rotateRight(Node<T> node) {
        Node<T> nodeLeftChild = (Node<T>) node.getLeftChild();
        nodeLeftChild.setParent(node.getParent());
        if (node.isALeftChild()) {
            node.getParent().setLeftChild(nodeLeftChild);
        } else if (node.isARightChild()) {
            node.getParent().setRightChild(nodeLeftChild);
        }
        node.setLeftChild((Node<T>) nodeLeftChild.getRightChild());
        if (nodeLeftChild.getRightChild()!= null){
            ((Node<T>) nodeLeftChild.getRightChild()).setParent(node);
        }
        nodeLeftChild.setRightChild(node);
        node.setParent(nodeLeftChild);
    }

    private void insertAsRightChild(Node<T> currentNode, T key) {
        Node<T> nodeToInsert = new Node<>(key, currentNode.getNodeDepth() + 1, currentNode);
        currentNode.setRightChild(nodeToInsert);
        fix(nodeToInsert);
    }



    private void insertAsLeftChild(Node<T> currentNode, T key) {
        Node<T> nodeToInsert = new Node<>(key, currentNode.getNodeDepth() + 1, currentNode);
        currentNode.setLeftChild(nodeToInsert);
        fix(nodeToInsert);
    }

    private void updateTreeHeight() {
        updateHeight(getRoot());
        int maxLeft = getMaxDepth((Node<T>) getRoot().getLeftChild());
        int maxRight = getMaxDepth((Node<T>) getRoot().getRightChild());

        if (maxLeft>=maxRight){
            setHeight(maxLeft + 1);
        }
        else
            setHeight(maxRight + 1);

    }

    private void updateHeight(Node<T> node) {
        if (node == null) {
            return;
        }
        if (node.getParent() == null) {
            node.setNodeDepth(0);
        } else {
            node.setNodeDepth(node.getParent().getNodeDepth() + 1);
        }
        updateHeight((Node<T>) node.getLeftChild());
        updateHeight((Node<T>) node.getRightChild());
    }

    private Node<T> getUnbalancedNode(Node<T> parentNode) {

        int maxLeftDepth = getMaxDepth((Node<T>) parentNode.getLeftChild());
        if (maxLeftDepth == 0){
            maxLeftDepth = parentNode.getNodeDepth();
        }
        int maxRightDepth = getMaxDepth((Node<T>) parentNode.getRightChild());
        if (maxRightDepth == 0){
            maxRightDepth = parentNode.getNodeDepth();
        }

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
        if (parentNode == null)
            return 0;
        if (parentNode.getLeftChild() == null) {
            maxLeftDepth = parentNode.getNodeDepth();
        }
        else
            maxLeftDepth = getMaxDepth((Node<T>) parentNode.getLeftChild());

        if (parentNode.getRightChild() == null) {
            maxRightDepth = parentNode.getNodeDepth();
        }
        else
            maxRightDepth = getMaxDepth((Node<T>) parentNode.getRightChild());

        return Math.max(maxLeftDepth, maxRightDepth);
    }

    private Node<T> successor(Node<T> node) {
        if (node.getLeftChild() != null) {
            node= successor((Node<T>) node.getLeftChild());
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
