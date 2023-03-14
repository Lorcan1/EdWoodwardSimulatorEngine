package players;

public class Goalkeeper extends Player {
    private int saving;
    private  int passing;

    public Goalkeeper(String firstName, String lastName, String position, int age, int ovr, int saving, int passing) {
        super(firstName,lastName,position, age, ovr);
        this.saving = saving;
        this.passing = passing;
    }

    public int getSaving() {
        return saving;
    }

    public void setSaving(int saving) {
        this.saving = saving;
    }

    public int getPassing() {
        return passing;
    }

    public void setPassing(int passing) {
        this.passing = passing;
    }
}
