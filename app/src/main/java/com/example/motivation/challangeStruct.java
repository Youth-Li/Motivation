package com.example.motivation;

public class challangeStruct {
    private int level;
    private int days;
    private String challange;
    public challangeStruct(int level, int days, String s){
        this.level = level;
        this.days = days;
        this.challange = s;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getChallange() {
        return challange;
    }

    public void setChallange(String challange) {
        this.challange = challange;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
