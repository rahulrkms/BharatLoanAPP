package com.example.bharatloan.model;

public class ModelFetchRepaymentDetails {
    String loan_amount,pay_again,payment_link,quotes,mob_num;

    public ModelFetchRepaymentDetails(String loan_amount, String pay_again, String payment_link, String quotes, String mob_num) {
        this.loan_amount = loan_amount;
        this.pay_again = pay_again;
        this.payment_link = payment_link;
        this.quotes = quotes;
        this.mob_num = mob_num;
    }

    public String getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(String loan_amount) {
        this.loan_amount = loan_amount;
    }

    public String getPay_again() {
        return pay_again;
    }

    public void setPay_again(String pay_again) {
        this.pay_again = pay_again;
    }

    public String getPayment_link() {
        return payment_link;
    }

    public void setPayment_link(String payment_link) {
        this.payment_link = payment_link;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    public String getMob_num() {
        return mob_num;
    }

    public void setMob_num(String mob_num) {
        this.mob_num = mob_num;
    }
}
