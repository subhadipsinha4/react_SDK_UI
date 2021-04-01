package core.ui.frontendui.actions;
import core.ui.frontendui.page.Search_Page;
import freemarker.cache.WebappTemplateLoader;
import io.restassured.response.Response;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.yaml.snakeyaml.tokens.Token.ID.Key;

public class Search_SiteActions extends Search_Page {

    SoftAssert softAssert = new SoftAssert();

    public Search_SiteActions goToWebsite(String url) {
        getDriver().navigate().to(url);
        awaitForPageToLoad();
        //getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return this;
    }

    public void searchBoxPresentOrNot()
    {
        await();
        softAssert.assertEquals(awaitForElementPresence(searchBox),"true");
        System.out.println("Test Case 1: Pass: search Box is Present");
        await();
    }

    public void searchAction(String query) throws InterruptedException {
        await();
        searchBox.clear();
        searchBox.fill().with(query);
        searchBoxClick.click();
        Thread.sleep(3000);
    }

//    public void searchQuery(String query, FluentWebElement searchBox) throws InterruptedException {
//        await();
//        Thread.sleep(6000);
//        awaitForPageToLoad();
//        awaitForElementPresence(searchBox);
//        click(searchBox);
//        searchBox.clear();
//        searchBox.fill().with(query);
//        searchBox.submit();
//        await();
//        softAssert.assertEquals(searchResultQuery.getText(),query,"Search button click is not working");
//        System.out.println("Pass: Search button click is working");
//    }

    public void testEnterButtonInCaseOfSearch(String query) throws InterruptedException {
        Thread.sleep(3000);
        awaitForPageToLoad();
        searchBox.clear();
        getDriver().findElement(By.xpath(UnbxdsearchBox)).sendKeys(query,Keys.ENTER);
        await();
        awaitForPageToLoad();
        softAssert.assertEquals(searchResultQuery.getText(),query);
        System.out.println("Test Case 3: Pass: Search query is getting display for enter query.");


    }

    public  void testSearchedResultsMessageFormat(){
        await();
        softAssert.assertEquals(searchResultFormat.getText(),"Results for -");
        if(searchResultFormat.getText().equals("Results for -")) {
            System.out.println("Test Case 5: Pass: Searched Results Message Format is correct");
        }else{ System.out.println("Test Case 5: Fail: Searched Results Message Format is not correct");
        }

    }
    public void testInvalidSearchQuery(String invQuery) throws InterruptedException {
        await();
        searchBox.clear();
        searchBox.fill().with(invQuery);
        searchBoxClick.click();
        Thread.sleep(2500);
        softAssert.assertEquals(awaitForElementPresence(noResultFound),"true","Zero Result page is getting displayed");
        if(awaitForElementPresence(noResultFound)) {
            System.out.println("Test Case 19: Pass: Zero result page is getting displayed");
        }else{            System.out.println("Test Case 19: Fail: Zero result page is not getting displayed");
        }



    }

    public void dontEnterQueryClickSearchIcon() throws InterruptedException {
        await();
        searchBox.clear();
        String oldURl= getDriver().getCurrentUrl();
        searchBoxClick.click();
        Thread.sleep(3000);
        String newUrl= getDriver().getCurrentUrl();
        softAssert.assertEquals(oldURl,newUrl,"Fail: Search is working while search box is empty");
        if(oldURl.equals(newUrl)) {
            System.out.println("Test Case 15: Pass: don't Enter Query Click SearchIcon");
        }else{System.out.println("Test Case 15: Fail: don't Enter Query Click SearchIcon, but search is working.");}

    }
    public void dontEnterQueryPressEnterKey() {
        await();
        searchBox.clear();
        String oldURl= getDriver().getCurrentUrl();
        getDriver().findElement(By.xpath(UnbxdsearchBox)).sendKeys(Keys.ENTER);
        String newUrl= getDriver().getCurrentUrl();
        softAssert.assertEquals(oldURl,newUrl);
        if(oldURl.equals(newUrl)) {
            System.out.println("Test Case 16: Pass: don't Enter Query press Enter key");
        }else{            System.out.println("Test Case 16: Fail: don't Enter Query press Enter key,but search is working");
        }

    }
    public void testSearchButtonClickIsWorkingOrNot(String query) throws InterruptedException {
        await();
        searchBox.clear();
        searchBox.fill().with(query);
        searchBoxClick.click();
        Thread.sleep(2000);
        softAssert.assertEquals(searchResultQuery.getText(),query,"Search icon click is not working");
        if(searchResultQuery.getText().equals(query)){System.out.println("Test Case 4: Pass: Search icon click is working");}
        else{System.out.println("Test Case 4: Fail: Search icon click is not working");}


    }



