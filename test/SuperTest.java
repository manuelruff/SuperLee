import HR.Bussiness.Zone;
import HR.Bussiness.Super;
import HR.Bussiness.Worker;
import HR.Bussiness.Weekly;
import HR.Bussiness.Days;
import HR.Bussiness.Jobs;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SuperTest {


    @Test
    public void addWorker() {
        Super Super1 = new Super("zolretzah","bersheva","55555555","manu",Zone.Center);
        Worker worker1 = new Worker("1", "manu" , 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Worker worker2 = new Worker( "2","manu" , 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Super1.AddWorker(worker1);
        Super1.AddWorker(worker2);
        assertEquals(2,Super1.GetWorkersIDS().size());
    }
    @Test
    public void removeWorker() {
        Super Super1 = new Super("zolretzah","bersheva","55555555","manu",Zone.Center);
        Worker worker1 = new Worker("1", "manu" , 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Worker worker2 = new Worker( "2","manu" , 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Super1.AddWorker(worker1);
        Super1.AddWorker(worker2);
        assertEquals(2,Super1.GetWorkersIDS().size());
        Super1.RemoveWorker("1");
        Super1.RemoveWorker("2");
        assertEquals(0,Super1.GetWorkersIDS().size());
    }
    @Test
    public void getName() {
        Super Super1 = new Super("zolretzah","bersheva","55555555","manu",Zone.Center);
        assertEquals("zolretzah",Super1.getName());

    }
    @Test
    public void hasWeekly() {
        Super Super1 = new Super("zolretzah","bersheva","55555555","manu",Zone.Center);
        assertFalse(Super1.HasWeekly());
        Weekly week=new Weekly();
        Super1.setWeekly(week);
        assertTrue(Super1.HasWeekly());
    }
    @Test
    public void getEnd_evening() {
        Super Super1 = new Super("zolretzah","bersheva","55555555","manu",Zone.Center);
        assertEquals(23,Super1.getEnd_evening(Days.Sunday),0);
        assertEquals(23,Super1.getEnd_evening(Days.Saturday),0);
        assertEquals(23,Super1.getEnd_evening(Days.Wednesday),0);
    }
    @Test
    public void setEnd_evening() {
        Super Super1 = new Super("zolretzah","bersheva","55555555","manu",Zone.Center);
        assertEquals(23,Super1.getEnd_evening(Days.Sunday),0);
        Super1.setEnd_evening(Days.Sunday,10.00);
        assertEquals(10,Super1.getEnd_evening(Days.Sunday),0);
    }

    @Test
    public void getEnd_morning() {
        Super Super1 = new Super("zolretzah","bersheva","55555555","manu",Zone.Center);
        assertEquals(14,Super1.getEnd_morning(Days.Sunday),0);
        assertEquals(14,Super1.getEnd_morning(Days.Saturday),0);
        assertEquals(14,Super1.getEnd_morning(Days.Wednesday),0);
    }
    @Test
    public void setEnd_morning() {
        Super Super1 = new Super("zolretzah","bersheva","55555555","manu",Zone.Center);
        assertEquals(14,Super1.getEnd_morning(Days.Sunday),0);
        Super1.setEnd_morning(Days.Sunday,10.00);
        assertEquals(10,Super1.getEnd_morning(Days.Sunday),0);
    }
    @Test
    public void getStart_evening() {
        Super Super1 = new Super("zolretzah","bersheva","55555555","manu",Zone.Center);
        assertEquals(14,Super1.getStart_evening(Days.Sunday),0);
        assertEquals(14,Super1.getStart_evening(Days.Saturday),0);
        assertEquals(14,Super1.getStart_evening(Days.Wednesday),0);
    }
    @Test
    public void setStart_evening() {
        Super Super1 = new Super("zolretzah","bersheva","55555555","manu",Zone.Center);
        assertEquals(14,Super1.getStart_evening(Days.Sunday),0);
        Super1.setStart_evening(Days.Sunday,10.00);
        assertEquals(10,Super1.getStart_evening(Days.Sunday),0);
    }
    @Test
    public void getStart_morning() {
        Super Super1 = new Super("zolretzah","bersheva","55555555","manu",Zone.Center);
        assertEquals(7,Super1.getStart_morning(Days.Sunday),0);
        assertEquals(7,Super1.getStart_morning(Days.Saturday),0);
        assertEquals(7,Super1.getStart_morning(Days.Wednesday),0);
    }
    @Test
    public void setStart_morning() {
        Super Super1 = new Super("zolretzah","bersheva","55555555","manu",Zone.Center);
        assertEquals(7,Super1.getStart_morning(Days.Sunday),0);
        Super1.setStart_morning(Days.Sunday,10.00);
        assertEquals(10,Super1.getStart_morning(Days.Sunday),0);
    }
    @Test
    public void getZone() {
        Super Super1 = new Super("zolretzah","bersheva","55555555","manu",Zone.Center);
        assertEquals(Zone.Center,Super1.getZone());
    }
    @Test
    public void getAddress() {
        Super Super1 = new Super("zolretzah","bersheva","55555555","manu",Zone.Center);
        assertEquals("bersheva",Super1.getAddress());
    }
    @Test
    public void getphoneNumber() {
        Super Super1 = new Super("zolretzah","bersheva","55555555","manu",Zone.Center);
        assertEquals("55555555",Super1.getPhoneNumber());
    }
    @Test
    public void getcontactName() {
        Super Super1 = new Super("zolretzah","bersheva","55555555","manu",Zone.Center);
        assertEquals("manu",Super1.getContactName());
    }
    @Test
    public void setAddress() {
        Super Super1 = new Super("zolretzah","bersheva","55555555","manu",Zone.Center);
        assertEquals("bersheva",Super1.getAddress());
        Super1.setAddress("bershevaaaa");
        assertEquals("bershevaaaa",Super1.getAddress());
    }
    @Test
    public void setphoneNumber() {
        Super Super1 = new Super("zolretzah","bersheva","55555555","manu",Zone.Center);
        assertEquals("55555555",Super1.getPhoneNumber());
        Super1.setPhoneNumber("66666666");
        assertEquals("66666666",Super1.getPhoneNumber());
    }
    @Test
    public void setcontactName() {
        Super Super1 = new Super("zolretzah","bersheva","55555555","manu",Zone.Center);
        assertEquals("manu",Super1.getContactName());
        Super1.setContactName("manuuuu");
        assertEquals("manuuuu",Super1.getContactName());
    }
}