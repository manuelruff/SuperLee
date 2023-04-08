public class Branch extends Site{

    private Zone zone;


    public Branch(String name, String address, String phoneNumber, String contactName, Zone zone) {
        super(name, address, phoneNumber, contactName);
        this.zone = zone;
    }

    public Zone getZone() {
        return zone;
    }

    @Override
    public void printSite() {
        System.out.println("Site Type: Branch");
        System.out.println("Name: " + getName());
        System.out.println("Address: " + getAddress());
        System.out.println("Phone Number: " + getPhoneNumber());
        System.out.println("Contact Name: " + getContactName());
        System.out.println("Zone: " + zone + "\n");
    }
}
