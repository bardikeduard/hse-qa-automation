package pages;

import dto.User;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

@Log4j2
public class CartPage extends BasePage {
    private static final By PLACE_ORDER_BUTTON = By.xpath("//button[text()='Place Order']");
    private static final By NAME_FIELD = By.id("name");
    private static final By CREDIT_CARD_FIELD = By.id("card");
    private static final By OK_BUTTON = By.xpath("//button[text()='Purchase']");
    private static final By SUCCESS_BUTTON = By.xpath("//button[text()='OK']");
    private static final By SUCCESS_WINDOW_ICON = By.xpath("//div[@class='sa-icon sa-success animate']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Place order")
    public CartPage pressPlaceOrderButton() {
        log.info("Placing order");

        driver.findElement(PLACE_ORDER_BUTTON).click();

        return this;
    }

    @Step("Fill order information")
    public CartPage fillOrderInformation(User user) {
        log.info("Filling order information");

        driver.findElement(NAME_FIELD).sendKeys(user.getName());
        driver.findElement(CREDIT_CARD_FIELD).sendKeys(user.getCreditCard());

        return this;
    }

    @Step("Press purchase button")
    public CartPage pressPurchaseButton() {
        log.info("Pressing purchase button");

        driver.findElement(OK_BUTTON).click();

        return this;
    }

    @Step("Assert order success")
    public void assertOrderSuccess() {
        log.info("Asserting order success");

        Assert.assertTrue(driver.findElement(SUCCESS_WINDOW_ICON).isDisplayed());
        driver.findElement(SUCCESS_BUTTON).click();

        log.info("Order succeeded");
    }
}
