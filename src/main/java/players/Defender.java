package players;

public class Defender extends Player {
    int tackling;
    int marking;
    int passing;

    public  Defender(String firstName, String lastName, String position,int age,int tackling, int marking,int passing){
        super(firstName,lastName,position,age);
        this.tackling = tackling;
        this.marking = marking;
        this.passing = passing;
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

    public int getPassing() {
        return passing;
    }

    public void setPassing(int passing) {
        this.passing = passing;
    }
}
