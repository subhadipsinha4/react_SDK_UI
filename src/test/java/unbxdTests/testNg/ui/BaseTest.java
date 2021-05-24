package unbxdTests.testNg.ui;


import core.ui.page.UiBase;
import org.fluentlenium.core.FluentAdapter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;

import lib.BrowserInitializer;
import lib.EnvironmentConfig;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class BaseTest extends FluentAdapter {


    public  WebDriver driver=null;
    //public static WebDriver driver=null;
    public final String testDataPath="src/test/resources/testData/";
    public  static EventFiringWebDriver e_driver;
    public static WebEventListener eventListener;



    public void  setUp() {
        try {
            BrowserInitializer initializer = new BrowserInitializer();
            initializer.init();
            driver = initializer.getDriver();
            e_driver = new EventFiringWebDriver(driver);
            eventListener = new WebEventListener();
            e_driver.register(eventListener);
            driver = e_driver;
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        catch(Exception e)
        {
            System.out.println("Browser Initialization is failed with Exception "+e.getMessage());
        }
    }


    @AfterClass(alwaysRun = true)
    public void removeContextForTest()
    {
        EnvironmentConfig.unSetContext();
    }

}
