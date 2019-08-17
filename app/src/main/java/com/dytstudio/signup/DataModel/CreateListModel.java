package com.dytstudio.signup.DataModel;

public class CreateListModel {
    private String uid;
    private String list;
    private String listBudget;
    private String listRemaining;


    public CreateListModel(String uid, String list, String listBudget, String listRemaining) {
        this.uid = uid;
        this.list = list;
        this.listBudget = listBudget;
        this.listRemaining = listRemaining;

    }

    public CreateListModel() {
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getListBudget() {
        return listBudget;
    }

    public void setListBudget(String listBudget) {
        this.listBudget = listBudget;
    }

    public String getListRemaining() {
        return listRemaining;
    }

    public void setListRemaining(String listRemaining) {
        this.listRemaining = listRemaining;
    }
}
