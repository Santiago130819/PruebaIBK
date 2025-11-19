package com.intercorp.shop.interactions;

import com.intercorp.shop.utils.ShadowUtils;
import net.serenitybdd.screenplay.*;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClickCatalogoGenero implements Interaction {

    private final String genero;

    public ClickCatalogoGenero(String genero) {
        this.genero = genero;
    }

    public static ClickCatalogoGenero del(String genero) {
        return Tasks.instrumented(ClickCatalogoGenero.class, genero);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        String selector = genero.equalsIgnoreCase("hombre") ?
                "a[aria-label*='Men']" :
                "a[aria-label*='Ladies']";

        WebElement catalogo = ShadowUtils.deepFindElement(driver, selector);
        catalogo.click();
    }
}
