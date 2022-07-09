package camp.thijs;

import java.text.NumberFormat;
import java.util.Scanner;
import java.util.Currency;

public class mortgageCalculator {

    public static void main(String[] args) {
        
        // Step 1: Ask for principal from user
        int principal = (int)readNumber("Principal: ", 1000, 1_000_000);

        // Step 2: Ask for Annual Interest Rate
        float annualInterestRate = (float)readNumber("Annual Interest Rate: ", 0, 30);

        // Step 3: Ask for the period in Years
        byte period = (byte)readNumber("Period (Years): ", 1, 30);

        // Step 4: Calculate Mortgage
        double mortgage = calculateMortgage(principal, annualInterestRate, period);

        // Step 5: Format Mortgage to currency
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String totalMortgage = currency.format(mortgage);   
        System.out.println("Mortgage: " + totalMortgage);
    }


    public static double readNumber(String prompt, double min, double max) {

        Scanner inputUser = new Scanner(System.in); 
        double value;

        while(true) {
            System.out.print(prompt);
            value = inputUser.nextFloat(); 

            // Check for valid input
            if(value <= max) 
                break;              
            System.out.println("Enter a value between " + min + " and " + max);
        }

        return value;
    }


    public static double calculateMortgage(
            int principal, 
            float annualInterest, 
            byte years) {

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
