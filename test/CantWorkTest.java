import HR.Bussiness.CantWork;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CantWorkTest {
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