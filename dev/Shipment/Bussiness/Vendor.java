package Shipment.Bussiness;

public class Vendor extends Site{


    public Vendor(String name, String address, String phoneNumber, String contactName) {
        super(name, address, phoneNumber, contactName);
    }
    @Override
    public void printSite() {
        System.out.println("Site Type: Vendor");
        System.out.println("Name: " + getName());
        System.out.println("Address: " + getAddress());
        System.out.println("Contact Name: " + getContactName());
        System.out.println("Phone Number: " + getPhoneNumber() + "\n");

    }
}