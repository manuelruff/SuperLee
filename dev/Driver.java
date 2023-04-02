
public class Driver {

    private String name, ID;

    private char license;

    private Training ability;


    private Days[] availability;

    public Driver(String name, String ID, char license, Training ability) {
        this.name = name;
        this.ID = ID;
        this.license = license;
        this.ability = ability;
    }


    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public char getLicense() {
        return license;
    }

    public Training getAbility() {
        return ability;
    }
}

