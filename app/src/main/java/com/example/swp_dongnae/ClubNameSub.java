package com.example.swp_dongnae;

public class ClubNameSub {
    String textView;
    String imageView;

    public ClubNameSub(String textView, String imageView){
        this.textView = textView;
        this.imageView = imageView;
    }
    public ClubNameSub(String textView){
        this.textView = textView;
    }
    public String getTextView() {return textView;}
    public void setTextView(String textView) {this.textView = textView;}
    public String getImageView() {return imageView;}
    public void setImageView(String imageView) {this.imageView = imageView;}


}