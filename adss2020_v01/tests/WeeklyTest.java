import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WeeklyTest {

    @Test
    void addShift() {
        Weekly week=new Weekly();
        assertNotNull(week);
        Worker work0 = new Worker("manu" , "1", 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Shift shift=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,"1",work0);
        week.AddShift(shift);
        Shift shift1=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,"1",work0);
        week.AddShift(shift1);
        Shift shift2=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,"1",work0);
        week.AddShift(shift2);
        Shift shift3=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,"1",work0);
        week.AddShift(shift3);
        assertNotNull(week.GetShift(0));
        assertNotNull(week.GetShift(1));
        assertNotNull(week.GetShift(2));
        assertNotNull(week.GetShift(3));
    }
    @Test
    void getShift() {
        Worker work0 = new Worker("manu" , "1", 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Weekly week=new Weekly();
        Shift shift=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,"1",work0);
        week.AddShift(shift);
        Shift shift1=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,"1",work0);
        week.AddShift(shift1);
        Shift shift2=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,"1",work0);
        week.AddShift(shift2);
        Shift shift3=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,"1",work0);
        week.AddShift(shift3);
        assertNotNull(week.GetShift(0));
        assertNotNull(week.GetShift(1));
        assertNotNull(week.GetShift(2));
        assertNotNull(week.GetShift(3));
        Worker work1 = new Worker("lala" , "2", 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        Worker work2 = new Worker("lali" , "3", 318 , "ata ahla gever",130, Jobs.ShiftManager ,"123" );
        shift.AddWorker("2",work1);
        shift.AddWorker("3",work2);
        assertTrue(week.GetShift(0).IsWorkerAtShift("1"));
        assertTrue(week.GetShift(0).IsWorkerAtShift("2"));
        assertTrue(week.GetShift(0).IsWorkerAtShift("3"));
        shift.RemoveWorker("3");
        shift.RemoveWorker("2");
        assertFalse(week.GetShift(0).IsWorkerAtShift("2"));
        assertFalse(week.GetShift(0).IsWorkerAtShift("3"));
    }
    @Test
    void getStartDate() {
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