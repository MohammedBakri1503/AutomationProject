

import org.example.HomePage;
import org.example.LoginPage;
import org.example.NewDashboardPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GrafanaCreateNewDashboardTest {
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
    public void testCreatingNewDashboard() {
        NewDashboardPage dash = loginPage.loginAsValidUser("admin", "12345").navigateToDashboards().createNewDashboard();
        //assertTrue(dash.isIndashPage());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
