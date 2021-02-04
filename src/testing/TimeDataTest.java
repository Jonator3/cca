package testing;

import datatypes.TimeData;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimeDataTest {
    TimeData td;

    @Before
    public void setUp() throws Exception {
        td = new TimeData(2020,6,12,12,0);
    }

    @Test
    public final void testisBefore() {
        // test when b is before td
        assertEquals(true, td.isBefore(new TimeData(0,1,1,0,0)));

        // test when b is after td
        assertEquals(false, td.isBefore(new TimeData(2021,1,1,0,0)));

        // test when b is equal to td
        assertEquals(false, td.isBefore(td));
    }
}