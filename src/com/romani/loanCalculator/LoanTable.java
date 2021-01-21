package com.romani.loanCalculator;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoanTable extends Application {
    Integer payment;
    Double interest;
    Double principal;
    Double balance;
    @Override
    public void start(Stage primaryStage) {
        BorderPane border = new BorderPane();
        GridPane grid = new GridPane();
        ScrollPane scroll = new ScrollPane();
        Label labelTitle = new Label("Enter Loan Amount, Number of Years, and     Annual Interest Rate");
        labelTitle.getStyleClass().add("labelTitle");
        Label loan = new Label("Loan Amount");
        TextField loanAmount = new TextField();
        Label years = new Label("Number of Years");
        TextField numYears = new TextField();
        Label interestRate = new Label("Annual Interest Rate");
        TextField rate = new TextField();
        Button compute = new Button("Display Loan Schedule");

        TableView<Finance> table = new TableView<>();

        TableColumn paymentColumn = new TableColumn("Payment#");
        paymentColumn.setMinWidth(200);
        paymentColumn.setCellValueFactory(new PropertyValueFactory<Finance, Integer>("payment"));

        TableColumn interestColumn = new TableColumn("Interest");
        interestColumn.setMinWidth(200);
        interestColumn.setCellValueFactory(new PropertyValueFactory<Finance, Double>("interest"));

        TableColumn prinColumn = new TableColumn("Principal");
        prinColumn.setMinWidth(200);
        prinColumn.setCellValueFactory(new PropertyValueFactory<Finance, Double>("principal"));

        TableColumn balColumn = new TableColumn("Balance");
        balColumn.setMinWidth(200);
        balColumn.setCellValueFactory(new PropertyValueFactory<Finance, Double>("balance"));

        table.getColumns().addAll(paymentColumn, interestColumn, prinColumn, balColumn);

        grid.add(labelTitle, 0, 0);
        grid.add(new Label(), 0, 1);
        grid.add(loan, 0, 2);
        grid.add(loanAmount, 1, 2);
        grid.add(years, 0, 3);
        grid.add(numYears, 1, 3);
        grid.add(interestRate, 0, 4);
        grid.add(rate, 1, 4);
        grid.add(compute, 4, 0);

        scroll.setContent(table);

        border.setTop(grid);
        border.setBottom(scroll);

        Scene scene = new Scene(border, 800, 800);
        scene.getStylesheets().add("loan_table.css");

        primaryStage.setScene(scene);
        primaryStage.show();

        compute.setOnAction((e) -> {
            Double loanTotal = Double.parseDouble(loanAmount.getText());
            Integer year = Integer.parseInt(numYears.getText());
            Double rateInterest = Double.parseDouble(rate.getText());
            double monthlyInterest = rateInterest / 1200;
            double monthlyPayment = loanTotal * monthlyInterest / (1 - 1 / Math.pow(1 + monthlyInterest, year * 12));
            balance = loanTotal;

            for(int i = 1; i < year * 12; i++) {
                interest = monthlyInterest * balance;
                principal = monthlyPayment - interest;
                balance = balance - principal;
                payment = i + 1;
            }

            ObservableList<Finance> data = FXCollections.observableArrayList(new Finance(payment, interest, principal, balance));
            table.setItems(data);

        });

    }

    public static class Finance {
        private final SimpleIntegerProperty payment;
        private final SimpleDoubleProperty interest;
        private final SimpleDoubleProperty principal;
        private final SimpleDoubleProperty balance;

        private Finance(int payment, double interest, double principal, double balance) {
            this.payment = new SimpleIntegerProperty(payment);
            this.interest = new SimpleDoubleProperty(interest);
            this.principal = new SimpleDoubleProperty(principal);
            this.balance = new SimpleDoubleProperty(balance);
        }

        public Integer getPayment(int numYears) {
            return payment.get();
        }

        public Double getInterest(double rate) {

            return interest.get();
        }

        public Double getPrincipal() {
            return principal.get();
        }

        public Double getBalance() {
            return balance.get();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}