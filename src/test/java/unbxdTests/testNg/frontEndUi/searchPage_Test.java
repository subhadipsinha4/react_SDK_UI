package unbxdTests.testNg.frontEndUi;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import core.ui.frontendui.actions.Facet_SiteActions;
import core.ui.frontendui.actions.Search_SiteActions;
import core.ui.frontendui.page.Search_Page;
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
        maximizeWindow();
    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "searchBoxPresentOrNot ", dataProvider = "getUrl")
    public void checkSearchBoxPresentOrNot(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.searchBoxPresentOrNot();

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "searchBox PlaceHolderIsDisplayOrNot", dataProvider = "getUrl")
    public void CheckSearchBoxPlaceHolderIsDisplayOrNot(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.searchBoxPlaceHolderIsDisplayOrNot();

    }

    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "test Enter Button In Case Of Search", dataProvider = "getUrl")
    public void checkEnterButtonInCaseOfSearch(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testEnterButtonInCaseOfSearch(query);

    }

    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testSearchButtonClickIsWorkingOrNot", dataProvider = "getUrl")
    public void checkSearchButtonClickIsWorkingOrNot(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testSearchButtonClickIsWorkingOrNot(query);

    }

    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "test ForSpecialCharactersAreDisplayedFine", dataProvider = "getUrl")
    public void checkForSpecialCharactersAreDisplayedFine(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.checkForSpecialCharactersAreDisplayedFine();

    }

    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "test SpacingBetweenTwoWords", dataProvider = "getUrl")
    public void checkSpacingBetweenTwoWords(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testSpacingBetweenTwoWords();

    }

    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testSpacingBetweenMoreThanTwoWords", dataProvider = "getUrl")
    public void checkSpacingBetweenMoreThanTwoWords(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testSpacingBetweenMoreThanTwoWords();

    }

    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testAllTextInLowerCase", dataProvider = "getUrl")
    public void checkAllTextInLowerCase(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testAllTextInLowerCase();

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testSearchedResultsMessageFormat", dataProvider = "getUrl")
    public void checkSearchedResultsMessageFormat(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testSearchedResultsMessageFormat(query);

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testWhetherEnterIsWorkingForSearchOption", dataProvider = "getUrl")
    public void checkWhetherEnterIsWorkingForSearchOption(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testWhetherEnterIsWorkingForSearchOption();

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testCombinationOfLowerCaseAndUpperCaseInSearchedBox", dataProvider = "getUrl")
    public void checkCombinationOfLowerCaseAndUpperCaseInSearchedBox(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testCombinationOfLowerCaseAndUpperCaseInSearchedBox();

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "strickenPriceIsDisplayOrNot", dataProvider = "getUrl")
    public void checkStrickenPriceIsDisplayOrNot(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.strickenPriceIsDisplayOrNot(query);

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "GridViewAndListViewArePresentOrNot", dataProvider = "getUrl")
    public void checkGridViewAndListViewArePresentOrNot(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testGridViewAndListViewArePresentOrNot();

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testListViewClick", dataProvider = "getUrl")
    public void checkListViewClick(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testListViewClick(query);

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testUIofListViewPage", dataProvider = "getUrl")
    public void checkUIofListViewPage(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testUIofListViewPage();

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testGridViewClick", dataProvider = "getUrl")
    public void checkGridViewClick(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testGridViewClick(query);

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testUIofGridViewPage", dataProvider = "getUrl")
    public void checkUIofGridViewPage(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testUIofGridViewPage();

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testFilterSelectionInCaseOfGridViewAndMoveToListViewPage", dataProvider = "getUrl")
    public void testFilterSelectionInCaseOfGridViewAndMoveToListViewPage(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testFilterSelectionInCaseOfGridViewAndMoveToListViewPage(query);

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testFilterSelectionInCaseOfListViewAndMoveToGridViewPage", dataProvider = "getUrl")
    public void testFilterSelectionInCaseOfListViewAndMoveToGridViewPage(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testFilterSelectionInCaseOfListViewAndMoveToGridViewPage(query);

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testNumberOfProductsOnGridViewAndListView", dataProvider = "getUrl")
    public void testNumberOfProductsOnGridViewAndListView(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testNumberOfProductsOnGridViewAndListView(query);

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "dontEnterQueryClickSearchIcon", dataProvider = "getUrl")
    public void dontEnterQueryClickSearchIcon(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.dontEnterQueryClickSearchIcon();

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "dontEnterQueryPressEnterKey", dataProvider = "getUrl")
    public void dontEnterQueryPressEnterKey(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.dontEnterQueryPressEnterKey();

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testBlankSpaceInSearchedBox", dataProvider = "getUrl")
    public void testBlankSpaceInSearchedBox(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testBlankSpaceInSearchedBox();

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "searchUsingUniqueId", dataProvider = "getUrl")
    public void searchUsingUniqueId(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.searchUsingUniqueId();

    }

    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testWhetherResultsAreDisplayingAsPerTheSearchedTerm_InTermsOfColor", dataProvider = "getUrl")
    public void testWhetherResultsAreDisplayingAsPerTheSearchedTerm_InTermsOfColor(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testWhetherResultsAreDisplayingAsPerTheSearchedTerm_InTermsOfColor();

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testInvalidSearchQuery", dataProvider = "getUrl")
    public void testInvalidSearchQuery(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testInvalidSearchQuery(search_SiteActions.invalidQuery);

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "spellErrorMessageIsDisplayOrNot", dataProvider = "getUrl")
    public void spellErrorMessageIsDisplayOrNot(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.spellErrorMessageIsDisplayOrNot();

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "singleWordSpellErrorIsWorking", dataProvider = "getUrl")
    public void singleWordSpellErrorIsWorking(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.singleWordSpellErrorIsWorking();

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testWhetherTwoWordsSpellErrorIsWorking", dataProvider = "getUrl")
    public void testWhetherTwoWordsSpellErrorIsWorking(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testWhetherTwoWordsSpellErrorIsWorking();

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testWhetherThreeWordsSpellErrorIsWorking", dataProvider = "getUrl")
    public void testWhetherThreeWordsSpellErrorIsWorking(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testWhetherThreeWordsSpellErrorIsWorking();

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testDidYouMeanLinkIsWorkingOrNot", dataProvider = "getUrl")
    public void testDidYouMeanLinkIsWorkingOrNot(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testDidYouMeanLinkIsWorkingOrNot();

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testWhetherAllTheUiElementsAreLoadedOrNot", dataProvider = "getUrl")
    public void testWhetherAllTheUiElementsAreLoadedOrNot(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testWhetherAllTheUiElementsAreLoadedOrNot();

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "checkProductAttributesAreDisplayingOrNot", dataProvider = "getUrl")
    public void checkProductAttributesAreDisplayingOrNot(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.checkProductAttributesAreDisplayingOrNot();

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testInCaseOfTwoPrices", dataProvider = "getUrl")
    public void testInCaseOfTwoPrices(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testInCaseOfTwoPrices();

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testWhetherPriceIsDisplayingInTwoDecimalFormatAllOverTheSite", dataProvider = "getUrl")
    public void testWhetherPriceIsDisplayingInTwoDecimalFormatAllOverTheSite(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testWhetherPriceIsDisplayingInTwoDecimalFormatAllOverTheSite();

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "checkTotalNumberOfProductsInSearchedResultsPageAndAPI", dataProvider = "getUrl")
    public void checkTotalNumberOfProductsInSearchedResultsPageAndAPI(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.checkTotalNumberOfProductsInSearchedResultsPageAndAPI();

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testWhetherInfiniteScrollIsWorkingOrNot", dataProvider = "getUrl")
    public void testWhetherInfiniteScrollIsWorkingOrNot(String url, String query) throws Exception {
        url=Search_Page.infiniteScrollURl;
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testWhetherInfiniteScrollIsWorkingOrNot();

    }
    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testDisplayingAllProductsInCaseOfInfiniteScroll", dataProvider = "getUrl")
    public void testDisplayingAllProductsInCaseOfInfiniteScroll(String url, String query) throws Exception {
        url=Search_Page.infiniteScrollURl;
        search_SiteActions.goToWebsite( url );
        search_SiteActions.testDisplayingAllProductsInCaseOfInfiniteScroll();

    }

    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testSearchForSKU_With_Hash_AsPrefix", dataProvider = "getUrl")
    public void testSearchForSKU_With_Hash_AsPrefix(String url, String query) throws Exception {
        search_SiteActions.goToWebsite( url );
        search_SiteActions.checkSearchForSKU_with_Hash_As_Prefix();

    }

    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testQuickViewIsPresentOrNot", dataProvider = "getUrl")
    public void testQuickViewIsPresentOrNot(String url, String query) throws Exception {
        url= Search_Page.urlQuickViewAddToCart;
        search_SiteActions.goToWebsite( url );
        search_SiteActions.checkQuickViewIsPresentOrNot(query);

    }

    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "testAddToCartIsPresentOrNot", dataProvider = "getUrl")
    public void testAddToCartIsPresentOrNot(String url, String query) throws Exception {
        url= Search_Page.urlQuickViewAddToCart;
        search_SiteActions.goToWebsite( url );
        search_SiteActions.checkAddToCartIsPresentOrNot(query);

    }

    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "Test whether color swatches are available or not", dataProvider = "getUrl")
    public void testWhetherColorSwatchesAreAvailableOrNot(String url, String query) throws Exception {
        url= Search_Page.urlColorVar;
        search_SiteActions.goToWebsite( url );
        search_SiteActions.checkColorSwitch(query);

    }

    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
    @Test(description = "Test whether color swatches are working or not", dataProvider = "getUrl")
    public void testWhetherColorSwatchesAreWorkingOrNot(String url, String query) throws Exception {
        url= Search_Page.urlColorVar;
        search_SiteActions.goToWebsite( url );
       // search_SiteActions.checkColorSwitchIsWorkingOrNot(query);

    }
//
//    @FileToTest(value = "/frontEndTestData/SanitySearchUrl.json")
//    @Test(description = "Search page functionality test ", dataProvider = "getUrl")
//    public void SearchPageActions(String url, String query) throws Exception {
//        search_SiteActions.goToWebsite(url);
//        search_SiteActions.searchBoxPresentOrNot();
//        search_SiteActions.searchBoxPlaceHolderIsDisplayOrNot();
//        search_SiteActions.testEnterButtonInCaseOfSearch(query);
//        search_SiteActions.testSearchButtonClickIsWorkingOrNot(query);
//        search_SiteActions.checkForSpecialCharactersAreDisplayedFine();
//        search_SiteActions.testSpacingBetweenTwoWords();
//        search_SiteActions.testSpacingBetweenMoreThanTwoWords();
//        search_SiteActions.testAllTextInLowerCase();
//        search_SiteActions.testSearchedResultsMessageFormat();
//        search_SiteActions.testWhetherEnterIsWorkingForSearchOption();
//        search_SiteActions.testCombinationOfLowerCaseAndUpperCaseInSearchedBox();
//        search_SiteActions.strickenPriceIsDisplayOrNot();
//        search_SiteActions.testGridViewAndListViewArePresentOrNot();
//        search_SiteActions.testListViewClick();
//        search_SiteActions.testUIofListViewPage();
//        search_SiteActions.testGridViewClick();
//        search_SiteActions.testUIofGridViewPage();
//
//        search_SiteActions.testFilterSelectionInCaseOfGridViewAndMoveToListViewPage();
//        search_SiteActions.testFilterSelectionInCaseOfListViewAndMoveToGridViewPage();
//        search_SiteActions.testNumberOfProductsOnGridViewAndListView();
//
//        search_SiteActions.dontEnterQueryClickSearchIcon();
//        search_SiteActions.dontEnterQueryPressEnterKey();
//        search_SiteActions.testBlankSpaceInSearchedBox();
//        search_SiteActions.searchUsingUniqueId();
//
//        search_SiteActions.testWhetherResultsAreDisplayingAsPerTheSearchedTerm_InTermsOfColor();
//        search_SiteActions.testInvalidSearchQuery(search_SiteActions.invalidQuery);
//        search_SiteActions.spellErrorMessageIsDisplayOrNot();
//        search_SiteActions.singleWordSpellErrorIsWorking();
//
//        search_SiteActions.testWhetherTwoWordsSpellErrorIsWorking();
//        search_SiteActions.testWhetherThreeWordsSpellErrorIsWorking();
//        search_SiteActions.testDidYouMeanLinkIsWorkingOrNot();
//        search_SiteActions.testWhetherAllTheUiElementsAreLoadedOrNot();
//        search_SiteActions.checkProductAttributesAreDisplayingOrNot();
//        search_SiteActions.testInCaseOfTwoPrices();
//
//        search_SiteActions.testWhetherPriceIsDisplayingInTwoDecimalFormatAllOverTheSite();
//        search_SiteActions.checkTotalNumberOfProductsInSearchedResultsPageAndAPI();
//        search_SiteActions.testWhetherInfiniteScrollIsWorkingOrNot();
//        search_SiteActions.testDisplayingAllProductsInCaseOfInfiniteScroll();
//
//
//    }

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
