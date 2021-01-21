package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement firstname;

    @FindBy(id = "inputLastName")
    private WebElement lastname;

    @FindBy(id = "inputUsername")
    private WebElement username;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(tagName = "button")
    private WebElement signupBtn;

    @FindBy(xpath = "//label//a[@href='/login']")
    private WebElement backToLoginBtn;

    private final WebDriver driver;

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void signup(String fname, String lname, String username, String password) {

        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + fname + "';", this.firstname);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + lname + "';", this.lastname);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + username + "';", this.username);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + password + "';", this.password);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", this.signupBtn);
    }
}
