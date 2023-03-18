package ru.ibs.framework.steps;

import io.cucumber.java.bg.И;
import ru.ibs.framework.managers.PageManager;

public class StartPageStep {
    private final PageManager pageManager = PageManager.getInstance();

    @И("^Проверка открытия 'Главной страницы'$")
    public void checkOpenPage() {
        pageManager.getStartPage().checkOpenPage();
    }

    @И("^Выбор пункта основного меню \"(.+)\"$")
    public void selectBaseMenuByName(String nameMenu) {
        pageManager.getStartPage().selectBaseMenuByName(nameMenu);
    }

    @И("^Выбор пункта подменю \"(.+)\"$")
    public void selectSubMenuByName(String nameMenu) {
        pageManager.getStartPage().selectSubMenuByName(nameMenu);
    }

}
