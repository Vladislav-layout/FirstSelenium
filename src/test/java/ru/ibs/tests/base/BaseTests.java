package ru.ibs.tests.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.ibs.framework.managers.DriverManager;
import ru.ibs.framework.managers.InitManager;
import ru.ibs.framework.managers.PageManager;
import ru.ibs.framework.managers.TestPropManager;
import ru.ibs.framework.utils.PropConst;

public class BaseTests {
    private final DriverManager driverManager = DriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    protected TestPropManager propManager = TestPropManager.getInstance();

    @BeforeEach
    public void before() {
        InitManager.initFramework();
        driverManager.getDriver().get(propManager.getProperty(PropConst.BASE_URL));
    }

    @AfterEach
    public void after() {
        InitManager.quitFramework();
    }
}
