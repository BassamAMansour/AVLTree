package eg.edu.alexu.csd.filestructure.avl.Implementation;

import eg.edu.alexu.csd.filestructure.avl.INode;

public class Node<T extends Comparable<T>> implements INode<T> {

    private int nodeDepth;
    private Node<T> parent;
    private Node<T> leftChild;
    private Node<T> rightChild;
    private T value;


    public Node( T value, int nodeDepth, Node<T> parent) {
        this.nodeDepth = nodeDepth;
        this.parent = parent;
        this.value = value;
    }

    public int getNodeDepth() {
        return nodeDepth;
    }

    public void setNodeDepth(int nodeDepth) {
        this.nodeDepth = nodeDepth;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public boolean isALeftChild(){
        if(this.getParent()!= null && this.getParent().getLeftChild()==this){
            return true;
        }
        return false;
    }

    public boolean isARightChild(){
        if(this.getParent()!= null && this.getParent().getRightChild()==this){
            return true;
        }
        return false;
    }

    @Override
    public INode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    @Override
    public INode<T> getRightChild() {

        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }
}
