public class Branch extends Site{

    private Zone zone;


    public Branch(String name, String address, String phoneNumber, String contactName, Zone zone) {
        super(name, address, phoneNumber, contactName);
        this.zone = zone;
    }
}
