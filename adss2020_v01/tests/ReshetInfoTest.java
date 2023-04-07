import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReshetInfoTest {

    @Test
    void hasWeekly() {
        ReshetInfo info=new ReshetInfo();
        assertFalse(info.HasWeekly("yakarmeod"));
        assertFalse(info.HasWeekly("zolretzah"));
    }
    @Test
    void isExistWorker() {
        ReshetInfo info=new ReshetInfo();
        assertTrue(info.isExistWorker("1"));
        assertTrue(info.isExistWorker("2"));
        assertTrue(info.isExistWorker("12"));
        assertTrue(info.isExistWorker("30"));
        assertFalse(info.isExistWorker("100"));
        assertFalse(info.isExistWorker("101"));
        assertFalse(info.isExistWorker("102"));

    }
    @Test
    void getAvailableEmployee() {
        ReshetInfo info=new ReshetInfo();
        List<String> workers=new ArrayList<>();
        workers.add("1");
        workers.add("2");
        workers.add("3");
        workers.add("4");
        assertEquals("1",info.GetAvailableEmployee(Days.Sunday, Jobs.ShiftManager, ShiftTime.Morning,workers,"yakarmeod").get(0));
        assertEquals("2",info.GetAvailableEmployee(Days.Sunday, Jobs.ShiftManager, ShiftTime.Morning,workers,"yakarmeod").get(1));
        assertEquals("3",info.GetAvailableEmployee(Days.Sunday, Jobs.ShiftManager, ShiftTime.Morning,workers,"yakarmeod").get(2));
        assertEquals("4",info.GetAvailableEmployee(Days.Sunday, Jobs.ShiftManager, ShiftTime.Morning,workers,"yakarmeod").get(3));
    }
    @Test
    void isTruePassword() {
        ReshetInfo info=new ReshetInfo();
        assertTrue(info.IsTruePassword("1","123"));
        assertFalse(info.IsTruePassword("1","1234"));
    }
    @Test
    void checkAllHaveWeekly() {
        ReshetInfo info=new ReshetInfo();
        assertFalse(info.CheckAllHaveWeekly());
    }
    @Test
    void checkTimeValidate() {
        ReshetInfo info=new ReshetInfo();
        assertFalse(info.CheckTimeValidate(12.56,26));
        assertFalse(info.CheckTimeValidate(12.61,20));
        assertFalse(info.CheckTimeValidate(12.56,20.70));
        assertTrue(info.CheckTimeValidate(12.56,20));
        assertTrue(info.CheckTimeValidate(12,20.2));
    }
    @Test
    void getWorkerByID() {
        ReshetInfo info=new ReshetInfo();
        assertEquals("manu",info.GetWorkerByID("1").GetName());
        assertEquals("david",info.GetWorkerByID("2").GetName());
    }
    @Test
    void isWorksInSuper() {
        ReshetInfo info=new ReshetInfo();
        assertTrue(info.IsWorksInSuper("1","yakarmeod"));
        assertTrue(info.IsWorksInSuper("2","yakarmeod"));
        assertTrue(info.IsWorksInSuper("3","yakarmeod"));
    }

    @Test
    void canDoJob() {
        ReshetInfo info=new ReshetInfo();
        assertTrue(info.CanDoJob("1", Jobs.ShiftManager));
        assertTrue(info.CanDoJob("14", Jobs.StoreKeeper));
        assertTrue(info.CanDoJob("19", Jobs.GeneralEmp));
        assertTrue(info.CanDoJob("22", Jobs.Guard));
    }
}