    public void testGridViewAndListViewArePresentOrNot() {
        await();
        softAssert.assertEquals(awaitForElementPresence(listViewClick),"true","List view is not getting displayed.");
        if(awaitForElementPresence(listViewClick)){System.out.println("Test Case 7: Pass: List View is getting displayed");}
        else{System.out.println("Test Case 7: Fail: List View is getting displayed");}
        awaitForPageToLoad();
        softAssert.assertEquals(awaitForElementPresence(gridViewClick),"true","Grid view is not getting displayed.");
        if(awaitForElementPresence(gridViewClick)){System.out.println("Test Case 7: Pass: Grid View is getting displayed");}
        else{System.out.println("Test Case 7: Fail: Grid View is getting displayed");}

    }
    public void testListViewClick() {
        await();
        listViewClick.click();
        awaitForPageToLoad();
        softAssert.assertEquals(listViewClick.getAttribute(viewValidate),ClickViewExpected,"List view click is not working.");
        if(listViewClick.getAttribute(viewValidate).equals(ClickViewExpected)){System.out.println("Test Case 8: Pass: list view click is working.");}
        else{System.out.println("Test Case 8: Fail: list view click is working.");}


    }

    public void testUIofListViewPage() throws InterruptedException {
        System.out.println("--------------------------------------------------");
        System.out.println("Test whether all the Ui elements of List view are loaded or not");
        System.out.println("--------------------------------------------------");
        checkUnbxdAttribute();
        System.out.println("--------------------------------------------------");
    }

    public void testGridViewClick() {
        await();
        gridViewClick.click();
        awaitForPageToLoad();
        softAssert.assertEquals(gridViewClick.getAttribute(viewValidate),ClickViewExpected,"Grid view click is not working.");
        if(gridViewClick.getAttribute(viewValidate).equals(ClickViewExpected))System.out.println("Test Case 9: Pass: grid view click is working.");
        else System.out.println("Test Case 9: Fail: grid view click is working.");


    }

    public void testUIofGridViewPage() throws InterruptedException {
        System.out.println("--------------------------------------------------");
        System.out.println("Test whether all the Ui elements of Grid view are loaded or not");
        System.out.println("--------------------------------------------------");
        checkUnbxdAttribute();
        System.out.println("--------------------------------------------------");
    }

    public void testNumberOfProductsOnGridViewAndListView() throws InterruptedException {
        int gcount=0,lcount=0;
        await();
        List<WebElement> title1=getDriver().findElements(By.xpath(productsPerPageCount));
        for(WebElement e: title1)
            gcount++;
        gridViewClick.click();
        Thread.sleep(2500);
        List<WebElement> title2=getDriver().findElements(By.xpath(productsPerPageCount));
        for(WebElement e: title2)
            lcount++;
        softAssert.assertEquals(gcount,lcount,"Number of product per page in grid view is not same compare to list view");
        if(gcount==lcount){System.out.println("Test Case 14: Pass: Equal number of products present on gridView And listView ");}
        else{System.out.println("Test Case 14: Fail: Equal number of products is not present on gridView And listView ");}


    }

    public void testFilterSelectionInCaseOfGridViewAndMoveToListViewPage() throws InterruptedException {
        await();
        colorFacet.click();Thread.sleep(2000);
        String productdescriptionGrid=productDescription.getText();
        listViewClick.click(); Thread.sleep(2000);
        String productdescriptionList=productDescription.getText();
        softAssert.assertEquals(productdescriptionGrid,productdescriptionList,"Fail: Same set of product is not getting displayed after move to list view.");
        if(productdescriptionGrid.equals(productdescriptionList)) {
            System.out.println("Test Case 12: Pass: Same set of product is getting displayed after move to the list view.");
        }else{System.out.println("Test Case 12: Fail: Same set of product is not getting displayed after move to the list view.");
        }
        clearButton.click();


    }

