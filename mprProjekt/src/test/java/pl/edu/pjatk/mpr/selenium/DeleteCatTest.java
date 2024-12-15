package pl.edu.pjatk.mpr.selenium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeleteCatTest {
    private WebDriver webDriver;


    @BeforeEach
    public void setUp(){
        this.webDriver = new ChromeDriver();
    }

    @Test
    public void testCatDelete(){
    AddCatPage addCatPage = new AddCatPage(webDriver)
            .open()
            .fillNameInput("Tom")
            .fillColorInput("White");
        ViewAllPage viewAllPage = addCatPage.submitForm();

        long catId = Long.parseLong(webDriver.findElement(By.id("catId")).getText());

        DeleteCatPage deleteCatPage = new DeleteCatPage(webDriver);
        deleteCatPage.open(catId).deleteCat();

        boolean isCatPresent = webDriver.getPageSource().contains("Tom");
        assertFalse(isCatPresent, "Cat should not be seen after deletion");

    }
}
