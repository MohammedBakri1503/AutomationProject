package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;


public class LoginPage {
    private WebDriver driver;

    private By emailFieldBy = By.id(":r0:");
    private By passwordFieldBy = By.id(":r1:");
    private By loginButtonBy = By.cssSelector("#pageContent > div.css-19pv48c > div > div > div > div.css-1f4iiuo > div > div > form > button");
    private By successMessageBy = By.id("toast-container");
    private By invalidlog = By.cssSelector("#pageContent > div.css-19pv48c > div > div > div > div.css-1f4iiuo > div > div.css-1du4g3n > div > div.css-vjkmk1 > div");
    private By ENT = By.cssSelector("#root > div > main > div > div > section.mb-4.border.border-gray-300.bg-white.shadow-md > div > footer > button");
    public LoginPage(WebDriver driver) {
        this.driver = driver;

        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        this.driver.findElement(ENT).click();

        if (!driver.getTitle().contains("Grafana")) {
            throw new IllegalStateException("This is not the Login Page. Current page: " + driver.getCurrentUrl());
        }
    }

    public HomePage loginAsValidUser(String userName, String password) {
        driver.findElement(emailFieldBy).sendKeys(userName);
        driver.findElement(passwordFieldBy).sendKeys(password);
        driver.findElement(loginButtonBy).click();

        return new HomePage(driver);
    }

    public String loginAsinValidUser(String userName, String password) {
        driver.findElement(emailFieldBy).sendKeys(userName);
        driver.findElement(passwordFieldBy).sendKeys(password);
        driver.findElement(loginButtonBy).click();

        // Get the visible text of the element
        String text = driver.findElement(invalidlog).getText();
        return text;

    }
}