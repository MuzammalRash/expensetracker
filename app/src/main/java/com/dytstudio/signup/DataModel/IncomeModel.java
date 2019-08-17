package com.dytstudio.signup.DataModel;

public class IncomeModel {

    String uid;
    String income;

    public IncomeModel(String uid, String income) {
        this.uid = uid;
        this.income = income;
    }

    public IncomeModel() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }
}
