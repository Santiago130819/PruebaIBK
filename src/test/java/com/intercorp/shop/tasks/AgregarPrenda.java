package com.intercorp.shop.tasks;

import com.intercorp.shop.interactions.ClickCatalogoGenero;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import com.intercorp.shop.interactions.ClickProductoExacto;
import com.intercorp.shop.utils.ShadowUtils;

import org.openqa.selenium.WebDriver;

public class AgregarPrenda implements Task {

    private final String nombreProducto;
    private final String genero;
    private final String talla;

    public AgregarPrenda(String nombreProducto, String genero, String talla) {
        this.nombreProducto = nombreProducto;
        this.genero = genero;
        this.talla = talla;
    }

    public static AgregarPrenda conDatos(String nombreProducto, String genero, String talla) {
        return Tasks.instrumented(AgregarPrenda.class, nombreProducto, genero, talla);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        // 1. Click en Hombre/Mujer
        actor.attemptsTo(
                ClickCatalogoGenero.del(genero)
        );

        // 2. Esperar a que el catálogo (shop-list) esté cargado
        ShadowUtils.waitForShadowElement(driver, "shop-list");

        // 3. Esperar a que exista el ul.grid (CONFIRMADO EN TU CAPTURA)
        ShadowUtils.waitForShadowElement(driver, "ul.grid");

        // 4. Click en el producto exacto
        actor.attemptsTo(
                ClickProductoExacto.llamado(nombreProducto)
        );


    }
}
