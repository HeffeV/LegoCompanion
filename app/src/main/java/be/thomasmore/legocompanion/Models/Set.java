package be.thomasmore.legocompanion.Models;

import java.util.ArrayList;
import java.util.List;

public class Set {
    private long setID;
    private String setName;
    private int legoCode;
    private int age;
    private int pieces;
    private double price;
    private String theme;
    private int releaseYear;

    private Dimensions dimensions;
    private List<SetPart> setParts = new ArrayList<SetPart>();
    private List<Image> images=new ArrayList<Image>();

    public Set(){

    }

    public Set(long _id,String _setName, int _legoCode,int _age,int _pieces,
               double _price,String _theme,int _releaseYear,Dimensions _dimensions,
               List<SetPart>_setParts,List<Image>_images){
        this.setID = _id;
        this.setName = _setName;
        this.legoCode = _legoCode;
        this.age = _age;
        this.pieces = _pieces;
        this.price = _price;
        this.theme = _theme;
        this.releaseYear=_releaseYear;
        this.dimensions = _dimensions;
        this.setParts=_setParts;
        this.images = _images;
    }

    public long getSetID() {
        return setID;
    }

    public void setSetID(long setID) {
        this.setID = setID;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public int getLegoCode() {
        return legoCode;
    }

    public void setLegoCode(int legoCode) {
        this.legoCode = legoCode;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
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
