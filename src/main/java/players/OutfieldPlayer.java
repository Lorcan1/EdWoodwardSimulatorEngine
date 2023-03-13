package players;

public class OutfieldPlayer extends Player {
    private int dribbling;

    public OutfieldPlayer(String firstName, String lastName, String position, int age, int dribbling) {
        super(firstName, lastName, position, age);
        this.dribbling = dribbling;
    }

    public int getDribbling() {
        return dribbling;
    }

    public void setDribbling(int dribbling) {
        this.dribbling = dribbling;
    }
}
