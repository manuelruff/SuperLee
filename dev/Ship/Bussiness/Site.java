package Ship.Bussiness;

public abstract class Site {

    private String address;
    private String phoneNumber;
    private String contactName;



    private String name;

    public Site(String name,String address, String phoneNumber, String contactName) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.contactName = contactName;
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setName(String name){this.name = name;}
    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getName() {
        return name;
    }
    public abstract void printSite();
}
