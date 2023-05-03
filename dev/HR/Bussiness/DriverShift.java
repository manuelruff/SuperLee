package HR.Bussiness;

public class DriverShift {
    private Days day;
    private Driver driver;
    private String branch;

    public DriverShift(Days day, Driver driver, String branch) {
        this.day = day;
        this.driver = driver;
        this.branch = branch;
    }

    public Days getDay() {
        return day;
    }
    public Driver getDriver() {
        return driver;
    }
    public String getBranch() {
        return branch;
    }
    public void setDay(Days day) {
        this.day = day;
    }
    public void setDriver(Driver driver) {
        this.driver = driver;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }
    public void print(){
        System.out.println(day + " " + driver + " " + branch);
    }

}
