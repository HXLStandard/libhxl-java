package org.hxlstandard;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit4 tests for the HXLRow class.
 */
public class HXLRowTest {

    //
    // Test constants
    //

    private final static int ROW_NUMBER = 10;
    
    private final static int SOURCE_ROW_NUMBER = 12;

    private final static String VALUES[][] = {
        {"#sector", "WASH"},
        {"#subsector", "Water Purification"},
        {"#org", "World Health Organization"},
        {"#country", "Mali"}
    };

    //
    // Instance variables
    //

    private HXLRow row;

    //
    // Tests
    //

    @Before
    public void setUp() throws Exception {
        row = new HXLRow(ROW_NUMBER, SOURCE_ROW_NUMBER);
        for (int i = 0; i < VALUES.length; i++) {
            HXLColumn column = new HXLColumn(VALUES[i][0], null, i, i);
            HXLValue value = new HXLValue(column, VALUES[i][1], ROW_NUMBER, SOURCE_ROW_NUMBER);
            row.getValuesModifiable().add(value);
        }
    }

    @Test
    public void testValues() {
        List<HXLValue> values = row.getValues();
        Assert.assertEquals(VALUES.length, values.size());
        for (int i = 0; i < values.size(); i++) {
            Assert.assertEquals(VALUES[i][0], values.get(i).getTag());
            Assert.assertEquals(VALUES[i][1], values.get(i).getContent());
        }
    }

    @Test(expected=UnsupportedOperationException.class)
    public void testValuesUnmodifiable() {
        row.getValues().add(null);
    }

    @Test
    public void testIterator() {
        int i = 0;
        for (HXLValue value : row) {
            i++;
        }
        Assert.assertEquals(VALUES.length, i);
    }


    @Test
    public void testRowNumber() {
        Assert.assertEquals(ROW_NUMBER, row.getRowNumber());
    }

    @Test
    public void testSourceRowNumber() {
        Assert.assertEquals(SOURCE_ROW_NUMBER, row.getSourceRowNumber());
    }

}
