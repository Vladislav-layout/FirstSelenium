import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;


public class firstTask {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver.get("http://training.appline.ru/user/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void auth() {

        //Авторизация
        wait.until(visibilityOf(driver.findElement(By.xpath("//form[@id='login-form']"))));
        driver.findElement(By.xpath("//input[@name='_username']")).sendKeys("Taraskina Valeriya");
        driver.findElement(By.xpath("//input[@name='_password']")).sendKeys("testing");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String expectHeader = "Панель быстрого запуска";
        WebElement actualHeader = driver.findElement(By.xpath("//h1[@class='oro-subtitle']"));
        assertTrue(actualHeader.isDisplayed(), "Заголовок не появился.");
        assertEquals(expectHeader, actualHeader.getText(), "Текст заголовка некорректный.");

        //Переход в командировки
        WebElement costlist = driver.findElement(By.xpath(
                "//ul[@class='nav nav-multilevel main-menu']/li/a/span[text()='Расходы']"));
        costlist.click();
        assertTrue(costlist.findElement(By.xpath("./ancestor::li//ul[@class='dropdown-menu menu_level_1']"))
                .isDisplayed(), "Выпадающее окно не открыто.");
        driver.findElement(By.xpath("//span[text()='Командировки']")).click();

        //Загрузка
        loading();

        //Создание командировки
        WebElement createTrip = driver.findElement(By.xpath("//a[text()='Создать командировку']"));
        createTrip.click();
        loading();
        assertEquals(createTrip.getText(), driver
                .findElement(By.xpath("//h1[text()='Создать командировку' and @class='user-name']")).getText());

        //Заполнение данных
        driver.findElement(By.xpath("//select[@name='crm_business_trip[businessUnit]']")).click();
        WebElement intDevDep = driver.findElement(By.xpath("//option[text()='Отдел внутренней разработки']"));
        assertTrue(intDevDep.isDisplayed());
        intDevDep.click();
        driver.findElement(By.xpath("//select[@name='crm_business_trip[businessUnit]']")).click();

        driver.findElement(By.xpath("//a[text()='Открыть список']")).click();
        driver.findElement(By.xpath("//span[@class='select2-chosen']")).click();
        assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'with-searchbox')]"))
                .isDisplayed(), "Выпадающее окно не отобразилось");
        WebElement organisation = driver.findElement(By.xpath("//li[contains(@class, 'select2')][2]"));
        String org = organisation.getText();
        organisation.click();
        assertEquals(org, driver.findElement(By.xpath("//span[@class='select2-chosen']"))
                .getText(), "Выбранная организация не соответсвует отображаемой в инпуте");

        driver.findElement(By.xpath("//input[@data-ftid='crm_business_trip_tasks_1']")).click();
        WebElement departureCity = driver.findElement(By.xpath("//input[@name='crm_business_trip[departureCity]']"));
        WebElement arrivalCity = driver.findElement(By.xpath("//input[@name='crm_business_trip[arrivalCity]']"));
        departureCity.clear();
        departureCity.sendKeys("Барнаул");
        //проверку на заполнение
        arrivalCity.clear();
        arrivalCity.sendKeys("Москва");
        //проверку на заполнение
        driver.findElement(By.xpath("//input[contains(@name, '_departureDate')]")).click();
        assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'ui-datepicker-header')]/.."))
                .isDisplayed());
        driver.findElement(By.xpath("//td[@class='ui-datepicker-today']")).click();
        driver.findElement(By.xpath("//input[contains(@name, '_returnDate')]")).click();
        assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'ui-datepicker-header')]/.."))
                .isDisplayed());
        driver.findElement(By.xpath("//td[@class='ui-datepicker-days-cell-over']")).click();

        //Нажать "Сохранить и закрыть"
        driver.findElement(By.xpath("//button[@class = 'btn btn-success action-button']")).click();
        loading();

        //Проверка появления сообщений о некорректно заполненных полях
        assertTrue(driver.findElement(By.xpath("//span[@class='validation-failed']")).isDisplayed(),
                "Сообщений о некорретно заполненных полях не появилось.");
        List<WebElement> allValidFail = driver.findElements(By.xpath("//span[@class='validation-failed']"));
        String actualMessage = "Список командируемых сотрудников не может быть пустым";
        for (WebElement element : allValidFail) {
            assertEquals(element.getText(), actualMessage, "Некорректное сообщение");
        }

    }

    @AfterEach
    public void after() {
        driver.close();
        driver.quit();
    }

    public void loading() {
        wait.until(invisibilityOf(driver.findElement(By.xpath("//div[@class='loader-content']"))));
    }
}
