package testing;

import application.CCApplication;
import dbAdapters.A_Adapter;
import junit.framework.TestCase;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class CCApplicationTest extends TestCase {
    public CCApplicationTest() {
        super();
    }

    @Test
    public void testRunTimer() {
        A_Adapter stub = mock(A_Adapter.class);
        A_Adapter.setInstance(stub);

        CCApplication.getInstance().runTimer();

        // Was zum F*** soll das hier bitte tun?
        // Wie wäre es mal mit einer Vorlesung bei die der Dozent auch seinen Job macht und uns etwas erkärt.
        verify(stub, times(1)).finalizeAppointment(1);
    }
}
