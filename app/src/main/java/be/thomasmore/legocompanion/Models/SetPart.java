package be.thomasmore.legocompanion.Models;

public class SetPart {
    private long setPartID;
    private long setID;
    private long partID;
    private Set set;
    private Part part;
    private int amount;

    public SetPart() {
    }

    public SetPart(long setPartID, long setID, long partID, Set set, Part part, int amount) {
        this.setPartID = setPartID;
        this.setID = setID;
        this.partID = partID;
        this.set = set;
        this.part = part;
        this.amount = amount;
    }

    public long getSetPartID() {
        return setPartID;
    }

    public void setSetPartID(long setPartID) {
        this.setPartID = setPartID;
    }

    public long getSetID() {
        return setID;
    }

    public void setSetID(long setID) {
        this.setID = setID;
    }

    public long getPartID() {
        return partID;
    }

    public void setPartID(long partID) {
        this.partID = partID;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
