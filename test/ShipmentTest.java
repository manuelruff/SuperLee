import Shipment.Bussiness.Shipment;
import Shipment.Bussiness.Site;
import Shipment.Bussiness.Vendor;
import Shipment.Bussiness.Branch;
import Shipment.Bussiness.Days;
import Shipment.Bussiness.Zone;
import Shipment.Bussiness.Item;
import Shipment.Bussiness.ItemsDoc;
import Shipment.Bussiness.Training;
import Shipment.Bussiness.Driver;
import Shipment.Bussiness.Status;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class ShipmentTest {
    Site vendor1 = new Vendor("Osem", "Main 134", "1231231231", "Ron");
    Site branch1 = new Branch("branch1", "Main 135", "1231231232", "avi1", Zone.South);
    Site branch2 = new Branch("branch2", "Main 136", "1231231233", "avi2", Zone.South);
    Driver driver = new Driver("207","Ron", 'C', Training.Freezer);
    List<Site> sites = new ArrayList<>();
    List<Item> Items1 = new ArrayList<>();
    List<Item> Items2 = new ArrayList<>();
    Item item1 = new Item("ketchup", 10, Training.Regular);
    Item item2 = new Item("ketchup1", 30, Training.Regular);
    Item item3 = new Item("ketchup2", 20, Training.Cooling);
    Item item4 = new Item("ketchup3", 100, Training.Regular);
    ItemsDoc itemsDoc1 = new ItemsDoc(branch1.getName());
    ItemsDoc itemsDoc2 = new ItemsDoc(branch2.getName());
    List<ItemsDoc> docs = new ArrayList<>();

    LocalDate date = LocalDate.of(2023, 5, 14);
    Shipment shipment1;
    Shipment shipment;
    ShipmentTest(){
        sites.add(branch1);
        sites.add(branch2);
        Items1.add(item1);
        Items1.add(item2);
        itemsDoc1.addListOfItems(Items1);
        Items2.add(item3);
        Items2.add(item4);
        itemsDoc2.addListOfItems(Items2);
        docs.add(itemsDoc1);
        docs.add(itemsDoc2);
        shipment = new Shipment("123", "1234", Days.Sunday,vendor1,sites,docs, date);
        shipment1 = new Shipment("12345", "1234",driver, Days.Monday,vendor1,sites,docs, date);
    }


    @Test
    void getID() {
        assertEquals("123", shipment.getID());
        assertEquals("12345", shipment1.getID());
    }

    @Test
    void getDriver() {
        assertNull(shipment.getDriver());
        assertEquals(driver.getID(), shipment1.getDriver().getID());
    }

    @Test
    void getSource() {
        assertEquals(vendor1.getName(), shipment.getSource().getName());
    }

    @Test
    void getDestinations(){
        List<Site> siteList = shipment.getDestinations();
        assertEquals(2,siteList.size());
    }

    @Test
    void getDocs() {
        List<ItemsDoc> docs1 = shipment.getDocs();
        assertEquals(2,docs1.size());

    }

    @Test
    void getShipmentStatus() {
        assertEquals(Status.NoChanges, shipment.getShipmentStatus());
        shipment.setShipmentStatus(Status.TruckExchange);
        assertEquals(Status.TruckExchange, shipment.getShipmentStatus());
    }

    @Test
    void deleteItemDoc() {
        assertEquals(2, shipment.getDocs().size());
        shipment.deleteItemDoc(itemsDoc1);
        assertEquals(1, shipment.getDocs().size());
    }

    @Test
    void removeSite() {
        assertEquals(2, shipment.getDestinations().size());
        shipment.removeSite(branch1);
        assertEquals(1, shipment.getDestinations().size());
    }

    @Test
    void getDate() {
        assertEquals(date, shipment.getDate());
    }


}