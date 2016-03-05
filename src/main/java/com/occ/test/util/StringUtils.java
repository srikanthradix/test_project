package com.occ.test.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.List;
import java.util.function.IntSupplier;

import static java.lang.String.format;

/**
 * Created by Srikanth Vuppula on 3/4/16.
 */
public class StringUtils {
    public StringUtils() {
        throw new UnsupportedOperationException("StringUtils cannot be instantiated");
    }

    public static String[] EMPTY_ARRAY = new String[]{};
    public static final String US_ASCII = "US-ASCII";
    public static final int ALPHABET_START_POS = 64;
    public static final Logger LOGGER = LogManager.getLogger(StringUtils.class.getName());

    public static boolean isBlank(String value) {
        return value == null || value.trim().length() == 0;
    }

    public static String[] getAlphaString(String line) {
        if (isBlank(line)) {
            return EMPTY_ARRAY;
        }
        return line.split("\\P{Alpha}+");
    }

    public static BigInteger sumOfAsciiStrings(String value) {
        BigInteger total = BigInteger.ZERO;
        if (isBlank(value)) {
            return total;
        }
        try {
            byte[] bytes = value.getBytes(US_ASCII);
            for (int j = 0; j < bytes.length; j++) {
                total = total.add(BigInteger.valueOf(bytes[j] - ALPHABET_START_POS));
            }
        } catch (UnsupportedEncodingException e) {
            //fall through.
        }
        return total;
    }

    public static void printSumOfAllStrings(List<String> names, String fileName) {
        if (names == null || names.size() == 0) {
            LOGGER.info(format("Sum of strings in %s is: %s", fileName, 0));
            return;
        }
        IntSupplier intSupplier = new IntSupplier() {
            int count = 1;

            public int getAsInt() {
                return count++;
            }
        };
        final BigInteger sum = names.stream()
                .sorted()
                .map(x -> sumOfAsciiStrings(x))
                .filter(x -> x.compareTo(BigInteger.ZERO) > 0)
                .map(total -> total.multiply(BigInteger.valueOf(intSupplier.getAsInt())))
                .reduce((x, y) -> x.add(y))
                .get();
        LOGGER.info(format("Sum of strings in %s is: %s", fileName, sum));
    }
}
