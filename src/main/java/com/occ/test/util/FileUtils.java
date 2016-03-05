package com.occ.test.util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.occ.test.util.StringUtils.isBlank;

/**
 * Created by Srikanth Vuppula on 3/4/16.
 */
public class FileUtils {
    public FileUtils() {
        throw new UnsupportedOperationException("FileUtils cannot be instantiated");
    }

    public static List<String> getDelimitedStrings(String fileName) {
        List<String> strings = new ArrayList<>();
        if (!isBlank(fileName)) {
            try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
                return lines.map(StringUtils::getAlphaString)
                        .flatMap(Arrays::stream)
                        .collect(Collectors.toList());
            } catch (Exception e) {
                throw new RuntimeException(String.format("Exception while reading the file %s: %s", fileName, e.getCause()), e);
            }
        }
        return strings;
    }
}
