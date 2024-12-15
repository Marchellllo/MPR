package pl.edu.pjatk.mpr.selenium;

import org.hibernate.annotations.processing.Find;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ViewAllPage {
    private WebDriver webDriver;

    @FindBy(id = "id")
    private WebElement idInput;
    @FindBy(name= "name")
    private WebElement nameInput;
    @FindBy(name = "color")
    private WebElement colorInput;

    public ViewAllPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isCatPresent(String name, String color) {
        // Wyszukujemy wszystkie elementy które mogą zawierać dane o kotach
        List<WebElement> catElements = webDriver.findElements(By.className("cat-info"));
        for (WebElement cat : catElements) {
            if (cat.getText().contains(name) && cat.getText().contains(color)) {
                return true;
            }
        }
        return false;
    }

    public ViewAllPage deleteCatById(long catId) {
        WebElement deleteButton = webDriver.findElement(By.id("delete-" + catId));
        deleteButton.click();
        return new ViewAllPage(webDriver);
    }

}
