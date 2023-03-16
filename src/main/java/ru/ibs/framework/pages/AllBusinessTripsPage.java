package ru.ibs.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.junit.Assert;
public class AllBusinessTripsPage extends BasePage{

    @FindBy(xpath = "//h1[@class='oro-subtitle']")
    private WebElement title;
    @FindBy(xpath = "//a[text()='Создать командировку']")
    private WebElement createTripButton;
    public AllBusinessTripsPage checkOpenPage() {
        Assert.assertTrue("Страница не открылась или кнопка не появилась.", createTripButton.isDisplayed());
        return this;
    }
    public CreateTripPage clickCreateTripButton() {
        waitUtilElementToBeClickable(createTripButton).click();
        return pageManager.getCreateTripPage();
    }

}
