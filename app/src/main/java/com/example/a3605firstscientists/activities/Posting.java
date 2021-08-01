package com.example.a3605firstscientists.activities;

import com.google.firebase.database.ServerValue;

public class Posting {



    private String postKey;
    private String title;
    private String location;
    private double latitude;
    private double longitude;
    private String description;
    private String picture;
    private String userId;
    private String userPhoto;
    private Object timeStamp;


    public Posting(String title, String location, double latitude, double longitude, String description, String picture, String userId, String userPhoto) {
        this.title = title;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.picture = picture;
        this.userId = userId;
        this.userPhoto = userPhoto;
        this.timeStamp = ServerValue.TIMESTAMP;
    }


    public Posting() {

    }


    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public double getLatitude() {return latitude;}

    public double getLongitude() {return longitude;}

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picture;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLatitude(double latitude){this.latitude = latitude;}

    public void setLongitude(double longitude){this.longitude = longitude;}

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }
}
