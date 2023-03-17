package ru.ibs.framework.utils;

import io.qameta.allure.Attachment;
import io.qameta.allure.junit4.AllureJunit4;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.ibs.framework.managers.DriverManager;

public class MyAllureListener extends AllureJunit4 {

    @Override
    public void testFailure(final Failure failure) {
       getScreenshot();
        super.testFailure(failure);
    }
    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] getScreenshot() {
        return ((TakesScreenshot) DriverManager.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
