package com.niit.ToDo.model;

public class Image {

    private int imageId;
    private String imageUrl;
    private String imageName;

    public Image() {
    }

    public Image(int imageId, String imageUrl, String imageName) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
        this.imageName = imageName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
