/*
 *  Copyright 2001-2005 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.gwttime.time;

import org.gwttime.time.Chronology;
import org.gwttime.time.DateTime;
import org.gwttime.time.DateTimeConstants;
import org.gwttime.time.DateTimeFieldType;
import org.gwttime.time.DateTimeUtils;
import org.gwttime.time.DateTimeZone;
import org.gwttime.time.LocalDate;
import org.gwttime.time.Partial;
import org.gwttime.time.gwt.JodaGwtTestCase;

import static org.gwttime.time.gwt.TestConstants.*;

/**
 * This class is a Junit unit test for Partial.
 *
 * @author Stephen Colebourne
 */
public class TestPartial_Match extends JodaGwtTestCase {

    // Removed for GWT private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    // Removed for GWT private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    // Removed for GWT private static final DateTimeZone TOKYO = DateTimeZone.forID("Asia/Tokyo");
//    private static final int OFFSET = 1;
    // Removed for GWT private static final Chronology COPTIC_PARIS = CopticChronology.getInstance(PARIS);
    // Removed for GWT private static final Chronology COPTIC_LONDON = CopticChronology.getInstance(LONDON);
    // Removed for GWT private static final Chronology COPTIC_TOKYO = CopticChronology.getInstance(TOKYO);
    // Removed for GWT private static final Chronology COPTIC_UTC = CopticChronology.getInstanceUTC();
    // Removed for GWT private static final Chronology ISO_PARIS = ISOChronology.getInstance(PARIS);
    // Removed for GWT private static final Chronology ISO_LONDON = ISOChronology.getInstance(LONDON);
    // Removed for GWT private static final Chronology ISO_TOKYO = ISOChronology.getInstance(TOKYO);
    // Removed for GWT private static final Chronology ISO_UTC = ISOChronology.getInstanceUTC();
    // Removed for GWT private static final Chronology BUDDHIST_PARIS = BuddhistChronology.getInstance(PARIS);
    // Removed for GWT private static final Chronology BUDDHIST_LONDON = BuddhistChronology.getInstance(LONDON);
    // Removed for GWT private static final Chronology BUDDHIST_TOKYO = BuddhistChronology.getInstance(TOKYO);
    // Removed for GWT private static final Chronology BUDDHIST_UTC = BuddhistChronology.getInstanceUTC();
    
    private long TEST_TIME_NOW =
            10L * DateTimeConstants.MILLIS_PER_HOUR
            + 20L * DateTimeConstants.MILLIS_PER_MINUTE
            + 30L * DateTimeConstants.MILLIS_PER_SECOND
            + 40L;
            
    private DateTimeZone zone = null;

    /* Removed for GWT public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    } */

    /* Removed for GWT public static TestSuite suite() {
        return new TestSuite(TestPartial_Match.class);
    } */

    /* Removed for GWT public TestPartial_Match(String name) {
        super(name);
    } */

    protected void gwtSetUp() throws Exception {
        super.gwtSetUp();
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW);
        zone = DateTimeZone.getDefault();
        DateTimeZone.setDefault(LONDON);
    }

    protected void gwtTearDown() throws Exception {
        super.gwtTearDown();
        DateTimeUtils.setCurrentMillisSystem();
        DateTimeZone.setDefault(zone);
        zone = null;
    }

    //-----------------------------------------------------------------------
    public void testIsMatch_Instant() {
        // Year=2005, Month=7 (July), DayOfWeek=2 (Tuesday)
        Partial test = createYMDwPartial(ISO_UTC, 2005, 7, 2);
        DateTime instant = new DateTime(2005, 7, 5, 0, 0, 0, 0);
        assertEquals(true, test.isMatch(instant));
        
        instant = new DateTime(2005, 7, 4, 0, 0, 0, 0);
        assertEquals(false, test.isMatch(instant));
        
        instant = new DateTime(2005, 7, 6, 0, 0, 0, 0);
        assertEquals(false, test.isMatch(instant));
        
        instant = new DateTime(2005, 7, 12, 0, 0, 0, 0);
        assertEquals(true, test.isMatch(instant));
        
        instant = new DateTime(2005, 7, 19, 0, 0, 0, 0);
        assertEquals(true, test.isMatch(instant));
        
        instant = new DateTime(2005, 7, 26, 0, 0, 0, 0);
        assertEquals(true, test.isMatch(instant));
        
        instant = new DateTime(2005, 8, 2, 0, 0, 0, 0);
        assertEquals(false, test.isMatch(instant));
        
        instant = new DateTime(2006, 7, 5, 0, 0, 0, 0);
        assertEquals(false, test.isMatch(instant));
        
        instant = new DateTime(2005, 6, 5, 0, 0, 0, 0);
        assertEquals(false, test.isMatch(instant));
    }

    //-----------------------------------------------------------------------
    public void testIsMatch_Partial() {
        // Year=2005, Month=7 (July), DayOfWeek=2 (Tuesday)
        Partial test = createYMDwPartial(ISO_UTC, 2005, 7, 2);
        LocalDate partial = new LocalDate(2005, 7, 5);
        assertEquals(true, test.isMatch(partial));
        
        partial = new LocalDate(2005, 7, 4);
        assertEquals(false, test.isMatch(partial));
        
        partial = new LocalDate(2005, 7, 6);
        assertEquals(false, test.isMatch(partial));
        
        partial = new LocalDate(2005, 7, 12);
        assertEquals(true, test.isMatch(partial));
        
        partial = new LocalDate(2005, 7, 19);
        assertEquals(true, test.isMatch(partial));
        
        partial = new LocalDate(2005, 7, 26);
        assertEquals(true, test.isMatch(partial));
        
        partial = new LocalDate(2005, 8, 2);
        assertEquals(false, test.isMatch(partial));
        
        partial = new LocalDate(2006, 7, 5);
        assertEquals(false, test.isMatch(partial));
        
        partial = new LocalDate(2005, 6, 5);
        assertEquals(false, test.isMatch(partial));
    }

    //-----------------------------------------------------------------------
    private Partial createYMDwPartial(Chronology chrono, int year, int month, int dow) {
        return new Partial(
            new DateTimeFieldType[] {
                    DateTimeFieldType.year(),
                    DateTimeFieldType.monthOfYear(),
                    DateTimeFieldType.dayOfWeek()},
            new int[] {year, month, dow},
            chrono);
    }

}
