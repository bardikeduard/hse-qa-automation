package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Alert;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

@Log4j2
public class ProductPage extends BasePage {
    private static final By ADD_BUTTON = By.xpath("//a[text()='Add to cart']");
    private static final By CART_PAGE = By.id("cartur");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Step("Add product to cart")
    public ProductPage addProductToCart() {
        log.info("Adding product to cart");

        driver.findElement(ADD_BUTTON).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "Product added.", alertText);
        alert.accept();

        return this;
    }

    @Step("Open cart")
    public CartPage openCartPage() {
        log.info("Opening cart");

        driver.findElement(CART_PAGE).click();

        return new CartPage(driver);
    }
}
