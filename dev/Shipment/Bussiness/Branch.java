package Shipment.Bussiness;

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
        System.out.println("Contact Name: " + getContactName());
        System.out.println("Phone Number: " + getPhoneNumber());
        System.out.println("Zone: " + zone + "\n");
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Site Type: Branch\n");
        sb.append("Name: ").append(getName()).append("\n");
        sb.append("Address: ").append(getAddress()).append("\n");
        sb.append("Contact Name: ").append(getContactName()).append("\n");
        sb.append("Phone Number: ").append(getPhoneNumber()).append("\n");
        sb.append("Zone: ").append(zone).append("\n");
        return sb.toString();
    }

}