    public void testFilterSelectionInCaseOfListViewAndMoveToGridViewPage() throws InterruptedException {
        await();
        colorFacet.click();
        Thread.sleep(2000);
        String productdescriptionList=productDescription.getText();
        gridViewClick.click();
        Thread.sleep(2000);
        String productdescriptionGrid=productDescription.getText();
        softAssert.assertEquals(productdescriptionGrid,productdescriptionList,"Fail: Same set of product is not getting displayed after move to grid view.");
        if(productdescriptionGrid.equals(productdescriptionList)) {
            System.out.println("Test Case 13: Pass: Same set of product is getting displayed after move to the grid view.");
        }else{ System.out.println("Test Case 13: Fail: Same set of product is not getting displayed after move to the grid view.");
        }
        clearButton.click();


    }

    public void testSpacingBetweenMoreThanTwoWords() throws InterruptedException {
        await();
        searchBox.clear();
        searchBox.fill().with(threeWordQuery);
        searchBoxClick.click();
        Thread.sleep(3000);
//        softAssert.assertEquals(searchResultQuery.getText(),twoWordQuery);
        if(searchResultQuery.getText().equalsIgnoreCase(threeWordQuery)){
            System.out.println("Pass: Spacing between more than three word is working as expecting on SRP");
        }else{
            System.out.println("Fail: Spacing between more than three word is working as expecting on SRP");
        }



    }

    public void testSpacingBetweenTwoWords() throws InterruptedException {
        await();
        searchBox.clear();
        getDriver().findElement(By.xpath(UnbxdsearchBox)).sendKeys(twoWordQuery+Keys.ENTER);
        Thread.sleep(3000);
        //softAssert.assertTrue(searchResultQuery.getText().equals(twoWordQuery));
        if(searchResultQuery.getText().equals(twoWordQuery)){
        System.out.println("Pass: Spacing between two word is working as expecting on SRP");}
        else {         System.out.println("Fail: Spacing between two word is working as expecting on SRP");
    }
    }

    public void strickenPriceIsDisplayOrNot()
    {
        await();
        softAssert.assertEquals(awaitForElementPresence(strickenPrice),"true","Stricken price is not getting displayed");
        if(awaitForElementPresence(strickenPrice)) {
            System.out.println("Test Case 6: Pass: Stricken Price is getting display");
        }else{            System.out.println("Test Case 6: Fail: Stricken Price is getting display");
        }

    }
    public void testBlankSpaceInSearchedBox() throws InterruptedException {
        await();
        searchBox.clear();
        String oldUrl=getDriver().getCurrentUrl();
        searchBox.fill().with(blankSpace);
        searchBox.click();
        Thread.sleep(3000);
        String newUrl=getDriver().getCurrentUrl();
        softAssert.assertEquals(oldUrl,newUrl,"Fail: Search is working for blank space.");
        if(oldUrl.equals(newUrl)) {
            System.out.println("Test Case 17: Pass: For blank space search is not working.");
        }else{            System.out.println("Test Case 17: Fail: For blank space search is working, it should not work");
        }

    }


    public void testPriceIsDisplayingTwoDecimalFormatOverTheSite()
    {
        await();

    }

    public void searchUsingUniqueId() throws InterruptedException {
        await();
        searchBox.clear();
        searchBox.fill().with(uniqueId);
        searchBoxClick.click();
        Thread.sleep(2500);
        softAssert.assertEquals(uniqueIDPath.getAttribute("data-uniqueid"),uniqueId);
        if(uniqueIDPath.getAttribute("data-uniqueid").equals(uniqueId)) {
            System.out.println("Test Case 18: Pass: Search using uniqueId is working fine");
        }else{ System.out.println("Test Case 18: Fail: Search using uniqueId is not working fine");
        }searchBox.clear();


    }

    public void checkForSpecialCharactersAreDisplayedFine() throws InterruptedException {
        await();
        searchBox.clear();
        searchBox.fill().with(specialCharacterQuery);
        searchBoxClick.click();
        Thread.sleep(3000);
        //softAssert.assertEquals(searchResultQuery.getText(),specialCharacterQuery,"Search using special characcter is not working.");
        if(searchResultQuery.getText().equals(specialCharacterQuery)) {
            System.out.println("Pass: Search using special characters is working as expected.");
        }else{            System.out.println("Fail: Search using special characters is working as expected.");
        }
        }

