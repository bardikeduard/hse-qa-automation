package tests;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.StartPage;

import java.time.Duration;

public class BaseTest {
    WebDriver driver;
    StartPage startPage;

    private static final String START_PAGE_URL = "https://www.demoblaze.com/";

    // p_ - positive
    User positiveUser = User.builder()
            .login(PropertyReader.getProperty("p_user"))
            .password(PropertyReader.getProperty("p_password"))
            .creditCard(PropertyReader.getProperty("p_credit_card"))
            .name(PropertyReader.getProperty("p_name")).build();

    // n_ - negative
    User negativeUser = User.builder()
            .login(PropertyReader.getProperty("n_user"))
            .password(PropertyReader.getProperty("n_password"))
            .creditCard(PropertyReader.getProperty("n_credit_card"))
            .name(PropertyReader.getProperty("n_name")).build();

    @BeforeMethod
    public void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        startPage = new StartPage(driver, START_PAGE_URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
