package steps;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.Evidences;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ConsultaProduto {

    String url;
    WebDriver driver;
    static String pastaPrints = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(Calendar.getInstance().getTime()) + "/";
    Evidences evidences = new Evidences();
    String id;

    @Before
    public void iniciar() {
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/87/chromedriver.exe");
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--disable-notifications");

        evidences = new Evidences();

        url = "https://www.petz.com.br/";

        driver = new ChromeDriver(co);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);

        System.out.println("Passo 0 - Configurou o setup");
    }

    @After
    public void finalizar() {
        driver.quit();
        System.out.println("Passo 6 - Fechou o browser");
    }

    @Dado("^que acesso o site da Petz \"([^\"]*)\"$")
    public void queAcessoOSiteDaPetz(String id) throws IOException {
        this.id = id;
        driver.get(url);
        System.out.println("Acessou o site da Petz");
        evidences.screenshot("Passo 1 - Acessou o site da Petz", driver, pastaPrints, id);
    }

    @Quando("^procuro por \"([^\"]*)\" e pressiono Enter$")
    public void procuro_por_e_pressiono_Enter(String termo) throws IOException {
        driver.findElement(By.cssSelector("input#search")).sendKeys(Keys.chord(termo));
        evidences.screenshot("Procurou por " + termo, driver, pastaPrints, id);
        driver.findElement(By.cssSelector("input#search")).sendKeys(Keys.ENTER);
        System.out.println("Passo 2 - Procurou por " + termo);

    }

    @Quando("^procuro por \"([^\"]*)\" e clico na Lupa$")
    public void procuro_por_e_clico_na_Lupa(String termo) throws IOException {
        driver.findElement(By.cssSelector("input#search")).sendKeys(Keys.chord(termo));
        evidences.screenshot("Procurou por " + termo, driver, pastaPrints, id);
        driver.findElement(By.cssSelector("button.button-search")).click();
        System.out.println("Passo 3 - Procurou por " + termo);
    }

    @Entao("^exibe a lista de produtos relacionados a \"([^\"]*)\"$")
    public void exibe_a_lista_de_produtos_relacionados_a(String termo) throws IOException {
        assertEquals(termo + " - Produtos pet em promoção | Petz", driver.getTitle());
        assertEquals("RESULTADOS PARA \"" + termo.toUpperCase() + "\"", driver.findElement(By.cssSelector("h1.nomeCategoria")).getText());
        System.out.println("Exibiu a lista de produtos relacionados a " + termo);
        evidences.screenshot("Passo 4 - Exibiu a lista de produtos relacionados a " + termo, driver, pastaPrints, id);
    }

    @Quando("^seleciono o primeiro produto da lista$")
    public void seleciono_o_primeiro_produto_da_lista() throws Throwable {
        driver.findElement(By.xpath("//h3[contains(text(),'Ração Royal Canin Maxi - Cães Adultos - 15kg')]")).click();
        System.out.println("Selecionou o primeiro produto da lista");
        evidences.screenshot("Passo 5 - Selecionou o primeiro produto da lista", driver, pastaPrints, id);
    }

    @Entao("^verifico a marca como \"([^\"]*)\" com preco normal de \"([^\"]*)\" e \"([^\"]*)\" para assinantes$")
    public void verifico_que_a_marca_com_preco_normal_de_e_para_assinantes(String marca, String precoNormal,
                                                                           String precoAssinante) throws IOException {
        // verifica a marca
        assertEquals(marca, driver.findElement(By.cssSelector("span.blue")).getText());

        //verifica o preço normal
        assertEquals(precoNormal, driver.findElement(By.cssSelector("div.price-current")).getText());

        //verifica o preço de assinante
        assertEquals(precoAssinante, driver.findElement(By.cssSelector("span.price-subscriber")).getText());

        System.out.println("Verificou a marca como " + marca + ", o preco normal como " + precoNormal +
                " e o preco de assinante como " + precoAssinante);

        evidences.screenshot("Verificou", driver, pastaPrints, id);
    }

    @Entao("^exibe uma lista de produtos relacionados e a mensagem de que nao encontrou \"([^\"]*)\"$")
    public void exibe_uma_lista_de_produtos_relacionados_e_a_mensagem_de_que_nao_encontrou_o_termo_pesquisado(String termo) throws IOException {

        assertEquals("Os resultados desta busca são aproximados, pois não encontramos resultados para \"" + termo + "\"", driver.findElement(By.cssSelector("span.descricao-lucene:nth-child(2)")).getText());
        System.out.println("Exibiu a lista de produtos relacionados a " + termo);

        evidences.screenshot("Exibiu a lista de produtos relacionados a " + termo, driver, pastaPrints, id);
    }


    @Entao("^exibe uma caixa de alerta informando que precisa preencher pelo menos tres letras na pesquisa$")
    public void exibeUmaCaixaDeAlertaInformandoQuePrecisaPreencherPeloMenosTresLetrasNaPesquisa() throws InterruptedException, IOException {
        Thread.sleep(3000);

        assertEquals("Digite pelo menos 3 caracteres", driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();

        System.out.println("Exibiu o alert informando que precisa preencher pelo menos tres letras na pesquisa");

        evidences.screenshot("Exibiu alert", driver, pastaPrints, id);
    }

    @Entao("^verifico a \"([^\"]*)\" com \"([^\"]*)\" e \"([^\"]*)\"$")
    public void verificoAComE(String marca, String precoNormal, String precoAssinante) throws IOException {

        // verifica a marca
        assertEquals(marca, driver.findElement(By.cssSelector("span.blue")).getText());

        //verifica o preço normal
        assertEquals(precoNormal, driver.findElement(By.cssSelector("div.price-current")).getText());

        //verifica o preço de assinante
        assertEquals(precoAssinante, driver.findElement(By.cssSelector("span.price-subscriber")).getText());

        System.out.println("Verificou a marca como " + marca + ", o preco normal como " + precoNormal +
                " e o preco de assinante como " + precoAssinante);

        evidences.screenshot("Verificou", driver, pastaPrints, id);
    }

    @Quando("^seleciono o \"([^\"]*)\" da lista$")
    public void selecionoODaLista(String produtoDescricao) throws IOException {
        driver.findElement(By.xpath("//h3[contains(text(),'" + produtoDescricao + "')]")).click();
        System.out.println("Selecionou o primeiro produto da lista");
        evidences.screenshot("Selecionou o primeiro produto da lista", driver, pastaPrints, id);
    }
}
