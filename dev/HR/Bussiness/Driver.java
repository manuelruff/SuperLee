package HR.Bussiness;

public class Driver extends AWorker{
    private char license;
    private Training ability;

    public Driver(String ID,String Name,  int Bank,String Contract, double Wage , String Password, char license, Training ability) {
        super(ID,Name,Bank,Contract,Wage,Password);
        this.license = license;
        this.ability = ability;
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

    public void setAbility(Training  training) {ability = training;}

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
        System.out.println("Name: " + Name);
        System.out.println("ID: " + ID);
        System.out.println("License type: " + license);
        System.out.println("Trainings: " + ability.toString());
        if (WeeklyWorkingDays.isEmpty())
            System.out.println(Name + " doesn't work this week");
        else{
            int i;
            StringBuilder print = new StringBuilder();
            for (i=0; i < WeeklyWorkingDays.size() - 1; i++){
                print.append(WeeklyWorkingDays.get(i).toString()).append(" , ");
            }
            print.append(WeeklyWorkingDays.get(i).toString());
            System.out.println(Name + " days of work are:");
            System.out.println(print);
        }
    }
}

