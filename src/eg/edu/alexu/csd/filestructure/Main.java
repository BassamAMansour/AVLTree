package eg.edu.alexu.csd.filestructure;

import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.Implementation.AVLTree;

public class Main {

    public static void main(String[] args) {
        IAVLTree avl = new AVLTree();
        int[] input = {13,8,5,9,4,6,12,2,1,3};
// int[] height = {0,1,1,2,2,2,2,3,3,3};
        int[] height = {1,2,2,3,3,3,3,4,4,4};

        for (int i = 1; i <1000; ++i) {
            System.out.println(i);
            avl.insert(i);
        }
    }
}
