package be.thomasmore.legocompanion.Models;

public class Image {
    private long imageID;
    private String imageUrl;

    public Image(){

    }

    public Image(long imageID, String imageUrl) {
        this.imageID = imageID;
        this.imageUrl = imageUrl;
    }

    public long getImageID() {
        return imageID;
    }

    public void setImageID(long imageID) {
        this.imageID = imageID;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
