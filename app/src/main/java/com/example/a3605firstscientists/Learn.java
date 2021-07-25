package com.example.a3605firstscientists;

import java.util.ArrayList;

public class Learn {

    // Constructor
    public Learn (String id, String title, String message, String moreMessage, Integer image){
        this.id = id;
        this.title = title;
        this.message = message;
        this.moreMessage = moreMessage;
        this.image = image;
    }

    private String id;
    private String title;
    private String message;
    private String moreMessage;
    private Integer image;

    // Getters and setters
    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String movie) { this.title = title; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public String getMoreMessage() { return moreMessage; }

    public void setMoreMessage(String moreMessage) { this.moreMessage = moreMessage; }

    public Integer getImage() { return image; }

    public void setImage(Integer image) { this.image =image; }

    // Create an array list containing data for each of the 10 movies
    public static ArrayList<Learn> getLearns() {
        ArrayList<Learn> learn = new ArrayList<>();
        learn.add(new Learn("1", "Our Action Plan", "It's time for a climate-ready conversation", "LEARN MORE", R.drawable.climateimage));
        learn.add(new Learn("2", "Hear Our Stories", "The power of connecting with community and country", "LEARN MORE", R.drawable.storiesimage));
        learn.add(new Learn("3", "Support Us", "Humanity in action. And we're so grateful", "LEARN MORE", R.drawable.supportimage));
        return learn;
    }

    public static Learn getTitle(String id) {
        // Implement a method that returns one cardview based on it's id
        // Get the list of the movies from the Movie java class
        ArrayList<Learn> learns = Learn.getLearns();
        // Loop through the movies and compare the parameter(id) with the movie of each row
        for(final Learn learn : learns) {
            if(learn.getId().equals(id)){
                return learn;
            }
        }
        return null;
    }


}
