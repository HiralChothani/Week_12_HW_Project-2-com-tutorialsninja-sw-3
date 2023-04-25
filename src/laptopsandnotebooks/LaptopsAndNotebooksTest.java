package laptopsandnotebooks;

import com.google.common.collect.Ordering;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LaptopsAndNotebooksTest extends Utility {
    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() {

        // Mouse hover on Laptops & Notebooks Tab.and click
        mouseHoverOnTheElement(By.xpath("//a[normalize-space()='Laptops & Notebooks']"));

        // Click on “Show All Laptops & Notebooks”
        clickOnElement(By.xpath("//a[normalize-space()='Show AllLaptops & Notebooks']"));

        // Select Sort By "Price (High > Low)"
        dropDownOption(By.xpath("//select[@id='input-sort']"), "Price (High > Low)");

        // Verify the Product price will arrange in High to Low order.
        List<WebElement> expectedProductPriceOrder = driver.findElements(By.xpath("//div[@class='row']//p[@class='price']"));
        ArrayList<String> expectedProductPriceOrderSequence = new ArrayList<String>();
        for (WebElement element : expectedProductPriceOrder) {
            expectedProductPriceOrderSequence.add(element.getText());
            System.out.println(expectedProductPriceOrderSequence);
        }
        boolean isSorted = Ordering.natural().isOrdered(expectedProductPriceOrderSequence);
        System.out.println(isSorted);

        List<WebElement> actualProductPriceOrder = driver.findElements(By.xpath("//div[@class='row']//p[@class='price']"));
        ArrayList<String> actualProductPriceOrderSequence = new ArrayList<String>();
        for (WebElement element : actualProductPriceOrder) {
            actualProductPriceOrderSequence.add(element.getText());
            System.out.println(actualProductPriceOrderSequence);
        }
        compareElements("Product not arranged in descending order", expectedProductPriceOrderSequence, actualProductPriceOrderSequence);
    }

    @Test
    public void verifyThatUserPlaceOrderSuccessfully() throws InterruptedException {
        Thread.sleep(2000);
        // Mouse hover on Laptops & Notebooks Tab.and click
        mouseHoverOnTheElement(By.xpath("//a[normalize-space()='Laptops & Notebooks']"));

        // Click on “Show All Laptops & Notebooks”
        clickOnElement(By.xpath("//a[normalize-space()='Show AllLaptops & Notebooks']"));

        // Select Sort By "Price (High > Low)"
        dropDownOption(By.xpath("//select[@id='input-sort']"), "Price (High > Low)");

        // Verify the text “MacBook”
        compareText("Text not displayed", "MacBook", By.linkText("MacBook"));

        // Click on ‘Add To Cart’ button
        Thread.sleep(2000);
        clickOnElement(By.xpath("(//span[contains(text(),'Add to Cart')])[4]"));

        //Verify the message “Success: You have added MacBook to your shopping cart!”
        compareText("Message not displayed", "Success: You have added MacBook to your shopping cart!\n" + "×", By.xpath("//div[@class='alert alert-success alert-dismissible']"));

        //Click on link “shopping cart” display into success message
        Thread.sleep(1000);
        clickOnElement(By.xpath("(//a[normalize-space()='shopping cart'])[1]"));

        //Verify the text "Shopping Cart"
        compareText("Text not displayed", "Shopping Cart", By.xpath("(//a[contains(text(),'Shopping Cart')])[1]"));

        //Verify the Product name "MacBook"
        compareText("Product name does not match", "MacBook", By.xpath("(//a[contains(text(),'MacBook')])[2]"));

        // Change Quantity "2"
        clickOnElement(By.xpath("//tbody/tr[1]/td[4]/div[1]/input[1]"));
        clearText(By.xpath("//tbody/tr[1]/td[4]/div[1]/input[1]"));
        sendTextToTheElement(By.xpath("//tbody/tr[1]/td[4]/div[1]/input[1]"), "2");

        // Click on “Update” Tab
        clickOnElement(By.xpath("(//i[@class='fa fa-refresh'])[1]"));

        // Verify the message “Success: You have modified your shopping cart!”
        Thread.sleep(1000);
        compareText("Message not displayed", "Success: You have modified your shopping cart!\n"+"×", By.xpath("(//div[@class='alert alert-success alert-dismissible'])[1]"));

        // Verify the Total £737.45
        compareText("Total matched", "$1,204.00", By.xpath("//tbody//tr//td[6]"));

        // Click on “Checkout” button
        clickOnElement(By.xpath("//a[@class='btn btn-primary']"));

        //  Verify the text “Checkout”
        compareText("Text not displayed", "Checkout", By.xpath("//h1[normalize-space()='Checkout']"));

        // 2.17 Verify the Text “New Customer
        compareText("Text not displayed", "New Customer", By.xpath("//h2[normalize-space()='New Customer']"));

        // Click on “Guest Checkout” radio button
        clickOnElement(By.xpath("(//input[@value='guest'])[1]"));

        //2.19 Click on “Continue” tab
        clickOnElement(By.id("button-account"));

        //  Fill the mandatory fields
        //firstName
        sendTextToTheElement(By.id("input-payment-firstname"),"Jenny");
        //lastname
        sendTextToTheElement(By.id("input-payment-lastname"), "Karen");
        //Email
        // Generate random Email
        final String randomEmail = randomEmail();
       sendTextToTheElement(By.id("input-payment-email"), randomEmail);
       //enter telephone
        sendTextToTheElement(By.id("input-payment-telephone"),"07850046387");
        //enter address
        sendTextToTheElement(By.id("input-payment-address-1"), "1xcvt");
        //enter city
        sendTextToTheElement(By.id("input-payment-city"), "Buckledom");
        //enter post code
        sendTextToTheElement(By.id("input-payment-postcode"), "XT1 8GH");
        //select country
        dropDownOption(By.id("input-payment-country"), "United Kingdom");
        // enter region
        dropDownOption(By.id("input-payment-zone"),"Isle of Wight");

        // Click on “Continue” Button
        clickOnElement(By.id("button-guest"));

        // Add Comments About your order into text area
        sendTextToTheElement(By.xpath("//textarea[@name='comment']"), "Please dispatch today, My order");

        // Check the Terms & Conditions check box
        Thread.sleep(1000);
        clickOnElement(By.name("agree"));

        // Click on “Continue” button
        clickOnElement(By.id("button-payment-method"));

        // Verify the message “Warning: Payment method required!”
        compareText("Message not displayed", "Warning: Payment method required!\n"+"×", By.xpath("//div[@class='alert alert-danger alert-dismissible']"));

    }

    private static String randomEmail() {
        return "Random-" + UUID.randomUUID().toString() + "@example.com";
    }

}

