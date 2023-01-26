package model.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public abstract class PageObject {
    protected WebDriver browser;

    public PageObject(WebDriver browser) {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver-109.0.5414.74.exe");
        // Se nulo cria nova jabela no chrome
        this.browser = (browser == null) ? new ChromeDriver() : browser;

        this.browser.manage().timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS)
                .pageLoadTimeout(3, TimeUnit.SECONDS);
    }

    public void sair() {
        this.browser.quit();
    }
}
