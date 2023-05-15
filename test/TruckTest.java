import Shipment.Bussiness.Truck;
import Shipment.Bussiness.FreezerTruck;
import Shipment.Bussiness.CoolingTruck;
import Shipment.Bussiness.RegularTruck;
import Shipment.Bussiness.Days;
import Shipment.Bussiness.Training;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TruckTest {
    Truck truck1 = new FreezerTruck("123", 15000, 2000, "mercedes");
    Truck truck2 = new CoolingTruck("456", 10000, 1500, "mercedes");
    Truck truck3 = new RegularTruck("789", 14000, 1000, "Opel");

    @Test
    void getLicenceType() {
        assertEquals('D', truck1.getLicenceType());
        assertEquals('C', truck2.getLicenceType());
        assertEquals('D', truck3.getLicenceType());
    }

    @Test
    void getStorageType() {
        assertEquals(Training.Freezer, truck1.getStorageType());
        assertEquals(Training.Cooling, truck2.getStorageType());
        assertEquals(Training.Regular, truck3.getStorageType());
    }

    @Test
    void addNewDay() {
        Days a = Days.Sunday, b = Days.Monday;
        truck1.addNewDay(a);
        truck1.addNewDay(b);
        List<Days> daysList = new ArrayList<>();
        daysList.add(a);
        daysList.add(b);
        assertEquals(2, truck1.getInUse().size());
        assertEquals(daysList, truck1.getInUse());
    }

    @Test
    void removeDay() {
        Days a = Days.Sunday, b = Days.Monday;
        truck2.addNewDay(a);
        truck2.addNewDay(b);
        assertEquals(2, truck2.getInUse().size());
        truck2.removeDay(a);
        assertEquals(1, truck2.getInUse().size());
    }

}