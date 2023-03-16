package players;

public class OutfieldPlayer extends Player {
    private double dribbling;

    public OutfieldPlayer(String firstName, String lastName, String club, String nation, String position, int age, int ovr, double passing, double composure, double dribbling) {
        super(firstName, lastName, club, nation, position, age, ovr, passing, composure);
        this.dribbling = dribbling;
    }

    public double getDribbling() {
        return dribbling;
    }

    public void setDribbling(double dribbling) {
        this.dribbling = dribbling;
    }
}
