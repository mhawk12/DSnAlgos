package cs6301.g29;

/**
* Created by Nishant Shekhar (nxs167130)
*            Anusha Agasthi  (nxa162430)
*            Prince Patel    (pap160930)
*/
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		AVLTree<Integer> avlTree = new AVLTree<Integer>();
		RedBlackTree<Integer> redBlackTree = new RedBlackTree<Integer>();

		Scanner in = new Scanner(System.in);
		System.out.println("Enter 1 for adding and removing elements in a AVL tree");
		System.out.println("Enter 2 for adding and removing elements in a Red Black tree");
		int options = in.nextInt();

		switch (options) {
		case 1: {
			System.out.println("Inserting values 1 to 9 in AVL tree");
			for (int i = 1; i < 10; i++)
				avlTree.add(new Integer(i));

			System.out.println("Enter the value to be removed. Choose any number from 1 to 9");

			while (in.hasNext()) {
				int remove = in.nextInt();

				Object removedValue = avlTree.remove(remove);
				if (removedValue == null) {
					System.out.println("Please enter correct number to delete");

				}

				else {
					System.out.println("Removed value: " + removedValue);
					System.out.println("Final inorder traversal of AVL tree is as follows");
					avlTree.printTree();
				}
			}

			break;
		}
		case 2: {

			System.out.println("Inserting values 1 to 9 in Red Black tree");
			for (int i = 1; i < 10; i++)
				redBlackTree.add(new Integer(i));
			redBlackTree.printTree();

			redBlackTree.remove(2);
			System.out.println("After removing two ");
			redBlackTree.printTree();

			break;
		}

		default:
			System.out.println("Invalid Option");
			System.out.println("Please choose the correct option");
		}
		in.close();

	}

}
