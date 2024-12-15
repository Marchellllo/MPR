package pl.edu.pjatk.mpr.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DeleteCatPage {
    private WebDriver webDriver;

    public DeleteCatPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }
    public DeleteCatPage open(long id){
        this.webDriver.get("http://localhost:8080/deleteForm" + id);
        return this;
    }



    public ViewAllPage deleteCat(){
        WebElement deleteButton = webDriver.findElement(By.id("delete"));
        deleteButton.click();
        return new ViewAllPage(webDriver);
    }

}
