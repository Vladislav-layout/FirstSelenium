package ru.ibs.framework.steps;

import io.cucumber.java.ru.И;
import ru.ibs.framework.managers.PageManager;

public class LoginPageStep {

    private final PageManager pageManager = PageManager.getInstance();
    @И("^Ввод логина: \"(.+)\" и пароля: \"(.+)\"$")
    public void enterLoginAndPass(String login, String pass) {
        pageManager.getLoginPage().enterLoginAndPass(login, pass);
    }
    @И("^Клик по кнопке 'Войти'$")
    public void submitClick(){
        pageManager.getLoginPage().submitClick();
    }


}
