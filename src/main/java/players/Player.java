package players;

public class Player {
    private String firstName;
    private String lastName;
    private String club;
    private String nation;
    private String position;
    private int age;
    private int ovr;
    private double passing;
    private double composure;

    public Player(String firstName, String lastName, String club, String nation, String position, int age, int ovr, double passing, double composure) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.club = club;
        this.nation = nation;
        this.position = position;
        this.age = age;
        this.ovr = ovr;
        this.passing = passing;
        this.composure = composure;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getOvr() {
        return ovr;
    }

    public void setOvr(int ovr) {
        this.ovr = ovr;
    }

    public double getPassing() {
        return passing;
    }

    public void setPassing(int passing) {
        this.passing = passing;
    }

    public double getComposure() {
        return composure;
    }

    public void setComposure(int composure) {
        this.composure = composure;
    }
}



