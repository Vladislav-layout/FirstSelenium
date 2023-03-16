package ru.ibs.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class CreateTripPage extends BasePage{

    @FindBy(xpath = "//h1[@class='user-name']")
    private WebElement title;

    @FindBy(xpath = "//select[@data-name = 'field__business-unit']")
    private WebElement subdivision;

    @FindBy(xpath = "//option[text()='Отдел внутренней разработки']")
    private WebElement subdivisions;

    @FindBy(xpath = "//a[text()='Открыть список']")
    private WebElement organisationHref;

    @FindBy(xpath = "//span[@class='select2-chosen']")
    private WebElement specifyOrganization;


    @FindBy(xpath = "//li[contains(@class, 'select2')]")
    private List<WebElement> listOrganisation;

    @FindBy(xpath = "//div[contains(@id, 'select2-drop')][2]")
    private WebElement organisationWindow;

    @FindBy(xpath = "//li[contains(@class, 'select2')][2]")
    private WebElement organisation;

    @FindBy(xpath = "//input[@data-ftid='crm_business_trip_tasks_1']")
    private WebElement ticketBookingCheckbox;

    @FindBy(xpath = "//input[contains(@name, 'departureCity')]")
    private WebElement departureCity;

    @FindBy(xpath = "//input[contains(@name, 'departureCity')]")
    private WebElement arrivalCity;

    @FindBy(xpath = "//input[contains(@name, '_departureDate')]")
    private WebElement departureDate;

    @FindBy(xpath = "//input[contains(@name, '_returnDate')]")
    private WebElement returnDate;

    @FindBy(xpath = "//button[@class = 'btn btn-success action-button']")
    private WebElement saveAndCloseButton;

    @FindBy(xpath = "//div[contains(@id, 'crm_business_trip_users-uid')]")
    private WebElement businessTripEmployees;
    @FindBy(xpath = "//div[contains(@id, 'crm_business_trip_foreignUsers-uid')]")
    private WebElement freelancers;

    @FindBy(xpath = "//div[@class='loader-content']")
    private WebElement loader;

    public CreateTripPage checkOpenPage() {
        Assert.assertEquals("Заголовок отсутсвует/не соответствует требуемому.", title.getText(),
                "Создать командировку");
        return pageManager.getCreateTripPage();
    }

    public CreateTripPage clickSubDivision() {
        subdivision.click();
        return this;
    }

    public CreateTripPage chooseSubdivisions() {
        assertTrue(subdivisions.isDisplayed());
        subdivisions.click();
        return this;
    }

    public CreateTripPage organisationOpening () {
        waitUtilElementToBeClickable(organisationHref).click();
        return this;
    }

    public CreateTripPage chooseOrganization(String organisations) {
        specifyOrganization.click();
        waitUtilElementToBeClickable(organisation).click();
        return this;
        /* Перехват клика...
        for (WebElement organisation : listOrganisation) {
            if(organisation.getText().equals(organisations)) {
                organisation.click();
            }
            return;
        }
        Assert.fail("Подразделение с текстом '" + organisations + "' не найдено.");
        */
    }

    public CreateTripPage checkBoxClick() {
        waitUtilElementToBeClickable(ticketBookingCheckbox).click();
        return this;
    }
    public CreateTripPage fillField(String nameField, String value) {
        WebElement element = null;
        switch (nameField) {
            case "Город выбытия":
                fillInputField(departureCity, value);
                element = departureCity;
                break;
            case "Город прибытия":
                fillInputField(arrivalCity, value);
                element = arrivalCity;
                break;
            case "Планируемая дата выезда":
                fillInputField(departureDate, value);
                element = departureDate;
                departureDate.sendKeys(Keys.ESCAPE);
                break;
            case "Планируемая дата возвращения":
                fillInputField(returnDate, value);
                element = returnDate;
                returnDate.sendKeys(Keys.ESCAPE);
                break;
            default:
                Assert.fail("Поле с наименованием '" + nameField + "' отсутствует на странице ");

        }
        Assert.assertEquals("Поле '" + nameField + "' было заполнено некорректно",
                value, element.getAttribute("value"));
        return this;
    }

    public CreateTripPage saveAndClose () {
        waitUtilElementToBeClickable(saveAndCloseButton).click();
        loading(loader);
        return this;
    }

    public CreateTripPage checkErrorMessageAtField(String nameField, String errMassage) {
        WebElement element = null;
        switch (nameField) {
            case "Командированные сотрудники":
                element = businessTripEmployees;
                break;
            case "Внештатные сотрудники":
                element = freelancers;
                break;
            default:
                Assert.fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                        "'Оформления страхования путешественников'");

        }
        element = element.findElement(By.xpath("./../..//span"));
        Assert.assertEquals("Проверка ошибки у поля '" + nameField + "' была не пройдена",
                errMassage, element.getText());
        return this;
    }

}
