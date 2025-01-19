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
    private By dashboardbutton = By.cssSelector("body > div:nth-child(13) > div.css-134s2wf.css-11ejiz0 > nav > div > div.scrollbar-view > div.css-1bqnmih > ul > div:nth-child(4) > li > div > div.css-2jmhat > div > a > div > div");

    public HomePage(WebDriver driver) {
        this.driver = driver;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("Grafana"));
    }

    public boolean isLoggedInSuccessfully() {
        WebElement successMessage = driver.findElement(successMessageBy);
        return successMessage.isDisplayed();
    }

    public DashboardsPage navigateToDashboards() throws InterruptedException {
        driver.findElement((openmenu)).click();
        //Thread.sleep(Long.parseLong("2222222222222222"));

        //driver.findElement((openmenu)).click();

        driver.findElement(dashboardbutton).click();
        return  new DashboardsPage(driver);

    }
}
