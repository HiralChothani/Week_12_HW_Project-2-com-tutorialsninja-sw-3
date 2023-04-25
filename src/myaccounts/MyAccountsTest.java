package myaccounts;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;
import java.util.UUID;

public class MyAccountsTest extends Utility {

    public void selectMyAccountOptions(String option) throws InterruptedException {

        Thread.sleep(2000);
        clickOnElement(By.linkText("My Account"));
        List<WebElement> optionsList = driver.findElements(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//li/a"));
        for (WebElement element : optionsList) {
            if (element.getText().equalsIgnoreCase(option)) {
                clickOnWebElement(element);
                break;
            }
        }
    }

    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() throws InterruptedException {
        // Click on My Account Link
        // Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        selectMyAccountOptions("Register");

        //  Verify the text “Register Account”.
        compareText("Text not matched", "Register Account",By.xpath("//h1[normalize-space()='Register Account']"));
    }

    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully() throws InterruptedException {
        // Click on My Account Link.
        // Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        selectMyAccountOptions("Login");

        // Verify the text “Returning Customer”.
        compareText("Text not matched", "Login",By.linkText("Login"));
    }

    @Test
    public void verifyThatUserRegisterAccountSuccessfully() throws InterruptedException {
        // Click on My Account Link
        // Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        selectMyAccountOptions("Register");

        //Enter First Name
        sendTextToTheElement(By.id("input-firstname"),"Julie");

        // Enter Last Name
        sendTextToTheElement(By.id("input-lastname"), "Samsonic");

        // Enter Email
        // Generate random Email
        final String randomEmail = randomEmail();
        sendTextToTheElement(By.id("input-email"), randomEmail);

        // Enter Telephone
        sendTextToTheElement(By.id("input-telephone"), "6875430598");

        // Enter Password
        sendTextToTheElement(By.id("input-password"), "Samgen123*");

        Thread.sleep(1000);
        // Enter Password Confirm
        sendTextToTheElement(By.xpath("//input[@id='input-confirm']"), "Samgen123*");

        // Select Subscribe Yes radio button
        clickOnElement(By.xpath("//label[normalize-space()='Yes']"));

        // Click on Privacy Policy check box
        clickOnElement(By.xpath("//input[@name='agree']"));

        // Click on Continue button
        clickOnElement(By.xpath("//input[@value='Continue']"));

        // Verify the message “Your Account Has Been Created!”
        compareText("Message not displayed", "Your Account Has Been Created!", By.xpath("//h1[normalize-space()='Your Account Has Been Created!']"));

        // Click on Continue button
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));

        // Click on My Account Link
        // Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        selectMyAccountOptions("Register");

        //3.15 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        Thread.sleep(1000);
        //selectMyAccountOptions("Logout");
        mouseHoverOnTheElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//li/a"));
        clickOnElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']"));

        //3.16 Verify the text “Account Logout”
        compareText("Text not matched","Account Logout", By.cssSelector("div[id='content'] h1"));

        //3.17 Click on Continue button
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));
    }

    private static String randomEmail() {
        return "Random-" + UUID.randomUUID().toString() + "@example.com";
    }

    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() throws InterruptedException {
        // Click on My Account Link.
        // Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        selectMyAccountOptions("Login");

        //4.3 Enter Email address
        sendTextToTheElement(By.id("input-email"), "Random-e283eeca-e472-4601-911a-6fc5fbbc9b4f@example.com");


        //4.4 Enter Last Name
        //4.5 Enter Password
        sendTextToTheElement(By.id("input-password"), "Samgen123*");


        //4.6 Click on Login button
        clickOnElement(By.xpath("//body/div[@id='account-login']/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/input[1]"));

        //4.7 Verify text “My Account”
        compareText("Text does not match", "My Account", By.xpath("//h2[contains(text(),'My Account')]"));

        //4.8 Click on My Account Link.
        //4.9 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        selectMyAccountOptions("Logout");

        //4.10 Verify the text “Account Logout”
        compareText("Text does not match", "Account Logout", By.xpath("//h1[contains(text(),'Account Logout')]"));

        //4.11 Click on Continue button
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
    }
}
