package com.intercorp.shop.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import com.intercorp.shop.utils.ShadowUtils;

import org.openqa.selenium.*;
import java.util.List;

public class ClickProductoExacto implements Interaction {

    private final String nombre;

    public ClickProductoExacto(String nombre) {
        this.nombre = nombre;
    }

    public static ClickProductoExacto llamado(String nombre) {
        return Tasks.instrumented(ClickProductoExacto.class, nombre);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        // 1. obtener todos los <shop-list-item>
        List<WebElement> items = ShadowUtils.deepFindElements(driver, "shop-list-item");

        if (items.isEmpty()) {
            throw new RuntimeException("No se encontraron shop-list-item en el catálogo");
        }

        for (WebElement item : items) {

            // 2. entrar al shadow-root del item
            SearchContext shadow = item.getShadowRoot();

            // 3. obtener el titulo
            WebElement titulo = shadow.findElement(By.cssSelector("div.title"));

            if (titulo.getText().trim().equalsIgnoreCase(nombre.trim())) {

                // 4. subir al padre <li> y encontrar su <a>
                WebElement li = (WebElement) ((JavascriptExecutor) driver)
                        .executeScript("return arguments[0].closest('li');", item);

                WebElement link = li.findElement(By.cssSelector("a"));

                link.click();
                return;
            }
        }

        throw new RuntimeException("No se encontró el producto: " + nombre);
    }
}
