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

public class Login {
    WebDriver driver;
    String url;
    String pastaEvidencias = "evidencias/login/" + new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime()) + "/";

    public void tiraPrint(String nomeEvidencia) throws IOException {
        File foto = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(foto, new File(pastaEvidencias + nomeEvidencia + ".png"));
    }
    @Before
    public void iniciar(){
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/87/chromedriver.exe");
        url = "https://www.iterasys.com.br";
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void finalizar(){
        driver.quit();
    }

    @Test
    public void realizarLogin() throws IOException {
        driver.get(url);

        String meusCursos = "Meus Cursos";

        tiraPrint("1 - Acessou paginal inicial");

        driver.findElement(By.cssSelector("div.col-md-12 #navbar li.login_header")).click();

        tiraPrint("2 - Acessou a tela de login");

        driver.findElement(By.cssSelector("div.col-md-12 div.form-group input#email")).sendKeys(Keys.chord("MUDAR EMAIL"));

        tiraPrint("3 - Digitou o e-mail");

        driver.findElement(By.cssSelector("div.col-md-12 div.form-group input#senha")).sendKeys(Keys.chord("MUDAR SENHA"));

        tiraPrint("4 - Digitou a senha");

        driver.findElement(By.id("btn_login")).click();

        tiraPrint("5 - Clicou em Entrar");

        assertTrue(driver.findElement(By.cssSelector("div.col-md-12 ol li.active")).getText().equals(meusCursos));

    }
}
