package site;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class Americanas {

    WebDriver driver;
    String url;
    String pastaEvidencias = "evidencias/americanas/" + new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime()) + "/";

    public void tiraPrint(String nomeEvidencia) throws IOException {
        File foto = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(foto, new File(pastaEvidencias + nomeEvidencia + ".png"));
    }

    @Before
    public void iniciar() {
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/87/chromedriver.exe");
        url = "https://www.americanas.com.br/";
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void finalizar() {
        driver.quit();
    }

    @Test
    public void procurarIphone12() throws IOException {
        driver.get(url);

        String iphone = "iPhone 12 Pro 512GB Dourado";

        tiraPrint("1 - Carregou a pagina inicial");

        driver.findElement(By.id("h_search-input")).sendKeys(Keys.chord(iphone + Keys.ENTER));

        tiraPrint("2 - Lista de produtos relacionados a Iphone 12");

        driver.findElement(By.cssSelector("div.grid__StyledGrid-sc-1man2hx-0:nth-child(3) div.col__StyledCol-sc-1snw5v3-0:nth-child(1)")).click();

        tiraPrint("3 - Seleciona primeiro Iphone 12 da lista");

        String titulo = driver.findElement(By.cssSelector("div.src__Container-m79eh9-0:nth-child(1) div.src__Container-lknyo-3 div.src__Cell-lknyo-4:nth-child(2) span.src__Title-sc-14j0fsd-0")).getText();

        assertTrue(titulo.contains(iphone));
    }
}
