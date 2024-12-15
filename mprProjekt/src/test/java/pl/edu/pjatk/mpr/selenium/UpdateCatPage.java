package pl.edu.pjatk.mpr.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpdateCatPage {
    private WebDriver webDriver;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "color")
    private WebElement colorInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public UpdateCatPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public UpdateCatPage open(long id) {
        this.webDriver.get("http://localhost:8080/updateForm/" + id);
        return this;
    }

    public UpdateCatPage fillNameInput(String text) {
        this.nameInput.clear();
        this.nameInput.sendKeys(text);
        return this;
    }

    public UpdateCatPage fillColorInput(String text) {
        this.colorInput.clear();
        this.colorInput.sendKeys(text);
        return this;
    }

    public ViewAllPage submitForm() {
        this.submitButton.click();
        return new ViewAllPage(webDriver);
    }
}
