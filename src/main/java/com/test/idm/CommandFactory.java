package com.test.idm;

import com.test.idm.cart.specials.Foods;
import com.test.idm.cart.specials.Books;
import com.test.idm.cart.specials.MedicalProducts;
import com.test.idm.exceptions.UnparsableCommandException;
import com.test.idm.cart.Billing;
import com.test.idm.cart.CartItem;
import com.test.idm.rules.ImportedRule;
import com.test.idm.rules.WithTaxRule;
import com.test.idm.rules.WithoutTaxRule;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The com.test.idm.CommandFactory class.
 */
class CommandFactory {
    static final String ADD_PRODUCTS = "add products";
    
    private final Map<String, Command> commands;
    private final Billing billing;
    
    private CommandFactory() {
        commands = new HashMap<>();
        billing = new Billing();
    }

    public void addCommand(final String name, final Command command) {
        commands.put(name, command);
    }

    public void executeCommand(String name, String command) {
        if (commands.containsKey(name)) {
            commands.get(name).apply(command);
        }
    }

    public void addToCart(CartItem item ){
        billing.addCartItem(item);
    }
    
    /* Factory pattern */
    public static CommandFactory init() {
        final CommandFactory cf = new CommandFactory();
        // Commands are added here using lambdas. It is also possible to dynamically add
        // commands without editing the code.
        cf.addCommand(ADD_PRODUCTS, (String command) -> {
            try{
                cf.addToCart(cf.getCartItem(command));
            } catch(UnparsableCommandException e){
                System.err.println(e.getMessage());
            }
        });

        return cf;
    }
    
    public Billing getBilling(){
        return billing;
    }
    
    public CartItem getCartItem(String command) throws UnparsableCommandException{
        if(command == null){
            throw new UnparsableCommandException("Empty command");
        }
        final Pattern pattern = Pattern.compile(Utils.REG_EX_COMMAND_VALIDATOR, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(command);
        CartItem item = new CartItem();
        if(matcher.find()) {
                try{
                    item.setNumber( Integer.parseInt(matcher.group(1)));
                } catch (NumberFormatException | NullPointerException e){
                    throw new UnparsableCommandException( "Error during number conversion");
                }

                if(matcher.group(2).contains("imported")){
                    item.addRule( new ImportedRule());
                }
                if(Utils.isProductInWithoutTaxLists(matcher.group(2), Books.BOOKS, MedicalProducts.MEDICAL_PRODUCTS, Foods.FOODS)){
                    item.addRule( new WithoutTaxRule());
                }
                else{
                    item.addRule( new WithTaxRule());
                }
                
                item.setName(matcher.group(2));
                if(Utils.priceIsValid(matcher.group(3))){
                    item.setPrice(Float.parseFloat(matcher.group(3)));
                }
                else{
                    throw new UnparsableCommandException( "Error during price conversion");
                }
        }
        else{
            throw new UnparsableCommandException("com.test.idm.Command doesn't match regex");
        }
        return item;
    }
}