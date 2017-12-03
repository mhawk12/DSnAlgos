/**
 * Created by
 * Nishant Shekhar (nxs167130)
 * Anusha Agasthi  (nxa162430)
 * Prince Patel    (pap160930)
 */

package cs6301.g29;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class SkipListDriver {
	public static void main(String[] args) throws FileNotFoundException {
		// If fileName is given as an argument, it adds all the elements in the file to
		// the list. All other operations can be performed here after.
		// Else the elements to be added are fed through console.
		Scanner sc = null;
		SkipList<Integer> skipList = new SkipList<>();
		// CAN ADD MAXIMUM OF 2*10^9 elements
		// adding elements in the skipList
		if (args.length > 0) {
			File file = new File(args[0]);
			sc = new Scanner(file);
			while (sc.hasNext())
				skipList.add(sc.nextInt());
		} else {
			sc = new Scanner(System.in);
		}

		System.out.println("Choose one of the options");
		System.out.println("stop to halt the query processing");
		System.out.println("0 for add(x)");
		System.out.println("1 for ceiling(x) ");
		System.out.println("2 for contains(x)");
		System.out.println("3 for first()");
		System.out.println("4 for floor()");
		System.out.println("5 for isEmpty()");
		System.out.println("6 for iterator()");
		System.out.println("7 for last()");
		System.out.println("8 for remove(x)");
		System.out.println("9 for size()");

		int operation = sc.nextInt();
		int operand = 0;

		// CAN ADD MAXIMUM OF 2*10^9 elements
		// adding elements in the skipList
		String halt = "";
		while (!halt.equalsIgnoreCase("stop")) {
			switch (operation) {
			case 0:
				System.out.println("Enter a element to add int the list");
				operand = sc.nextInt();
				if (skipList.add(operand))
					System.out.println(operand + " " + "addded");
				else
					System.err.println(operand + " " + "is already in the list");
				break;

			case 1:
				System.out.println("Enter a element to find its ceil value in the list");
				operand = sc.nextInt();
				System.out.println("Ceiling : " + skipList.ceiling(operand));
				break;

			case 2:
				System.out.println("Enter a element to check wehter \n it exists in the list or not");
				operand = sc.nextInt();
				if (skipList.contains(operand))
					System.out.println("Element exists in the list");
				else
					System.err.println("Element does not exist in the list");

				break;

			case 3:
				System.out.println("First Element : " + skipList.first());
				break;

			case 4:
				System.out.println("Enter a element to find its floor value in the list");
				operand = sc.nextInt();
				System.out.println("Floor : " + skipList.floor(operand));
				break;

			case 5:
				System.out.println("Is the List Empty : " + skipList.isEmpty());
				break;

			case 6:
				System.out.println();
				Iterator<Integer> l = skipList.iterator();
				while (l.hasNext()) {
					System.out.println(l.next());
				}
				break;

			case 7:
				System.out.println("Last Element : " + skipList.last());
				break;

			case 8:
				System.out.println("Enter the element to be removed" + " ");
				operand = sc.nextInt();
				System.out.println("Removing" + " " + operand);

				if (skipList.remove(operand) != null)
					System.out.println(operand + " " + "removed");
				else
					System.err.println(operand + " " + "is not present in the list");

				break;

			case 9:
				System.out.println("Size of the list : " + skipList.size());
				break;

			default:
				System.err.println("Invalid Input, PLease Choose from given options");
				break;

			}

			System.out.println("enter stop to end or Any other value to continue");
			halt = sc.next();
			System.out.println("Enter one of the options");
			operation = sc.nextInt();
		}
		sc.close();
	}
}
