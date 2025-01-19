

import org.example.DriverFactory;
import org.example.HomePage;
import org.example.LoginPage;
import org.example.NewDashboardPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GrafanaCreateNewDashboardTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        driver = DriverFactory.getDriver();
        driver.get("https://ace4-2a06-c701-78cc-700-b86f-b847-a66b-5c27.ngrok-free.app/login");
        loginPage = new LoginPage(driver);

    }

    @Test
    public void testCreatingNewDashboard() throws InterruptedException {
        NewDashboardPage dash = loginPage.loginAsValidUser("admin", "12345").navigateToDashboards().createNewDashboard();
        //assertTrue(dash.isIndashPage());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
