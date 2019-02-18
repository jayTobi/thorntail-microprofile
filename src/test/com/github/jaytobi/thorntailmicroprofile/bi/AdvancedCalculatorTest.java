package com.github.jaytobi.thorntailmicroprofile.bi;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class AdvancedCalculatorTest {
    private AdvancedCalculator advancedCalculator;

    @BeforeEach
    public void init() {
        //we could make the methods static - but this is not important atm
        advancedCalculator = new AdvancedCalculator();
    }

    @Test
    void sum3PositiveValues() {
        List<Long> values = Arrays.asList(1L, 2L, 3L);
        assertThat(values.size()).isEqualTo(3);
        assertThat(advancedCalculator.sumAll(values)).isEqualTo(6);
    }

    @Test
    void sumEmptyValues() {
        List<Long> values = Lists.emptyList();
        assertThat(values).isEmpty();
        assertThat(advancedCalculator.sumAll(values)).isLessThan(BigInteger.ONE);
        assertThat(advancedCalculator.sumAll(values)).isEqualTo(BigInteger.ZERO);
    }

    @Test
    void zeroSum() {
        List<Long> values = Arrays.asList(-1L, 1L, 2L, -2L);
        assertThat(values).hasSize(4);
        assertThat(advancedCalculator.sumAll(values)).isEqualTo(BigInteger.ZERO);
    }

    @Test
    void evenNumbers() {
        List<Integer> values = Arrays.asList(1, 2, 3, 4);
        List<Integer> even = advancedCalculator.onlyEvenNumbers(values);
        assertThat(even).hasSize(2);
        assertThat(even).contains(2, 4);
        assertThat(even).contains(2).contains(4);
        assertThat(even).doesNotContain(1, 3);
        assertThat(even).containsOnly(2, 4);
        assertThat(even).containsOnlyOnce(2);
    }
}
