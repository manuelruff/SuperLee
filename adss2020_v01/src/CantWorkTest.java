import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CantWorkTest {

    @Test
    void getStart() {
        CantWork tester=new CantWork(12,13.50,"blablabla");
        assertEquals(12,tester.getStart());
    }

    @Test
    void getEnd() {
        CantWork tester=new CantWork(12,13.50,"blablabla");
        assertEquals(13.50,tester.getEnd());
    }
}