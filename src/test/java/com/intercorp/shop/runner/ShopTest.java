package com.intercorp.shop.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/shop/",
        glue = {"com.intercorp.shop.steps"},
        plugin = {"pretty"},
        tags = "@Escenario1"
)
public class ShopTest {
}
