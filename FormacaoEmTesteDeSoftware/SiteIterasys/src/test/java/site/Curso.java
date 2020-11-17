package site;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Curso {

    WebDriver driver;
    String url;

    @Before
    public void iniciar(){
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/86/chromedriver.exe");
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
    public void consultarCursoTestLink() throws InterruptedException {
        driver.get(url);

        String nomeCursoEsperado = "TestLink";
        String precoCursoEsperado = "R$ 79,99";
        String precoSubTotalEsperado = "SUBTOTAL R$ 79,99";
        String valorDasParcelasEsperado = "ou em 12 x de R$ 8,03";

        //pesquisar curso
        driver.findElement(By.id("searchtext")).sendKeys(Keys.chord(nomeCursoEsperado + Keys.ENTER));
        Thread.sleep(1000);
        //driver.findElement(By.cssSelector("#all_courses_search a:nth-child(2)")).click();
        driver.findElement(By.cssSelector("span.comprar")).click();
        Thread.sleep(3000);

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
    public void consultarCursoMantis() throws InterruptedException {
        driver.get(url);

        String nomeCursoEsperado = "Mantis";
        String precoCursoEsperado = "R$ 49,99";
        String precoSubTotalEsperado = "SUBTOTAL R$ 49,99";
        String valorDasParcelasEsperado = "ou em 12 x de R$ 5,02";

        //pesquisar curso
        driver.findElement(By.id("searchtext")).sendKeys(Keys.chord(nomeCursoEsperado + Keys.ENTER));
        Thread.sleep(1000);
        //driver.findElement(By.cssSelector("#all_courses_search a:nth-child(2)")).click();
        driver.findElement(By.cssSelector("span.comprar")).click();
        Thread.sleep(3000);

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