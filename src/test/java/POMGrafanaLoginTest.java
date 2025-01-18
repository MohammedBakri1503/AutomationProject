import org.example.HomePage;
import org.example.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class POMGrafanaLoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/login");

        loginPage = new LoginPage(driver);
    }

    @Test
    public void testValidLogin() {
        HomePage home = loginPage.loginAsValidUser("admin", "12345");
        assertTrue(home.isLoggedInSuccessfully());
    }

    @Test
    public void testInvalidLogin()  {
        String err = loginPage.loginAsinValidUser("admin","1");
        assertEquals(err,"Invalid username or password");
    }

    @Test
    public void testInvalidLoginUSER()  {
        String err = loginPage.loginAsinValidUser("ain","12345");
        assertEquals(err,"Invalid username or password");
    }



    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
