package com.example.swp_dongnae;

public class NoticeSub {

    private String des;
    private String user;
    private String date;
    private String title;

    public NoticeSub() {
    }
    public NoticeSub(String mDes, String mUser, String mDate,String mTitle){ // qna, 협업 게시글 서브 객체
        this.des = mDes;
        this.user = mUser;
        this.date = mDate;
        this.title = mTitle;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() { return date; }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }
}