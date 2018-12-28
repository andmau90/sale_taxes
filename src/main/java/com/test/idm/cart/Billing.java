package com.test.idm.cart;

import com.test.idm.rules.PriceRule;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrea
 */
public class Billing {
    private List<CartItem> items;
    private float total = 0;
    private float taxes = 0;
    
    public Billing(){
        items = new ArrayList<>();
    }
    
    private float evaluateItemTaxes(CartItem item){
        float tax = 0;
        for(PriceRule rule: item.getRules()){
            tax += rule.apply(item.getPrice());
        }
        return tax;
    }
    public void addCartItem(CartItem item){
        items.add(item);
    }
    
    private void updateTotal(){
        total = 0;
        items.forEach((item) -> total +=  item.getPrice() + item.getTax());
    }
    
    private void updateTaxes(){
        taxes = 0;
        items.forEach((item) -> {
            item.setTax( evaluateItemTaxes( item ));
            taxes +=  item.getTax();
        });
    }
    
    public void evaluate(){
        updateTaxes();
        updateTotal();
    }

    public int size(){
        return items.size();
    }

    public float total(){
        return total;
    }

    public float taxes(){
        return taxes;
    }
    
    @Override
    public String toString(){
        String billing = "";
        billing = items.stream().map((item) -> item.toString() + "\n").reduce(billing, String::concat);
        billing += "Sales Taxes: " + taxes  + "\n";
        billing += "Total: " + total + "\n";
        return billing;
    }
    
    public void clear(){
        items.clear();
        taxes = 0;
        total = 0;
    }
}
