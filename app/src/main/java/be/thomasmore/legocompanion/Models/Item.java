package be.thomasmore.legocompanion.Models;

public class Item {
    private long itemID;
    private int setOrPart;
    private String name;
    private String description;
    private String imageUrl;

    public Item() {
    }

    public Item(long itemID, int setOrPart, String name, String description, String imageUrl) {
        this.itemID = itemID;
        this.setOrPart = setOrPart;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    public int getSetOrPart() {
        return setOrPart;
    }

    public void setSetOrPart(int setOrPart) {
        this.setOrPart = setOrPart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
