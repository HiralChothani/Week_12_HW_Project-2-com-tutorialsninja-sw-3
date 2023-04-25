package utilities;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;

public class Utility extends BaseTest {
    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }
    public void selectMenu(String menu) {
        driver.findElement(By.linkText(menu)).click();
    }

    public void clickOnElement(By by){
        /**
         * WebElement loginLink = driver.findElement(By.linkText("Log in"));
         *         loginLink.click();
         */
        driver.findElement(by).click();
    }

    public void clearText(By by){
        driver.findElement(by).clear();
    }
    public void sendTextToTheElement(By by, String text){
        driver.findElement(by).sendKeys(text);
    }


    public void mouseHoverOnTheElement(By by){
        WebElement element = driver.findElement(by);
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public String getTextFromTheElement(By by){
        return driver.findElement(by).getText();
    }
    public void compareText(String text, String expectedText, By by){
        Assert.assertEquals(text,expectedText,getTextFromTheElement(by) );
    }

    public void compareElements(String text, ArrayList expectedArray, ArrayList actualArray ){
        Assert.assertEquals(text,expectedArray,actualArray);
    }
    public void dropDownOption(By by, String preferance){
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(preferance);
    }

    public void clickOnWebElement(WebElement element) {
        element.click();
    }

}
