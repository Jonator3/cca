package testing;

import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.Before;
import org.junit.Test;

public class MemberGUIWebTestCase {

    private WebTester tester;

    /**
     * Create a new WebTester object that performs the test.
     */
    @Before
    public void prepare() {
        tester = new WebTester();
        tester.setScriptingEnabled(false);
        tester.setBaseUrl("http://localhost:8080");
    }

    @Test
    public void testViewCalendar() {
        // Start testing for MemberGUI
        tester.beginAt("calendar?action=viewCalendar");

        // Check all components of the search form
        tester.assertTitleEquals("CCA - Group Calendar");
        tester.assertFormPresent();
        tester.assertTextPresent("group");
        tester.assertTextPresent("submit");
        tester.assertButtonPresent("vcbutton");
        tester.assertFormElementPresent("groupid");

        // Submit the form with given parameters
        tester.setTextField("groupid", "0");

        tester.clickButton("vcbutton");

        // Check the representation of the table for an empty result
        tester.assertElementPresent("calendar");

    }

    @Test
    public void testAddCalendar() {
        tester.beginAt("calendar?action=addAppointment");

        tester.assertTitleEquals("CCA - Add Appointment");
        tester.assertFormPresent();
        tester.assertTextPresent("name");
        tester.assertTextPresent("description");
        tester.assertTextPresent("location");
        tester.assertTextPresent("duration");
        tester.assertTextPresent("planned participants");
        tester.assertTextPresent("possible dates");
        tester.assertTextPresent("deadline");
        tester.assertTextPresent("group");
        tester.assertTextPresent("submit");
        tester.assertButtonPresent("aabutton");

        tester.setTextField("Name", "TestAppointment");
        tester.setTextField("descr", "TestDescription");
        tester.setTextField("Name", "TestAppointment");
        tester.setTextField("loc", "TestLocation");
        tester.setTextField("date", "01:30");
        tester.setTextField("numberOfplannedParticipants", "1");
        tester.setTextField("member0", "myName");
        tester.setTextField("posDatesDate", "2021-11-11");
        tester.setTextField("posDatesTime", "11:11");
        tester.setTextField("deadline", "2021-11-10");
        tester.setTextField("group_id", "0");
        
    }

    @Test
    public void testAnswerAppointment() {
        tester.beginAt("calendar?action=viewCalendar&groupid=0");
    }
}
