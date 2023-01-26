package model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage extends PageObject {
    private static final String URL_LOGIN = "http://localhost:8080/login";

    public LoginPage() {
        super(null);

        this.browser = new ChromeDriver(); // Abre nova janela do navegador
        this.browser.navigate().to(URL_LOGIN);
    }



    public LeilaoPage preencheFormulario(String login, String senha) {
        browser.findElement(By.name("username")).sendKeys(login);
        browser.findElement(By.name("password")).sendKeys(senha);
        return efetuaLogin();
    }

    private LeilaoPage efetuaLogin() {
        browser.findElement(By.id("login-form")).submit();
        return new LeilaoPage(browser);
    }

    public boolean isPaginaDeLogin() {
        return browser.getCurrentUrl().contains(URL_LOGIN);
    }

    public String getNomeUsuarioLogado() {
        try {
            return browser.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public boolean contemTexto(String msg) {
        return browser.getPageSource().contains(msg);
    }

    public void navegarPara(String path) {
        this.browser.navigate().to(URL_LOGIN + path);
    }
}
