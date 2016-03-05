package com.occ.test;

import com.occ.test.util.FileUtils;
import com.occ.test.util.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Optional;

import static java.lang.String.format;

/**
 * Print the string length according to spec.
 */
public class App {

    public static final Logger LOGGER = LogManager.getLogger(App.class.getName());

    protected void printSumOfStrings(final String fileName) {
        Optional.ofNullable(fileName)
                .map(FileUtils::getDelimitedStrings)
                .ifPresent(strings -> StringUtils.printSumOfAllStrings(strings, fileName));
    }

    public static void main(String args[]) {
        if(args == null || args.length != 1 || args[0].trim().length() == 0) {
            LOGGER.error(format("Usage: java -jar jar_file absolute_file_name"));
        } else {
            new App().printSumOfStrings(args[0]);
        }
    }
}
