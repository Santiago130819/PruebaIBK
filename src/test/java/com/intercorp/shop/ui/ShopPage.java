package com.intercorp.shop.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ShopPage {


    // Cart total - approximate selectors used by Polymer shop cart area
    public static final Target PRECIO_TOTAL = Target.the("precio total").located(By.cssSelector(".cart-total, .total, .cart-price, #cart-total"));

}
