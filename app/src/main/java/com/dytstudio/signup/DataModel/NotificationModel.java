package com.dytstudio.signup.DataModel;

public class NotificationModel {

    private String msg;
    private String idkey;
   private String setIncome;

    public NotificationModel() {
    }

    public NotificationModel(String msg, String idkey, String setIncome) {
        this.msg = msg;
        this.idkey = idkey;
        this.setIncome = setIncome;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getIdkey() {
        return idkey;
    }

    public void setIdkey(String idkey) {
        this.idkey = idkey;
    }

    public String getSetIncome() {
        return setIncome;
    }

    public void setSetIncome(String setIncome) {
        this.setIncome = setIncome;
    }
}
