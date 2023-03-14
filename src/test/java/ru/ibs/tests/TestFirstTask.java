package ru.ibs.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class TestFirstTask {
    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10, 1000);
        String baseUrl = "http://training.appline.ru/user/login";
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void crmTest() {

        //Авторизация
        wait.until(visibilityOf(driver.findElement(By.xpath("//form[@id='login-form']"))));
        driver.findElement(By.xpath("//input[@name='_username']")).sendKeys("Taraskina Valeriya");
        driver.findElement(By.xpath("//input[@name='_password']")).sendKeys("testing");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String expectHeader = "Панель быстрого запуска";
        String headerXPath = "//h1[@class='oro-subtitle']";
        WebElement actualHeader = driver.findElement(By.xpath(headerXPath));
        assertTrue(actualHeader.isDisplayed(), "Заголовок не появился.");
        assertEquals(expectHeader, actualHeader.getText(), "Текст заголовка некорректный.");

        //Переход в командировки
        String costListXPath = "//ul[@class='nav nav-multilevel main-menu']/li/a/span[text()='Расходы']";
        WebElement costlist = driver.findElement(By.xpath(costListXPath));
        costlist.click();
        assertTrue(costlist.findElement(By.xpath("./ancestor::li//ul[@class='dropdown-menu menu_level_1']"))
                .isDisplayed(), "Выпадающее окно не открыто.");

        String bussinesTripXpath = "//span[text()='Командировки']";
        driver.findElement(By.xpath(bussinesTripXpath)).click();

        //Загрузка
        loading();

        //Создание командировки
        String createTripXPath = "//a[text()='Создать командировку']";
        WebElement createTrip = driver.findElement(By.xpath(createTripXPath));
        createTrip.click();
        loading();
        assertEquals(createTrip.getText(), driver
                        .findElement(By.xpath("//h1[text()='Создать командировку' and @class='user-name']")).getText(),
                "Страница создания командировки не открылась.");

        //Заполнение данных
        //Подразделение
        String subdivisionXPath = "//select[@name='crm_business_trip[businessUnit]']";
        String intDevDepXpath = "//option[text()='Отдел внутренней разработки']";
        driver.findElement(By.xpath(subdivisionXPath)).click();
        WebElement intDevDep = driver.findElement(By.xpath(intDevDepXpath));
        String intDevDepString = driver.findElement(By.xpath(intDevDepXpath)).getText();
        assertTrue(intDevDep.isDisplayed());
        intDevDep.click();
        assertEquals(intDevDepString, intDevDep.getText(), "Выбранное подразделение не соответсвует отображаемому в инпуте.");
        //Принимающая организация
        driver.findElement(By.xpath("//a[text()='Открыть список']")).click();
        driver.findElement(By.xpath("//span[@class='select2-chosen']")).click();
        assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'with-searchbox')]"))
                .isDisplayed(), "Выпадающее окно не отобразилось");
        WebElement organisation = driver.findElement(By.xpath("//li[contains(@class, 'select2')][2]"));
        String org = organisation.getText();
        organisation.click();
        assertEquals(org, driver.findElement(By.xpath("//span[@class='select2-chosen']"))
                .getText(), "Выбранная организация не соответсвует отображаемой в инпуте.");

        driver.findElement(By.xpath("//input[@data-ftid='crm_business_trip_tasks_1']")).click();

        //Ввод города и дат
        String fieldXPath = "//%s[contains(@%s, '%s')]";
        fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "input", "name", "departureCity"))), "Барнаул");
        fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "input", "name", "arrivalCity"))), "Москва");
        fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "input", "name", "_departureDate"))), "10.10.2023");
        driver.findElement(By.xpath(String.format(fieldXPath, "input", "name", "_departureDate")))
                .sendKeys(Keys.ESCAPE);
        fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "input", "name", "_returnDate"))), "15.10.2023");
        driver.findElement(By.xpath(String.format(fieldXPath, "input", "name", "_returnDate")))
                .sendKeys(Keys.ESCAPE);

        //Нажать "Сохранить и закрыть"
        String saveAndCloseButtonXPath = "//button[@class = 'btn btn-success action-button']";
        driver.findElement(By.xpath(saveAndCloseButtonXPath)).click();
        loading();

        //Проверка появления сообщений о некорректно заполненных полях
        checkErrorMessageAtField(driver.findElement(By.xpath(String.format(fieldXPath, "div", "id", "crm_business_trip_users-uid"))),
                "Список командируемых сотрудников не может быть пустым");
        checkErrorMessageAtField(driver.findElement(By.xpath(String.format(fieldXPath, "div", "id", "crm_business_trip_foreignUsers-uid"))),
                "Список командируемых сотрудников не может быть пустым");
    }

    @AfterEach
    public void after() {
        driver.close();
        driver.quit();
    }

    private void loading() {
        String loaderXPath = "//div[@class='loader-content']";
        WebElement loader = driver.findElement(By.xpath(loaderXPath));
        if (loader.isDisplayed()) {
            wait.until(invisibilityOf(loader));
        }
    }

    private void fillInputField(WebElement element, String value) {
        element.click();
        element.clear();
        element.sendKeys(value);
        boolean checkFlag = wait.until(attributeContains(element, "value", value));
        assertTrue(checkFlag, "Поле было заполнено некорректно");
    }

    private void checkErrorMessageAtField(WebElement element, String errorMessage) {
        element = element.findElement(By.xpath("./../..//span"));
        assertEquals(errorMessage, element.getText(), "Сообщение некорректно.");
    }
}
