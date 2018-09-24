package com.example.yanjx.myapplication.mode;

public class Content {

    private String imgUrl;
    private String titel;
    private String date;

    public Content(String imgUrl, String titel, String date) {
        this.imgUrl = imgUrl;
        this.titel = titel;
        this.date = date;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getTitel() {
        return titel;
    }

    public String getDate() {
        return date;
    }

    public void setImgUrl(String imgUrl) {

        this.imgUrl = imgUrl;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
