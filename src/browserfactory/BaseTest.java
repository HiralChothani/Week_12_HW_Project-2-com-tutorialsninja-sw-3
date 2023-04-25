package browserfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static WebDriver driver;
    public void openBrowser(String baseUrl){
        driver = new ChromeDriver();
        //Launch the browser
        driver.get(baseUrl);
        // Maximize the window
        driver.manage().window().maximize();
        // Implicit wait
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void closeBrowser(){
        driver.quit();
    }
}
