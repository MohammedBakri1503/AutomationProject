package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class DashboardsPage {
    private WebDriver driver;

    //private By newButtonBy = By.cssSelector("#pageContent > div.css-19pv48c > div > div > div.css-1hgwamg-page-header > div.css-1yhi3xa > div.css-5ax1kt > button > span");
    private By newDashboardButtonBy = By.cssSelector("#grafana-portal-container > div > div > div > div > div > a:nth-child(1)");
    private By newButtonBy = By.xpath("//div[contains(@class, 'page-header')]//button");

    private By successMessageBy = By.id("toast-container");

    public DashboardsPage(WebDriver driver) {
        this.driver = driver;

        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

     /*   if (!driver.getTitle().contains("Dashboards - Grafana")) {
            throw new IllegalStateException("This is not the Dasshboards page. Current page: " + driver.getCurrentUrl());
        }*/
    }

    public NewDashboardPage createNewDashboard() {

        driver.findElement(newButtonBy).click();
        driver.findElement(newDashboardButtonBy).click();

        return new NewDashboardPage(driver);
    }
}
