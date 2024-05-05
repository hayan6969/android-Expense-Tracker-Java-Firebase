package com.example.expensetrackerfirebase;

public class MainModel {
    String Amount,Date,Description,ItemName,Time,ToOrBy,participant;
    MainModel(){

    }
    // Constructor
    public MainModel(String amount, String date, String description, String itemName, String time, String ToOrBy, String participant) {
        Amount = amount;
        Date = date;
        Description = description;
        ItemName = itemName;
        Time = time;
        this.ToOrBy = ToOrBy;
        participant = participant;
    }
    // Getters
    public String getAmount() {
        return Amount;
    }

    public String getDate() {
        return Date;
    }

    public String getDescription() {
        return Description;
    }

    public String getItemName() {
        return ItemName;
    }

    public String getTime() {
        return Time;
    }

    public String getToOrBy() {
        return ToOrBy;
    }

    public String getParticipant() {
        return participant;
    }

    // Setters
    public void setAmount(String amount) {
        this.Amount = amount;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void setItemName(String itemName) {
        this.ItemName = itemName;
    }

    public void setTime(String time) {
        this.Time = time;
    }

    public void setToOrBy(String toOrBy) {
        this.ToOrBy = toOrBy;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }
}

