package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locator
    private By searchInput = By.cssSelector("input[placeholder='Cari di Tokopedia']");
    private By searchButton = By.cssSelector("button[data-testid='search-button']");
    private By productResults = By.cssSelector("div[data-testid='product-card']");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openHomePage() {
        driver.get("https://www.tokopedia.com/");
        dismissPopupIfPresent();
    }

    public void searchProduct(String keyword) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear();
        input.sendKeys(keyword);

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        btn.click();
    }

    public boolean hasResults() {
        List<WebElement> results = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productResults));
        return !results.isEmpty();
    }

    private void dismissPopupIfPresent() {
        try {
            By popupClose = By.cssSelector("button[aria-label='Close']");
            WebElement closeBtn = driver.findElement(popupClose);
            if (closeBtn.isDisplayed()) {
                closeBtn.click();
            }
        } catch (Exception ignored) {}
    }
}
