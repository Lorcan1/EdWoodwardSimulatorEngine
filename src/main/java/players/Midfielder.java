package players;

public class Midfielder extends OutfieldPlayer {
    double passing;
    double firstTouch;
    double tackling;
    Midfielder marker;

    public Midfielder(String firstName, String lastName, String club, String nation, String position, int age, int ovr, double passing, double firstTouch, double tackling, double dribbling) {
        super(firstName, lastName, club, nation, position, age, ovr, dribbling);
        this.passing = passing;
        this.firstTouch = firstTouch;
        this.tackling = tackling;
    }

    public double getPassing() {
        return passing;
    }

    public Midfielder getMarker() {
        return marker;
    }

    public void setMarker(Midfielder marker) {
        this.marker = marker;
    }

    public void setPassing(double passing) {
        this.passing = passing;
    }

    public double getFirstTouch() {
        return firstTouch;
    }

    public void setFirstTouch(double firstTouch) {
        this.firstTouch = firstTouch;
    }

    public double getTackling() {
        return tackling;
    }

    public void setTackling(double tackling) {
        this.tackling = tackling;
    }
}
