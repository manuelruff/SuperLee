import HR.Bussiness.Driver;
import HR.Bussiness.Super;
import HR.Bussiness.Training;
import HR.Bussiness.Worker;
import HR.Bussiness.Days;
import HR.DataAccess.DataController;
import HR.Service.ShipmentService;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ShipmentServiceTest {
    private DataController dataController;
    private ShipmentService shipmentService;
    private Map<String, Super> Superim;
    //all the Workers in the company
    private Map<String, Worker> Workers;
    private Map<String, Driver> Drivers;
    public ShipmentServiceTest(){
        dataController=DataController.getInstance();
        shipmentService=ShipmentService.getInstance();
        Superim = dataController.getSuperMap();
        Workers= dataController.getWorkerMap();
        Drivers=dataController.getDriverMap();
    }
    @Test
    void checkWeekly() {
        dataController.getSuper("branch1");
        dataController.getSuper("branch2");
        List<String> list=new ArrayList<>();
        list.add("branch1");
        if(Superim.get("branch1")!=null) {
            //we check that he has a weekly in the database
            if (Superim.get("branch1").HasWeekly()) {
                assertTrue(shipmentService.checkWeekly(list, Superim.get("branch1").GetWeekShifts().getStartDate()));
            } else {
                assertFalse(shipmentService.checkWeekly(list, Superim.get("branch1").GetWeekShifts().getStartDate()));
            }
            list.add("branch2");
            if(Superim.get("branch2")!=null) {
                if (Superim.get("branch1").HasWeekly() && Superim.get("branch2").HasWeekly()) {
                    assertTrue(shipmentService.checkWeekly(list, Superim.get("branch1").GetWeekShifts().getStartDate()));
                } else {
                    assertFalse(shipmentService.checkWeekly(list, Superim.get("branch1").GetWeekShifts().getStartDate()));
                }
            }
        }
    }
    @Test
    void checkASite() {
        dataController.getSuper("branch1");
        dataController.getSuper("branch2");
        if(Superim.get("branch1")!=null) {
            assertTrue(shipmentService.checkASite("branch1"));
        }
        if(Superim.get("branch2")!=null) {
            assertTrue(shipmentService.checkASite("branch2"));
        }
    }
    @Test
    void getUpdateForDriver() {
        dataController.getWorker("1003");
        if(Drivers.get("1003")!=null) {
            shipmentService.getUpdateForDriver("1003", 'D', 2);
            assertEquals('D', Drivers.get("1003").getLicense());
            assertEquals(Training.values()[2], Drivers.get("1003").getAbility());
            shipmentService.getUpdateForDriver("1003", 'D', 2);
            assertEquals('D', Drivers.get("1003").getLicense());
            shipmentService.getUpdateForDriver("1003", 'C', 1);
            assertEquals('C', Drivers.get("1003").getLicense());
            assertEquals(Training.values()[1], Drivers.get("1003").getAbility());
        }
    }
    @Test
    void askRemoveDayForDriver() {
        dataController.getWorker("1003");
        if(Drivers.get("1003")!=null) {
            Driver curr = Drivers.get("1003");
            //if he doesnt have a working day there we will add it, check and then remove
            if (curr.checkDay(Days.Sunday)) {
                curr.addNewDay(Days.Sunday);
                //we check he has the day
                assertFalse(curr.checkDay(Days.Sunday));
                shipmentService.askRemoveDayForDriver("1003", 0);
                //we check he doesnt have the day
                assertTrue(curr.checkDay(Days.Sunday));
            }
            //if he has a day there we will just check the removal
            else {
                //we check he has the day
                assertFalse(curr.checkDay(Days.Sunday));
                shipmentService.askRemoveDayForDriver("1003", 0);
                //we check he doesnt have the day
                assertTrue(curr.checkDay(Days.Sunday));
                //we add it again
                curr.addNewDay(Days.Sunday);
            }
        }
    }
    @Test
    void getUpdateForSite() {
        dataController.getSuper("branch1");
        if(Superim.get("branch1")!=null){
            String name1=Superim.get("branch1").getContactName();
            String phone1=Superim.get("branch1").getPhoneNumber();
            shipmentService.getUpdateForSite("branch1","manu1111","00000000");
            assertEquals("00000000",Superim.get("branch1").getPhoneNumber());
            assertEquals("manu1111",Superim.get("branch1").getContactName());
            shipmentService.getUpdateForSite("branch1",name1,phone1);
        }

    }

}