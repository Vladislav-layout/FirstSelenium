package ru.ibs.framework.steps;

import io.cucumber.java.ru.И;
import ru.ibs.framework.managers.PageManager;

public class AllBusinessTripPageStep {
    private final PageManager pageManager = PageManager.getInstance();

    @И("^Проверка открытия страницы Командировок$")
    public void checkOpenPage() {
        pageManager.getAllBusinessTripsPage().checkOpenPage();
    }

    @И("^Клик по кнопке 'Создать командировку'$")
    public void clickCreateTripButton() {
        pageManager.getAllBusinessTripsPage().clickCreateTripButton();
    }

}
