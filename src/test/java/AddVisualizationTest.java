import org.example.LoginPage;
import org.example.NewDashboardPage;
import org.example.Visual;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class AddVisualizationTest {
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
    public void testAddVisual() {
        Visual v = loginPage.loginAsValidUser("admin", "12345").navigateToDashboards().createNewDashboard().AddVisualization();
        assertTrue(v.isVisualPage());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
