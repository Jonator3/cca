package testing;

import junit.framework.TestCase;
import org.junit.Test;

import static org.mockito.Mockito.times;

public class CCApplicationTest extends TestCase {
    public CCApplicationTest() {
        super();
    }

    @Test
    public void testRunTimer() {
        DBFacade stub = mock(DBFacade.class);
        DBFacade.setInstance(stub);

        VRApplication.getInstance().checkPayment();

        verify(stub, times(1)).setAvailableHolidayOffer();
    }
}
