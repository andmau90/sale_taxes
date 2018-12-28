package com.test.idm.rules;

/**
 *
 * @author Andrea
 */
public class WithoutTaxRule extends PriceRule{
    
    public float apply(float price) {
        return 0;
    }
    
}
