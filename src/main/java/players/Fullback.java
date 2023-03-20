package players;

public class Fullback extends Defender{

    Forward forwardMarker;
    Fullback fullbackMarker;

    public Fullback(String firstName, String lastName, String club, String nation, String position, int age, int ovr, double passing, double composure, double dribbling, double crossing, double tackling, double marking) {
        super(firstName, lastName, club, nation, position, age, ovr, passing, composure, dribbling, crossing, tackling, marking);
    }

    public Forward getForwardMarker() {
        return forwardMarker;
    }

    public void setForwardMarker(Forward forwardMarker) {
        this.forwardMarker = forwardMarker;
    }

    public Fullback getFullbackMarker() {
        return fullbackMarker;
    }

    public void setFullbackMarker(Fullback fullbackMarker) {
        this.fullbackMarker = fullbackMarker;
    }
}
