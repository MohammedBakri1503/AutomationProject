package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;


public class Visual {

    private WebDriver driver;

   // private By type = By.xpath("span[@id=”first”]/.."
          //  );//"css selector: div[data-testid='infinity ...']" + "span[starts-with(@id, 'react-select')]/.."
    //private final By t = By.id("react-select-8-input");
    private final By type = By.id("react-select-8-input");
    private final By parser = By.id("react-select-9-input");
    //private By parserBackend = By.cssSelector("");
    private final By source = By.id("react-select-10-input");
    private By addData = By.cssSelector("#A_31 > div.infinity-query-editor > div > div:nth-child(2) > div > div:nth-child(1) > div > div > div > textarea");
    private By scroller = By.cssSelector("#pageContent > div.css-19pv48c > div > div > div.css-rxxai3-body > div > div.css-k0qrhd > div:nth-child(3) > div > div.css-rkqksg > div.track-vertical > div");

    private By parseOptions = By.cssSelector("#A_31 > div > div > div:nth-child(3) > div:nth-child(1) > button");
    private By addColumn = By.cssSelector("#A_31 > div > div > div:nth-child(3) > div:nth-child(2) > div.css-1me57sq > div > div > div > div > div > div > button");
    private By firstName = By.cssSelector("#A_31 > div > div > div:nth-child(3) > div:nth-child(2) > div.css-1me57sq > div > div > div > div:nth-child(1) > div > div > div:nth-child(2) > div > input");
    private By firstTitle = By.cssSelector("#A_31 > div > div > div:nth-child(3) > div:nth-child(2) > div.css-1me57sq > div > div > div > div:nth-child(1) > div > div > div:nth-child(4) > div > input");
    private By firstFormat = By.id("react-select-17-input");
    private By secondName = By.cssSelector("#A_31 > div > div > div:nth-child(3) > div:nth-child(2) > div.css-1me57sq > div > div > div > div:nth-child(2) > div > div > div:nth-child(2) > div > input");
    private By secondTitle = By.cssSelector("#A_31 > div > div > div:nth-child(3) > div:nth-child(2) > div.css-1me57sq > div > div > div > div:nth-child(2) > div > div > div:nth-child(4) > div > input");
    private By secondFormat = By.id("react-select-18-input");

    private By refresh = By.cssSelector("#pageContent > div.css-19pv48c > div > div > div.css-rxxai3-body > div > div.css-16hl5pe > div > div.css-1r4g60 > div.button-group.css-8qah51.refresh-picker > button");

    private By chengetime = By.cssSelector("#pageContent > div.css-19pv48c > div > div > div.css-rxxai3-body > div > div.css-16hl5pe > div > div.css-1r4g60 > div.button-group.css-wmojnn > button:nth-child(1)");

    private By saveDashboard = By.cssSelector("#reactRoot > div > div.main-view > header > div.css-1fj7032 > div.css-1ntsjus-NavToolbar-actions > div.css-16lblkh > div > div:nth-child(4) > button");
    private By dashName = By.cssSelector("#reactRoot > div > div.main-view > div.rc-drawer.rc-drawer-right.css-el6sfw.rc-drawer-open > div.rc-drawer-content-wrapper.css-cifegn-drawer-content-wrapper-md > div > div > div.css-rzpihd > div.scrollbar-view > div > form > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div > div > input");

    private By save = By.cssSelector("#reactRoot > div > div.main-view > div.rc-drawer.rc-drawer-right.css-el6sfw.rc-drawer-open > div.rc-drawer-content-wrapper.css-cifegn-drawer-content-wrapper-md > div > div > div.css-rzpihd > div.scrollbar-view > div > form > div.css-1iuwxif > div > button.css-td06pi-button");

    private By messege = By.cssSelector("#\\:res\\: > div > div");

    private By skiperror = By.cssSelector("#A_31 > div.infinity-query-editor > div > div:nth-child(3) > div:nth-child(2) > div.css-yiuggo > div > div > div > div > div > div:nth-child(3) > div > label > div > input");
    public Visual(WebDriver driver) {
        this.driver = driver;

        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        if (!driver.getTitle().contains("Edit panel - New dashboard - Dashboards - Grafana")) {
            throw new IllegalStateException("This is not the Visulization page. Current page: " + driver.getCurrentUrl());
        }
    }

    public boolean isVisualPage() {
        if (!driver.getTitle().contains("Edit panel - New dashboard - Dashboards - Grafana")) {
            return  false;
        }
        return true;
    }

    public void selectDataType()  {
        //Thread.sleep(120000);
        driver.findElement(type).sendKeys("CSV");
        driver.findElement(type).sendKeys(Keys.ENTER);

    }

    public void selectParser() {
        driver.findElement(parser).sendKeys("Backend");
        driver.findElement(parser).sendKeys(Keys.ENTER);

    }

    public void selectDataSource() {
        driver.findElement(source).sendKeys("Inline");
        driver.findElement(source).sendKeys(Keys.ENTER);


    }

    public void addData(String data) {
        driver.findElement(addData).sendKeys(data);


    }

    public void scrollElementByPixels(int pixels) {
        WebElement element = driver.findElement(scroller);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop += arguments[1];", element, pixels);
    }

    public void chooseColumns() throws InterruptedException {
        driver.findElement(parseOptions).click();
        driver.findElement(addColumn).click();
        driver.findElement(addColumn).click();
        driver.findElement(firstName).sendKeys("Year (July 1)");
        driver.findElement(secondName).sendKeys("Population");
        driver.findElement(firstTitle).sendKeys("Year (July 1)");
        driver.findElement(secondTitle).sendKeys("Population");
        driver.findElement(firstFormat).sendKeys("Time");
        driver.findElement(firstFormat).sendKeys(Keys.ENTER);
        driver.findElement(secondFormat).sendKeys("Number");
        driver.findElement(secondFormat).sendKeys(Keys.ENTER);
        driver.findElement(refresh).click();






    }
    public void saveDashboar() throws InterruptedException {
        driver.findElement(saveDashboard).click();
        driver.findElement(dashName).sendKeys("test Dash2");

        driver.findElement(save).click();

    }

    public String invalidData() throws InterruptedException {
         return  driver.findElement(messege).getText();

    }

    public void skiperror() {
        // Use Actions to move and click
        Actions actions = new Actions(driver);
        actions.moveToElement( driver.findElement(skiperror)).click().perform();
        //driver.findElement(skiperror).click();
    }
}
