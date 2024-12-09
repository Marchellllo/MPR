package pl.edu.pjatk.mpr.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import javax.swing.text.View;

public class AddCatPage {
    private WebDriver webDriver;


    @FindBy(id = "name")
    private WebElement nameInput;
    @FindBy(id = "color")
    private WebElement colorInput;
    @FindBy(id = "submit")
    private WebElement submitInput;

    public AddCatPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //<- very wazne
    }



    public AddCatPage open() {
        this.webDriver.get("http://localhost:8080/addForm");

        return this;

    }
    public AddCatPage fillColorInput(String text) {
        this.colorInput.sendKeys(text);

        return this;

    }
    public AddCatPage fillNameInput(String text) {
        this.nameInput.sendKeys(text);

        return this;

    }
    public ViewAllPage submitForm() {
        this.submitInput.click();
        return new ViewAllPage();
    }


}
