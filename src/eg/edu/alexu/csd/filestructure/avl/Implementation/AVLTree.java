package eg.edu.alexu.csd.filestructure.avl.Implementation;

import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.INode;

public class AVLTree<T extends Comparable<T>> implements IAVLTree<T> {
    @Override
    public void insert(T key) {

    }

    @Override
    public boolean delete(T key) {
        return false;
    }

    @Override
    public boolean search(T key) {
        return false;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public INode<T> getTree() {
        return null;
    }
}
