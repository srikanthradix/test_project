package com.occ.test.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Srikanth Vuppula on 3/4/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class FileUtilsTest {
    @Test
    public void testOutput() {
        File file = new File("src/test/resources/names.txt");
        String filePath = file.getAbsolutePath();
        List<String> actual = FileUtils.getDelimitedStrings(filePath);
        List<String> expected = Arrays.asList("MARY", "PATRICIA", "LINDA", "BARBARA", "VINCENZO", "SHON", "LYNWOOD", "JERE", "HAI");
        assertFalse(Collections.disjoint(actual, expected));
    }

    @Test
    public void testEmptyFile() {
        File file = new File("src/test/resources/empty.txt");
        String filePath = file.getAbsolutePath();
        List<String> actual = FileUtils.getDelimitedStrings(filePath);
        assertTrue(actual.isEmpty());
    }

    @Test(expected = RuntimeException.class)
    public void testOutputInvalidFile() {
        FileUtils.getDelimitedStrings("names1.txt");
    }
}