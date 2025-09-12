package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        loginPage = new LoginPage(driver);
    }

    @Test
    public void testTokopediaLoginError() {
        loginPage.open();

        // Verifikasi title login
        String title = loginPage.getTitleText();
        Assert.assertTrue(title.contains("Masuk"), "Login title tidak sesuai");

        // Login dengan username tidak valid
        loginPage.loginWithUsername("Adryn.ivanna");

        // Verifikasi pesan error
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertTrue(errorMsg.length() > 0, "Pesan error tidak muncul");

        System.out.println("Title: " + title);
        System.out.println("Error: " + errorMsg);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
