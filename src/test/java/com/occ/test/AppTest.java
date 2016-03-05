package com.occ.test;

import com.occ.test.util.LoggerUtilTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.File;

import static org.apache.log4j.Level.ERROR;
import static org.apache.log4j.Level.INFO;

/**
 * Created by Srikanth Vuppula on 3/4/16.
 */
public class AppTest extends LoggerUtilTest {
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    private void verify(String fileName, int expected) {
        verifyLoggedMessage(String.format("Sum of strings in %s is: %s", fileName, expected), INFO);
    }

    @Test
    public void testSample() {
        File file = new File("src/test/resources/names.txt");
        String filePath = file.getAbsolutePath();
        App.main(new String[]{filePath});
        verify(file.getAbsolutePath(), 3194);
    }

    @Test
    public void testFormal() {
        File file = new File("src/test/resources/main.txt");
        String filePath = file.getAbsolutePath();
        App.main(new String[]{filePath});
        verify(file.getAbsolutePath(), 871198282);
    }

    @Test
    public void testEmptyFile() {
        File file = new File("src/test/resources/empty.txt");
        String filePath = file.getAbsolutePath();
        App.main(new String[]{filePath});
        verify(file.getAbsolutePath(), 0);
    }

    @Test
    public void testNullFileName() {
        App.main(null);
        verifyLoggedMessage(String.format("Usage: java -jar jar_file absolute_file_name"), ERROR);
    }

    @Test
    public void testEmptyString() {
        App.main(new String[]{""});
        verifyLoggedMessage(String.format("Usage: java -jar jar_file absolute_file_name"), ERROR);
    }

    @Test(expected = RuntimeException.class)
    public void testNotExistentFile() {
        App.main(new String[]{"no_such_file"});
    }
}