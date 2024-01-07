package com.example.myapplication;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Expense
{
    @SerializedName("id")
    private Integer id;
    @SerializedName("kwota")
    private Double amount;
    @SerializedName("tytul")
    private String title;
    @SerializedName("idKlientaUser")
    private Integer userid;
    @SerializedName("idKlientaGrupa")
    private Integer userGroup;

    public Expense(Double amount, String title, int userid, int userGroup)
    {
        this.amount = amount;
        this.title = title;
        this.userid = userid;
        this.userGroup = userGroup;
    }
    public Expense(Double amount, String title)
    {
        this.amount = amount;
        this.title = title;
        userid = null;
        userGroup = null;
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

//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public Integer getUserid() {
//        return userid;
//    }
//
//    public void setUserid(Integer userid) {
//        this.userid = userid;
//    }
//
//    public Integer getUserGroup() {
//        return userGroup;
//    }
//
//    public void setUserGroup(Integer userGroup) {
//        this.userGroup = userGroup;
//    }

    @NonNull
    @Override
    public String toString() {
        return "Expense{" +
                "amount=" + amount +
                ", title='" + title + '\'' +
                ", userid=" + userid +
                ", userGroup=" + userGroup +
                '}';
    }
}
