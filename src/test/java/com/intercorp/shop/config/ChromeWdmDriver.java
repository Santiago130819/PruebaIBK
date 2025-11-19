package com.intercorp.shop.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeWdmDriver implements DriverSource {

    @Override
    public WebDriver newDriver() {
        System.out.println(">>> USANDO WebDriverManager + Chrome ACTUAL <<<");
        WebDriverManager.chromedriver().setup(); // descarga el driver que corresponde a tu Chrome 142
        return new ChromeDriver();
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }
}
