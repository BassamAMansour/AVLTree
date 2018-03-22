package eg.edu.alexu.csd.filestructure;

import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.Implementation.AVLTree;

public class Main {

    public static void main(String[] args) {
        IAVLTree avl = new AVLTree();
        int[] input = {13,8,5,9,4,6,12,2,1,3};
// int[] height = {0,1,1,2,2,2,2,3,3,3};
        int[] height = {1, 2, 2, 3, 3, 3, 3, 4, 4, 4};


//        for (int i = 0; i <input.length; ++i) {
//            avl.insert(input[i]);
//            System.out.println(avl.height());
//
//        }
        for (int i = 1; i <1000; ++i) {
            System.out.println(i);
            avl.insert(i);
        }

//        int[] input = {13,8,5,9,4,6,12,2,1,3};
//        int[] positive = {8,12,3};
//        int[] negative = {0,11,20};
//
//        for (int i = 0; i <input.length; ++i) {
//            avl.insert(input[i]);
//        }
//        for (int i = 0; i <positive.length; ++i)
//            System.out.print(avl.search(positive[i]));
//        for (int i = 0; i <negative.length; ++i)
//            System.out.print(avl.search(negative[i]));
//        int[] input = {13, 8, 5, 9, 4, 6, 12, 2, 1, 3};
//
//        for (int i = 0; i < input.length; ++i)
//            avl.insert(input[i]);
//// try deleting non-existing elements
////        for (int i = -1; i >= -5; --i) {
////            System.out.print(avl.delete(i));
////        }
//
//        int[] deleteOrder = {8, 4, 2, 12, 9, 13, 5, 3, 1, 6};
//        for (int element : deleteOrder) {
//            System.out.print(avl.delete(element));
//
//        }
    }
}
