package players;

public class Forward extends Player {
    double dribbling;
    double firstTouch;
    double finishing;

    public Forward(String firstName, String lastName, String club, String nation, String position,int age, int ovr, double dribbling, double firstTouch, double finishing) {
        super(firstName, lastName, club, nation, position, age, ovr);
        this.firstTouch = firstTouch;
        this.finishing = finishing;
        this.dribbling = dribbling;
    }

    public double getdribbling() {
        return dribbling;
    }

    public void setdribbling(double dribbling) {
        this.dribbling = dribbling;
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
}
