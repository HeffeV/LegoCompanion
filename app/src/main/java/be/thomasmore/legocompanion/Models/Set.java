package be.thomasmore.legocompanion.Models;

public class Set {
    private long setID;
    private String setName;

    public Set(){

    }

    public Set(long id,String setName){
        this.setID=id;
        this.setName=setName;
    }

    public long getId(){return setID;}

    public void setId(long id) {
        this.setID = id;
    }

    public String getName() {
        return setName;
    }

    public void setName(String name) {
        this.setName = name;
    }
}
