package com.test.idm.rules;

import org.junit.Test;

import static org.junit.Assert.*;

public class WithTaxRuleTest {
    private static final double DELTA = 0.0001;

    @Test
    public void apply() {
        WithTaxRule rule = new WithTaxRule();
        assertEquals( 1, rule.apply( 10f ), DELTA );
        assertEquals( 1, rule.apply( 9.75f ), DELTA );
        assertEquals( 1.5, rule.apply( 14.99f ), DELTA );

    }
}