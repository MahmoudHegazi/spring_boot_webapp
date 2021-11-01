package com.project.javausers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
  @FindBy(id = "inputFirstName")
  private WebElement firstName;

  @FindBy(id = "inputLastName")
  private WebElement lastName;

  @FindBy(id = "inputUsername")
  private WebElement userName;

  @FindBy(id = "inputPassword")
  private WebElement password;

  @FindBy(id = "submit-button")
  private WebElement submitBtn;

  @FindBy(id = "error_message")
  private WebElement errorMessage;

  @FindBy(id = "success")
  private WebElement successMessage;

  @FindBy(id = "back_login")
  private WebElement loginLink;

  @FindBy(id = "login-link")
  private WebElement successLoginLink;



  public SignupPage(WebDriver driver){
      PageFactory.initElements(driver, this);
  }

    public void createUser(String fn, String ln, String un, String ps) throws InterruptedException {
      firstName.clear();
      lastName.clear();
      userName.clear();
      password.clear();
      firstName.sendKeys(fn);
      lastName.sendKeys(ln);
      userName.sendKeys(un);
      password.sendKeys(ps);
      Thread.sleep(1000);
      submitBtn.submit();
      System.out.println("Created User with Name: " + fn);
      System.out.println("Success: " + getSuccessMessage());
      System.out.println("Errors: " + getErrorMessage());
  }

  public String getErrorMessage(){
      try{
          return errorMessage.getText();
      } catch(NoSuchElementException ee){
          return "";
      }
  }


  public String getSuccessMessage(){
      try{
          return successMessage.getText();
      } catch(NoSuchElementException ee){
          return "";
      }
  }

  public void goToLoginAfterSuccess(){
      try{
          successLoginLink.click();
      } catch(NoSuchElementException ee){
          System.out.println("Could not find the Success Login link");
      }
  }
}
