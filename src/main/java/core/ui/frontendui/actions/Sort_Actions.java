package core.ui.frontendui.actions;

import core.ui.frontendui.page.Sort_Page;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Sort_Actions extends Sort_Page {
    SoftAssert softAssert = new SoftAssert();
    //public Select sortList= new Select(getDriver().findElement(By.xpath(sortByDropdown)));
    //Select sortList;


    public Sort_Actions goToWebsite(String url) {
        getDriver().navigate().to(url);
        awaitForPageToLoad();
        return this;
    }
    public void searchQuery(String query) throws InterruptedException {
        await();
        awaitForPageToLoad();
        awaitForElementPresence(searchBox);
        click(searchBox);
        searchBox.clear();
        searchBox.fill().with(query);
        searchBox.submit();
        await();
    }

    public void testCheckWhetherSortByIsThereOrNot(){
        await();
        softAssert.assertEquals(awaitForElementPresence(sortOptions),"true","Sort By options is not present on SRP");
        System.out.println("Pass: Sort by options is present on SRP");
    }

    public void testSortByIsSelectedAsSORTByRelevancy()
    {
        await();
        softAssert.assertEquals(sortOptions.getText(),"Most Relevant","Default sort by option is not 'Most Relevant'");
        System.out.println("Pass: Default sort by option is selected 'Most Relevant'");
    }

    public void testWhetherSortByIsWorkingOrNot() throws InterruptedException {
        int response=0;
        await();
        Select perPageP = new Select(getDriver().findElement(By.xpath(UnbxdProductPerPageDropDown)));
        perPageP.selectByValue("5");
        Thread.sleep(3000);
        Select sortList = new Select(getDriver().findElement(By.xpath(sortByDropdown)));
        response=selectSort(sortList,"min_price|asc");
        if(response==1) System.out.println("Pass: Sort By: Lowest price is working as expected.");
        response=selectSort(sortList,"min_price|desc");
        if(response==1) System.out.println("Pass: Sort By: Highest price is working as expected.");
        selectSort(sortList,"undefined|undefined");
        if(response==1) System.out.println("Pass: Sort By: Most Relevant is working as expected.");

    }

    public int selectSort(Select sortList, String sortValue) throws InterruptedException {
        int count=0,flag=1;
        sortList.selectByValue(sortValue);
        System.out.println("--------------------------------");
        System.out.println("Selected SortBy: "+sortValue);
        softAssert.assertEquals(sortValue,sortList.getFirstSelectedOption().getText(),"Selected sort by option is not getting selected.");
        System.out.println("Sort applyed on page: "+sortList.getFirstSelectedOption().getText());
        await();
        String[] result=productDescription.getText().split("to");
        softAssert.assertEquals(result[0].contains("1"),"true","Page is not redirected to the 1st page.");
        System.out.println("Current page: "+currentPage.getText());
        System.out.println("--------------------------------");
        Thread.sleep(3000);
//        System.out.println("Sorted product list on SRP");
//        System.out.println("--------------------------------");
//        List<WebElement> title=getDriver().findElements(By.xpath(productTitle));
//        for(WebElement e: title)
//            System.out.println(e.getText());
//        System.out.println("--------------------------------");

        List<WebElement>unbxdPrices=getDriver().findElements(By.xpath(unbxdPrice));
        float defaultPrice=0;
        for(WebElement uP: unbxdPrices) {
            if (sortValue.equals("min_price|asc")) {
                Float priceFloat=Float.parseFloat(uP.getText().replace("$",""));
                if(priceFloat<defaultPrice) {
                    System.out.println("Sort By: Lowest price is not working");
                    defaultPrice = 0;
                    return 0;
                }else {defaultPrice=priceFloat; }

            }else if (sortValue.equals("min_price|desc")) {
                Float priceFloat=Float.parseFloat(uP.getText().replace("$",""));
                if(flag==1){
                    defaultPrice=priceFloat;
                    flag=0;
                }
                if(defaultPrice<priceFloat) {
                    System.out.println("Sort By: Highest price is not working");
                    flag=1;
                    return 0;
                }else
                { defaultPrice=priceFloat; }

            }

        }
        return 1;

    }

    public void testWhetherSortingIsWorkingInPagination() throws InterruptedException {
        await();
        Select sortList= new Select(getDriver().findElement(By.xpath(sortByDropdown)));
        sortInPagination(sortList,"min_price|asc");
        sortInPagination(sortList,"min_price|desc");
        sortInPagination(sortList,"undefined|undefined");
    }

    public void sortInPagination(Select sortList, String sortValue) throws InterruptedException {
        int response=0;
        await();
        selectSort(sortList,sortValue);
        //if(response==1)System.out.println("Sort By: "+ sortList.getFirstSelectedOption().getText()+" is working for page"+currentPage.getText());
        forwardArrow.click();
        await();
        awaitForPageToLoad();
        System.out.println("----------------------------------");
        System.out.println("Current page: "+currentPage.getText()+" applyed sort: "+sortList.getFirstSelectedOption().getText());
        response=selectSort(sortList,sortValue);
        if(response==1)System.out.println("Pass: Sort By: "+ sortList.getFirstSelectedOption().getText()+" is working for pagination");

    }

    public void testWhetherSortIsWorkingEvenRefreshingOrReloadingPage()
    {
        await();
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("TEST WHETHER SORT IS WORKING EVEN THOUGH REFRESHING OR RELOADING THE PAGE");
        System.out.println("-------------------------------------------------------------------------");
        String oldPage=currentPage.getText();
        String productRange=productDescription.getText();
        System.out.println("Current page number: "+currentPage.getText());
        Select perPageP = new Select(getDriver().findElement(By.xpath(UnbxdProductPerPageDropDown)));
        Select sortList= new Select(getDriver().findElement(By.xpath(sortByDropdown)));
        String selectedSort=sortList.getFirstSelectedOption().getText();
        String noOfProduct=perPageP.getFirstSelectedOption().getText();
        System.out.println("Selected Sort By: "+sortList.getFirstSelectedOption().getText());
        System.out.println("No of product per page: "+perPageP.getFirstSelectedOption().getText());
        System.out.println("Product range between: "+productDescription.getText());
        System.out.println("Reload the page");
        getDriver().findElement(By.xpath(unbxdPage)).sendKeys(pageLoad);
        await();
        awaitForPageToLoad();
        System.out.println("Current page number: "+currentPage.getText());
        System.out.println("No of product per page: "+perPageP.getFirstSelectedOption().getText());
        System.out.println("Selected Sort By: "+sortList.getFirstSelectedOption().getText());
        System.out.println("Product range between: "+productDescription.getText());
        softAssert.assertEquals(oldPage,currentPage.getText(),"Pagination is getting changed after reload the page.");
        softAssert.assertEquals(productRange,productDescription.getText(),"Product description is getting changed after reload the page.");
        softAssert.assertEquals(selectedSort,sortList.getFirstSelectedOption().getText(),"Selected sort is getting changed after reload the page.");
        softAssert.assertEquals(noOfProduct,perPageP.getFirstSelectedOption().getText(),"No of product per page is getting changed after reload the page.");
        System.out.println("After reload the page, it is getting displayed earlier selected sort.");


    }

    public void testWhetherSortIsWorkingWhenCopyingTheSameURLInTheNewTab() throws InterruptedException {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println(" TEST WHETHER SORT IS WORKING WHEN WE ARE COPYING THE SAME URL IN THE NEW TAB.");
        System.out.println("-------------------------------------------------------------------------");
        Select sortList= new Select(getDriver().findElement(By.xpath(sortByDropdown)));
        String selectedSort=sortList.getFirstSelectedOption().getText();
        String url=getDriver().getCurrentUrl();
        ((JavascriptExecutor) getDriver()).executeScript("window.open('')");
        ArrayList<String> tabs = new ArrayList(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(1));
        getDriver().navigate().to(url);
        await();
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        softAssert.assertEquals(awaitTillElementDisplayed(sortdropDown),"true","Sort By is not getting display");
        Select sortList1= new Select(getDriver().findElement(By.xpath(sortByDropdown)));
        softAssert.assertEquals(selectedSort,sortList1.getFirstSelectedOption().getText(),"Sort is not working when copy the same URL in new tab");




    }


}
