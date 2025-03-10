package tests;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

@Log4j2
public class LoginTest extends BaseTest {
    /*
        1. Open https://www.demoblaze.com/
        2. Open login subwindow
        3. Input positive user credentials
        4. Press "Login" button
        5. Assert that navbar contains link with welcome message
     */

    @Test
    public void testPositiveLogin() {
        startPage.open()
                .openLogin()
                .loginPositive(positiveUser);
    }

    /*
        1. Open https://www.demoblaze.com/
        2. Open login subwindow
        3. Input negative user credentials
        4. Press "Login" button
        5. Assert that alert with message about wrong credentials is presented
     */

    @Test
    public void testNegativeLogin() {
        startPage.open()
                .openLogin()
                .loginNegativeWithWrongPasswd(negativeUser);
    }

}
