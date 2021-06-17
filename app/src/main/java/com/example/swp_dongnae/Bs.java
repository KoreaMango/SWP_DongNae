package com.example.swp_dongnae;

public class Bs {
    //파이어베이스에서 가져올 값들 담을 변수들 선언
    private String activity;
    private String purpose;
    private String captain;
    private String category;
    private String email;
    private String tel;
    private String media;

    public Bs(){  }


    //생성자
    public Bs(String activity, String purpose, String captain, String category, String email, String tel) {
        this.activity = activity;
        this.purpose = purpose;
        this.captain = captain;
        this.category = category;
        this.email = email;
        this.tel = tel;
    }

    public String getActivity() {
        return activity;
    }
    public String getPurpose() {
        return purpose;
    }
    public String getCaptain() { return captain; }
    public String getCategory() {
        return category;
    }
    public String getEmail() {
        return email;
    }
    public String getTel() {
        return tel;
    }
    public String getMedia() { return media; }


    public void setActivity(String activity) {
        this.activity = activity;
    }
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    public void setCaptain(String captain) {
        this.captain = captain;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public void setMedia(String media) { this.media = media; }
}
