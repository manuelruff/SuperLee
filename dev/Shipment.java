import java.util.Date;

public class Shipment {

    private String ID, truckNumber, driverName;
    private Date date;
    private Site source;
    private Site[] destinations;
    private ItemsDoc[] docs;
    private Status shipmentStatus;
}
