import org.example.LoginPage;
import org.example.Visual;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;




public class SetSettingsTest {
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
    public void testAddVisual() throws InterruptedException {
        Visual v = loginPage.loginAsValidUser("admin", "12345").navigateToDashboards().createNewDashboard().AddVisualization();
        v.selectDataType();
        v.selectParser();
        v.selectDataSource();
        v.addData("Year (July 1),Population,Yearly % Change,Yearly Change,Median Age,Fertility Rate,Density (P/Km²)\n" +
                "2020,7794798739,0.011,83000320,31,2.47,52\n" +
                "2025,8184437460,0.0098,77927744,32,2.54,55\n" +
                "2030,8548487400,0.0087,72809988,33,2.62,57\n" +
                "2035,8887524213,0.0078,67807363,34,2.7,60\n" +
                "2040,9198847240,0.0069,62264605,35,2.77,62\n" +
                "2045,9481803274,0.0061,56591207,35,2.85,64\n" +
                "2050,9735033990,0.0053,50646143,36,2.95,65\n");
        v.chooseColumns();
        //Thread.sleep(Long.parseLong("10000"));
        assertTrue(v.isVisualPage());
    }

    @Test
    public void saveDash() throws InterruptedException {
        Visual v = loginPage.loginAsValidUser("admin", "12345").navigateToDashboards().createNewDashboard().AddVisualization();
        v.selectDataType();
        v.selectParser();
        v.selectDataSource();
        v.addData("Year (July 1),Population,Yearly % Change,Yearly Change,Median Age,Fertility Rate,Density (P/Km²)\n" +
                "2020,7794798739,0.011,83000320,31,2.47,52\n" +
                "2025,8184437460,0.0098,77927744,32,2.54,55\n" +
                "2030,8548487400,0.0087,72809988,33,2.62,57\n" +
                "2035,8887524213,0.0078,67807363,34,2.7,60\n" +
                "2040,9198847240,0.0069,62264605,35,2.77,62\n" +
                "2045,9481803274,0.0061,56591207,35,2.85,64\n" +
                "2050,9735033990,0.0053,50646143,36,2.95,65\n");
        v.chooseColumns();
        v.saveDashboar();
        //Thread.sleep(Long.parseLong("10000"));
        //assertTrue(v.isVisualPage());
    }

    @Test
    public void manageparsing() throws InterruptedException {
        Visual v = loginPage.loginAsValidUser("admin", "12345").navigateToDashboards().createNewDashboard().AddVisualization();
        v.selectDataType();
        v.selectParser();
        v.selectDataSource();
        v.addData("Year (July 1),Population,Yearly % Change,Yearly Change,Median Age,Fertility Rate,Density (P/Km²)\n" +
                "2020,7794798739,0.011,83000320,31,2.47,52\n" +
                "2025,0.0098,77927744,32,2.54,55\n" +
                "2030,8548487400,0.0087,72809988,33,2.62,57\n" +
                "2035,8887524213,0.0078,67807363,34,2.7,60\n" +
                "2040,9198847240,0.0069,62264605,35,2.77,62\n" +
                "2045,9481803274,0.0061,56591207,35,2.85,64\n" +
                "2050,9735033990,0.0053,50646143,36,2.95,65\n");
        v.chooseColumns();
        //Thread.sleep(Long.parseLong("100000000000"));

        v.skiperror();
        //Thread.sleep(Long.parseLong("100000000000"));
        /*String m =v.invalidData();
        assertEquals(m,"error while performing the infinity inline query. error reading csv response\n" +
                "record on line 3: wrong number of fields, [2025 0.0098 77927744 32 2.54 55]");*/
        //Thread.sleep(Long.parseLong("10000"));
        //assertTrue(v.isVisualPage());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
