package camp.thijs;

import java.text.NumberFormat;
import java.util.Scanner;
import java.util.Currency;

public class mortgageCalculator {

    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

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
        String totalMortgage = formatToCurrency(mortgage);
        System.out.println("\n\nMORTGAGE\n________\nMonthly Payments: " + totalMortgage);

        // Step 6: CaLculate Payment Schedule
        System.out.println("\nPAYMENT SCHEDULE\n________________\n");
        for(short month = 1; month <= period * MONTHS_IN_YEAR; month++) {
            double paymentSchedule = calculatePaymentSchedule(principal, annualInterestRate, period, month);
            String totalPayments = formatToCurrency(paymentSchedule);
            System.out.println(totalPayments);
        }
    }

    public static String formatToCurrency(double money) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String totalMoney = currency.format(money);  
        return totalMoney;
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


    public static double calculatePaymentSchedule(int principal, float annualInterest, byte years, short numberOfPaymentsMade) {

        float monthlyInterestRate = annualInterest / PERCENT / MONTHS_IN_YEAR; 
        float numberOfPayments = (short)(years * MONTHS_IN_YEAR); 

        double paymentSchedule = principal 
                * ( Math.pow(1 + monthlyInterestRate,  numberOfPayments) - Math.pow(1 + monthlyInterestRate, numberOfPaymentsMade) ) 
                / ( Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

        return paymentSchedule;
    }


    public static double calculateMortgage(int principal, float annualInterest, byte years) {

        float monthlyInterestRate = annualInterest / PERCENT / MONTHS_IN_YEAR; 
        short numberOfPayments = (short)(years * MONTHS_IN_YEAR); 

        float monthlyInterestRatePlusOne = 1 + monthlyInterestRate;
        double upperSum = Math.pow(monthlyInterestRatePlusOne, numberOfPayments); 
        double upperSumFinal = monthlyInterestRate * upperSum;
        double underSum = upperSum - 1;

        double totalSum = upperSumFinal / underSum;

        double mortgage = principal *  totalSum;
        return mortgage;
    }
}
