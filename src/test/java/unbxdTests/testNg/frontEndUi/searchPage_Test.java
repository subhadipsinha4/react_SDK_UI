package unbxdTests.testNg.frontEndUi;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import core.ui.frontendui.actions.Facet_SiteActions;
import core.ui.frontendui.actions.Search_SiteActions;
import lib.Helper;
import lib.UnbxdFileUtils;
import lib.annotation.FileToTest;
import org.fluentlenium.core.annotation.Page;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import unbxdTests.testNg.ui.BaseTest;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class searchPage_Test extends BaseTest {
    @Page
    Search_SiteActions search_SiteActions;
    static int NoOfProducts;
    public static ArrayList<String> array = new ArrayList<String>();

    @BeforeClass
    public void init() {
        super.setUp();
        initFluent(driver);
        initTest();
    }

    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "Search page functionality test ", dataProvider = "getUrl")
    public void SearchPageActions(String url, String query) throws Exception {
        search_SiteActions.goToWebsite(url);
        maximizeWindow();
        search_SiteActions.searchBoxPresentOrNot();
        search_SiteActions.searchBoxPlaceHolderIsDisplayOrNot();
        search_SiteActions.testEnterButtonInCaseOfSearch(query);
        search_SiteActions.testSearchButtonClickIsWorkingOrNot(query);
        search_SiteActions.checkForSpecialCharactersAreDisplayedFine();
        search_SiteActions.testSpacingBetweenTwoWords();
        search_SiteActions.testSpacingBetweenMoreThanTwoWords();
        search_SiteActions.testAllTextInLowerCase();
        search_SiteActions.testSearchedResultsMessageFormat();
        search_SiteActions.testWhetherEnterIsWorkingForSearchOption();
        search_SiteActions.testCombinationOfLowerCaseAndUpperCaseInSearchedBox();
        search_SiteActions.strickenPriceIsDisplayOrNot();
        search_SiteActions.testGridViewAndListViewArePresentOrNot();
        search_SiteActions.testListViewClick();
        search_SiteActions.testUIofListViewPage();
        search_SiteActions.testGridViewClick();
        search_SiteActions.testUIofGridViewPage();
        search_SiteActions.testFilterSelectionInCaseOfGridViewAndMoveToListViewPage();
        search_SiteActions.testFilterSelectionInCaseOfListViewAndMoveToGridViewPage();
        search_SiteActions.testNumberOfProductsOnGridViewAndListView();
        search_SiteActions.dontEnterQueryClickSearchIcon();
        search_SiteActions.dontEnterQueryPressEnterKey();
        search_SiteActions.testBlankSpaceInSearchedBox();
        search_SiteActions.searchUsingUniqueId();
        //search_SiteActions.testWhetherResultsAreDisplayingAsPerTheSearchedTerm_InTermsOfColor();
        search_SiteActions.testInvalidSearchQuery(search_SiteActions.invalidQuery);
        search_SiteActions.spellErrorMessageIsDisplayOrNot();
        search_SiteActions.singleWordSpellErrorIsWorking();
        search_SiteActions.testWhetherTwoWordsSpellErrorIsWorking();
        search_SiteActions.testWhetherThreeWordsSpellErrorIsWorking();
        search_SiteActions.testDidYouMeanLinkIsWorkingOrNot();
        search_SiteActions.testWhetherAllTheUiElementsAreLoadedOrNot();
        search_SiteActions.checkProductAttributesAreDisplayingOrNot();
        search_SiteActions.testInCaseOfTwoPrices();
        search_SiteActions.testWhetherPriceIsDisplayingInTwoDecimalFormatAllOverTheSite();
        search_SiteActions.checkTotalNumberOfProductsInSearchedResultsPageAndAPI();
        //search_SiteActions.paginationIsDisplayOrNot();
        //search_SiteActions.paginationIsWorkingOrNot();
        //search_SiteActions.forwardArrowKey();
        //search_SiteActions.backArrowKey();
        //search_SiteActions.selectedPageHighlightOrNot();
        //search_SiteActions.numberOfProductPerPage();

        await();

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
