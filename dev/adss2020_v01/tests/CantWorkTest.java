import Domain.CantWork;
import org.junit.Test;

import static org.junit.Assert.*;

public class CantWorkTest {

    @Test
    public void getStart() {
        CantWork tester=new CantWork(12,13.50,"blablabla");
        assertEquals(12.00,tester.getStart(),0);
    }

    @Test
    public void getEnd() {
        CantWork tester=new CantWork(12,13.50,"blablabla");
        assertEquals(13.50,tester.getEnd(),0);
    }
}