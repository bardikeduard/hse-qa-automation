package tests;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

@Log4j2
public class CheckoutTest extends BaseTest {
    /*
        1. Open https://www.demoblaze.com/
        2. Open login subwindow
        3. Input positive user login and password
        4. Press "Login" button
        5. Click on item
        6. Add item to cart
        7. Open cart
        8. Press button "Place Order"
        9. Input positive user's name and credit card
        10. Press "Purchase" button
        11. Assert success message is displayed
     */

    @Test
    public void createOrder() {
        startPage.open()
                .openLogin()
                .loginPositive(positiveUser)
                .openProductPage("1")
                .addProductToCart()
                .openCartPage()
                .pressPlaceOrderButton()
                .fillOrderInformation(positiveUser)
                .pressPurchaseButton()
                .assertOrderSuccess();
    }

    /*
        1. Open https://www.demoblaze.com/
        2. Open login subwindow
        3. Input positive user login and password
        4. Press "Login" button
        5. Click on item
        6. Add item to cart
        7. Open cart
        8. Press button "Place Order"
        9. Press "Purchase" button
        10. Assert alert with failure message is displayed
     */

    @Test
    public void createOrderWithoutCredentials() {
        startPage.open()
                .openLogin()
                .loginPositive(positiveUser)
                .openProductPage("1")
                .addProductToCart()
                .openCartPage()
                .pressPlaceOrderButton()
                .pressPurchaseButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "Please fill out Name and Creditcard.", alertText);
        alert.accept();
    }

}