    public void testWhetherEnterIsWorkingForSearchOption() throws InterruptedException {
        await();
        searchBox.clear();
        getDriver().findElement(By.xpath(UnbxdsearchBox)).sendKeys(unbxdSearchQuery+Keys.ENTER);
        Thread.sleep(3000);
        softAssert.assertEquals(searchResultQuery.getText(),unbxdSearchQuery,"Enter Query and search result query is not same");
        if(searchResultQuery.getText().equals(unbxdSearchQuery)) {
            System.out.println("Pass: Search using enter key is working as expected,entered query is getting displayed on search result.");
        }else{         System.out.println("Fail: Search using enter key is working as expected,entered query is getting displayed on search result.");
        }

    }

    public void testCombinationOfLowerCaseAndUpperCaseInSearchedBox() throws InterruptedException {
        await();
        searchBox.clear();
        getDriver().findElement(By.xpath(UnbxdsearchBox)).sendKeys(combinationQuery+Keys.ENTER);
        Thread.sleep(3000);
        softAssert.assertEquals(searchResultQuery.getText(),combinationQuery,"Combination of lower case and upper case query search is not working.");
        if(searchResultQuery.getText().equals(combinationQuery)){System.out.println("Pass: Combination of lower and upper case query search is working as expected.");}
        else{System.out.println("Fail: Combination of lower and upper case query search is not working as expected.");}
    }

    public void testAllTextInLowerCase() throws Exception {
        await();
        searchBox.clear();
        searchBox.fill().with(lowerCaseQuery);
        searchBoxClick.click();
        Thread.sleep(3000);
            //softAssert.assertTrue(searchResultQuery.getText().equals("ShiRT"));
        if(searchResultQuery.getText().equals(lowerCaseQuery)){            System.out.println("Pass: Search is lower case test is working as expected.");
            }else {System.out.println("Fail: Search is lower case test is working as expected.");}

    }

    public void spellErrorMessageIsDisplayOrNot() throws InterruptedException {
        await();
        searchBox.clear();
        searchBox.fill().with(spellMistakeSingleWorld);
        searchBoxClick.click();
        Thread.sleep(3000);
        softAssert.assertEquals(awaitForElementPresence(didYouMean),"true","Spell error message is not getting displayed");
        if(awaitForElementPresence(didYouMean)){System.out.println("Test Case 20: Pass: Spell error message (did you mean) is getting displayed.");}
        else{System.out.println("Test Case 20: Fail: Spell error message (did you mean) is not getting displayed.");}

    }

    public void singleWordSpellErrorIsWorking()
    {
        await();
        softAssert.assertEquals(spellCorrectWord.getText(),spellCorrectSingleWord,"Spell correct for single word is not working well");
        if(spellCorrectWord.getText().equals(spellCorrectSingleWord)){System.out.println("Test Case 21: Pass: Spell correct for single word is working well");}
        else{System.out.println("Test Case 21: Fail: Spell correct for single word is not working well");}


    }

    public void testDidYouMeanLinkIsWorkingOrNot() throws InterruptedException {
        await();
        spellCorrectWord.click();
        Thread.sleep(3000);
        softAssert.assertEquals(searchResultQuery.getText(),threeWordQuery,"Did you mean click is not working");
        if(searchResultQuery.getText().equals(threeWordQuery)){ System.out.println("Test Case 22: Pass: Did you mean link is working");}
        else{System.out.println("Test Case 22: Fail: Did you mean link is not working");}


    }

    public void testWhetherTwoWordsSpellErrorIsWorking() throws InterruptedException {
        await();
        searchBox.clear();
        searchBox.fill().with(twoWordSpellMistake);
        searchBoxClick.click();
        Thread.sleep(3000);
        softAssert.assertEquals(spellCorrectWord.getText(),twoWordQuery,"Two word spell check error is not working.");
        if(spellCorrectWord.getText().equals(twoWordQuery)){System.out.println("Pass: Two words spell error is working as expected.");}
        else{ System.out.println("Fail: Two words spell error is not working as expected.");
        }
    }

    public void testWhetherThreeWordsSpellErrorIsWorking() throws InterruptedException {
        await();
        searchBox.clear();
        searchBox.fill().with(threeWordSpellMistake);
        searchBoxClick.click();
        Thread.sleep(2000);
        softAssert.assertEquals(spellCorrectWord.getText(),threeWordQuery,"Three word spell check error is not working.");
        if(spellCorrectWord.getText().equals(threeWordQuery)){System.out.println("Pass: Three words spell error is working as expected.");}
        else{ System.out.println("Fail: Three words spell error is not working as expected.");}
    }

