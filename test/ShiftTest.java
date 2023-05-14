import HR.Bussiness.Jobs;
import HR.Bussiness.ShiftTime;
import HR.Bussiness.Worker;
import org.junit.jupiter.api.Test;
import HR.Bussiness.Shift;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ShiftTest {


    @Test
    public void addWorker() {
        Worker manager = new Worker( "1","manu" , 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Shift tester=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,manager);
        assertFalse(tester.IsWorkerAtShift("2"));
        assertFalse(tester.IsWorkerAtShift("3"));
        Worker worker1 = new Worker( "2","lala" , 318 , "ata ahla gever",130, Jobs.Cashier ,"123" );
        Worker worker2 = new Worker("3","lali" ,  318 , "ata ahla gever",130, Jobs.Cashier ,"123" );

        tester.AddWorker(Jobs.Cashier,worker1);
        tester.AddWorker(Jobs.Cashier,worker2);
        assertTrue(tester.IsWorkerAtShift("1"));
        assertTrue(tester.IsWorkerAtShift("2"));
        assertTrue(tester.IsWorkerAtShift("3"));
    }

    @Test
    public void removeWorker() {
        Worker manager = new Worker("1","manu" ,  318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Shift tester=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,manager);
        assertFalse(tester.IsWorkerAtShift("2"));
        assertFalse(tester.IsWorkerAtShift("3"));
        Worker worker1 = new Worker( "2","lala" , 318 , "ata ahla gever",130, Jobs.Cashier ,"123" );
        Worker worker2 = new Worker("3","lali" ,  318 , "ata ahla gever",130, Jobs.Cashier ,"123" );
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
        Worker manager = new Worker("1","manu" ,  318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Shift tester=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,manager);
        assertFalse(tester.IsWorkerAtShift("2"));
        assertFalse(tester.IsWorkerAtShift("3"));
        Worker worker1 = new Worker( "2","lala" , 318 , "ata ahla gever",130, Jobs.Cashier ,"123" );
        Worker worker2 = new Worker("3","lali" ,  318 , "ata ahla gever",130, Jobs.Cashier ,"123" );
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
        Worker manager = new Worker("1","manu" ,  318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Shift tester=new Shift(LocalDate.now(), ShiftTime.Morning,10,16);
        assertTrue(tester.IsEmptyShift());
        tester=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,manager);
        assertFalse(tester.IsEmptyShift());
    }

}