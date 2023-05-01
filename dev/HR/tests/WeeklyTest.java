import HR.Domain.*;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class WeeklyTest {


    @Test
    public void addShift() {
        Weekly week=new Weekly();
        assertNotNull(week);
        Worker manager = new Worker( "1","manu" , 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Shift shift=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,manager);
        week.AddShift(shift);
        Shift shift1=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,manager);
        week.AddShift(shift1);
        Shift shift2=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,manager);
        week.AddShift(shift2);
        Shift shift3=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,manager);
        week.AddShift(shift3);
        assertNotNull(week.GetShift(0));
        assertNotNull(week.GetShift(1));
        assertNotNull(week.GetShift(2));
        assertNotNull(week.GetShift(3));
    }
    @Test
    public void getShift() {
        Worker manager = new Worker( "1","manu" , 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Weekly week=new Weekly();
        Shift shift=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,manager);
        week.AddShift(shift);
        Shift shift1=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,manager);
        week.AddShift(shift1);
        Shift shift2=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,manager);
        week.AddShift(shift2);
        Shift shift3=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,manager);
        week.AddShift(shift3);
        assertNotNull(week.GetShift(0));
        assertNotNull(week.GetShift(1));
        assertNotNull(week.GetShift(2));
        assertNotNull(week.GetShift(3));
        Worker worker1 = new Worker("2","lala" ,  318 , "ata ahla gever",130, Jobs.Cashier ,"123" );
        Worker worker2 = new Worker("3","lali" ,  318 , "ata ahla gever",130, Jobs.Cashier ,"123" );
        shift.AddWorker(Jobs.Cashier,worker1);
        shift.AddWorker(Jobs.Cashier,worker2);
        assertTrue(week.GetShift(0).IsWorkerAtShift("1"));
        assertTrue(week.GetShift(0).IsWorkerAtShift("2"));
        assertTrue(week.GetShift(0).IsWorkerAtShift("3"));
        shift.RemoveWorker("3");
        shift.RemoveWorker("2");
        assertFalse(week.GetShift(0).IsWorkerAtShift("2"));
        assertFalse(week.GetShift(0).IsWorkerAtShift("3"));
    }
    @Test
    public void getStartDate() {
        Weekly week=new Weekly();
        LocalDate s=LocalDate.now();
        DayOfWeek day=s.getDayOfWeek();
        while(day!=DayOfWeek.SUNDAY){
            s=s.plusDays(1);
            day=s.getDayOfWeek();
        }
        assertEquals(s.getDayOfWeek(),week.getStartDate().getDayOfWeek());
        assertEquals(s.getDayOfMonth(),week.getStartDate().getDayOfMonth());
        assertEquals(s,week.getStartDate());
    }
}