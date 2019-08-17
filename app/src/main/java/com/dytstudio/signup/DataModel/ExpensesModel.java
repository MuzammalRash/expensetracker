package com.dytstudio.signup.DataModel;

public class ExpensesModel {

    private String uid;
    private String date;
    private String description;
    private String amount;

    public ExpensesModel() {
    }

    public ExpensesModel(String uid, String date, String description, String amount) {
        this.uid = uid;
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
