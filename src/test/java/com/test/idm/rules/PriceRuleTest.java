package com.test.idm.rules;

import org.junit.Test;

import static org.junit.Assert.*;

public class PriceRuleTest {
    private static final double DELTA = 0.0001;

    @Test
    public void nearest() {
        WithoutTaxRule rule = new WithoutTaxRule();
        assertEquals( 0.6, rule.nearest( 0.57f ), DELTA );
        assertEquals( 0.55, rule.nearest( 0.54f ), DELTA );
        assertEquals( 0.55, rule.nearest( 0.51f ), DELTA );
        assertEquals( 0.50, rule.nearest( 0.50f ), DELTA );
        assertEquals( 0.55, rule.nearest( 0.509f ), DELTA );
        assertEquals( 0.6, rule.nearest( 0.559f ), DELTA );
    }
}