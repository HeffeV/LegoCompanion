package be.thomasmore.legocompanion.Models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private long userID;
    private String googleID;
    private String email;

    private List<Part> favoriteParts=new ArrayList<Part>();
    private List<Part> wishlistParts=new ArrayList<Part>();
    private List<Set> favoriteSets=new ArrayList<Set>();
    private List<Set> wishlistSets=new ArrayList<Set>();
    private List<Part> collectionParts=new ArrayList<Part>();
    private List<Set> collectionSets=new ArrayList<Set>();

    public User(){

    }

    public User(long userID, String googleID, String email, List<Part> favoriteParts, List<Part> wishlistParts, List<Set> favoriteSets, List<Set> wishlistSets, List<Part> collectionParts, List<Set> collectionSets) {
        this.userID = userID;
        this.googleID = googleID;
        this.email = email;
        this.favoriteParts = favoriteParts;
        this.wishlistParts = wishlistParts;
        this.favoriteSets = favoriteSets;
        this.wishlistSets = wishlistSets;
        this.collectionParts = collectionParts;
        this.collectionSets = collectionSets;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getGoogleID() {
        return googleID;
    }

    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Part> getFavoriteParts() {
        return favoriteParts;
    }

    public void setFavoriteParts(List<Part> favoriteParts) {
        this.favoriteParts = favoriteParts;
    }

    public List<Part> getWishlistParts() {
        return wishlistParts;
    }

    public void setWishlistParts(List<Part> wishlistParts) {
        this.wishlistParts = wishlistParts;
    }

    public List<Set> getFavoriteSets() {
        return favoriteSets;
    }

    public void setFavoriteSets(List<Set> favoriteSets) {
        this.favoriteSets = favoriteSets;
    }

    public List<Set> getWishlistSets() {
        return wishlistSets;
    }

    public void setWishlistSets(List<Set> wishlistSets) {
        this.wishlistSets = wishlistSets;
    }

    public List<Part> getCollectionParts() {
        return collectionParts;
    }

    public void setCollectionParts(List<Part> collectionParts) {
        this.collectionParts = collectionParts;
    }

    public List<Set> getCollectionSets() {
        return collectionSets;
    }

    public void setCollectionSets(List<Set> collectionSets) {
        this.collectionSets = collectionSets;
    }


}
