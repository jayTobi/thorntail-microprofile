package com.github.jaytobi.thorntailmicroprofile.bi;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is a simple class with some logic to show the possibilities and syntax of AssertJ
 * in the corresponding test.
 */
public class AdvancedCalculator {
    public BigInteger sumAll(List<Long> values) {
        BigInteger sum = BigInteger.ZERO;
        for (Long value : values) {
            sum = sum.add(BigInteger.valueOf(value));
        }
        return sum;
    }

    public List<Integer> onlyEvenNumbers(List<Integer> values) {
        return values.stream().filter(v -> v % 2 == 0).collect(Collectors.toList());
    }
}
