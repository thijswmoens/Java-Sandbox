package camp.thijs;

import java.text.NumberFormat;
import java.util.Scanner;
import java.util.Currency;

public class mortgageCalculator {

    public static void main(String[] args) {
        
        int principal = 0;
        byte period = 0;
        float annualInterestRate = 0;

        // create Scanner object for Input
        Scanner inputUser = new Scanner(System.in); 

        /*
         * Step 1: Ask for principal form user
         */
         while(true) {
            System.out.print("Principal (1K - 1M): "); 
            principal = inputUser.nextInt();

            // Check for valid input
            if(principal >= 1000 && principal <= 1_000_000) 
                break;
            System.out.println("Enter a number between 1,000 and 1,000,000");
        }

        /*
         * Step 2: Ask for Annual Interest Rate
         */
        while(true) {
            System.out.print("Annual Interest Rate: ");
            annualInterestRate = inputUser.nextFloat(); 

            // Check for valid input
            if(annualInterestRate < 30) 
                break;              
            System.out.println("Enter a value greater than 0 and less than or equal to 30");
        }


        /*
         * Step 3: Ask for the period in Years
         */
        while(true) {
            System.out.print("Period (Years): ");
            period = inputUser.nextByte(); 

            // Check for valid input
            if(period <= 30) 
                break;         
            
            System.out.println("Enter a value between 1 and 30");
        }

        /*
         * Step 4: Calculate Mortgage
         */
        double mortgage = calculateMortgage(principal, annualInterestRate, period);

        
        /*
         * Step 5: Format Mortgage to currency
         */
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String totalMortgage = currency.format(mortgage);   
        System.out.println("Mortgage: " + totalMortgage);
    }

    public static double calculateMortgage(
            int principal, 
            float annualInterest, 
            byte years) {

        // Create constants
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        float monthlyInterestRate = annualInterest / PERCENT / MONTHS_IN_YEAR; // Calculate monthly Interest Rate
        short numberOfPayments = (short)(years * MONTHS_IN_YEAR); // Calculate monthly payments

        float monthlyInterestRatePlusOne = 1 + monthlyInterestRate;
        double upperSum = Math.pow(monthlyInterestRatePlusOne, numberOfPayments); 
        double upperSumFinal = monthlyInterestRate * upperSum;
        double underSum = upperSum - 1;

        double totalSum = upperSumFinal / underSum;

        double mortgage = principal *  totalSum;
        return mortgage;
    }
}
