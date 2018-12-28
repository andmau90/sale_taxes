package com.test.idm.rules;

import org.junit.Test;

import static org.junit.Assert.*;

public class ImportedRuleTest {
    private static final double DELTA = 0.0001;

    @Test
    public void apply() {
        ImportedRule rule = new ImportedRule();
        assertEquals( 0.95, rule.apply( 18.99f ), DELTA );
        assertEquals( 2.4, rule.apply( 47.5f ), DELTA );
        assertEquals( 1.4, rule.apply( 27.99f ), DELTA );
        assertEquals( 0.6, rule.apply( 11.25f ), DELTA );
    }
}