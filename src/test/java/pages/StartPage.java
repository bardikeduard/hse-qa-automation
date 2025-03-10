package pages;

import dto.User;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

@Log4j2
public class StartPage extends BasePage {
    private final String url;

    private static final By LOGIN_NAV_BUTTON = By.id("login2");
    private static final By LOGIN_FIELD = By.id("loginusername");
    private static final By PASSWORD_FIELD = By.id("loginpassword");
    private static final By LOGIN_BUTTON = By.xpath("//button[text()='Log in']");
    private static final By WELCOME_NAV_BUTTON = By.id("nameofuser");

    public StartPage(WebDriver driver, String url) {
        super(driver);
        this.url = url;
    }

    @Step("Open initial page")
    public StartPage open() {
        log.info("Opening initial page");

        driver.get(this.url);

        return this;
    }

    @Step("Open login subwindow")
    public StartPage openLogin() {
        log.info("Opening login subwindow");

        driver.findElement(LOGIN_NAV_BUTTON).click();

        return this;
    }

    @Step("Positive login to account")
    public StartPage loginPositive(User user) {
        log.info("Positive login to account");

        driver.findElement(LOGIN_FIELD).sendKeys(user.getLogin());
        driver.findElement(PASSWORD_FIELD).sendKeys(user.getPassword());
        driver.findElement(LOGIN_BUTTON).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfElementLocated(WELCOME_NAV_BUTTON));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(WELCOME_NAV_BUTTON, "Welcome " + user.getLogin()));

        return this;
    }

    @Step("Negative login to account")
    public void loginNegativeWithWrongPasswd(User user) {
        log.info("Negative login to account");

        driver.findElement(LOGIN_FIELD).sendKeys(user.getLogin());
        driver.findElement(PASSWORD_FIELD).sendKeys(user.getPassword());
        driver.findElement(LOGIN_BUTTON).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "Wrong password.", alertText);
        alert.accept();
    }

    @Step("Choosing product")
    public ProductPage openProductPage(String id) {
        log.info("Choosing product");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By linkLocator = By.xpath(String.format("//a[@href='prod.html?idp_=%s']", id));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(linkLocator));
        link.click();

        return new ProductPage(driver);
    }

}
