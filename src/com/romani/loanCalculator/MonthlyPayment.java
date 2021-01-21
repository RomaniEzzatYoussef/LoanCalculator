package com.romani.loanCalculator;

import java.time.LocalDate;


public class MonthlyPayment {

    public static void main(String[] args) {

        double loanAmount = 1000000;
        double annualInterestRate = 0.16;
        int loanPeriodInYears = 10;
        LocalDate startDateOfLoan = LocalDate.now();

        double monthlyInterestRate = annualInterestRate/12;
        int numberOfPayment = loanPeriodInYears*12;
        double f = Math.pow(1+monthlyInterestRate, numberOfPayment);

        double monthlyPayment = loanAmount * (monthlyInterestRate * f / (-1 + f));
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
            String formattedDate = String.format("%1$td.%1$tm.20%1$ty", startDateOfLoan);

            monthlyInterest = ((annualInterestRate/numberOfPayment) * beginningBalance)*10;
            principal = monthlyPayment - monthlyInterest;
            endingBalance = beginningBalance-principal;
            System.out.printf("\n%-15d%-13s%-18.2f%-16.2f%-10.2f%-17.2f%-14.2f",i,formattedDate,beginningBalance,monthlyPayment,principal,monthlyInterest,endingBalance);
            beginningBalance = endingBalance;
        }

    }
}
