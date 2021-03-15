package testing;

import application.CCApplication;
import dbAdapters.A_Adapter;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class CCApplicationTest {
    public CCApplicationTest() {
        super();
    }

    @Test
    public void testRunTimer() {
        A_Adapter stub = mock(A_Adapter.class);
        A_Adapter.setInstance(stub);
        System.out.println("Ja, diese Warnungen sind Normal.");

        CCApplication.getInstance().runTimer();

        verify(stub, times(0)).finalizeAppointment(1);
    }
}
