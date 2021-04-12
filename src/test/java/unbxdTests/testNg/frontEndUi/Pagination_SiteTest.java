package unbxdTests.testNg.frontEndUi;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import core.ui.frontendui.actions.Pagination_Actions;
import core.ui.frontendui.actions.Sort_Actions;
import lib.Helper;
import lib.UnbxdFileUtils;
import lib.annotation.FileToTest;
import org.fluentlenium.core.annotation.Page;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import unbxdTests.testNg.ui.BaseTest;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Pagination_SiteTest extends BaseTest {

    @Page
    Pagination_Actions pagination_actions;
    static int NoOfProducts;
    public static ArrayList<String> array = new ArrayList<String>();

    @BeforeClass
    public void init() {
        super.setUp();
        initFluent(driver);
        initTest();
    }

    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "Pagination functionality test on search result page", dataProvider = "getUrl")
    public void paginationSearchPageActions(String url, String query) throws Exception {
        pagination_actions.goToWebsite(url);
        maximizeWindow();
        pagination_actions.searchQuery(query);
        pagination_actions.testPaginationIsDisplayingOrNot();
        pagination_actions.testPaginationIsWorkingOrNot();
        pagination_actions.testPaginationBackArrowIsWorking();
        pagination_actions.testPaginationForwardArrowIsWorking();
        pagination_actions.testSelectedPageHighlightOrNot();
        pagination_actions.testPaginationScenarioInCaseOfLastPageResults();
        pagination_actions.testPaginationInCaseOfSort();
        pagination_actions.testPaginationInCaseOfFilter();
        pagination_actions.testNumberOfItemsPerPageOption();
        pagination_actions.testNumberOfItemsPerPageOptionWorkingOrNot();
        pagination_actions.testSelectedPageOptionIsShowingAsHighlighted();
        pagination_actions.testFooterDisplayingPaginationOptionOrNot();
        pagination_actions.testWhetherFooterPaginationIsWorkingOrNot();
        pagination_actions.testCaseOfOnePageResults();



    }

    private final String testFilePath = "target" + File.separator + "test-classes" + File.separator + "testData" + File.separator;

    @DataProvider(name = "getUrl")
    public Object[][] getUrls (Method m){
        Object[][] urls;
        String filePath = (m.getAnnotation(FileToTest.class)).value();
        String fileName = testFilePath + filePath;
        fileName = UnbxdFileUtils.normalizePath(fileName);

        JsonParser parser = new JsonParser();
        int count = 0;
        try {
            List<Object[]> testObjects = new ArrayList<>();
            Object obj = parser.parse(new FileReader(fileName));
            JsonObject object = (JsonObject) obj;
            JsonArray array = object.getAsJsonArray("testData");
            for (int i = 0; i < array.size(); i++){
                String url = array.get(i).getAsJsonObject().get("url").getAsString();
                JsonArray array1 = array.get(i).getAsJsonObject().get("queries").getAsJsonArray();
                for (int j = 0; j < array1.size(); j++) {
                    testObjects.add(new Object[]{url, array1.get(j).getAsString()});
                }
            }
            urls = new Object[testObjects.size()][];
            for (Object testObject : testObjects) {
                Object[] object2 = (Object[]) testObject;
                urls[count] = object2;
                count++;

            }

            return urls;
        }catch (Exception e) {
            e.printStackTrace();

        }
        return new Object[0][0];
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.close();
        driver.quit();
        Helper.tearDown();
    }

}
