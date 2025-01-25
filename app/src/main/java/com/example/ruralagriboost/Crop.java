package com.example.ruralagriboost;

public class Crop {
    private final String name;
    private final String description;
    private final int imageResource;

    public Crop(String name, String description, int imageResource) {
        this.name = name;
        this.description = description;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResource() {
        return imageResource;
    }
}
