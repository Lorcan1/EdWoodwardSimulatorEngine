package players;

public class Forward extends Player {
    int dribbling;
    int firstTouch;
    int finishing;

    public Forward(String firstName, String lastName, String position,int age,int dribbling, int firstTouch, int finishing) {
        super(firstName,lastName,position,age);
        this.firstTouch = firstTouch;
        this.finishing = finishing;
        this.dribbling = dribbling;
    }

    public int getdribbling() {
        return dribbling;
    }

    public void setdribbling(int dribbling) {
        this.dribbling = dribbling;
    }

    public int getFirstTouch() {
        return firstTouch;
    }

    public void setFirstTouch(int firstTouch) {
        this.firstTouch = firstTouch;
    }

    public int getFinishing() {
        return finishing;
    }

    public void setFinishing(int finishing) {
        this.finishing = finishing;
    }
}