    public void testWhetherAllTheUiElementsAreLoadedOrNot() throws InterruptedException {
        System.out.println("--------------------------------------------------");
        System.out.println("Test whether all the Ui elements are loaded or not");
        System.out.println("--------------------------------------------------");
        checkUnbxdAttribute();
        System.out.println("----------------------------------------------------");
    }

    private void checkUnbxdAttribute() throws InterruptedException {
        searchAction(lowerCaseQuery);
        //softAssert.assertTrue(searchResultQuery.isDisplayed(),"Search result query is not getting displayed.");
        if(searchResultQuery.isDisplayed()){System.out.println("Pass: Search result query is getting displayed on SRP.");}
        else{System.out.println("Fail: Search result query is not getting displayed on SRP.");}
        //softAssert.assertTrue(productDescription.isDisplayed(),"Search result description is not getting displayed");
        if(productDescription.isDisplayed()){System.out.println("Pass: Search result description is getting displayed on SRP.");}
        else{System.out.println("Fail: Search result description is not getting displayed on SRP.");}
        if(listViewClick.isDisplayed()){System.out.println("Pass: List view is getting displayed on SRP.");}
        else{System.out.println("Fail: List view is not getting displayed on SRP.");}
        //softAssert.assertTrue(listViewClick.isDisplayed(),"List view is not getting displayed.");
        if(gridViewClick.isDisplayed()){System.out.println("Pass: Grid view is getting displayed on SRP.");}
        else{System.out.println("Fail: Grid view is not getting displayed on SRP.");}
        //softAssert.assertTrue(gridViewClick.isDisplayed(),"Grid view is not getting displayed.");
        if(pageSizeContainer.isDisplayed()){System.out.println("Pass: Pagination is getting displayed on SRP.");}
        else{System.out.println("Fail: Pagination is not getting displayed on SRP.");}
        //softAssert.assertTrue(paginationDisplayOrNot.isDisplayed(),"Pagination is not getting displayed");
        if(pageSizeContainer.isDisplayed()){System.out.println("Pass: Page size dropdown is getting displayed on SRP.");}
        else{System.out.println("Fail: Page size dropdown is not getting displayed on SRP.");}
        //softAssert.assertTrue(pageSizeContainer.isDisplayed(),"Page size dropdown is not getting displayed");
        if(sortByContainer.isDisplayed()){System.out.println("Pass: Sort By dropdown is getting displayed on SRP.");}
        else{System.out.println("Fail: Sort By dropdown is not getting displayed on SRP.");}
        //softAssert.assertTrue(sortByContainer.isDisplayed(),"Sort By dropdown is not getting displayed.");
        if(facetContainer.isDisplayed()){System.out.println("Pass: Facet container is getting displayed on SRP.");}
        else{System.out.println("Fail: Facet container is not getting displayed on SRP.");}
        //softAssert.assertTrue(facetContainer.isDisplayed(),"Facet container is not getting displayed");
        if(productContainer.isDisplayed()){System.out.println("Pass: Product list is getting displayed on SRP.");}
        else{System.out.println("Fail: Product list is not getting displayed on SRP.");}
        //softAssert.assertTrue(productContainer.isDisplayed(),"Product list is not getting displayed");
    }

    public void checkProductAttributesAreDisplayingOrNot() {
        int numberProductCount = 0, i = 1, p = 1, t = 1;
        System.out.println("---------------------------------------------------");
        System.out.println("Test each product attributes are displaying or not");
        System.out.println("---------------------------------------------------");
        List<WebElement> allProductImage = getDriver().findElements(By.xpath(UnbxdProductImage));
        for (WebElement image : allProductImage)
            if (image.isDisplayed() == false) {
                System.out.println("Product Image is not getting displayed for uniqueId: " + image.getAttribute("data-uniqueid"));
                break;
            } else {
                i = 0;
            }

        List<WebElement> allProductTitle = getDriver().findElements(By.xpath(UnbxdProductTitle));
        for (WebElement title : allProductTitle)
            if (title.isDisplayed() == false) {
                System.out.println("Product Title is not getting displayed for uniqueId: " + title.getAttribute("data-uniqueid"));
                break;
            } else {
                t = 0;
            }
        List<WebElement> allProductPrice = getDriver().findElements(By.xpath(UnbxdProductPrice));
        for (WebElement price : allProductPrice)
            if (price.isDisplayed() == false){
                System.out.println("Product price is not getting displayed for uniqueId: " + price.getAttribute("data-uniqueid"));
                break;
            }   else {  p=0;}

        if(i==0&&p==0&&t==0)
            System.out.println("Pass: All product attribute is getting displayed");
        else
            System.out.println("Fail: All product attribute is not getting displayed");
        System.out.println("---------------------------------------------------");

    }

