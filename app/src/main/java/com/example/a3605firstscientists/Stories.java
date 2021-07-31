package com.example.a3605firstscientists;

import java.util.ArrayList;

public class Stories {

    // Constructor
    public Stories (String id, String quote, String person, Integer image){
        this.id = id;
        this.quote = quote;
        this.person = person;
        this.image = image;
    }

    private String id;
    private String quote;
    private String person;
    private Integer image;

    // Getters and setters
    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getPerson() { return person; }

    public void setPerson(String person) { this.person = person; }

    public String getQuote() { return quote; }

    public void setQuote(String quote) { this.quote = quote; }

    public Integer getImage() { return image; }

    public void setImage(Integer image) { this.image =image; }

    // Create an array list containing data
    public static ArrayList<Stories> getStories() {
        ArrayList<Stories> stories = new ArrayList<>();
        stories.add(new Stories ("1", "We want to be included in the climate policy, not just like ticking the box for an environmental program. The people here are talking about how the birds' movements across country have changed, so that's changing songlines that they have been singing for thousands and thousands of years, impacting their community and culture.", "- Bianca McNeair (Malagna Woman, Gatharagudu)", R.drawable.voicebgone));
        stories.add(new Stories ("3", "The Government don't seem to recognise the deep connection between land and identity ... especially when it is threatened by their climate policies. Neither do the rest of the public - I think mostly due to lack of shared knowledge about my culture and the other cultures.", "-Oliver Whitely (Dandaloo Mob, Wiradjuri Clan", R.drawable.voicebgthree));
        stories.add(new Stories ("5", "Indigenous peoples are repositories of learning and knowledge about how to cope successfully with local-level climate change and respond effectively to major environmental changes such as natural disasters.", "- Myrna Cunningham Kain (Pawanka Fund)", R.drawable.voicebgfive));
        stories.add(new Stories ("4", "What's not discussed as much is the impact that climate breakdown has had on our cultures. My community originally created our calendar based on the native wildlife surrounding or home to keep track of agricultural and hunting seasons. Within the past decade, most of the wildlife featured in this calendar has disappeared due to climate change.", "- Minnie Degawan (Kankanaey-Igorot Group)", R.drawable.voicebgfour));
        stories.add(new Stories ("2", "Climate change is a clear and present threat to the survival of our people and their culture. During our summers you can see people hosing the outside of their brick walls to keep cool despite the water shortages - that's how desperate they are.", "- Josie Douglas (FNP, Alice Springs)", R.drawable.voicebgtwo));
        return stories;
    }

    public static Stories getTitle(String id) {
        // Implement a method that returns one list item based on it's id
        // Get the list from the java class
        ArrayList<Stories> story = Stories.getStories();
        // Loop through and compare the parameter(id) with each row
        for(final Stories stories : story) {
            if(stories.getId().equals(id)){
                return stories;
            }
        }
        return null;
    }


}

