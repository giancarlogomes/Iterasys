package site;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class Curso {

    WebDriver driver;
    static String url;
    String pastaPrint = "evidencias/" + new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime()) + "/";

    //apoio
    //metodo para tirar print
    public void tirarPrint(String nomeEvidencia) throws IOException {
        File foto = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(foto, new File(pastaPrint + nomeEvidencia + ".png"));
    }
    //funcao para ler massa de teste

    //atributos
    private String id;
    private String curso;
    private String valor;
    private String subtotal;
    private String parcelamento;
    private String browser;

    //construtor
    public Curso(String id, String curso, String valor, String subtotal, String parcelamento, String browser) throws IOException {
        this.id = id;
        this.curso = curso;
        this.valor = valor;
        this.subtotal = subtotal;
        this.parcelamento = parcelamento;
        this.browser = browser;

        LerArquivo();
    }

    //collection intermediaria entre construtor e a collection que vai fazer a leitura
    //serve para apontar a pasta e o nome do arquivo a ser lido

    @Parameters
    public static Collection<String[]> LerArquivo() throws IOException {
        return LerCsv("db/FTS128 Massa Iterasys.csv");
    }

    //collection que lê o arquivo csv
    public static Collection<String[]> LerCsv(String nomeCsv) throws IOException {

        BufferedReader arquivo = new BufferedReader(new FileReader(nomeCsv));
        String linha;
        List<String[]> dados = new ArrayList<String[]>();

        while((linha = arquivo.readLine()) != null){
            String[] campos = linha.split(";");
            dados.add(campos);
        }
        arquivo.close();

        return dados;
    }

    @BeforeClass
    public static void antesDeTudo(){
        url = "https://iterasys.com.br/";
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/87/chromedriver.exe");
        System.setProperty("webdriver.edge.driver", "drivers/edge/87.0.664.47/msedgedriver.exe");
        System.setProperty("webdriver.gecko.driver", "drivers/firefox/0.28.0/geckodriver.exe");
        System.setProperty("webdriver.ie.driver", "drivers/ie/3.15.1/IEDriverServer.exe");
    }

    @Before
    public void iniciar(){
        switch (browser){
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "ie":
                driver = new InternetExplorerDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void finalizar(){
        driver.quit();
    }

    @Test
    public void consultarCurso() throws InterruptedException, IOException {
        driver.get(url);

        tirarPrint("Passo 1 - Acessou pagina inicial");

        //pesquisar curso
        driver.findElement(By.id("searchtext")).sendKeys(Keys.chord(curso + Keys.ENTER));
        tirarPrint("Passo 2 - Exibe os cursos relacionados a " + curso);
        Thread.sleep(10000);
        driver.findElement(By.cssSelector("span.comprar")).click();
        Thread.sleep(3000);
        tirarPrint("Passo 3 - Exibe o titulo, valor e parcelamento do curso");

        //validar nome do curso
        String nomeCursoObtido = driver.findElement(By.cssSelector("span.item-title")).getText();
        assertEquals(curso, nomeCursoObtido);

        //validar preço a esquerda
        assertEquals(valor,driver.findElement(By.cssSelector("span.new-price")).getText());

        //validar preco a direita
        assertEquals(subtotal, driver.findElement(By.cssSelector("div.subtotal")).getText());

        //validar valor das parcelas
        //assertEquals(valorDasParcelasEsperado, driver.findElement(By.cssSelector("div.ou-parcele")).getText());
        assertTrue(driver.findElement(By.cssSelector("div.ou-parcele")).getText().contains(parcelamento));
    }
}