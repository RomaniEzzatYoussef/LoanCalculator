package com.romani.loanCalculator;

import java.time.LocalDate;
import java.util.Scanner;


public class LoanCalculator {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter loan amount: ");
        double loanAmount = input.nextDouble();

        System.out.print("Enter annual interest rate(like 0.16): ");
        double annualInterestRate = input.nextDouble();

        System.out.print("Enter loan period in years: ");
        int loanPeriodInYears = input.nextInt();

        LocalDate startDateOfLoan = LocalDate.now();

        double monthlyInterestRate = annualInterestRate/12;
        int numberOfPayment = loanPeriodInYears*12;
        double formula = Math.pow(1+monthlyInterestRate, numberOfPayment);

        double monthlyPayment = loanAmount * (monthlyInterestRate * formula / (-1 + formula));
        double totalCostOfLoan = monthlyPayment * numberOfPayment;
        double totalInterest = totalCostOfLoan - loanAmount;

        double monthlyInterest;
        double principal;
        double beginningBalance = loanAmount;
        double endingBalance;

        System.out.println("monthlyPayment: " + monthlyPayment);
        System.out.println("numberOfPayment: " + numberOfPayment);
        System.out.println("totalInterest: " + totalInterest);
        System.out.println("totalCostOfLoan: " + totalCostOfLoan);


        System.out.printf("\n%-15s%-13s%-18s%-16s%-10s%-17s%-14s","Payment-Number","Payment-Date","Beginning-Balance","Monthly-Payment","Principal","Monthly-Interest","Ending-Balance");
        for (int i = 1; i <= numberOfPayment; i++) {
            startDateOfLoan = startDateOfLoan.plusMonths(1);
            String formattedDate = String.format("%1$td/%1$tm/20%1$ty", startDateOfLoan);

            monthlyInterest = ((annualInterestRate/numberOfPayment) * beginningBalance)*10;
            principal = monthlyPayment - monthlyInterest;
            endingBalance = beginningBalance-principal;
            System.out.printf("\n%-15d%-13s%-18.2f%-16.2f%-10.2f%-17.2f%-14.2f",i,formattedDate,beginningBalance,monthlyPayment,principal,monthlyInterest,endingBalance);
            beginningBalance = endingBalance;
        }

    }
}
