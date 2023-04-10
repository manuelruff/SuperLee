import org.junit.Test;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeeklyTest {


    @Test
    public void addShift() {
        Weekly week=new Weekly();
        assertNotNull(week);
        Shift shift=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,"1","manu");
        week.AddShift(shift);
        Shift shift1=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,"1","manu");
        week.AddShift(shift1);
        Shift shift2=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,"1","manu");
        week.AddShift(shift2);
        Shift shift3=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,"1","manu");
        week.AddShift(shift3);
        assertNotNull(week.GetShift(0));
        assertNotNull(week.GetShift(1));
        assertNotNull(week.GetShift(2));
        assertNotNull(week.GetShift(3));
    }
    @Test
    public void getShift() {
        Weekly week=new Weekly();
        Shift shift=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,"1","manu");
        week.AddShift(shift);
        Shift shift1=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,"1","manu");
        week.AddShift(shift1);
        Shift shift2=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,"1","manu");
        week.AddShift(shift2);
        Shift shift3=new Shift(LocalDate.now(), ShiftTime.Morning,10,16,"1","manu");
        week.AddShift(shift3);
        assertNotNull(week.GetShift(0));
        assertNotNull(week.GetShift(1));
        assertNotNull(week.GetShift(2));
        assertNotNull(week.GetShift(3));
        shift.AddWorker("2","lala");
        shift.AddWorker("3","lali");
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