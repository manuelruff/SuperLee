import HR.Bussiness.Days;
import HR.Bussiness.Jobs;
import HR.Bussiness.Worker;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerTest {


    @Test
    public void resetShiftsAmount() {
        Worker tester = new Worker( "1","manu" , 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        tester.AddShift(Days.Sunday);
        tester.AddShiftWorked();
        tester.AddShift(Days.Monday);
        tester.AddShiftWorked();
        tester.AddShift(Days.Thursday);
        tester.AddShiftWorked();
        assertEquals(3,tester.getShiftWorked());
        tester.ResetShiftsAmount();
        assertEquals(0,tester.getShiftWorked());
    }
    @Test
    public void checkPassword() {
        Worker tester = new Worker( "1","manu" , 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        assertTrue(tester.CheckPassword("123"));
        tester.SetPassword("1111");
        assertTrue(tester.CheckPassword("1111"));
        tester.SetPassword("341586");
        assertTrue(tester.CheckPassword("341586"));
        assertFalse(tester.CheckPassword("11111"));
    }
    @Test
    public void setPassword() {
        Worker tester = new Worker( "1","manu" , 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        assertTrue(tester.CheckPassword("123"));
        tester.SetPassword("1111");
        assertTrue(tester.CheckPassword("1111"));
    }
    @Test
    public void canDoJob() {
        Worker tester = new Worker( "1","manu" , 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        assertTrue(tester.CanDoJob(Jobs.ShiftManager));
        assertFalse(tester.CanDoJob(Jobs.Cashier));
        tester.AddJob(Jobs.Cashier);
        assertTrue(tester.CanDoJob(Jobs.Cashier));
    }
    @Test
    public void addJob() {
        Worker tester = new Worker( "1","manu" , 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        tester.AddJob(Jobs.Cashier);
        assertTrue(tester.CanDoJob(Jobs.Cashier));
    }
    @Test
    public void isFree() {
        Worker tester = new Worker( "1","manu" , 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        assertTrue(tester.IsFree(Days.Sunday,12,13));
        tester.AddShift(Days.Sunday);
        assertFalse(tester.IsFree(Days.Sunday,12,13));
        tester.AddShift(Days.Monday);
        tester.AddShift(Days.Thursday);
        tester.AddShift(Days.Friday);
        tester.AddShift(Days.Tuesday);
        tester.AddShift(Days.Wednesday);
        assertFalse(tester.IsFree(Days.Saturday,1,2));
    }
    @Test
    public void getID() {
        Worker tester = new Worker( "1","manu" , 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        assertEquals("1",tester.getID());
        Worker tester2 = new Worker( "25","manu" , 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        assertEquals("25",tester2.getID());
        assertNotEquals("1",tester2.getID());
    }
    @Test
    public void addShift() {
        Worker tester = new Worker( "1","manu" , 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        tester.AddShift(Days.Sunday);
        tester.AddShift(Days.Monday);
        tester.AddShift(Days.Thursday);
        assertEquals(3,tester.getWeeklyWorkingDays().size());
    }
    @Test
    public void removeShift() {
        Worker tester = new Worker( "1","manu" , 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        tester.AddShift(Days.Sunday);
        tester.AddShift(Days.Monday);
        tester.AddShift(Days.Thursday);
        tester.RemoveShift(Days.Sunday);
        tester.RemoveShift(Days.Monday);
        tester.RemoveShift(Days.Thursday);
        assertEquals(0,tester.getWeeklyWorkingDays().size());
    }
    @Test
    public void getName() {
        Worker tester = new Worker( "1","manu" , 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        assertEquals("manu",tester.getName());
    }
    @Test
    public void setName() {
        Worker tester = new Worker( "1","manu" , 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        assertEquals("manu",tester.getName());
        tester.SetName("ba");
        assertEquals("ba",tester.getName());
    }
    @Test
    public void getShiftWorked() {
        Worker tester = new Worker( "1","manu" , 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        tester.AddShift(Days.Sunday);
        tester.AddShift(Days.Monday);
        tester.AddShift(Days.Thursday);
        assertEquals(0,tester.getShiftWorked());
        tester.ResetDaysOfWork();
        tester.AddShift(Days.Sunday);
        tester.AddShiftWorked();
        tester.AddShift(Days.Monday);
        tester.AddShiftWorked();
        tester.AddShift(Days.Thursday);
        tester.AddShiftWorked();
        assertEquals(3,tester.getShiftWorked());
        tester.ResetDaysOfWork();
        tester.AddShift(Days.Sunday);
        tester.AddShift(Days.Monday);
        tester.AddShift(Days.Thursday);
        //we check that while we wont reset it it will count all the shifts
        assertEquals(3,tester.getShiftWorked());
        tester.ResetShiftsAmount();
        assertEquals(0,tester.getShiftWorked());
    }

    @Test
    public void getWage() {
        Worker tester = new Worker( "1","manu" , 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        assertEquals(130,tester.getWage(),0);
    }

    @Test
    public void setWage() {
        Worker tester = new Worker( "1","manu" , 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        assertEquals(130,tester.getWage(),0);
        tester.setWage(1000);
        assertEquals(1000,tester.getWage(),0);
    }

}