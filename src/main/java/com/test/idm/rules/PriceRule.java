package com.test.idm.rules;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Andrea
 */
public abstract class PriceRule {
    abstract public float apply(float price);
    
    float nearest(float price){
        BigDecimal bPrice = new BigDecimal("" + price);
        BigDecimal tick = new BigDecimal("0.05");
        BigDecimal newPrice = bPrice.divide(tick, 9, RoundingMode.HALF_EVEN);
        newPrice = newPrice.setScale(0, RoundingMode.UP).multiply(tick);
        return newPrice.floatValue();
    }
}
