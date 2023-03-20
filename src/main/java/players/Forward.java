package players;

public class Forward extends OutfieldPlayer {
    double firstTouch;
    double finishing;
    double positioning;

    public Forward(String firstName, String lastName, String club, String nation, String position, int age, int ovr, double passing, double composure, double crossing, double positioning, double dribbling, double firstTouch, double finishing) {
        super(firstName, lastName, club, nation, position, age, ovr, passing, composure,dribbling,crossing);
        this.firstTouch = firstTouch;
        this.finishing = finishing;
        this.positioning = positioning;
    }

    public double getFirstTouch() {
        return firstTouch;
    }

    public void setFirstTouch(double firstTouch) {
        this.firstTouch = firstTouch;
    }

    public double getFinishing() {
        return finishing;
    }

    public void setFinishing(double finishing) {
        this.finishing = finishing;
    }

    public double getPositioning() {
        return positioning;
    }

    public void setPositioning(double positioning) {
        this.positioning = positioning;
    }
}