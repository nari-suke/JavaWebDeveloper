package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotePage {

    @FindBy(xpath = "//*[@id='logoutDiv']//button")
    private WebElement logoutBtn;

    @FindBy(id = "nav-notes-tab")
    private WebElement noteTab;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(xpath = "//*[@id='noteTitleText']")
    private WebElement noteTitleText;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(xpath = "//*[@id='noteDescriptionText']")
    private WebElement noteDescriptionText;

    @FindBy(id = "add-note-btn")
    private WebElement addNoteBtn;

    @FindBy(id = "note-savechanges-btn")
    private WebElement submitBtn;

    @FindBy(id = "note-editBtn")
    private WebElement editNoteBtn;

    @FindBy(id = "note-deleteBtn")
    private WebElement deleteNoteBtn;

    private final WebDriver driver;

    public NotePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickNoteTab() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", this.noteTab);
    }

    public void clickAddNoteBtn() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", this.addNoteBtn);
    }

    public void clickEditBtn() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", this.editNoteBtn);
    }

    public void clickDeleteBtn() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", this.deleteNoteBtn);
    }

    public void fillNoteData(String title, String description) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + title + "';", this.noteTitle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + description + "';", this.noteDescription);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);
    }

    public String getNoteTitleText() {
        return noteTitleText.getAttribute("innerHTML");
    }

    public String getNoteDescriptionText() {
        return noteDescriptionText.getAttribute("innerHTML");
    }

    public void logout() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", this.logoutBtn);
    }
}
