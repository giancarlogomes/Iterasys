//Pacote de classes
package todo;

//Bibliotecas

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

//Classe
public class ListasPuro {
    //Atributos
    String url;
    WebDriver driver;

    @Before
    public void inicializar() {
        url = "https://todo.microsoft.com";

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
    }

    private WebDriver realizarLogin(WebDriver driver) throws InterruptedException {
        driver.get(url);

        driver.findElement(By.id("mectrl_headerPicture")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("i0116")).sendKeys(Keys.chord("TROCAR EMAIL"));
        Thread.sleep(3000);
        driver.findElement(By.id("idSIButton9")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("i0118")).sendKeys(Keys.chord("TROCAR SENHA"));
        Thread.sleep(3000);
        driver.findElement(By.id("idSIButton9")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("idSIButton9")).click();
        Thread.sleep(10000);

        return driver;
    }

    //Métodos
    @Test
    public void criarListaComTresItens() throws InterruptedException {

        //login
        driver = realizarLogin(driver);

        //criar lista
        driver.findElement(By.id("baseAddInput-addList")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("baseAddInput-addList")).clear();
        Thread.sleep(3000);
        driver.findElement(By.id("baseAddInput-addList")).sendKeys(Keys.chord("Musicas"));
        Thread.sleep(3000);
        driver.findElement(By.id("baseAddInput-addList")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        // Adiciona 3 músicas a lista
        driver.findElement(By.id("baseAddInput-addTask")).sendKeys("O Quereres" + Keys.ENTER);
        Thread.sleep(1000);
        driver.findElement(By.id("baseAddInput-addTask")).sendKeys("Terra" + Keys.ENTER);
        Thread.sleep(1000);
        driver.findElement(By.id("baseAddInput-addTask")).sendKeys("Me Gusta" + Keys.ENTER);
        Thread.sleep(1000);
    }

    @Test
    public void alterarNomeLista() throws InterruptedException {

        //login
        driver = realizarLogin(driver);

        //alterar nome da lista Musicas
        driver.findElement(By.cssSelector(".sortable-lists span:nth-child(2) span")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("i.ms-Icon--More")).click();
        Thread.sleep(1000);
        driver.findElement((By.cssSelector("i.ms-Icon--Rename"))).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#centerColumn div.tasksToolbar-titleItem input")).sendKeys(Keys.chord("Minhas musicas" + Keys.ENTER));
        Thread.sleep(1000);
    }

    @Test
    public void excluirLista() throws InterruptedException {

        //login
        driver = realizarLogin(driver);

        //excluir lista
        driver.findElement(By.cssSelector(".sortable-lists span:nth-child(2) span")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("i.ms-Icon--More")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("div.ms-FocusZone ul li:nth-child(5) button")).click();
        Thread.sleep(1000);
        driver.findElement((By.cssSelector("div.modal-footer div button:nth-child(2)"))).click();
        Thread.sleep(1000);
    }

    @After
    public void finalizar() {
        driver.quit();
    }
}