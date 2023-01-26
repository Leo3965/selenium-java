import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloSelenium {
    private WebDriver browser;
    @BeforeAll
    static void before() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver-109.0.5414.74.exe");
    }
    @Test
    public void hello() {
        browser = new ChromeDriver();
        browser.navigate().to("http://localhost:8080/leiloes");
        browser.quit(); // Fecha navegador
    }
}
