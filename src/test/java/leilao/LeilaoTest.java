package leilao;

import model.pages.CadastroLeilaoPage;
import model.pages.LeilaoPage;
import model.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeilaoTest {
    private LeilaoPage leilaoPage;
    private CadastroLeilaoPage cadastroPage;

    @BeforeEach
    void beforeEach() {
        LoginPage loginPage = new LoginPage();
        this.leilaoPage = loginPage.preencheFormulario("fulano", "pass");
        this.cadastroPage = this.leilaoPage.carregarFormulario();
    }
    @AfterEach
    void afterEach() {
        this.leilaoPage.sair(); // Fecha navegador
    }


    @Test
    void deveriaCadastrarLeilao() {
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilão do dia " + hoje;
        String valor = "500.00";

        this.leilaoPage = cadastroPage.cadastrarLeilao(nome, valor, hoje);
        assertTrue(leilaoPage.isLeilaoCadastrado(nome, valor, hoje));
    }

    @Test
    void deveriaValidarCadastroDeLeilao() {
        this.leilaoPage = cadastroPage.cadastrarLeilao("", "", "");
        assertTrue(this.cadastroPage.isPaginaAtual());
        assertTrue(this.cadastroPage.isErrorMessageVisibel());
    }
}
