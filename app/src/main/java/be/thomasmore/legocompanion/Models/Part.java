package be.thomasmore.legocompanion.Models;

import java.util.List;

public class Part {
    private long partID;
    private String partName;
    private int legoCode;
    private double price;
    private String color;

    private List<SetPart>setParts;
    private List<Image>images;

    public Part(){

    }

    public Part(long partID, String partName, int legoCode, double price, String color, List<SetPart> setParts, List<Image> images) {
        this.partID = partID;
        this.partName = partName;
        this.legoCode = legoCode;
        this.price = price;
        this.color = color;
        this.setParts = setParts;
        this.images = images;
    }

    public long getPartID() {
        return partID;
    }

    public void setPartID(long partID) {
        this.partID = partID;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public int getLegoCode() {
        return legoCode;
    }

    public void setLegoCode(int legoCode) {
        this.legoCode = legoCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<SetPart> getSetParts() {
        return setParts;
    }

    public void setSetParts(List<SetPart> setParts) {
        this.setParts = setParts;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
