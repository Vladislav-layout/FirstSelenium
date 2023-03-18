package ru.ibs.framework.pages;


import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class StartPage extends BasePage {

    @FindBy(xpath = "//h1[@class='oro-subtitle']")
    private WebElement title;
    @FindBy(xpath = "//ul[@class='nav nav-multilevel main-menu']/li/a/span[text()='Расходы']")
    private WebElement baseMenu;

    @FindBy(xpath = "//span[text()='Командировки']")
    private WebElement subMenu;

    public StartPage checkOpenPage() {
        Assert.assertEquals("Текст заголовка некорректный.", title.getText(),"Панель быстрого запуска");
        return this;
    }

    public StartPage selectBaseMenuByName () {
        waitUtilElementToBeClickable(baseMenu).click();
        return this;
    }

    public AllBusinessTripsPage selectSubMenuByName () {
        waitUtilElementToBeClickable(subMenu).click();
        return pageManager.getAllBusinessTripsPage();
    }

}
