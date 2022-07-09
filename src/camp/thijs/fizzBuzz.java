package camp.thijs;

import java.util.Scanner;

public class fizzBuzz {

    public static void main(String[] args) {
    
        /*
        * If the number is divisible by 5: Fizz
        * 
        * If the number is divisible by 3: Buzz
        * 
        * If the number is divisible by 5 and 3: FizzBuzz
        * 
        * If the number is not divisible by 5 and 3: show the number
        */

       // create Scanner object for Input
       Scanner inputUser = new Scanner(System.in);

       /*
        * Step 1: Ask for a number
        */
        System.out.print("Number: ");
        int number = inputUser.nextInt(); 

       /*
        * Step 2: Check if the number is divisible by 5
        */
        if (number % 5 == 0 && number % 3 == 0) {
            System.out.println("FizzBuzz");
        } else if((number % 5) == 0) {
            System.out.println("Fizz");
        } else if((number % 3) == 0) {
            System.out.println("Buzz");
        } else {
            System.out.println(number);
        }

    }
}
