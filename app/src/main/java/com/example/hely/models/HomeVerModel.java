package com.example.hely.models;

public class HomeVerModel {

    int image;
    String name;
    String sanatciAdi;
    String rating;

    public HomeVerModel(int image, String name, String sanatciAdi, String rating) {
        this.image = image;
        this.name = name;
        this.sanatciAdi = sanatciAdi;
        this.rating = rating;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSanatciAdi() {
        return sanatciAdi;
    }

    public void setSanatciAdi(String sanatciAdi) {
        this.sanatciAdi = sanatciAdi;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
