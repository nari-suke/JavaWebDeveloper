package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.NotePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthTests {

	@LocalServerPort
	private int port; // RANDOM_PORT, port of server

	// fields:
	private static WebDriver driver;

	private SignupPage signupPage;
	private LoginPage loginPage;
	private NotePage homePage;

	String baseURL;

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
	}

	@Test
	public void testLoginLogout() {

		driver.get(baseURL + "/signup");

		signupPage = new SignupPage(driver);
		signupPage.signup("Foo", "Bar", "FooBar", "p@ssword");

		assertEquals("Sign Up", driver.getTitle());

		driver.get(baseURL + "/login");

		loginPage = new LoginPage(driver);
		loginPage.login("FooBar", "p@ssword");

		assertEquals("Home", driver.getTitle());

		homePage = new NotePage(driver);

		homePage.logout();

		assertTrue(loginPage.isLoggedOut());

		driver.get(baseURL + "/home");
		assertEquals("Login", driver.getTitle());
	}

	@Test
	public void testUnauthorizeUser() {
		driver.get(baseURL + "/signup");
		signupPage = new SignupPage(driver);
		signupPage.signup("Phuong", "Tran", "ploratran", "p@ssword");

		assertEquals("Sign Up", driver.getTitle());

		driver.get(baseURL + "/login");

		loginPage = new LoginPage(driver);
		loginPage.login("plora", "p@ssword");

		assertEquals("Login", driver.getTitle());

		assertTrue(loginPage.isInvalid());
	}
}
