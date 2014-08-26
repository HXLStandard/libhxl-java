package org.hxlstandard;

import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit4 tests for the HXLReader class.
 */
public class HXLReaderTest {

    //
    // Instance variables
    //

    private Reader input;

    private HXLReader hxlReader;

    //
    // Tests
    //

    @Before
    public void setUp() throws Exception {
        input = new InputStreamReader(getClass().getResourceAsStream("/hxl-sample-01.csv"));
        hxlReader = new HXLReader(input);
    }

    @After
    public void tearDown() throws Exception {
        input.close();
    }

    @Test
    public void testResource() {
        Assert.assertNotNull(getClass().getResourceAsStream("/hxl-sample-01.csv"));
    }

    @Test
    public void testColumns() throws Exception {
        Assert.assertNotNull(hxlReader.getColumns());
        Assert.assertEquals(4, hxlReader.getColumns().size());
    }

    @Test
    public void testRow() throws Exception {
        HXLRow row = hxlReader.read();
        Assert.assertNotNull(row);
        Assert.assertEquals(4, row.getValues().size());
    }

    @Test
    public void testValues() throws Exception {
        HXLRow row = hxlReader.read();
        HXLValue value = row.getValues().get(1);
        Assert.assertEquals("#subsector", value.getTag());
        Assert.assertEquals("Subsector 1", value.getContent());
        Assert.assertEquals(0, value.getRowNumber());
        Assert.assertEquals(3, value.getSourceRowNumber());
    }

}
