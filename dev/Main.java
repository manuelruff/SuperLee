public class Main {
    public static void main(String[] args) {
        shipmentManagement s = new shipmentManagement();
        s.addDriver("Ron ohayon", "207991019", 'c', Training.Freezer);
        s.addDriver("roee", "123", 'd', Training.Cooling);
        s.printDrivers();
    }
}