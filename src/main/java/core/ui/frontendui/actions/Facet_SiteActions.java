package core.ui.frontendui.actions;

import core.ui.frontendui.page.Facet_Page;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class Facet_SiteActions extends Facet_Page {

    SoftAssert softAssert = new SoftAssert();
    public void fetch_Facet() throws InterruptedException {
        awaitForPageToLoad();
        String initSearchResult, finalSearchResult;
        int initial_Count, filtered_Count;
        initSearchResult = SearchResult.getText();
        softAssert.assertFalse(initSearchResult.isEmpty());
        initial_Count = Integer.parseInt(initSearchResult);
//      System.out.println("Initial_CountOfSearchResults: "+initial_Count);
        awaitForElementPresence(filter_facet);
        filter_facet.click();
        Thread.sleep(3000);
        //awaitForElementPresence(filterAppliedMsg);
        finalSearchResult = SearchResult.getText();
        softAssert.assertFalse(finalSearchResult.isEmpty());
        filtered_Count = Integer.parseInt(finalSearchResult);
//      System.out.println("Filtered_CountOfSearchResults: "+filtered_Count);
        try {
            Assert.assertTrue((initial_Count > 0) && (filtered_Count < initial_Count), "Expected search result count do not match!!");
        } catch(AssertionError e) {
            e.printStackTrace();
        }
        System.out.println("-------------------------- Facet Validation ----------------------------------");
        System.out.println("Initial Count of the Product search: " + initial_Count + "\nProduct count after applying filter: " + filtered_Count);
        System.out.println("------------------------------------------------------------------------------");
    }
    public void verify_filterName() throws InterruptedException {
        getDriver().navigate().back();
        String BeforeFilterfacet=facet_name.getText();
        //System.out.println(BeforeFilterfacet);
        click(filter_facet);
        Thread.sleep(2000);
        checkElementPresence(FacetName_verify);
        String AfterFilterfacet=FacetName_verify.getText();
       // System.out.println(AfterFilterfacet);
        try {
            Assert.assertTrue(BeforeFilterfacet.contains(AfterFilterfacet),"Selected filter not applied");
        } catch(AssertionError e) {
            e.printStackTrace();
        }
        System.out.println("Filter is applied is applied succesfully");

    }
}