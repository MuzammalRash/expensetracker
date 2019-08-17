package com.dytstudio.signup.DataModel;

public class ReportModel {

    String uid;
    String name;
    String percent;

    public ReportModel() {
    }

    public ReportModel(String uid, String name, String percent) {
        this.uid = uid;
        this.name = name;
        this.percent = percent;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}
