package steps.cadastraUsuario;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import utils.Evidences;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class CadastraUsuario {

    String url;
    WebDriver driver;
    Evidences evidencies;
    static String pastaPrints = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(Calendar.getInstance().getTime()) + "/";
    String id;

    @Before
    public void iniciar(){
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/87/chromedriver.exe");
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--disable-notifications");

        url = "https://www.petz.com.br/";

        driver = new ChromeDriver(co);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);

        evidencies = new Evidences();

        System.out.println("Passo 0 - Fez a configuração de setup");
    }

    @After
    public void finalizar(){
        driver.quit();
        System.out.println("Passo 6 - Fechou o navegador");
    }

    @Dado("^que acesso o site da Petz \"([^\"]*)\"$")
    public void queAcessoOSiteDaPetz(String id) throws IOException {
        this.id = id;
        driver.get(url);
        evidencies.screenshot("CadastraUsuario - Passo 1 - Acessou o site da Petz", driver, pastaPrints, id);
        System.out.println("Passo 1 - Acessou o site da Petz");
    }

    @Quando("^passo o mouse por cima do icone de usuario e clico em Criar Conta$")
    public void passoOMousePorCimaDoIconeDeUsuarioEClicoEmCriarConta() throws IOException {
        driver.findElement(By.cssSelector("button.greetings")).click();
        driver.findElement(By.cssSelector("div.dropdown div p a")).click();
        evidencies.screenshot("CadastraUsuario - Passo 2 - Clicou em Criar Conta", driver, pastaPrints, id);
        System.out.println("Passo 2 - Clicou em Criar Conta");
    }

    @Quando("^passo o mouse por cime do icone de usuario e clico em Entrar$")
    public void passoOMousePorCimeDoIconeDeUsuarioEClicoEmEntrar() throws IOException {
        driver.findElement(By.cssSelector("button.greetings")).click();
        driver.findElement(By.cssSelector("div.dropdown div a.button-login")).click();
        evidencies.screenshot("CadastraUsuario - Passo 2 - Clicou em Entrar", driver, pastaPrints, id);
        System.out.println("Passo 2 - Clicou em Entrar");
    }

    @Entao("^a tela de cadastro de usuario eh exibida$")
    public void aTelaDeCadastroDeUsuarioEhExibida() throws IOException {
        assertEquals("Minha Conta", driver.findElement(By.cssSelector("h1.pull-left")).getText());
        evidencies.screenshot("CadastraUsuario - Passo 3 - Tela de cadastro exibida", driver, pastaPrints, id);
        System.out.println("Passo 3 - Tela de cadastro exibida");
    }

    @Entao("^a tela de login eh exibida$")
    public void aTelaDeLoginEhExibida() throws IOException {
        assertEquals("Faça seu Login", driver.findElement(By.cssSelector("#loginCliente h2")).getText());
        evidencies.screenshot("CadastraUsuario - Passo 3 - Tela de login exibida", driver, pastaPrints, id);
        System.out.println("Passo 3 - Tela de login exibida");
    }

    @Quando("^preencho os dados \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" e confirmo a senha e clico em Criar Conta$")
    public void preenchoOsDadosEConfirmoASenhaEClicoEmCriarConta(String nome, String email, String celular, String sexo, String dataNascimento, String cpf, String senha) throws InterruptedException, IOException {
        driver.findElement(By.id("Nome")).sendKeys(Keys.chord(nome));
        driver.findElement(By.id("Email")).sendKeys(Keys.chord(email));
        driver.findElement(By.name("cliente.dddCelular")).sendKeys(Keys.chord(celular.substring(0,2)));
        driver.findElement(By.name("cliente.celular")).sendKeys(Keys.chord(celular.substring(2)));
        Select select = new Select(driver.findElement(By.id("Sexo")));
        select.selectByValue(sexo);
        driver.findElement(By.id("dataNascimento")).sendKeys(Keys.chord(dataNascimento));
        driver.findElement((By.id("CPF-CNPJ"))).sendKeys(Keys.chord(cpf));
        driver.findElement(By.id("Senha")).sendKeys(Keys.chord(senha));
        driver.findElement(By.id("confirmasenha")).sendKeys(Keys.chord(senha));
        driver.findElement(By.id("criarContaButton")).click();
        evidencies.screenshot("CadastraUsuario - Passo 4 - Preencheu todos os campos de cadastro", driver, pastaPrints, id);
        System.out.println("Passo 4 - Preencheu todos os campos de cadastro");
    }

    @Quando("^preencho os dados \"([^\"]*)\" \"([^\"]*)\" e clico em Entrar$")
    public void preenchoOsDadosEClicoEmEntrar(String email, String senha) throws IOException {
        driver.findElement(By.cssSelector("#loginCliente div.form-group input#email")).sendKeys(Keys.chord(email));
        driver.findElement(By.cssSelector("#loginCliente div.form-group input#senha")).sendKeys(Keys.chord(senha));
        driver.findElement(By.cssSelector("input.btn.modal-petz-btn.btn-redondo-verde")).click();
        evidencies.screenshot("CadastraUsuario - Passo 4 - Preencheu os campos de login", driver, pastaPrints, id);
        System.out.println("Passo 4 - Preencheu os campos de login");
    }

    @Entao("^eh exibido o aviso \"([^\"]*)\" e clico em Entendi$")
    public void ehExibidoOAvisoEClicoEmEntendi(String aviso) throws IOException {
        assertEquals(aviso, driver.findElement(By.xpath("//p[contains(text(),'Dados salvos com sucesso')]")).getText());
        driver.findElement(By.xpath("//a[contains(text(),'Entendi')]")).click();
        evidencies.screenshot("CadastraUsuario - Passo 5 - Cadastro efetuado com sucesso", driver, pastaPrints, id);
        System.out.println("Passo 5 - Cadastro efetuado com sucesso");
    }

    @Entao("^o \"([^\"]*)\" eh logado com sucesso$")
    public void oEhLogadoComSucesso(String usuario) throws IOException {
        assertEquals(usuario, driver.findElement(By.cssSelector("span.username")).getText());
        evidencies.screenshot("CadastraUsuario - Passo 5 - Usuario " + usuario + " logado com sucesso", driver, pastaPrints, id);
        System.out.println("Passo 5 - Usuario " + usuario + " logado com sucesso");
    }

    @Entao("^o \"([^\"]*)\" nao eh logado com sucesso$")
    public void oUsuarioNaoEhLogadoComSucesso(String usuario) throws IOException {
        assertEquals("Dados incorretos!", driver.findElement(By.cssSelector("div.dados-incorretos.escondido")).getText());
        evidencies.screenshot("CadastraUsuario - Passo 5 - Usuario " + usuario + " não logado", driver, pastaPrints, id);
        System.out.println("Passo 5 - Usuario " + usuario + " não logado");
    }
}
