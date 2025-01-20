import org.example.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertTrue;



public class AddVisualizationTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        driver = DriverFactory.getDriver();
        driver.get("https://ace4-2a06-c701-78cc-700-b86f-b847-a66b-5c27.ngrok-free.app/login");
        loginPage = new LoginPage(driver);

    }

    @Test
    public void testAddVisual() throws InterruptedException {
        Thread.sleep(20000);

        HomePage h = loginPage.loginAsValidUser("admin", "12345");
        Thread.sleep(20000);

        DashboardsPage dash = h.navigateToDashboards();
        Thread.sleep(20000);

        NewDashboardPage newdash =  dash.createNewDashboard();
        Thread.sleep(20000);

        Visual v =  newdash.AddVisualization();
        Thread.sleep(20000);

        assertTrue(v.isVisualPage());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
