package ru.ibs.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.ibs.framework.managers.TestPropManager;
import ru.ibs.framework.utils.PropConst;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//form[@id='login-form']")
    private WebElement loginWindow;

    @FindBy(xpath = "//input[@name='_username']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@name='_password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;
    @FindBy(xpath = "//h1[@class='oro-subtitle']")
    private WebElement title;

    public LoginPage enterLoginAndPass(String login, String pass){
        wait.until(visibilityOf(loginWindow));
        usernameInput.sendKeys(login);
        passwordInput.sendKeys(pass);
        return this;
    }

    public StartPage submitClick(){
        waitUtilElementToBeClickable(submitButton).click();
        waitUtilElementToBeVisible(title);
        return pageManager.getStartPage();
    }

}
