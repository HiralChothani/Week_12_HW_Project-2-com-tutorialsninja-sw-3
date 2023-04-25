package desktops;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesktopsTest extends Utility {
    @Test
    public void verifyProductArrangeInAlphabeticalOrder() {

        //  Mouse hover on “Desktops” Tab and click
        mouseHoverOnTheElement(By.linkText("Desktops"));

        // call selectMenu method and pass the menu = “Show All Desktops”
        selectMenu("Show AllDesktops");

        List<WebElement> expectedProductName = driver.findElements(By.xpath("//div[@class='row']//h4"));
        ArrayList<String> expectedProductSequence = new ArrayList<String>();
        for (WebElement element : expectedProductName) {
            expectedProductSequence.add(element.getText());
        }
        Collections.reverse(expectedProductSequence);

        //  Select Sort By position "Name: Z to A"
        dropDownOption(By.id("input-sort"), "Name (Z - A)");

        //  Verify the Product will arrange in Descending order.
        List<WebElement> actualProductName = driver.findElements(By.xpath("//div[@class='row']//h4"));
        ArrayList<String> actualProductSequence = new ArrayList<String>();
        for (WebElement element : actualProductName) {
            actualProductSequence.add(element.getText());
        }
        compareElements("Product not arranged in descending order", expectedProductSequence, actualProductSequence);
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully(){
        //  Mouse hover on “Desktops” Tab and click
        mouseHoverOnTheElement(By.linkText("Desktops"));

        // call selectMenu method and pass the menu = “Show All Desktops”
        selectMenu("Show AllDesktops");

        //  Select Sort By position "Name: A to Z"
        dropDownOption(By.id("input-sort"), "Name (A - Z)");

        // Select product “HP LP3065”
        selectMenu("HP LP3065");

        //Verify the Text "HP LP3065"
        compareText("Text not displayed", "HP LP3065", By.linkText("HP LP3065"));

        // Select Delivery Date "2022-11-30"
        clickOnElement(By.xpath("//input[@id='input-option225']"));
        clearText(By.xpath("//input[@id='input-option225']"));
        sendTextToTheElement(By.xpath("//input[@id='input-option225']"), "2022-11-30");

        // Enter Qty "1” using Select class.
        clickOnElement(By.xpath("//input[@id='input-quantity']"));
        clearText(By.xpath("//input[@id='input-quantity']"));
        sendTextToTheElement(By.xpath("//input[@id='input-quantity']"), "1");

        // Click on “Add to Cart” button
        clickOnElement(By.xpath("//button[@id='button-cart']"));

        // Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
        compareText("Message not displayed", "Success: You have added HP LP3065 to your shopping cart!\n" + "×", By.xpath("//div[@class='alert alert-success alert-dismissible']"));

        // Click on link “shopping cart” display into success message
        clickOnElement(By.linkText("shopping cart"));

//        Verify the text "Shopping Cart"
        compareText("Text not displayed", "Shopping Cart", By.xpath("//a[contains(text(),'Shopping Cart')]"));

//        Verify the Product name "HP LP3065"
        compareText("Product name not matched", "HP LP3065", By.linkText("HP LP3065"));

//        Verify the Delivery Date "2022-11-30"
        compareText("Date not matched","Delivery Date:2022-11-30",By.xpath("//small[contains(text(),'Delivery Date:2022-11-30')]"));

//        Verify the Model "Product21"
        compareText("Model does not match", "Product 21",By.xpath("//td[contains(text(),'Product 21')]"));

//        Verify the Total "$122.00"
        String expectedPriceTotal = "$122.00";
        WebElement actualPriceElement = driver.findElement(RelativeLocator.with(By.xpath("//td[contains(text(),'$122.00')]")).below(By.xpath("//td[contains(text(),'Total')]")));
        String actualPrice = actualPriceElement.getText();
        Assert.assertEquals("Price not matched", expectedPriceTotal, actualPrice);
        }
    }




