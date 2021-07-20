package com.example.clorofill_app;

public class ModelClassOfMyOrders {

    private String imageView1;
    private String nameTextView;
    private String amountTextView;
    private String dateTextView;
    private String idTextView;
    private String shareIdTextView;
    private String numberTextView;

    ModelClassOfMyOrders(String imageView1, String nameTextView,String amountTextView,String dateTextView, String idTextView,String shareIdTextView, String numberTextView){

        this.imageView1=imageView1;
        this.nameTextView=nameTextView;
        this.amountTextView=amountTextView;
        this.dateTextView=dateTextView;
        this.idTextView=idTextView;
        this.shareIdTextView=shareIdTextView;
        this.numberTextView=numberTextView;

    }

    public String getImageView1() {
        return imageView1;
    }

    public String getNameTextView() {
        return nameTextView;
    }

    public String getAmountTextView() {
        return amountTextView;
    }

    public String getDateTextView() {
        return dateTextView;
    }

    public String getIdTextView() { return idTextView; }

    public String getShareIdTextView() { return shareIdTextView; }

    public String getNumberTextView() { return numberTextView; }
}
