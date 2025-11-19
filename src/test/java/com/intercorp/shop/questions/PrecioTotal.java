package com.intercorp.shop.questions;

import com.intercorp.shop.ui.ShopPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.questions.Text;

public class PrecioTotal implements Question<String> {

    public static PrecioTotal mostrado() { return new PrecioTotal(); }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(ShopPage.PRECIO_TOTAL).answeredBy(actor);
    }
}
