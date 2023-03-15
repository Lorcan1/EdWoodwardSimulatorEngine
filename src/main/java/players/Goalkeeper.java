package players;

public class Goalkeeper extends Player {
    private double saving;
    private  double passing;

    public Goalkeeper(String firstName, String lastName, String club, String nation, String position, int age, int ovr, double saving, double passing) {
        super(firstName, lastName, club, nation, position, age, ovr);
        this.saving = saving;
        this.passing = passing;
    }

    public double getSaving() {
        return saving;
    }

    public void setSaving(double saving) {
        this.saving = saving;
    }

    public double getPassing() {
        return passing;
    }

    public void setPassing(double passing) {
        this.passing = passing;
    }
}
