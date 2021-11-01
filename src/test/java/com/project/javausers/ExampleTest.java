package com.project.javausers;


import com.google.common.base.Functions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExampleTest {

    private final String[] urls = new String[]{"signup", "signup","signup","signup", "login", "chat", "signup", "signup"};
    private static int testIndex = 0;

    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private SignupPage signupPage;
    private LoginPage loginPage;
    private ChatPage chatPage;

    @BeforeAll
    public static void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void end(){
        driver.close();
        System.out.println("We Finished Our Amazing Professional Selenuim Test with real objects and classes");
        System.out.println("I love my tests");
    }

    @BeforeEach()
    public void beforeTest(){
        driver.get("http://localhost:" + port + "/" + Arrays.stream(urls).toArray()[testIndex]);
        testIndex++;
    }

    /* Signup Tests */

    // test add valid user
    @Test
    public void addNewUser() throws InterruptedException {
        if (!driver.getCurrentUrl().contains("signup")){
            driver.get("http://localhost:" + port + "/signup");
        }

        int randomNum = (int)(Math.random() * 990000);
        String userName = "user" + randomNum + "_" + (int)(Math.random() * 990000);
        SignupPage signupPage = new SignupPage(driver);

        signupPage.createUser("Python", "king", userName, "myStrongPass");
        assertEquals("You successfully signed up! Please continue to the",signupPage.getSuccessMessage());
    }

    // test add invalid user (duplicated username)
    @Test
    public void addInvalidUser() throws InterruptedException {
        if (!driver.getCurrentUrl().contains("signup")){
            driver.get("http://localhost:" + port + "/signup");
        }

        int randomNum = (int)(Math.random() * 990000);
        String userName = "user" + randomNum + "_" + (int)(Math.random() * 990000);

        SignupPage signupPage = new SignupPage(driver);
        // test add invalid user (first add valid user with username and then check if it accepts same username);

        signupPage.createUser("Python" + randomNum, "king" + randomNum, userName, "apassword");
        assertEquals("You successfully signed up! Please continue to the",signupPage.getSuccessMessage());
        Thread.sleep(2000);
        driver.get("http://localhost:" + port + "/signup");
        signupPage.createUser("Python" + randomNum, "king" + randomNum, userName, "strongpasss");
        assertEquals("The username already exists.",signupPage.getErrorMessage());
    }

    // test add new user and make sure valid message and make sure it returns redirect login link in Messages
    // and click on it and make sure it redirects to login page and ready for any login
    @Test
    public void addUserAndGoLoginPage() throws InterruptedException {
        if (!driver.getCurrentUrl().contains("signup")){
            driver.get("http://localhost:" + port + "/signup");
        }

        int randomNum = (int)(Math.random() * 990000);
        String userName = "user" + randomNum + "_" + (int)(Math.random() * 990000);
        SignupPage signupPage = new SignupPage(driver);
        String previousUrl  = driver.getCurrentUrl();
        signupPage.createUser("nice"+randomNum, "user"+randomNum, userName, "mypassword");
        signupPage.goToLoginAfterSuccess();
        Thread.sleep(500);
        assertEquals("http://localhost:" + port + "/login",driver.getCurrentUrl());
        assertNotEquals(previousUrl, driver.getCurrentUrl());
    }

    /* Login Tests */

    // test that will create user , click login then log with that user tricky test
    @Test
    public void SignupThenLogin() throws InterruptedException {
        if (!driver.getCurrentUrl().contains("signup")){
            driver.get("http://localhost:" + port + "/signup");
        }

        int randomNum = (int)(Math.random() * 990000);
        String userName = "user" + randomNum + "_" + (int)(Math.random() * 990000);
        SignupPage signupPage = new SignupPage(driver);
        signupPage.createUser("nice"+randomNum, "user"+randomNum, userName, "mypassword");
        Thread.sleep(500);
        signupPage.goToLoginAfterSuccess();
        String urlBeforeLogin = driver.getCurrentUrl();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn(userName, "mypassword");
        Thread.sleep(500);
        assertNotEquals(urlBeforeLogin,driver.getCurrentUrl());
    }

    // test invalid login user
    @Test
    public void testInvalidLogin() throws InterruptedException {
        if (!driver.getCurrentUrl().contains("login")){
            driver.get("http://localhost:" + port + "/login");
        }

        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn("ehdhb2a", "ehdhb2a");
        Thread.sleep(500);
        assertEquals("Invalid username or password",loginPage.getErrorMsg());

    }

    /* Chat Tests */

    // send normal message and make sure total messages increased

    @Test
    public void testSendMessageAndMakeSureItSent() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        if (!driver.getCurrentUrl().contains("signup")){
            driver.get("http://localhost:" + port + "/signup");
        }

        int randomNum = (int)(Math.random() * 990000);
        String userName = "user" + randomNum + "_" + (int)(Math.random() * 990000);
        SignupPage signupPage = new SignupPage(driver);
        signupPage.createUser("Python" + randomNum, "king" + randomNum, userName, "testPass@@");
        Thread.sleep(500);
        // move to login page
        signupPage.goToLoginAfterSuccess();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn(userName,"testPass@@");
        ChatPage chatPage = new ChatPage(driver);
        int totalMessagesBefore = chatPage.getMessagesSize();
        int messageIndex = chatPage.sendMessage("Hello World From Java Selenium");
        WebElement tested = wait.until(webElm-> webElm.findElement(By.cssSelector("p.msg")));
        int totalMessagesAfter = chatPage.getMessagesSize();
        //boolean validateResult = chatPage.validateMessage(messageIndex,"Hello World From Java Selenium");
        assertTrue(totalMessagesAfter > totalMessagesBefore);
        assertEquals(userName,chatPage.getLoggedUser());
    }

    // send normal message and make sure it printed out as same as it is
    // log first then send message
    @Test
    public void TestSendMessageAndValidateIt() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        if (!driver.getCurrentUrl().contains("signup")){
            driver.get("http://localhost:" + port + "/signup");
        }
        int randomNum = (int)(Math.random() * 990000);
        String userName = "user" + randomNum + "_" + (int)(Math.random() * 990000);
        SignupPage signupPage = new SignupPage(driver);
        signupPage.createUser("Python" + randomNum, "king" + randomNum, userName, "testPass@@");
        Thread.sleep(500);
        // move to login page
        signupPage.goToLoginAfterSuccess();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn(userName,"testPass@@");
        ChatPage chatPage = new ChatPage(driver);
        int messageIndex = chatPage.sendMessage("hello world from java selenium");
        // nice this how to use wait within pages
        WebElement tested = wait.until(webElm-> webElm.findElement(By.cssSelector("p.msg")));
        boolean validateResult = chatPage.validateMessage("hello world from java selenium");
        System.out.println(chatPage.getMessagesStrings());
        assertTrue(validateResult);
    }

    @Test
    public void testAddMessageWithType() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        if (!driver.getCurrentUrl().contains("signup")){
            driver.get("http://localhost:" + port + "/signup");
        }

        int randomNum = (int)(Math.random() * 990000);
        String userName = "user" + randomNum + "_" + (int)(Math.random() * 990000);
        SignupPage signupPage = new SignupPage(driver);
        signupPage.createUser("Python" + randomNum, "king" + randomNum, userName, "testPass@@");
        Thread.sleep(500);
        // move to login page
        signupPage.goToLoginAfterSuccess();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn(userName,"testPass@@");
        ChatPage chatPage = new ChatPage(driver);

        String[] types = new String[]{"Whisper", "Shout", "Normal"};
        for (String type : types ){
            String selectedMsg = "Hello World";
            chatPage.addMessageWithType(selectedMsg, type);
            // nice this how to use wait within pages
            WebElement tested = wait.until(webElm-> webElm.findElement(By.cssSelector("p.msg")));

            if (type.equals("Whisper")){
                boolean validateResult = chatPage.validateMessage(selectedMsg.toLowerCase());
                assertTrue(validateResult);
            } else if (type.equals("Shout")) {
                boolean validateResult = chatPage.validateMessage(selectedMsg.toUpperCase());
                assertTrue(validateResult);
            } else {
                boolean validateResult = chatPage.validateMessage(selectedMsg);
                assertTrue(validateResult);
            }
        }
    }




}

