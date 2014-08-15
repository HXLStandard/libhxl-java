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

    private HXLReader hxl_reader;

    //
    // Tests
    //

    @Before
    public void setUp() throws Exception {
        input = new InputStreamReader(getClass().getResourceAsStream("/hxl-sample-01.csv"));
        hxl_reader = new HXLReader(input);
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
        Assert.assertNotNull(hxl_reader.getColumns());
        Assert.assertEquals(4, hxl_reader.getColumns().size());
    }

    @Test
    public void testRow() throws Exception {
        HXLRow row = hxl_reader.read();
        Assert.assertNotNull(row);
        Assert.assertEquals(4, row.getValues().size());
    }

}
