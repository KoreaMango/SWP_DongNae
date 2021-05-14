package com.example.swp_dongnae;

public class Reple {

    String user; // name
    String description; // text


    public Reple(String user, String description) {
        this.user = user;
        this.description = description;
    }




    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDescription() { return description;}

    public void setDescription(String description) {this.description=description;}

}
