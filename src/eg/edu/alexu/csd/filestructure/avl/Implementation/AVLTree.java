package eg.edu.alexu.csd.filestructure.avl.Implementation;

import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.INode;

public class AVLTree<T extends Comparable<T>> implements IAVLTree<T> {

    private int numberOfNodes;
    private int height;
    private Node<T> parent;


    public AVLTree() {
    }

    @Override
    public void insert(T key) {

        boolean inserted = false;

        if (key == null) {
            return;
        }

        if (getNumberOfNodes() == 0 || getParent() == null) {
            setParent(new Node<>(key, 0, null));
            inserted = true;
        } else {
            inserted = insertNonParentNode(key);
        }

        if (inserted) {
            setNumberOfNodes(getNumberOfNodes() + 1);
            balanceTree();
            updateHeight();
        }
    }

    @Override
    public boolean delete(T key) {
        if (search(key)) {

            //TODO: Akromty
            //TODO: delete the key after it's found in the tree

            setNumberOfNodes(getNumberOfNodes() - 1);
            balanceTree();
            updateHeight();
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
        return getParent();
    }

    private boolean insertNonParentNode(T key) {
        return insertNonParentNode(getParent(), key);
    }

    private boolean insertNonParentNode(Node<T> currentNode, T key) {
        //TODO: implement the insertion for non parent node using recursion
        return false;
    }

    private void updateHeight() {

        //TODO: Akromty
        //TODO: Update the tree height
        //Called after an insertion or deletion
    }

    private void balanceTree() {

        //TODO: Akromty or Bassam
        //TODO: balance the tree by making rotations at the non balanced branches
        //Called after an insertion or deletion
    }


    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public void setNumberOfNodes(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

}
