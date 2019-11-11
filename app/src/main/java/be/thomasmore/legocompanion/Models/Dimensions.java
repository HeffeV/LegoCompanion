package be.thomasmore.legocompanion.Models;

public class Dimensions {
    private long dimensionsID;
    private double length;
    private double width;
    private double height;


    public Dimensions(){

    }

    public Dimensions(long dimensionsID, double length, double width, double height) {
        this.dimensionsID = dimensionsID;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public long getDimensionsID() {
        return dimensionsID;
    }

    public void setDimensionsID(long dimensionsID) {
        this.dimensionsID = dimensionsID;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
