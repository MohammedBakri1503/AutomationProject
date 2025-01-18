package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class HomePage {
    private WebDriver driver;

    private By successMessageBy = By.cssSelector("#reactRoot > div > div.main-view > header > div.css-1fj7032 > nav");
    private By openmenu = By.cssSelector("#mega-menu-toggle > svg");
    private By dashboardbutton = By.xpath("//*[@id=\"reactRoot\"]/div/div[1]/div/div/div[1]/nav/div/div[1]/div[2]/ul/div[4]/li/div/div[2]/div/a/div/div/span");

    public HomePage(WebDriver driver) {
        this.driver = driver;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("Grafana"));
    }

    public boolean isLoggedInSuccessfully() {
        WebElement successMessage = driver.findElement(successMessageBy);
        return successMessage.isDisplayed();
    }

    public DashboardsPage navigateToDashboards() {
        driver.findElement((openmenu)).click();
        driver.findElement((openmenu)).click();

        driver.findElement(dashboardbutton).click();
        return  new DashboardsPage(driver);

    }
}
