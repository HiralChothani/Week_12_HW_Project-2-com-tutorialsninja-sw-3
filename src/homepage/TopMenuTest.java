package homepage;

import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;


public class TopMenuTest extends Utility {
    @Test
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        //  Mouse hover on “Desktops” Tab and click
        mouseHoverOnTheElement(By.linkText("Desktops"));

        // call selectMenu method and pass the menu = “Show All Desktops”
        selectMenu("Show AllDesktops");

        //  Verify the text ‘Desktops'
        compareText("Text not displayed", "Desktops", By.linkText("Desktops"));
    }

    @Test
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {
        // Mouse hover on “Laptops & Notebooks” Tab and click
        mouseHoverOnTheElement(By.linkText("Laptops & Notebooks"));
        selectMenu("Show AllLaptops & Notebooks");

        //  Verify the text ‘Laptops & Notebooks’
        compareText("Text not displayed", "Laptops & Notebooks", By.linkText("Laptops & Notebooks"));
    }

    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully(){
        // Mouse hover on “Components” Tab and click
        mouseHoverOnTheElement(By.linkText("Components"));

        // call selectMenu method and pass the menu = “Show All Components”
        selectMenu("Show AllComponents");

        // Verify the text ‘Components’
        compareText("Text not displayed", "Components", By.linkText("Components"));
    }
}
