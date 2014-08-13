package org.hxlstandard;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit4 tests for the HXLValue class.
 */
public class HXLValueTest {

    //
    // Test constants
    //

    final private String TAG = "#sector";

    final private String LANG = "en";

    final private int COLUMN_NUMBER = 2;

    final private int SOURCE_COLUMN_NUMBER = 3;

    final private String CONTENT = "Education";

    final private int ROW_NUMBER = 10;

    final private int SOURCE_ROW_NUMBER = 12;

    //
    // Instance variables
    //

    private HXLColumn column;

    private HXLValue value;

    //
    // Tests
    //

    @Before
    public void setUp() throws Exception {
        column = new HXLColumn(TAG, LANG, COLUMN_NUMBER, SOURCE_COLUMN_NUMBER);
        value = new HXLValue(column, CONTENT, ROW_NUMBER, SOURCE_ROW_NUMBER);
    }

    @Test
    public void testInit() {
        value.init(column, "WASH", 7, 8);
        Assert.assertEquals(column, value.getColumn());
        Assert.assertEquals("WASH", value.getContent());
        Assert.assertEquals(7, value.getRowNumber());
        Assert.assertEquals(8, value.getSourceRowNumber());
    }

    @Test
    public void testColumn() {
        Assert.assertEquals(column, value.getColumn());
    }

    @Test
    public void testContent() {
        Assert.assertEquals(CONTENT, value.getContent());
    }

    @Test
    public void testRowNumber() {
        Assert.assertEquals(ROW_NUMBER, value.getRowNumber());
    }

    @Test
    public void testSourceRowNumber() {
        Assert.assertEquals(SOURCE_ROW_NUMBER, value.getSourceRowNumber());
    }

    @Test
    public void testTag() {
        Assert.assertEquals(value.getColumn().getTag(), value.getTag());
        Assert.assertEquals(TAG, value.getTag());
    }

    @Test
    public void testLang() {
        Assert.assertEquals(value.getColumn().getLang(), value.getLang());
        Assert.assertEquals(LANG, value.getLang());
    }

    @Test
    public void testColumnNumber() {
        Assert.assertEquals(value.getColumn().getColumnNumber(), value.getColumnNumber());
        Assert.assertEquals(COLUMN_NUMBER, value.getColumnNumber());
    }

    @Test
    public void testSourceColumnNumber() {
        Assert.assertEquals(value.getColumn().getSourceColumnNumber(), value.getSourceColumnNumber());
        Assert.assertEquals(SOURCE_COLUMN_NUMBER, value.getSourceColumnNumber());
    }

}
