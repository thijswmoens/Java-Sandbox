package camp.thijs;

import java.text.NumberFormat;
import java.util.Scanner;
import java.util.Currency;

public class Main {

    public static void main(String[] args) {

        // Create constants
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        // create Scanner object for Input
        Scanner inputUser = new Scanner(System.in); 

        /*
         * Step 1: Ask for principal form user
         */
        System.out.print("Principal: "); // prompt the user for an input
        int principal = inputUser.nextInt(); // Input field for principal
    
        /*
         * Step 2: Ask for Annual Interest Rate
         */
        System.out.print("Annual Interest Rate: ");
        float annualInterestRate = inputUser.nextFloat(); 
        float monthlyInterestRate = annualInterestRate / PERCENT / MONTHS_IN_YEAR; // Calculate monthly Interest Rate

        /*
         * Step 3: Ask for the period in Years
         */
        System.out.print("Period (Years): ");
        byte period = inputUser.nextByte(); 
        int numberOfPayments = period * MONTHS_IN_YEAR; // Calculate monthly payments

        /*
         * Step 4: Calculate Mortgage
         */
        float monthlyInterestRatePlusOne = 1 + monthlyInterestRate;
        double upperSum = Math.pow(monthlyInterestRatePlusOne, numberOfPayments); 
        double upperSumFinal = monthlyInterestRate * upperSum;
        double underSum = upperSum - 1;

        double totalSum = upperSumFinal / underSum;

        double mortgage = principal *  totalSum;

        
        /*
         * Step 5: Format Mortgage to currency
         */
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String totalMortgage = currency.format(mortgage);   
        System.out.println("Mortgage: " + totalMortgage);
    }

        // Ask for Principal from user
        // public int principal() {
        //     Scanner principalInput = new Scanner(System.in); // create Scanner object for Input
        //     int principal; // this will be the return variable

        //     try {
        //         System.out.print("Principal: "); // prompt the user for an input
        //         principal = Integer.parseInt(principalInput.nextLine()); // Input field for principal
        //     } finally {
        //         principalInput.close();
        //     }

        //     return principal;
        // }
}
