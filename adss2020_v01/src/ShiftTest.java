import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ShiftTest {

    @Test
    void addWorker() {
        Shift tester=new Shift(LocalDate.now(),ShiftTime.Morning,10,16,"1","manu");
        assertFalse(tester.IsWorkerAtShift("2"));
        assertFalse(tester.IsWorkerAtShift("3"));
        tester.AddWorker("2","lala");
        tester.AddWorker("3","lali");
        assertTrue(tester.IsWorkerAtShift("1"));
        assertTrue(tester.IsWorkerAtShift("2"));
        assertTrue(tester.IsWorkerAtShift("3"));
    }

    @Test
    void removeWorker() {
        Shift tester=new Shift(LocalDate.now(),ShiftTime.Morning,10,16,"1","manu");
        tester.AddWorker("2","lala");
        tester.AddWorker("3","lali");
        tester.RemoveWorker("2");
        tester.RemoveWorker("3");
        assertTrue(tester.IsWorkerAtShift("1"));
        assertFalse(tester.IsWorkerAtShift("2"));
        assertFalse(tester.IsWorkerAtShift("3"));
    }

    @Test
    void isWorkerAtShift() {
        Shift tester=new Shift(LocalDate.now(),ShiftTime.Morning,10,16,"1","manu");
        tester.AddWorker("2","lala");
        tester.AddWorker("3","lali");
        assertTrue(tester.IsWorkerAtShift("1"));
        assertTrue(tester.IsWorkerAtShift("2"));
        assertTrue(tester.IsWorkerAtShift("3"));
        tester.RemoveWorker("2");
        tester.RemoveWorker("3");
        assertTrue(tester.IsWorkerAtShift("1"));
        assertFalse(tester.IsWorkerAtShift("2"));
        assertFalse(tester.IsWorkerAtShift("3"));
    }
}