package ru.ibs.framework.managers;

import ru.ibs.framework.pages.AllBusinessTripsPage;
import ru.ibs.framework.pages.CreateTripPage;
import ru.ibs.framework.pages.LoginPage;
import ru.ibs.framework.pages.StartPage;

public class PageManager {

    private static PageManager INSTANCE;
    private LoginPage loginPage;
    private StartPage startPage;
    private AllBusinessTripsPage allBusinessTripsPage;
    private CreateTripPage createTripPage;

    private PageManager() {
    }

    public static PageManager getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PageManager();
        }
        return INSTANCE;
    }

    public LoginPage getLoginPage() {
        if(loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public StartPage getStartPage() {
        if(startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    public AllBusinessTripsPage getAllBusinessTripsPage() {
        if(allBusinessTripsPage == null) {
            allBusinessTripsPage = new AllBusinessTripsPage();
        }
        return allBusinessTripsPage;
    }

    public CreateTripPage getCreateTripPage() {
        if(createTripPage == null) {
            createTripPage = new CreateTripPage();
        }
        return createTripPage;
    }

}
