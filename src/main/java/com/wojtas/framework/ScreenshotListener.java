package com.wojtas.framework;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ScreenshotListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        ITestContext context = getTestContexts().get(0);
        try {
            takeScreenshot(result, context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        ITestContext context = getTestContexts().get(0);
//        try {
//            takeScreeshot(result, context);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
        public void takeScreenshot(ITestResult result, ITestContext context) throws IOException {
            WebDriver driver = (WebDriver) context.getSuite().getAttribute("driver");
            Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
            String path = System.getProperty("user.dir") + "/target/surefire-reports/ScreenShots/";
            Date date = new Date();
            String fileName = result.getTestClass().getRealClass().getSimpleName() + "_" + result.getMethod().getMethodName() + "_" + date.toString().replace(' ', '_').replace(':', '_').replace("_CET", "") + ".jpg";
            new File(path).mkdirs();
            ImageIO.write(screenshot.getImage(), "jpg", new File(path + fileName));
            Reporter.log("<a href='../ScreenShots/" + fileName + "'> <img src='../ScreenShots/" + fileName + "' border='3' height='200' width='200'/> </a>");
        }
    }

