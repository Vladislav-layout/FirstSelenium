package ru.ibs.framework.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.ibs.framework.utils.PropConst;

public class DriverManager {

    private static DriverManager INSTANCE;
    private WebDriver driver;
    private TestPropManager propManager = TestPropManager.getInstance();
    private DriverManager () {
    }

    public static DriverManager getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    public WebDriver getDriver() {
        if(driver == null) {
            initDriver();
        }
        return driver;
    }

    public void initDriver() {

        System.setProperty("webdriver." + propManager.getProperty(PropConst.CHROME_DRIVER), propManager.getProperty(PropConst.PATH_CHROME_DRIVER_WINDOWS));
        driver = new ChromeDriver();
    }

    public void quitDriver() {
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }
}