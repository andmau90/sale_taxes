package com.test.idm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static final String REG_EX_COMMAND_VALIDATOR = "([0-9]+) ([\\w ]+) at ([0-9.]*)";
    public static final String REG_EX_PRICE = "^([0-9]*)(.([0-9]{2}))?$";
    
    public static boolean isProductInWithoutTaxLists(String product, String[]... withoutTaxLists) {
        if (product != null && withoutTaxLists != null) {
            for (String[] withoutTaxList : withoutTaxLists) {
                for (String withoutTaxItem : withoutTaxList) {
                    if (product.contains(withoutTaxItem)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean priceIsValid(String price){
        if(price != null){
            final Pattern pattern = Pattern.compile(REG_EX_PRICE);
            final Matcher matcher = pattern.matcher(price);
            return matcher.find();
        }
        return false;
    }
}