package players;

public class Midfielder extends OutfieldPlayer {
    double passing;
    double firstTouch;
    double tackling;
    Midfielder marker;

    public Midfielder(String firstName, String lastName, String club, String nation, String position, int age, int ovr, double passing, double composure, double firstTouch, double tackling, double dribbling, double crossing) {
        super(firstName, lastName, club, nation, position, age, ovr, passing, composure, dribbling, crossing);
        this.passing = passing;
        this.firstTouch = firstTouch;
        this.tackling = tackling;
    }


    public Midfielder getMarker() {
        return marker;
    }

    public void setMarker(Midfielder marker) {
        this.marker = marker;
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
