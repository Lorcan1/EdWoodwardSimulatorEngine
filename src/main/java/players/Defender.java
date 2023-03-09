package players;

public class Defender extends Player {
    int tackling;
    int marking;

    public  Defender(String firstName, String lastName, String position,int age,int tackling, int marking){
        super(firstName,lastName,position,age);
        this.tackling = tackling;
        this.marking = marking;
    }

    public int getTackling() {
        return tackling;
    }

    public void setTackling(int tackling) {
        this.tackling = tackling;
    }

    public int getMarking() {
        return marking;
    }

    public void setMarking(int marking) {
        this.marking = marking;
    }
}
