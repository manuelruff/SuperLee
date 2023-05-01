package Ship;

import java.util.ArrayList;
import java.util.List;

public class Driver {

    private String name, ID;

    private char license;

    private Training ability;


    private List<Days> workingDays;

    public Driver(String name, String ID, char license, Training ability) {
        this.name = name;
        this.ID = ID;
        this.license = license;
        this.ability = ability;
        this.workingDays = new ArrayList<>();
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

    public void setLicense(char newLicence){license = newLicence;}

    public void setAbility(Training training) {ability = training;}

    public boolean addNewDay(Days newDay) {
        for (Days day : workingDays) {
            if (day == newDay)
                return false;
        }
        workingDays.add(newDay);
        return true;
    }

    public boolean checkDay(Days cday)
    {
        for(Days day : workingDays)
        {
            if(day == cday)
                return false;
        }
        return true;
    }

    public void removeDay(Days dayToRemove)
    {
        for (Days day : workingDays){
            if(day == dayToRemove)
                workingDays.remove(dayToRemove);
        }
    }

    public void printDriver() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + ID);
        System.out.println("License type: " + license);
        System.out.println("Trainings: " + ability.toString());
        if (workingDays.isEmpty())
            System.out.println(name + " doesn't work this week");
        else{
            int i;
            StringBuilder print = new StringBuilder();
            for (i=0; i < workingDays.size() - 1; i++){
                print.append(workingDays.get(i).toString()).append(" , ");
            }
            print.append(workingDays.get(i).toString());
            System.out.println(name + " days of work are:");
            System.out.println(print);
        }
    }
}

