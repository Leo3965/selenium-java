package model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastroLeilaoPage extends PageObject {
    private static final String URL_CADASTRO = "http://localhost:8080/leiloes/new";

    public CadastroLeilaoPage(WebDriver browser) {
        super(browser);
        this.browser = browser;
    }

    public LeilaoPage cadastrarLeilao(String nomeDoLeilao, String valor, String data) {
        this.browser.findElement(By.id("nome")).sendKeys(nomeDoLeilao);
        this.browser.findElement(By.id("valorInicial")).sendKeys(valor);
        this.browser.findElement(By.id("dataAbertura")).sendKeys(data);
        this.browser.findElement(By.id("button-submit")).submit();
        return new LeilaoPage(browser);
    }

    public boolean isPaginaAtual() {
        return browser.getCurrentUrl().equals(URL_CADASTRO.replace("/new", ""));
    }

    public boolean isErrorMessageVisibel() {
        String pageSource = browser.getPageSource();
        return pageSource.contains("minimo 3 caracteres") &&
                pageSource.contains("não deve estar em branco") &&
                pageSource.contains("deve ser um valor maior de 0.1") &&
                pageSource.contains("deve ser uma data no formato dd/MM/yyyy");
    }
}