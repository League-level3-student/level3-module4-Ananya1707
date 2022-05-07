package _00_IntroToStacks;

import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

public class _01_IntroToStack {
    public static void main(String[] args) {
        // 1. Create a Stack of Doubles
        //    Don't forget to import the Stack class
        Stack<Double> numbers = new Stack<Double>();

        // 2. Use a loop to push 100 random doubles between 0 and 100 to the Stack.
        Random r = new Random(); 
        for(int i = 0; i<101; i++) {
        	numbers.push(r.nextDouble()*100);
        	
        }

        // 3. Ask the user to enter in two numbers between 0 and 100, inclusive. 
        String input0 = JOptionPane.showInputDialog("Enter one number between 0 and 100, inclusive");
        String input1 = JOptionPane.showInputDialog("Enter another number between 0 and 100. inclusive");
        
        int num0 = Integer.parseInt(input0);
        int num1 = Integer.parseInt(input1);
        
        if(num1 <= num0) {
        	int temp = num0;
        	num0 = num1; 
        	num1 = temp;
        }
        
        System.out.println("NUM 1: " + num0);
        System.out.println("NUM 2: " + num1);
        System.out.println();

        // 4. Pop all the elements off of the Stack. Every time a double is popped that is
        //    between the two numbers entered by the user, print it to the screen.
        System.out.println("Popping elements off stack...");
        System.out.println("Elements between " + num0 + " and " + num1 + " :");
        Double num = 0.0;
        
        for(int i = 0; i<101; i++) {
        	num = numbers.pop();
        	if(num >= num0 && num <= num1) {
        		System.out.println(num);
        	}
        }


        // EXAMPLE:
        // NUM 1: 65
        // NUM 2: 75

        // Popping elements off stack...
        // Elements between 65 and 75:
        // 66.66876846
        // 74.51651681
        // 70.05110654
        // 69.21350456
        // 71.54506465
        // 66.47984807
        // 74.12121224
    }
}
