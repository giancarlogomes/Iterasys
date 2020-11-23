package site;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Boticario {

    WebDriver driver;
    String url = "https://www.boticario.com.br";

    @Before
    public void iniciar(){
        ChromeOptions co = new ChromeOptions(); // instancia do objeto de configuracoes do chromedriver
        co.addArguments("--disable-notifications");

        System.setProperty("webdriver.chrome.driver", "drivers/chrome/86/chromedriver.exe");
        driver = new ChromeDriver(co);
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void finalizar(){
        driver.quit();
    }

    @Test
    public void abrirSiteBoticario() {
        driver.get(url);
        //clica no entendi
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();

        //clicar no talvez depois
        //driver.findElement(By.id("onesignal-slidedown-cancel-button")).click();

    }

}
