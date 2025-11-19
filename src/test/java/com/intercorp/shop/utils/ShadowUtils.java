package com.intercorp.shop.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ShadowUtils {

    // ======================================================
    // MÉTODO PRINCIPAL: BUSCAR 1 ELEMENTO EN TODOS LOS SHADOW ROOTS
    // ======================================================
    public static WebElement deepFindElement(WebDriver driver, String selector) {
        List<WebElement> results = deepFindElements(driver, selector);

        if (results.isEmpty()) {
            throw new NoSuchElementException("No se pudo descender más buscando: " + selector);
        }

        return results.getFirst();
    }

    // ======================================================
    // MÉTODO PARA BUSCAR VARIOS ELEMENTOS EN TODOS LOS SHADOW ROOTS
    // ======================================================
    public static List<WebElement> deepFindElements(WebDriver driver, String selector) {
        List<WebElement> found = new ArrayList<>();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Script JS REAL que recorre TODOS los shadow-roots recursivamente
        String script =
                "const findAll = (root) => {" +
                        " let out = [];" +
                        " try {" +
                        "   root.querySelectorAll(arguments[0]).forEach(e => out.push(e));" +
                        " } catch(e){}" +

                        " const it = root.querySelectorAll('*');" +
                        " for (let el of it) {" +
                        "   if (el.shadowRoot) {" +
                        "      out = out.concat(findAll(el.shadowRoot));" +
                        "   }" +
                        " }" +
                        " return out;" +
                        "};" +
                        "return findAll(document);";

        List<WebElement> elements = (List<WebElement>) js.executeScript(script, selector);

        if (elements != null) {
            found.addAll(elements);
        }

        return found;
    }

    // ======================================================
    // ESPERAR A QUE UN ELEMENTO EN SHADOW DOM EXISTA
    // ======================================================
    public static void waitForShadowElement(WebDriver driver, String selector) {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> {
                    try {
                        return !deepFindElements(driver, selector).isEmpty();
                    } catch (Exception e) {
                        return false;
                    }
                });
    }
}
