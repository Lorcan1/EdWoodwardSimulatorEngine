package players;

public class Midfielder extends Player {
    int passing;
    int firstTouch;
    int tackling;

    public Midfielder(String firstName, String lastName, String position,int age,int passing, int firstTouch, int tackling) {
        super(firstName,lastName,position,age);
        this.passing = passing;
        this.firstTouch = firstTouch;
        this.tackling = tackling;
    }

    public int getPassing() {
        return passing;
    }

    public void setPassing(int passing) {
        this.passing = passing;
    }

    public int getFirstTouch() {
        return firstTouch;
    }

    public void setFirstTouch(int firstTouch) {
        this.firstTouch = firstTouch;
    }

    public int getTackling() {
        return tackling;
    }

    public void setTackling(int tackling) {
        this.tackling = tackling;
    }
}
