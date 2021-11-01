package com.project.javausers;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ChatPage {


    public ChatPage (WebDriver driver){
        PageFactory.initElements(driver, this);

    }
    @FindBy(id = "newMessageText")
    private WebElement messageInput;

    @FindBy(id = "select_input")
    private WebElement selectInput;

    @FindBy(id = "submit_btn")
    private WebElement submitInput;

    @FindBy(id = "logged_user")
    private WebElement loggedUsername;

    @FindBy(className = "msg")
    private List<WebElement> messages;




    public int sendMessage(String msg){
        messageInput.clear();
        messageInput.sendKeys(msg);
        submitInput.submit();

        return messages.toArray().length-1;
    }

    public boolean validateMessage(String message){
        boolean found = false;
        List<String> currentMessages = getMessagesStrings();
        if (currentMessages.size() > 0 && currentMessages.contains(message)){
            return true;
        } else {
            return false;
        }

    }

    public int getMessagesSize(){
        try{
            return messages.size();
        }catch(NoSuchElementException er){
            return 0;
        }
    }


    public void addMessageWithType(String message, String type){
        //Select
        messageInput.clear();
        messageInput.sendKeys(message);
        // define the select
        Select dropDown = new Select(selectInput);
        dropDown.selectByVisibleText(type);
        submitInput.submit();
    }

    public List<String> getMessagesStrings(){
        List<String> result = new ArrayList<String>();
        try{
            for (WebElement pMsg : messages) {
                result.add(pMsg.getText());
            }
        }catch(NoSuchElementException er){
            System.out.println("No Elements Found");
        }
        return result;
    }

    public boolean validatedUser(String username){
        try{
            return loggedUsername.getText().equals(username);
        }catch(NoSuchElementException er){
            return false;
        }
    }

    public String getLoggedUser(){
        try{
            return loggedUsername.getText();
        }catch(NoSuchElementException er){
            return null;
        }
    }
}
