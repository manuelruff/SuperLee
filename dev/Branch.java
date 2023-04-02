public class Branch extends Site{

    private Zone zone;


    public Branch(String address, String phoneNumber, String contactName,Zone zone) {
        super(address, phoneNumber, contactName);
        this.zone = zone;
    }
}
