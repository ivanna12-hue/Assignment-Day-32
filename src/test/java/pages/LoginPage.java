package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;


    private By titleLogin = By.xpath("//h2[contains(text(),'Masuk')]");
    private By inputUsername = By.cssSelector("input[data-testid='email-phone-input']");
    private By buttonLogin = By.cssSelector("button[data-testid='login-button']");
    private By textErrorLogin = By.cssSelector("div[data-testid='error-message']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://www.tokopedia.com/login");
    }

    public String getTitleText() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(titleLogin));
        return title.getText();
    }

    public void loginWithUsername(String username) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(inputUsername));
        input.clear();
        input.sendKeys(username);

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(buttonLogin));
        btn.click();
    }

    public String getErrorMessage() {
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(textErrorLogin));
        return error.getText();
    }
}
