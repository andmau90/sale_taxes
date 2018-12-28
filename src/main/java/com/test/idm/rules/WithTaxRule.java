package com.test.idm.rules;

/**
 *
 * @author Andrea
 */
public class WithTaxRule extends PriceRule{
    public static final float TAX = 0.10f;
    
    public float apply(float price) {
        return nearest(price * TAX);
    }
    
}
