package be.thomasmore.legocompanion.Models;

public class Theme {
    private long themeID;
    private String themeName;

    public Theme(){

    }

    public Theme(long themeID, String themeName) {
        this.themeID = themeID;
        this.themeName = themeName;
    }

    public long getThemeID() {
        return themeID;
    }

    public void getThemeID(long themeID) {
        this.themeID = themeID;
    }

    public String getThemeName() {
        return themeName;
    }

    public void getThemeName(String themeName) {
        this.themeName = themeName;
    }
}
