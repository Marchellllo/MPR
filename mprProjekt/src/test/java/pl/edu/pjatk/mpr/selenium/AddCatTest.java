package pl.edu.pjatk.mpr.selenium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class AddCatTest {
    private WebDriver webDriver;

    @BeforeEach
    public void setUp(){
        this.webDriver = new ChromeDriver();

    }
    @Test
    public void testAddCatForm(){
        AddCatPage page = new AddCatPage(webDriver)
                .open()
                .fillNameInput("Tom")
                .fillColorInput("White");
        ViewAllPage viewAllPage = page.submitForm();
    }
}
