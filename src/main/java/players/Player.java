package players;

public class Player {
    private String firstName;
    private String lastName;
    private String position;
    private int age;
    private int ovr;

    public Player(String firstName, String lastName, String position, int age, int ovr){
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.age = age;
        this.ovr = ovr;
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

    public int getOvr() {
        return ovr;
    }

    public void setOvr(int ovr) {
        this.ovr = ovr;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}
