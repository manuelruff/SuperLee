import org.junit.Test;

import static org.junit.Assert.*;

import java.time.LocalDate;

public class ShiftTest {


    @Test
    public void addWorker() {
        Worker manager = new Worker("manu" , "1", 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Shift tester=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,manager);
        assertFalse(tester.IsWorkerAtShift("2"));
        assertFalse(tester.IsWorkerAtShift("3"));
        Worker worker1 = new Worker("lala" , "2", 318 , "ata ahla gever",130, Jobs.Cashier ,"123" );
        Worker worker2 = new Worker("lali" , "3", 318 , "ata ahla gever",130, Jobs.Cashier ,"123" );

        tester.AddWorker(Jobs.Cashier,worker1);
        tester.AddWorker(Jobs.Cashier,worker2);
        assertTrue(tester.IsWorkerAtShift("1"));
        assertTrue(tester.IsWorkerAtShift("2"));
        assertTrue(tester.IsWorkerAtShift("3"));
    }

    @Test
    public void removeWorker() {
        Worker manager = new Worker("manu" , "1", 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Shift tester=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,manager);
        assertFalse(tester.IsWorkerAtShift("2"));
        assertFalse(tester.IsWorkerAtShift("3"));
        Worker worker1 = new Worker("lala" , "2", 318 , "ata ahla gever",130, Jobs.Cashier ,"123" );
        Worker worker2 = new Worker("lali" , "3", 318 , "ata ahla gever",130, Jobs.Cashier ,"123" );
        tester.AddWorker(Jobs.Cashier,worker1);
        tester.AddWorker(Jobs.Cashier,worker2);
        tester.RemoveWorker("2");
        tester.RemoveWorker("3");
        assertTrue(tester.IsWorkerAtShift("1"));
        assertFalse(tester.IsWorkerAtShift("2"));
        assertFalse(tester.IsWorkerAtShift("3"));
    }

    @Test
    public void isWorkerAtShift() {
        Worker manager = new Worker("manu" , "1", 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Shift tester=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,manager);
        assertFalse(tester.IsWorkerAtShift("2"));
        assertFalse(tester.IsWorkerAtShift("3"));
        Worker worker1 = new Worker("lala" , "2", 318 , "ata ahla gever",130, Jobs.Cashier ,"123" );
        Worker worker2 = new Worker("lali" , "3", 318 , "ata ahla gever",130, Jobs.Cashier ,"123" );
        tester.AddWorker(Jobs.Cashier,worker1);
        tester.AddWorker(Jobs.Cashier,worker2);
        assertTrue(tester.IsWorkerAtShift("1"));
        assertTrue(tester.IsWorkerAtShift("2"));
        assertTrue(tester.IsWorkerAtShift("3"));
        tester.RemoveWorker("2");
        tester.RemoveWorker("3");
        assertTrue(tester.IsWorkerAtShift("1"));
        assertFalse(tester.IsWorkerAtShift("2"));
        assertFalse(tester.IsWorkerAtShift("3"));
    }

    @Test
    public void isEmptyShift() {
        Worker manager = new Worker("manu" , "1", 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Shift tester=new Shift(LocalDate.now(), ShiftTime.Morning,10,16);
        assertTrue(tester.IsEmptyShift());
        tester=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,manager);
        assertFalse(tester.IsEmptyShift());
    }
}