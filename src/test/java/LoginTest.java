import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;


public class LoginTest {
    private WebDriver browser;
    private static final String URL_LOGIN = "http://localhost:8080/login";

    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver-109.0.5414.74.exe");
    }

    @BeforeEach
    void beforeEach() {
        browser = new ChromeDriver();
        browser.navigate().to(URL_LOGIN);
    }

    @AfterEach
    void afterEach() {
        browser.quit(); // Fecha navegador
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        WebElement username = browser.findElement(By.name("username"));
        username.sendKeys("fulano");

        WebElement password = browser.findElement(By.name("password"));
        password.sendKeys("pass");

        WebElement form = browser.findElement(By.id("login-form"));
        form.submit();

        WebElement currentUser = browser.findElement(By.id("usuario-logado"));

        assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));
        assertEquals("fulano", currentUser.getText());
    }

    @Test
    void naoDeveriaLogarComDadosInvalidos() {
        WebElement username = browser.findElement(By.name("username"));
        username.sendKeys("invalido");

        WebElement password = browser.findElement(By.name("password"));
        password.sendKeys("123123");

        WebElement form = browser.findElement(By.id("login-form"));
        form.submit();


        String page = browser.getPageSource();

        // Verificando se permanece na página de login
        assertTrue(browser.getCurrentUrl().equals(URL_LOGIN + "?error"));
        // Verificando se o login e senha são inválidos
        assertTrue(page.contains("Usuário e senha inválidos."));
        // Verificando se o elemento que contém o nome do usuário está vazio
        assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")).getText());
    }

    @Test
    void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        browser.navigate().to("http://localhost:8080/leiloes/2");
        assertTrue(browser.getCurrentUrl().equals(URL_LOGIN));
        assertFalse(browser.getPageSource().contains("Dados do Leilão"));
    }
}
