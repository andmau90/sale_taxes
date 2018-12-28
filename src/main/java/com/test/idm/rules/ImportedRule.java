package com.test.idm.rules;

/**
 *
 * @author Andrea
 */
public class ImportedRule extends PriceRule{
    public static final float TAX = 0.05f;
    
    public float apply(float price) {
        return nearest( price * TAX);
    }
    
}
