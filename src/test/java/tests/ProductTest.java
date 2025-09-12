package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ProductPage;

import java.time.Duration;

public class ProductTest {
    WebDriver driver;
    ProductPage productPage;

    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        productPage = new ProductPage(driver);
    }

    @Test
    public void testSearchProduct() {
        productPage.openHomePage();
        productPage.searchProduct("laptop");

        Assert.assertTrue(productPage.hasResults(), "Pencarian tidak menghasilkan produk!");
        System.out.println("Hasil pencarian berhasil ditemukan.");
    }

    @AfterClass
    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