    public void testInCaseOfTwoPrices() throws InterruptedException {
        //System.out.println("---------------------------------------------------");
        //System.out.println("Test in case of two prices");
        //System.out.println("---------------------------------------------------");
        searchAction(lowerCaseQuery);
        List<WebElement> allProductStrickenPrice=getDriver().findElements(By.xpath(UnbxdStrickenPrice));
        List<WebElement> allProductPrice=getDriver().findElements(By.xpath(UnbxdProductPrice));
//        for (int i=0;i<allProductStrickenPrice.size();i++)
//            System.out.println("Stricken price is present for product uniqueId: "+allProductPrice.get(i).getAttribute("data-uniqueid"));
//      softAssert.assertEquals(allProductPrice.size(),allProductStrickenPrice.size());
        if(allProductPrice.size()==allProductStrickenPrice.size())
            System.out.println("Pass: For all product stricken price is present");
        else
            System.out.println("Total number of product present on SRP: "+allProductPrice.size()+" and stricken price is present for product is:"+ allProductStrickenPrice.size());

    }

    public void testWhetherPriceIsDisplayingInTwoDecimalFormatAllOverTheSite(){
      //  System.out.println("Test whether Price is displaying in two decimal format all over the site");
        List<WebElement> allPrice=getDriver().findElements(By.xpath(productsPrice));
        int f=1;
        for(WebElement e: allPrice) {
            if (e.getText().contains(".")) {
                int p=e.getText().length()-1;
                int q=e.getText().lastIndexOf(".");
                if(p-q==2) f = 0;
            } else {
                f = 1;break;            }
        }
        if(f==1)
            System.out.println("Fail: Price is not displaying in two decimal format all over the site");
        else
            System.out.println("Pass: Price is displaying in two decimal format all over the site");
    }

    public void testWhetherResultsAreDisplayingAsPerTheSearchedTerm_InTermsOfColor() throws InterruptedException {
        int f=1;
        searchAction(unbxdColorQuerySearch);
        List<WebElement>allProductsTitle=getDriver().findElements(By.xpath(unbxdProductsTitle));
        for(WebElement e: allProductsTitle)
        {
            if(e.getText().equalsIgnoreCase("red")==true) f=0;
            else{f=1;break;}
        }
        if(f==0)System.out.println("Pass: Search terms is getting displayed on product title");
        else System.out.println("Fail: Search terms is not getting displayed on product title ");

    }

