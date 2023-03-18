package ru.ibs.framework.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import ru.ibs.framework.managers.PageManager;

public class CreateTripPageStep {

    private final PageManager pageManager = PageManager.getInstance();

    @И("^Проверка открытия страницы создания командировки$")
    public void checkOpenPage() {
        pageManager.getCreateTripPage().checkOpenPage();
    }

    @И("^Клик по полю 'Подразделение'$")
    public void clickSubDivision() {
        pageManager.getCreateTripPage().clickSubDivision();
    }

    @И("Выбор подразделения 'Отдел внутренней разработки' из выпадающего списка")
    public void chooseSubdivisions() {
        pageManager.getCreateTripPage().chooseSubdivisions();
    }

    @И("^Клик по ссылке 'Открыть список' для поля 'Выбрать организацию из списка'$")
    public void organisationOpening() {
        pageManager.getCreateTripPage().organisationOpening();
    }

    @И("^Выбор организации \"(.+)\" из выпадающего списка$")
    public void chooseOrganization(String organisations) {
        pageManager.getCreateTripPage().chooseOrganization(organisations);
    }

    @И("^Активация чекбоса 'Заказ билетов'$")
    public void checkBoxClick() {
        pageManager.getCreateTripPage().checkBoxClick();
    }

    @И("^Заполняем поля:$")
    public void fillField(DataTable mapFieldsAndValue) {
        mapFieldsAndValue.asMap(String.class, String.class).forEach((key, value) ->
                pageManager.getCreateTripPage().fillField((String) key, (String) value));
    }

    @И("Клик по кнопке 'Сохранить и закрыть'")
    public void saveAndClose() {
        pageManager.getCreateTripPage().saveAndClose();
    }

    @И("^Проверяем сообщения под полями формы:$")
    public void checkErrorMessageAtField(DataTable mapFieldsAndValue) {
        mapFieldsAndValue.asMap(String.class, String.class).forEach((key, value) ->
                pageManager.getCreateTripPage().checkErrorMessageAtField((String) key, (String) value));
    }

}
