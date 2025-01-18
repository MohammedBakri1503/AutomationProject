package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class NewDashboardPage {

    private WebDriver driver;

    private By addVisual = By.cssSelector("#pageContent > div.css-19pv48c > div > div > div.css-1yrhboa-canvas-content > div.css-6pf3n5 > div > div > div.css-87366u > div > button");
    private By dataSource = By.cssSelector("body > div:nth-child(15) > div.css-1fuqvhh > div.css-cutunk > div.css-aw7ekp > div.css-rzpihd > div.scrollbar-view > div.css-sna5ro > div > h2 > button");

    private By successMessageBy = By.id("toast-container");
    public NewDashboardPage(WebDriver driver) {
        this.driver = driver;

        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

       /* if (!driver.getTitle().contains("New dashboard - Dashboards - Grafana")) {
            throw new IllegalStateException("This is not the new Dasshboards page. Current page: " + driver.getCurrentUrl());
        }*/
    }
    public Visual AddVisualization() {

        driver.findElement(addVisual).click();
        driver.findElement(dataSource).click();

        return new Visual(driver);
    }

}
