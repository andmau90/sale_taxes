package com.test.idm.cart;

import com.test.idm.rules.PriceRule;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrea
 */
public class CartItem {
    private List<PriceRule> rules = new ArrayList<>();
    private String name;
    private float price;
    private float tax = 0;
    private int number;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    public void addRule(PriceRule rule){
        this.rules.add(rule);
    }

    public List<PriceRule> getRules() {
        return rules;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }
    
    public float getTotal(){
        return getPrice() + getTax();
    }

    @Override
    public String toString(){
        return "" + getNumber() + " " + getName() + ": " + getTotal();
    }
}
