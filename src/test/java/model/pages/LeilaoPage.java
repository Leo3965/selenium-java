package model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeilaoPage extends PageObject {
    private static final String URL_LEILAO = "http://localhost:8080/leiloes";

    public LeilaoPage(WebDriver browser) {
        super(browser);
        this.browser = browser;
    }

    public CadastroLeilaoPage carregarFormulario() {
        //this.browser.navigate().to(URL_LEILAO + "/new");
        WebElement botao = browser.findElement(By.id("novo_leilao_link"));
        botao.click();
        return new CadastroLeilaoPage(browser);
    }

    public boolean isLeilaoCadastrado(String nome, String valor, String data) {
        WebElement linhaDaTabela = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
        WebElement colunaNome = linhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaData = linhaDaTabela.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement colunaValor = linhaDaTabela.findElement(By.cssSelector("td:nth-child(3)"));
        return (
                nome.equals(colunaNome.getText()) &&
                        valor.equals(colunaValor.getText()) &&
                        data.equals(colunaData.getText())
        );
    }
}
