package com.project.javausers;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(id = "inputUsername")
    private WebElement username;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(id = "submit-button")
    private WebElement submitBtn;

    @FindBy(id = "error-msg")
    private WebElement message;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logIn(String un, String ps){
       username.clear();
       password.clear();
       username.sendKeys(un);
       password.sendKeys(ps);
       submitBtn.submit();
       System.out.println("Error Message: " + getErrorMsg());
    }

    public String getErrorMsg(){
        try{
            return message.getText();
        } catch(NoSuchElementException er) {
            return null;
        }
    }
}
