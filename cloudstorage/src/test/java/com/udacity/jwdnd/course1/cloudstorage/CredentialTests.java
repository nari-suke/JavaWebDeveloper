package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.pages.CredentialPage;
//import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
//import com.udacity.jwdnd.course1.cloudstorage.pages.ResultPage;
//import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.EncryptionService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialTests {

    @LocalServerPort
    private Integer port;

    @Autowired
    private CredentialService credentialService;

    private static WebDriver driver;
    String baseURL;
    private CredentialPage credentialPage;
    private ResultPage resultPage;
    private EncryptionService encryptionService;

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

        SignupPage signupPage;
        signupPage = new SignupPage(driver);

        signupPage.signup("Foo", "Bar", "FooBar", "p@ssword");

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("FooBar", "p@ssword");

        encryptionService = new EncryptionService();

        credentialPage = new CredentialPage(driver);

        credentialPage.clickCredTab();
    }

    @Test
    public void addNewCredential() {

        credentialPage.clickAddCredBtn();

        credentialPage.fillCredentialData("foobar.com", "FooBar", "p@ssword");

        resultPage = new ResultPage(driver);
        resultPage.clickHereBtn();

        assertEquals("Home", driver.getTitle());

        credentialPage.clickCredTab();

        Credential credential = this.credentialService.getCredentialByCredentialId(1);

        assertEquals("foobar.com", credentialPage.getUrlText());
        assertEquals("FooBar", credentialPage.getUsernameText());
        assertEquals(this.encryptionService.encryptValue("p@ssword", credential.getKey()), credentialPage.getPasswordText());
    }

    @Test
    public void editCredential() {
        credentialPage.clickEditBtn();

        credentialPage.fillCredentialData("fizzbuzz.com", "FizzBuzz", "newP@ssword");

        resultPage = new ResultPage(driver);
        resultPage.clickHereBtn();

        assertEquals("Home", driver.getTitle());

        credentialPage.clickCredTab();

        Credential credential = this.credentialService.getCredentialByCredentialId(1);

        assertEquals("fizzbuzz.com", credentialPage.getUrlText());
        assertEquals("FizzBuzz", credentialPage.getUsernameText());
        assertEquals(this.encryptionService.encryptValue("newP@ssword", credential.getKey()), credentialPage.getPasswordText());
    }

    @Test
    public void deleteCredential() {

        credentialPage.clickDeleteBtn();
        resultPage = new ResultPage(driver);
        resultPage.clickHereBtn();
        credentialPage.clickCredTab();

        assertThrows(NoSuchElementException.class, () -> {
            credentialPage.getUsernameText();
        });
    }
}
