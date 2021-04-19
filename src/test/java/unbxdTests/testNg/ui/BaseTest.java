package unbxdTests.testNg.ui;

import org.fluentlenium.core.FluentAdapter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

import lib.BrowserInitializer;
import lib.EnvironmentConfig;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class BaseTest extends FluentAdapter {


    public  WebDriver driver=null;
    public final String testDataPath="src/test/resources/testData/";




    public void  setUp() {
        try {
            BrowserInitializer initializer = new BrowserInitializer();
            initializer.init();
            driver = initializer.getDriver();
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
