package org.hxlstandard;

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

    private HXLReader hxl_reader;

    //
    // Tests
    //

    @Before
    public void setUp() throws Exception {
        hxl_reader = new HXLReader();
    }

    @Test
    public void testNothing() {
        Assert.assertTrue(true);
    }

}
