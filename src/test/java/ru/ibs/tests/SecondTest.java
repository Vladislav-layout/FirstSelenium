package ru.ibs.tests;



import org.junit.Test;
import ru.ibs.framework.utils.PropConst;
import ru.ibs.tests.base.BaseTests;


public class SecondTest extends BaseTests {

    @Test
    public void crmTest2() {
        pageManager.getLoginPage()
                .enterLoginAndPass(propManager.getProperty(PropConst.LOGIN_FILIPOVA), propManager.getProperty(PropConst.PASSWORD))
                .submitClick()
                .checkOpenPage()
                .selectBaseMenuByName()
                .selectSubMenuByName()
                .checkOpenPage()
                .clickCreateTripButton()
                .checkOpenPage()
                .clickSubDivision()
                .chooseSubdivisions()
                .organisationOpening()
                .chooseOrganization("(Edge) Призрачная Организация Охотников")
                .checkBoxClick()
                .fillField("Город выбытия", "Барнаул")
                .fillField("Город прибытия", "Москва")
                .fillField("Планируемая дата выезда", "10.10.2023")
                .fillField("Планируемая дата возвращения", "15.10.2023")
                .saveAndClose()
                .checkErrorMessageAtField("Командированные сотрудники",
                "Список командируемых сотрудников не может быть пустым")
                .checkErrorMessageAtField("Внештатные сотрудники",
                "Список командируемых сотрудников не может быть пустым");
    }
}
