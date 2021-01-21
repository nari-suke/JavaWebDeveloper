package com.udacity.jwdnd.course1.cloudstorage.pages;

import com.udacity.jwdnd.course1.cloudstorage.service.EncryptionService;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CredentialPage {
    @FindBy(xpath = "//*[@id='logoutDiv']//button")
    private WebElement logoutBtn;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credTab;

    @FindBy(id = "credential-url")
    private WebElement credentialUrl;

    @FindBy(xpath = "//*[@id='credUrlText']")
    private WebElement credentialUrlText;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(xpath = "//*[@id='credUsernameText']")
    private WebElement credentialUsernameText;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(xpath = "//*[@id='credPasswordText']")
    private WebElement credentialPasswordText;

    @FindBy(id = "add-cred-btn")
    private WebElement addCredBtn;

    @FindBy(id = "cred-savechanges-btn")
    private WebElement submitBtn;

    @FindBy(id = "cred-EditBtn")
    private WebElement editBtn;

    @FindBy(id = "cred-DeleteBtn")
    private WebElement deleteBtn;

    private final WebDriver driver;

    public CredentialPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCredTab() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", this.credTab);
    }

    public void clickAddCredBtn() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", this.addCredBtn);
    }

    public void clickEditBtn() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", this.editBtn);
    }

    public void clickDeleteBtn() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", this.deleteBtn);
    }

    public void fillCredentialData(String url, String username, String password) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + url + "';", this.credentialUrl);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + username + "';", this.credentialUsername);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + password + "';", this.credentialPassword);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", this.submitBtn);
    }

    public String getUrlText() {
        return credentialUrlText.getAttribute("innerHTML");
    }

    public String getUsernameText() {
        return credentialUsernameText.getAttribute("innerHTML");
    }

    public String getPasswordText() {
        return credentialPasswordText.getAttribute("innerHTML");
    }

    public String getUnencryptedPassword() {
        return this.credentialPassword.getAttribute("value");
    }

}
