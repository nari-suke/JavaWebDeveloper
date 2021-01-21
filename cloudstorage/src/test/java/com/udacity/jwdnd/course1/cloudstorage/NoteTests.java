package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.NotePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteTests {

    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    String baseURL;
    private NotePage notePage;
    private ResultPage resultPage;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @BeforeEach
    public void beforeEach() {
        baseURL = "http://localhost:" + port;

        driver.get(baseURL + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("Phuong", "Tran", "ploratran", "p@ssword");

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("ploratran", "p@ssword");

        notePage = new NotePage(driver);

        notePage.clickNoteTab();
    }


    @Test
    public void addNewNote() {
        notePage.clickAddNoteBtn();
        notePage.fillNoteData("Test Title", "Test Description");

        resultPage = new ResultPage(driver);
        resultPage.clickHereBtn();

        notePage.clickNoteTab();

        assertEquals("Test Title", notePage.getNoteTitleText());
        assertEquals("Test Description", notePage.getNoteDescriptionText());
    }


    @Test
    public void editNote() {

        notePage.clickEditBtn();
        notePage.fillNoteData("New Title", "New Description");

        resultPage = new ResultPage(driver);
        resultPage.clickHereBtn();

        notePage.clickNoteTab();

        assertEquals("New Title", notePage.getNoteTitleText());
        assertEquals("New Description", notePage.getNoteDescriptionText());
    }

    @Test
    public void deleteNote() {

        notePage.clickDeleteBtn();

        resultPage = new ResultPage(driver);
        resultPage.clickHereBtn();

        notePage.clickNoteTab();

        assertThrows(NoSuchElementException.class, () -> {
            notePage.getNoteTitleText();
        });
    }
}
