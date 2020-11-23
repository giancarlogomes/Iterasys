package site;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Curso {

    WebDriver driver;
    String url;
    String pastaPrint = "evidencias/" + new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime()) + "/";

    //apoio
    //metodo para tirar print
    public void tirarPrint(String nomeEvidencia) throws IOException {
        File foto = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(foto, new File(pastaPrint + nomeEvidencia + ".png"));
    }

    //funcao para ler massa de teste
//    private String id;
//    private String curso;
//    private String valor;
//    private String subtotal;
//    private String parcelamento;
//    private String browser;
//
//    public Curso(String id, String curso, String valor, String subtotal, String parcelamento, String browser) {
//        this.id = id;
//        this.curso = curso;
//        this.valor = valor;
//        this.subtotal = subtotal;
//        this.parcelamento = parcelamento;
//        this.browser = browser;
//    }

    @Before
    public void iniciar(){
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/87/chromedriver.exe");
        url = "https://iterasys.com.br/";
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void finalizar(){
        driver.quit();
    }

    @Test
    public void consultarCursoTestLink() throws InterruptedException, IOException {
        driver.get(url);

        tirarPrint("Passo 1 - Acessou pagina inicial");

        String nomeCursoEsperado = "TestLink";
        String precoCursoEsperado = "R$ 79,99";
        String precoSubTotalEsperado = "SUBTOTAL R$ 79,99";
        String valorDasParcelasEsperado = "ou em 12 x de R$ 8,03";

        //pesquisar curso
        driver.findElement(By.id("searchtext")).sendKeys(Keys.chord(nomeCursoEsperado + Keys.ENTER));
        Thread.sleep(1000);
        tirarPrint("Passo 2 - Exibe os cursos relacionados a TestLink");
        //driver.findElement(By.cssSelector("#all_courses_search a:nth-child(2)")).click();
        driver.findElement(By.cssSelector("span.comprar")).click();
        Thread.sleep(3000);
        tirarPrint("Passo 3 - Exibe o titulo, valor e parcelamento do curso");

        //validar nome do curso
        String nomeCursoObtido = driver.findElement(By.cssSelector("span.item-title")).getText();
        assertEquals(nomeCursoEsperado, nomeCursoObtido);

        //validar preço a esquerda
        assertEquals(precoCursoEsperado,driver.findElement(By.cssSelector("span.new-price")).getText());

        //validar preco a direita
        assertEquals(precoSubTotalEsperado, driver.findElement(By.cssSelector("div.subtotal")).getText());

        //validar valor das parcelas
        //assertEquals(valorDasParcelasEsperado, driver.findElement(By.cssSelector("div.ou-parcele")).getText());
        assertTrue(driver.findElement(By.cssSelector("div.ou-parcele")).getText().contains(valorDasParcelasEsperado));
    }

    @Test
    public void consultarCursoMantis() throws InterruptedException, IOException {
        driver.get(url);

        tirarPrint("Passo 1 - Acessou pagina inicial");

        String nomeCursoEsperado = "Mantis";
        String precoCursoEsperado = "R$ 22,90";
        String precoSubTotalEsperado = "SUBTOTAL R$ 22,90";
        String valorDasParcelasEsperado = "ou em 3 x de R$ 7,63 * no cartão";

        //pesquisar curso
        driver.findElement(By.id("searchtext")).sendKeys(Keys.chord(nomeCursoEsperado + Keys.ENTER));
        Thread.sleep(1000);

        tirarPrint("Passo 2 - Exibe os cursos relacionados a Mantis");

        //driver.findElement(By.cssSelector("#all_courses_search a:nth-child(2)")).click();
        driver.findElement(By.cssSelector("span.comprar")).click();
        Thread.sleep(3000);

        tirarPrint("Passo 3 - Exibe o titulo, valor e parcelamento do curso");

        //validar nome do curso
        String nomeCursoObtido = driver.findElement(By.cssSelector("span.item-title")).getText();
        assertEquals(nomeCursoEsperado, nomeCursoObtido);

        //validar preço a esquerda
        assertEquals(precoCursoEsperado,driver.findElement(By.cssSelector("span.new-price")).getText());

        //validar preco a direita
        assertEquals(precoSubTotalEsperado, driver.findElement(By.cssSelector("div.subtotal")).getText());

        //validar valor das parcelas
        //assertEquals(valorDasParcelasEsperado, driver.findElement(By.cssSelector("div.ou-parcele")).getText());
        assertTrue(driver.findElement(By.cssSelector("div.ou-parcele")).getText().contains(valorDasParcelasEsperado));
    }

    @Test
    public void consultarCursoFormacaoTesteSoftware() throws InterruptedException, IOException {
        driver.get(url);

        tirarPrint("Passo 1 - Acessou pagina inicial");

        String nomeCursoEsperado = "Formação em Teste de Software (Videoconferência)";
        String precoCursoEsperado = "R$ 1.990,00";
        String precoSubTotalEsperado = "SUBTOTAL R$ 1.990,00";
        String valorDasParcelasEsperado = "ou em 12 x de R$ 165,83 * no cartão";

        //pesquisar curso
        driver.findElement(By.id("searchtext")).sendKeys(Keys.chord(nomeCursoEsperado + Keys.ENTER));
        Thread.sleep(1000);

        tirarPrint("Passo 2 - Exibe os cursos relacionados a Formacao em Teste de Software");

        //driver.findElement(By.cssSelector("#all_courses_search a:nth-child(2)")).click();
        driver.findElement(By.cssSelector("span.comprar")).click();
        Thread.sleep(3000);

        tirarPrint("Passo 3 - Exibe o titulo, valor e parcelamento do curso");

        //validar nome do curso
        String nomeCursoObtido = driver.findElement(By.cssSelector("span.item-title")).getText();
        assertEquals(nomeCursoEsperado, nomeCursoObtido);

        //validar preço a esquerda
        assertEquals(precoCursoEsperado,driver.findElement(By.cssSelector("span.new-price")).getText());

        //validar preco a direita
        assertEquals(precoSubTotalEsperado, driver.findElement(By.cssSelector("div.subtotal")).getText());

        //validar valor das parcelas
        //assertEquals(valorDasParcelasEsperado, driver.findElement(By.cssSelector("div.ou-parcele")).getText());
        assertTrue(driver.findElement(By.cssSelector("div.ou-parcele")).getText().contains(valorDasParcelasEsperado));
    }
}