    public void  checkTotalNumberOfProductsInSearchedResultsPageAndAPI() throws InterruptedException {
        Response resp;
        resp=given().get(baseURL);
        String productCount=resp.jsonPath().getString("response.numberOfProducts");
        System.out.println("Total number of products in API: "+productCount);
        searchAction("red");
        String result=productDescription.getText();
        String[] totalSrcResult=result.split("of");
        if(totalSrcResult[1].replace("products","").replace(" ","").equals(productCount))
        {
            System.out.println("Pass: Total number Of products in searched results page and API is same");
        }else{
            System.out.println("Pass: Total number Of products in searched results page and API is not same");
        }

    }
    public void testForSKUids()
    {
        await();

    }
//    public void paginationIsDisplayOrNot()
//    {
//        Assert.assertEquals(pagination.isDisplayed(),true,"Fail: Pagination is not getting displayed");
//        System.out.println("Pass: Pagination is getting displayed");
//    }
//
//    public void paginationIsWorkingOrNot()
//    {
//        int cPage=Integer.parseInt(currentPage.getText());
//        goTo2ndPage.click();
//        await();
//    try{
//            Assert.assertEquals(Integer.parseInt(currentPage.getText()),cPage+1,"Fail: Pagination is not working");
//            System.out.println("Pass: Pagination is working");
//        } catch(Exception e) {
//            e.getMessage();
//        }
//
//    }
//
//    public void forwardArrowKey() throws InterruptedException {
//        int oldPageNo=Integer.parseInt(currentPage.getText());
//        forwardArrow.click();
//        Thread.sleep(2000);
//        Assert.assertEquals(Integer.parseInt(currentPage.getText()),oldPageNo+1,"Forward Arrow key is working");
//        System.out.println("Pass: Pagination forward arrow key is working.");
//
//    }
//
//    public void backArrowKey() throws InterruptedException {
//        int oldPageNo=Integer.parseInt(currentPage.getText());
//        click(backwardArrow);
//        Thread.sleep(2000);
//        Assert.assertEquals(Integer.parseInt(currentPage.getText()),oldPageNo-1,"Pagination back arrow key is not working.");
//        System.out.println("Pass: Pagination Back arrow key is working.");
//    }
//
//
//    public void selectedPageHighlightOrNot()
//    {
//        int oldPageNo=Integer.parseInt(currentPage.getText());
//        click(forwardArrow);
//        await();
//        int newPageNo=Integer.parseInt(currentPage.getText());
//        Assert.assertEquals(newPageNo,oldPageNo+1,"Fail: Selected page is not getting highlighted");
//        System.out.println("Pass: Selected page is highlighted.");
//
//    }
//    public void testSortIsWorkingORNot() throws InterruptedException {
//        Select sortList= new Select(getDriver().findElement(By.xpath(sortByDropdown)));
//        selectSort(sortList,"min_price|asc");
//        selectSort(sortList,"min_price|desc");
//    }

    public void selectSort(Select sortList, String sortValue) throws InterruptedException {
        int count=0;
        sortList.selectByValue(sortValue);
        Thread.sleep(4000);
        List<WebElement> title=getDriver().findElements(By.xpath(productTitle));
        for(WebElement e: title)
            System.out.println(e.getText());
//        if(sortValue.equals("min_price|asc"))
//        {
//
//        }

    }

    public void productPerPageworkingOrNot() throws Exception {
        Select noOfPro=new Select(getDriver().findElement(By.xpath(perPageProduct)));
        for(int i=1;i<=4;i++) {
            String s= String.valueOf(i*5);
            pageSize(noOfPro,s, 1);
        }
    }

    public void pageSize(Select noOfPro, String s,int flag) throws Exception {
        int count=0;
        noOfPro.selectByValue(s);
        Thread.sleep(5000);
        List<WebElement> titles=getDriver().findElements(By.xpath(productsPerPageCount));
        for(WebElement e: titles)
            count++;
        Assert.assertEquals(Integer.parseInt(s),count,"Fail: Number of product per page is not same.");
        System.out.println("Pass: Selected product per page is "+count+" and Number of product per page is same as per selected");
        }

    public void searchBoxPlaceHolderIsDisplayOrNot() {
        await();
        awaitForPageToLoad();
        softAssert.assertEquals(awaitForElementPresence(placeHolder),"true");
        System.out.println("Test Case 2: Pass: searchBox PlaceHolder is getting Display");
    }


}



//    public void Fetch_Result(String pUrl) {
//        String searchMsg, initSearchResult;
//        try {
//            awaitForElementPresence(SearchMsg);
//            searchMsg = SearchMsg.getText();
//            System.out.println(pUrl);
//            initSearchResult = SearchResult.getText();
//            Assert.assertTrue(awaitForElementPresence(SearchResult));
//            int ActualSearchResult = Integer.parseInt(initSearchResult);
//            if (ActualSearchResult > 0) {
//                System.out.println("\n" + searchMsg);
//                System.out.println("We found " + ActualSearchResult);
//            } else {
//                System.out.println("\nExpected search result not displayed");
//                Assert.fail("Expected search result is not displayed!");
//            }
//        } catch (Exception e) {
//            String InvalidSearch = NoSearchMsg.getText();
//            System.out.println("InvalidSearch : " + InvalidSearch + "\n");
//
//        }
//    }
//
//    public void HandleRandomPopup(String pUrl){
//        if(pUrl.contains("https://www.Boscovs.com/")){
//            closePopUp();
//        }
//    }
//    public void closePopUp() {
//        try {
//            if (monetateEmailPopup.isDisplayed()) {
//                monetateEmailPopup.click();
//            }
//        } catch (Exception e) {
//        }
//    }



    //##### END CODE #####
