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

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class Google {

    WebDriver driver;
    static String url;
    String pastaPrints = "google/" + new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime()) + "/";

    public void tirarPrint(String nomePrint) throws IOException {
        File foto = ((TakesScreenshot)driver).getScreenshotAs((OutputType.FILE));
        FileUtils.copyFile(foto, new File("evidencias/" + pastaPrints + nomePrint + ".png"));
    }

    private String id;
    private String pesquisaGoogle;
    private String pesquisaYoutube;
    private String descricao;
    private String browser;

    public Google(String id, String pesquisaGoogle, String pesquisaYoutube, String descricao, String browser) throws IOException {
        this.id = id;
        this.pesquisaGoogle = pesquisaGoogle;
        this.pesquisaYoutube = pesquisaYoutube;
        this.descricao = descricao;
        this.browser = browser;

        LerArquivo();
    }

    @Parameters
    public static Collection<String[]> LerArquivo() throws IOException {
        return LerCsv("db/FTS128 Massa Google.csv");
    }

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
    public static void inciarClasse(){
        url = "https://www.google.com/";
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
    public void pesquisaGoogle() throws IOException, InterruptedException {
        driver.get(url);

        tirarPrint("1 - Acessou pagina do google");

        driver.findElement(By.cssSelector("input.gLFyf")).sendKeys(Keys.chord(pesquisaGoogle) + Keys.ENTER);
        Thread.sleep(5000);

        tirarPrint("2 - Realizou a pesquisa no Google");

        driver.findElement(By.cssSelector("div.g:nth-child(1) div.rc h3")).click();
        Thread.sleep(5000);

        tirarPrint("3 - Acessou o primeiro resultado da pesquisa");

        driver.findElement(By.cssSelector("input#search")).sendKeys(Keys.chord(pesquisaYoutube) + Keys.ENTER);
        Thread.sleep(5000);

        tirarPrint("4 - Pesquisou no youtube");

        assertEquals(pesquisaYoutube, driver.findElement(By.cssSelector("div#content-section yt-formatted-string#text")).getText());
        assertEquals(descricao, driver.findElement(By.cssSelector("div#content-section yt-formatted-string#description span.style-scope:nth-child(1)")).getText());
    }
}
