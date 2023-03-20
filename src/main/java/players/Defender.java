package players;

public class Defender extends OutfieldPlayer {
    double tackling;
    double marking;
    double passing;

    public Defender(String firstName, String lastName, String club, String nation, String position, int age, int ovr, double passing, double composure, double dribbling, double crossing, double tackling, double marking) {
        super(firstName, lastName, club, nation, position, age, ovr, passing, composure, dribbling, crossing);
        this.tackling = tackling;
        this.marking = marking;
    }

    public double getTackling() {
        return tackling;
    }

    public void setTackling(double tackling) {
        this.tackling = tackling;
    }

    public double getMarking() {
        return marking;
    }

    public void setMarking(double marking) {
        this.marking = marking;
    }
}
