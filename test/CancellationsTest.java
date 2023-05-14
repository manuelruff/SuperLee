import HR.Bussiness.Cancellations;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CancellationsTest {

    @Test
    public void getYear() {
        Cancellations tester=new Cancellations(10,"banana","1","manu");
        assertEquals(LocalDateTime.now().getYear(), tester.getYear());
    }

    @Test
    public void getMonth() {
        Cancellations tester=new Cancellations(10,"banana","1","manu");
        assertEquals(LocalDateTime.now().getMonthValue(), tester.getMonth());
    }

    @Test
    public void getDay() {
        Cancellations tester=new Cancellations(10,"banana","1","manu");
        assertEquals(LocalDateTime.now().getDayOfMonth(), tester.getDay());

    }

}