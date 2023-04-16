import Domain.Constraints;
import Domain.Days;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConstraintsTest {


    @Test
    public void canWork() {
        Constraints tester=new Constraints();
        tester.AddCantWork(Days.Saturday,12,22,"blabla");
        tester.AddCantWork(Days.Sunday,12,22,"blabla");
        tester.AddCantWork(Days.Tuesday,12,22,"blabla");
        assertFalse(tester.CanWork(Days.Sunday,8,13));
        assertFalse(tester.CanWork(Days.Saturday,21,23));
        assertTrue(tester.CanWork(Days.Sunday,8,12));
        assertTrue(tester.CanWork(Days.Saturday,22,23));
        tester.RemoveCantWork(Days.Saturday,12,22);
        tester.RemoveCantWork(Days.Sunday,12,22);
        tester.RemoveCantWork(Days.Tuesday,12,22);
        assertTrue(tester.CanWork(Days.Sunday,8,13));
        assertTrue(tester.CanWork(Days.Saturday,21,23));

        tester.AddCantWork(Days.Saturday,12,22,"blabla");
        tester.AddCantWork(Days.Sunday,12,22,"blabla");
        tester.AddCantWork(Days.Tuesday,12,22,"blabla");
        tester.RemoveCantWork(Days.Saturday,12,20);
        tester.RemoveCantWork(Days.Sunday,12,20);
        //it supposed to still be false because there is no listing of 12-20, he needs to fiil the right times of the listing to remove it
        assertFalse(tester.CanWork(Days.Sunday,8,13));
        assertFalse(tester.CanWork(Days.Saturday,10,19));
    }
}