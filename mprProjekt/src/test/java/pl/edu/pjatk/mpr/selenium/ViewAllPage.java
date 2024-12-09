package pl.edu.pjatk.mpr.selenium;

import org.hibernate.annotations.processing.Find;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewAllPage {
    private WebDriver webDriver;

    @FindBy(id = "id")
    private WebElement idInput;
    @FindBy(name= "name")
    private WebElement nameInput;
    @FindBy(color = "color")
    private WebElement colorInput;

    public ViewAllPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

}
