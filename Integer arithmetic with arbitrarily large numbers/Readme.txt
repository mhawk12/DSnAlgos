Readme file for LP1 for Group g29.

Team Members:

Nishant Shekhar (nxs167130)
Anusha Agasthi (nxa162430)
Prince Patel (pap160930)


Required file:
Num.java

1. The driver code for the program is in the main method in the same class.
2. We have implemented Karatsuba's algorithm for finding the product of the two numbers.
3. We have implemented this program only for base 10.
4. While running any method say add, comment other method calls, inorder to account for the correct sign in the output.

For example while running add method, 

inputs:

Num num1 = new Num("3");
Num num2 = new Num(2);
Num outAdd = add(num1, num2);

output:  

System.out.println("Add : " + outAdd.toString());
outAdd.printList();

comment the remaining method calls in the main method of Num.java.

Note:

The test input LP1L1Test.java is working fine on our code.
The test input LP1L2Test.java is working fine for subtract, divide, mod but is throwing a stack overflow exception for power as the numbers are huge 
(We tried increasing our stack size to 10240M (-Xss10240M), even then it did not work, it worked till 9 instead of 13.). 
As squareroot is depending on the output of the power, it is also not been executed.
Inorder to check if it is working for subtract, divide and mod, just comment the code related to power and squareroot and try running the code in LP1L2Test.java, it should work.