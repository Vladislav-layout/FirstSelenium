package ru.ibs.framework.managers;

import ru.ibs.framework.utils.PropConst;

import java.util.concurrent.TimeUnit;

import static ru.ibs.framework.utils.PropConst.*;

public class InitManager {
    private static final TestPropManager props = TestPropManager.getInstance();
    private static final DriverManager driverManager = DriverManager.getInstance();

    public static void initFramework() {
        driverManager.getDriver().manage().window().maximize();
        driverManager.getDriver().manage().timeouts().
                implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        driverManager.getDriver().manage().timeouts().
                pageLoadTimeout(Integer.parseInt(props.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
        driverManager.getDriver().get(TestPropManager.getInstance().getProperty(PropConst.BASE_URL));
    }

    public static void quitFramework() {
        driverManager.quitDriver();
    }
}
