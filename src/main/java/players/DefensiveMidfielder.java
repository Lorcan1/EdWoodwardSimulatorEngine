package players;

public class DefensiveMidfielder extends Midfielder{
    double positioning;
    public DefensiveMidfielder(String firstName, String lastName, String club, String nation, String position, int age, int ovr, double passing, double composure, double firstTouch, double tackling, double dribbling, double crossing, double positioning) {
        super(firstName, lastName, club, nation, position, age, ovr, passing, composure, firstTouch, tackling, dribbling, crossing);
        this.positioning = positioning;
    }

    public double getPositioning() {
        return positioning;
    }

    public void setPositioning(double positioning) {
        this.positioning = positioning;
    }
}
