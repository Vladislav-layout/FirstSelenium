package ru.ibs.framework.steps;

import io.cucumber.java.bg.И;
import ru.ibs.framework.managers.PageManager;

public class StartPageStep {
    private final PageManager pageManager = PageManager.getInstance();

    @И("^Проверка открытия 'Главной страницы'$")
    public void checkOpenPage() {
        pageManager.getStartPage().checkOpenPage();
    }

    @И("^Выбор пункта основного меню 'Расходы'$")
    public void selectBaseMenuByName() {
        pageManager.getStartPage().selectBaseMenuByName();
    }

    @И("^Выбор пункта подменю 'Командировки'$")
    public void selectSubMenuByName() {
        pageManager.getStartPage().selectSubMenuByName();
    }

}
