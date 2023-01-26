package login;

import model.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class LoginPageObjectTest {
    private LoginPage paginaDeLogin;

    @BeforeEach
    void beforeEach() {
        this.paginaDeLogin = new LoginPage();
    }

    @AfterEach
    void afterEach() {
        this.paginaDeLogin.sair(); // Fecha navegador
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        this.paginaDeLogin.preencheFormulario("fulano", "pass");

        assertFalse(this.paginaDeLogin.isPaginaDeLogin());
        assertEquals("fulano", this.paginaDeLogin.getNomeUsuarioLogado());
    }

    @Test
    void naoDeveriaLogarComDadosInvalidos() {
        this.paginaDeLogin.preencheFormulario("invalido", "123123");

        // Verificando se permanece na página de login
        assertTrue(this.paginaDeLogin.isPaginaDeLogin());
        // Verificando se o login e senha são inválidos
        assertTrue(this.paginaDeLogin.contemTexto("Usuário e senha inválidos."));
        // Verificando se o elemento que contém o nome do usuário está vazio
        assertNull(this.paginaDeLogin.getNomeUsuarioLogado());
    }

    @Test
    void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        this.paginaDeLogin.navegarPara("/leiloes/2");
        assertTrue(this.paginaDeLogin.isPaginaDeLogin());
        assertFalse(this.paginaDeLogin.contemTexto("Dados do Leilão"));
    }
}
