import HR.Bussiness.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {

    @Test
    void getLicense() {
        Driver tester = new Driver("1","manu" , 318 , "ata ahla gever",130, "123",'D',Training.Cooling );
        assertEquals('D',tester.getLicense());
    }

    @Test
    void getAbility() {
        Driver tester = new Driver("1","manu" , 318 , "ata ahla gever",130, "123",'D',Training.Cooling );
        assertEquals(Training.Cooling,tester.getAbility());
    }

    @Test
    void setLicense() {
        Driver tester = new Driver("1","manu" , 318 , "ata ahla gever",130, "123",'D',Training.Cooling );
        assertEquals('D',tester.getLicense());
        tester.setLicense('C');
        assertEquals('C',tester.getLicense());
    }

    @Test
    void setAbility() {
        Driver tester = new Driver("1","manu" , 318 , "ata ahla gever",130, "123",'D',Training.Cooling );
        tester.setAbility(Training.Regular);
        assertEquals(Training.Regular,tester.getAbility());
    }

    @Test
    void addNewDay() {
        Driver tester = new Driver("1","manu" , 318 , "ata ahla gever",130, "123",'D',Training.Cooling );
        assertTrue(tester.checkDay(Days.Sunday));
        tester.addNewDay(Days.Sunday);
        assertFalse(tester.checkDay(Days.Sunday));
    }

    @Test
    void checkDay() {
        Driver tester = new Driver("1","manu" , 318 , "ata ahla gever",130, "123",'D',Training.Cooling );
        assertTrue(tester.checkDay(Days.Sunday));
    }
}