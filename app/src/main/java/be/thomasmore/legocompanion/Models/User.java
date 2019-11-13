package be.thomasmore.legocompanion.Models;

import java.util.List;

public class User {
    private long user;
    private String googleID;
    private String email;

    private List<Part> favoriteParts;
    private List<Part> wishlistParts;
    private List<Set> favoriteSets;
    private List<Set> wishlistSets;
    private List<Part> collectionParts;
    private List<Set> collectionSets;

    public User(){

    }

    public User(long user, String googleID, String email, List<Part> favoriteParts, List<Part> wishlistParts, List<Set> favoriteSets, List<Set> wishlistSets, List<Part> collectionParts, List<Set> collectionSets) {
        this.user = user;
        this.googleID = googleID;
        this.email = email;
        this.favoriteParts = favoriteParts;
        this.wishlistParts = wishlistParts;
        this.favoriteSets = favoriteSets;
        this.wishlistSets = wishlistSets;
        this.collectionParts = collectionParts;
        this.collectionSets = collectionSets;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
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
