package com.dytstudio.signup.DataModel;

public class RemaingModel {

    String uid;
    String income;

    public RemaingModel(String uid, String income) {
        this.uid = uid;
        this.income = income;
    }

    public RemaingModel() {
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

