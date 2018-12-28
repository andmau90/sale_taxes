package com.test.idm;

import com.test.idm.cart.Billing;

/**
 *
 * @author Andrea
 */
public class IDM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Billing billing;
        
        /* 
            1 book : 12.49 
            1 music CD: 16.49 
            1 chocolate bar: 0.85 
            Sales Taxes: 1.50 Total: 29.83 
        */
        CommandFactory cf = CommandFactory.init();
        cf.executeCommand( CommandFactory.ADD_PRODUCTS, "1 book at 12.49");
        cf.executeCommand( CommandFactory.ADD_PRODUCTS, "1 music CD at 14.99");
        cf.executeCommand( CommandFactory.ADD_PRODUCTS, "1 chocolate bar at 0.85");
        billing = cf.getBilling();
        billing.evaluate();
        System.out.println(cf.getBilling().toString());
        billing.clear();
        
        /* 
            1 imported box of chocolates: 10.50 
            1 imported bottle of perfume: 54.65 
            Sales Taxes: 7.65 Total: 65.15 
        */
        cf.executeCommand( CommandFactory.ADD_PRODUCTS, "1 imported box of chocolates at 10.00");
        cf.executeCommand( CommandFactory.ADD_PRODUCTS, "1 imported bottle of perfume at 47.50");
        billing = cf.getBilling();
        billing.evaluate();
        System.out.println(cf.getBilling().toString());
        billing.clear();
        
        /* 
            1 imported bottle of perfume: 32.19 
            1 bottle of perfume: 20.89 
            1 packet of headache pills: 9.75 
            1 imported box of chocolates: 11.85 
            Sales Taxes: 6.70 Total: 74.68
        */
        cf.executeCommand( CommandFactory.ADD_PRODUCTS, "1 imported bottle of perfume at 27.99");
        cf.executeCommand( CommandFactory.ADD_PRODUCTS, "1 bottle of perfume at 18.99");
        cf.executeCommand( CommandFactory.ADD_PRODUCTS, "1 packet of headache pills at 9.75");
        cf.executeCommand( CommandFactory.ADD_PRODUCTS, "1 box of imported chocolates at 11.25");
        billing = cf.getBilling();
        billing.evaluate();
        System.out.println(cf.getBilling().toString());
        billing.clear();
    }
    
}
