package com.example.myapplication;

public class Expense
{
    private Double amount;
    private String title;
    private Integer userid;
    private Integer userGroup;

    public Expense(Double amount, String title, int userid, int userGroup)
    {
        this.amount = amount;
        this.title = title;
        this.userid = userid;
        this.userGroup = userGroup;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(Integer userGroup) {
        this.userGroup = userGroup;
    }
}
