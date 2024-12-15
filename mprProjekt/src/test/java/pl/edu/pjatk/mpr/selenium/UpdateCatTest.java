package pl.edu.pjatk.mpr.selenium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateCatTest {
    private WebDriver webDriver;

    @BeforeEach
    public void setUp() {
        // Ustawienie ścieżki do chromedrivera
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        this.webDriver = new ChromeDriver();
    }

    @Test
    public void testUpdateCat() {
        // Zakładając, że dodajemy kota przed testem
        AddCatPage addCatPage = new AddCatPage(webDriver)
                .open()
                .fillNameInput("Tom")
                .fillColorInput("White");
        ViewAllPage viewAllPage = addCatPage.submitForm();

        // Pobieramy ID kota z widoku wszystkich kotów
        long catId = Long.parseLong(webDriver.findElement(By.id("catId")).getText());

        // Aktualizujemy kota
        UpdateCatPage updateCatPage = new UpdateCatPage(webDriver);
        updateCatPage.open(catId)
                .fillNameInput("Jerry")
                .fillColorInput("Black");
        viewAllPage = updateCatPage.submitForm();

        // Sprawdzamy, czy zaktualizowany kot jest widoczny na stronie
        boolean isUpdatedCatPresent = webDriver.getPageSource().contains("Jerry") &&
                webDriver.getPageSource().contains("Black");
        assertTrue(isUpdatedCatPresent, "Updated cat should be visible on the page");
    }
}
