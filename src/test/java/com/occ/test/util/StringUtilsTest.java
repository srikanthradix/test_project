package com.occ.test.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static com.occ.test.util.StringUtils.printSumOfAllStrings;
import static java.util.Arrays.asList;
import static org.apache.log4j.Level.INFO;

/**
 * Created by Srikanth Vuppula on 3/4/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class StringUtilsTest extends LoggerUtilTest {
    private static final String fileName = "test";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    private void verify(String[] input, int expected) {
        printSumOfAllStrings(asList(input), fileName);
        verifyLoggedMessage(String.format("Sum of strings in %s is: %s", fileName, expected), INFO);
    }

    @Test
    public void testOutput() {
        String[] input = {"MARY", "PATRICIA", "LINDA", "BARBARA", "VINCENZO", "SHON", "LYNWOOD", "JERE", "HAI"};
        verify(input, 3194);
    }

    @Test
    public void testCommaDelimitedString() {
        String line = "\"MARY\", \"PATRICIA\",\"LINDA\",\"BARBARA\",\"VINCENZO\",\"SHON\",\"LYNWOOD\",\"JERE\",\"HAI\",\"\"";
        String[] input = StringUtils.getAlphaString(line);
        verify(input, 3194);
    }

    @Test
    public void testEmptyArray() {
        String[] input = StringUtils.getAlphaString(null);
        verify(input, 0);
    }
}