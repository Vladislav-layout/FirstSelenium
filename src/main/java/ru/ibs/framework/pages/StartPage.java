package ru.ibs.framework.pages;


import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;


public class StartPage extends BasePage {

    @FindBy(xpath = "//h1[@class='oro-subtitle']")
    private WebElement title;
    /* Не работает, не видит пункты меню?
    @FindBy(xpath = "//ul[@class='nav nav-multilevel main-menu']/li/a/span[text()]")
    private List<WebElement> listBaseMenu;
    */
    @FindBy(xpath = "//ul[@class='nav nav-multilevel main-menu']/li/a/span[text()='Расходы']")
    private WebElement baseMenu;

    /* Не работает, не видит пункты меню?
    @FindBy(xpath = "//ul[@class = 'dropdown-menu menu_level_1']/li/a/span")
    private List<WebElement> listSubMenu;*/

    @FindBy(xpath = "//span[text()='Командировки']")
    private WebElement subMenu;

    public StartPage checkOpenPage() {
        Assert.assertEquals("Текст заголовка некорректный.", title.getText(),"Панель быстрого запуска");
        return this;
    }

/*    public StartPage selectBaseMenuByName(String nameMenu){

        for (WebElement menuItem : listBaseMenu) {
            if (menuItem.getText().trim().equalsIgnoreCase(nameMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                return pageManager.getStartPage();
            }
        }
        Assert.fail("Меню '" + nameMenu + "' не было найдено на стартовой странице!");
        return pageManager.getStartPage();
    }*/

    public StartPage selectBaseMenuByName () {
        waitUtilElementToBeClickable(baseMenu).click();
        return this;
    }

    public AllBusinessTripsPage selectSubMenuByName () {
        waitUtilElementToBeClickable(subMenu).click();
        return pageManager.getAllBusinessTripsPage();
    }

/*    public AllBusinessTripsPage selectSubMenuByName(String nameMenu){

        for (WebElement menuItem : listSubMenu) {
            if (menuItem.getText().trim().equalsIgnoreCase(nameMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                return pageManager.getAllBusinessTripsPage();
            }
        }
        Assert.fail("Подменю '" + nameMenu + "' не было найдено в выпадающем списке.");
        return pageManager.getAllBusinessTripsPage();
    }*/

}
