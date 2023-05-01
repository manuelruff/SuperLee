package Shipment.Bussiness;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    private String name;
    private String  ID;
    private char license;
    private Training ability;
    private List<Days> WeeklyWorkingDays;

    public Driver(String ID, String name, char license, Training ability) {
        this.ID=ID;
        this.name = name;
        this.license = license;
        this.ability = ability;
        WeeklyWorkingDays=new ArrayList<>();
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

    public String getName() {return name;}

    public void setLicense(char newLicence){license = newLicence;}

    public void setAbility(Training training) {ability = training;}

    public boolean addNewDay(Days day) {
        for (Days d : WeeklyWorkingDays) {
            if (d == day)
                return false;
        }
        WeeklyWorkingDays.add(day);
        return true;
    }

    public boolean checkDay(Days cDay)
    {
        for(Days day : WeeklyWorkingDays)
        {
            if(day == cDay)
                return false;
        }
        return true;
    }

    public void removeDay(Days dayToRemove)
    {
        for (Days day : WeeklyWorkingDays) {
            if (day == dayToRemove)
                WeeklyWorkingDays.remove(day);
        }
    }

    public void Printme() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + ID);
        System.out.println("License type: " + license);
        System.out.println("Trainings: " + ability.toString());
        if (WeeklyWorkingDays.isEmpty())
            System.out.println(name + " doesn't work this week");
        else{
            int i;
            StringBuilder print = new StringBuilder();
            for (i=0; i < WeeklyWorkingDays.size() - 1; i++){
                print.append(WeeklyWorkingDays.get(i).toString()).append(" , ");
            }
            print.append(WeeklyWorkingDays.get(i).toString());
            System.out.println(name + " days of work are:");
            System.out.println(print);
        }
    }
}

