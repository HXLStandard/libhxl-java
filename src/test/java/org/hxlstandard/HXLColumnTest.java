package org.hxlstandard;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit4 tests for the HXLColumn class.
 */
public class HXLColumnTest {

    //
    // Test constants
    //

    private final static String TAG = "#sector";

    private final static String LANG = "en";

    private final static int COLUMN_NUMBER = 10;
    
    private final static int SOURCE_COLUMN_NUMBER = 12;

    //
    // Instance variables
    //

    private HXLColumn column;

    //
    // Tests
    //

    @Before
    public void setUp() throws Exception {
        column = new HXLColumn(TAG, LANG, COLUMN_NUMBER, SOURCE_COLUMN_NUMBER);
    }

    @Test
    public void testTag() {
        Assert.assertEquals(TAG, column.getTag());
    }

    @Test
    public void testLang() {
        Assert.assertEquals(LANG, column.getLang());
    }

    @Test
    public void testColumnNumber() {
        Assert.assertEquals(COLUMN_NUMBER, column.getColumnNumber());
    }

    @Test
    public void testSourceColumnNumber() {
        Assert.assertEquals(SOURCE_COLUMN_NUMBER, column.getSourceColumnNumber());
    }

    @Test(expected=NullPointerException.class)
    public void testNullTagError() {
        new HXLColumn(null, LANG, COLUMN_NUMBER, SOURCE_COLUMN_NUMBER);
    }

    @Test
    public void testNullLangNoError() {
        new HXLColumn(TAG, null, COLUMN_NUMBER, SOURCE_COLUMN_NUMBER);
    }

}
