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
        tester.setBaseUrl("http://localhost:8080/");
    }

    @Test
    public void testBrowseHolidayOffers() {
        // Start testing for MemberGUI
        tester.beginAt("calendar?action=viewCalendar");

        // Check all components of the search form
        tester.assertTitleEquals("CCA - Group Calendar");
        tester.assertFormPresent();
        tester.assertTextPresent("Required Information");
        tester.assertTextPresent("Arrival Time");
        tester.assertFormElementPresent("arrivalTime");
        tester.assertTextPresent("Departure Time");
        tester.assertFormElementPresent("departureTime");
        tester.assertTextPresent("Persons");
        tester.assertFormElementPresent("persons");
        tester.assertButtonPresent("SelectHOWebpage");

        // Submit the form with given parameters
        tester.setTextField("arrivalTime", "06/23/2016");
        tester.setTextField("departureTime", "06/24/2016");
        tester.setTextField("persons", "2");

        tester.clickButton("SelectHOWebpage");

        // Check the representation of the table for an empty result
        tester.assertTablePresent("availableHOs");
        String[][] tableHeadings = { { "#", "Street", "Town", "Capacity" } };
        tester.assertTableEquals("availableHOs", tableHeadings);

    }
}