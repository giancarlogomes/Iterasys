package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Evidences {

    public void screenshot(String nomePrint, WebDriver driver, String pastaPrints, String id) throws IOException {
        File foto = ((TakesScreenshot) driver).getScreenshotAs((OutputType.FILE));
        FileUtils.copyFile(foto, new File("evidencias/petz/" + pastaPrints + id + "/" + nomePrint + ".png"));
    }

}
