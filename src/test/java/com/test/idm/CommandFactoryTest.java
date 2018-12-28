package com.test.idm;

import com.test.idm.cart.CartItem;
import com.test.idm.exceptions.UnparsableCommandException;
import com.test.idm.rules.ImportedRule;
import com.test.idm.rules.PriceRule;
import com.test.idm.rules.WithTaxRule;
import com.test.idm.rules.WithoutTaxRule;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandFactoryTest {
    private static final double DELTA = 0.0001;

    CommandFactory cf;

    @Before
    public void init(){
        cf = CommandFactory.init();
    }

    @Test
    public void getCartItem() throws UnparsableCommandException {
        CartItem item = cf.getCartItem("1 imported bottle of perfume at 47.50");
        assertNotNull(item);
        assertEquals(2, item.getRules().size());
        assertTrue(item.getRules().get(0) instanceof ImportedRule);
        assertTrue(item.getRules().get(1) instanceof WithTaxRule);
    }

    @Test
    public void getCartItem1() throws UnparsableCommandException {
        CartItem item = cf.getCartItem("1 bottle of perfume at 18.99");
        assertNotNull(item);
        assertEquals(1, item.getRules().size());
        assertTrue(item.getRules().get(0) instanceof WithTaxRule);
    }

    @Test
    public void getCartItem2() throws UnparsableCommandException {
        CartItem item = cf.getCartItem("1 packet of headache pills at 9.75");
        assertNotNull(item);
        assertEquals(1, item.getRules().size());
        assertTrue(item.getRules().get(0) instanceof WithoutTaxRule);
    }

    @Test
    public void getCartItem3() throws UnparsableCommandException {
        CartItem item = cf.getCartItem("1 box of imported chocolates at 11.25");
        assertNotNull(item);
        assertEquals(2, item.getRules().size());
        assertTrue(item.getRules().get(0) instanceof ImportedRule);
        assertTrue(item.getRules().get(1) instanceof WithoutTaxRule);
    }

    @Test
    public void addToCart() throws UnparsableCommandException {
        cf.addToCart(cf.getCartItem("1 imported bottle of perfume at 27.99"));
        cf.addToCart(cf.getCartItem("1 bottle of perfume at 18.99"));
        cf.addToCart(cf.getCartItem("1 packet of headache pills at 9.75"));
        cf.addToCart(cf.getCartItem("1 box of imported chocolates at 11.25"));
        assertEquals(4, cf.getBilling().size());
    }

    @Test
    public void evaluate() throws UnparsableCommandException {
        cf.addToCart(cf.getCartItem("1 imported bottle of perfume at 27.99"));
        cf.addToCart(cf.getCartItem("1 bottle of perfume at 18.99"));
        cf.addToCart(cf.getCartItem("1 packet of headache pills at 9.75"));
        cf.addToCart(cf.getCartItem("1 box of imported chocolates at 11.25"));
        cf.getBilling().evaluate();
        assertEquals( 74.68, cf.getBilling().total(), DELTA );
        assertEquals( 6.70, cf.getBilling().taxes(), DELTA );
    }
}