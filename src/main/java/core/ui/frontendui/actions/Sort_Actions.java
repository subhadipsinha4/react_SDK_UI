package core.ui.frontendui.actions;

import core.ui.frontendui.page.Sort_Page;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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

    public void testCheckWhetherSortByIsThereOrNot() {
        await();
        softAssert.assertEquals(awaitForElementPresence(sortOptions), "true", "Sort By options is not present on SRP");
        System.out.println("Pass: Sort by options is present on SRP");
    }

    public void testSortByIsSelectedAsSORTByRelevancy() {
        await();
        softAssert.assertEquals(sortOptions.getText(), "Most Relevant", "Default sort by option is not 'Most Relevant'");
        System.out.println("Pass: Default sort by option is selected 'Most Relevant'");
    }

    public void testWhetherSortByIsWorkingOrNot() throws InterruptedException {
        int response = 0;
        await();
        Select perPageP = new Select(getDriver().findElement(By.xpath(UnbxdProductPerPageDropDown)));
        perPageP.selectByValue("5");
        Thread.sleep(3000);
        Select sortList = new Select(getDriver().findElement(By.xpath(sortByDropdown)));
        List<WebElement> allSortOptions = sortList.getOptions();
        for (int i = allSortOptions.size() - 1; i >= 0; i--) {
            selectSort(sortList, allSortOptions.get(i).getAttribute("value"));
            response= testProductPriceForSelectedSort(allSortOptions.get(i).getAttribute("value"));
            if (response == 0)
                System.out.println("Fail: Sort By" + allSortOptions.get(i).getText() + "is not working as expected.");
        }
        if (response == 1)
            System.out.println("Pass: Sort by is working. ");

    }
//
//    public void sortOptionsTest() throws InterruptedException {
//        int response = 0;
//        await();
//        Select perPageP = new Select(getDriver().findElement(By.xpath(UnbxdProductPerPageDropDown)));
//        perPageP.selectByValue("5");
//        Thread.sleep(3000);
//        Select sortList = new Select(getDriver().findElement(By.xpath(sortByDropdown)));
//        List<WebElement> allSortOptions = sortList.getOptions();
//        for (int i = allSortOptions.size() - 1; i >= 0; i--) {
//            selectSort(sortList, allSortOptions.get(i).getAttribute("value"));
//            response= testProductPriceForSelectedSort(allSortOptions.get(i).getAttribute("value"));
//            if (response == 0)
//                System.out.println("Fail: Sort By" + allSortOptions.get(i).getText() + "is not working as expected.");
//        }
//        if (response == 1)
//            System.out.println("Pass: Sort by is working. ");
//    }

    public void selectSort(Select sortList, String sortValue) throws InterruptedException {
        sortList.selectByValue(sortValue);
        softAssert.assertEquals(sortValue, sortList.getFirstSelectedOption().getText(), "Selected sort by option is not getting selected.");
        String[] result = productDescription.getText().split("to");
        softAssert.assertEquals(result[0].contains("1"), "true", "Page is not redirected to the 1st page.");
        Thread.sleep(3000);
    }
//        List<WebElement> unbxdPrices = getDriver().findElements(By.xpath(unbxdPrice));
//        float defaultPrice = 0;
//        for (WebElement uP : unbxdPrices) {
//            if (sortValue.equals("min_price|asc")) {
//                Float priceFloat = Float.parseFloat(uP.getText().replace("$", ""));
//                if (priceFloat < defaultPrice) {
//                    defaultPrice = 0;
//                    return 0;
//                } else {
//                    defaultPrice = priceFloat;
//                }
//
//            } else if (sortValue.equals("min_price|desc")) {
//                Float priceFloat = Float.parseFloat(uP.getText().replace("$", ""));
//                if (flag == 1) {
//                    defaultPrice = priceFloat;
//                    flag = 0;
//                }
//                if (defaultPrice < priceFloat) {
//                    flag = 1;
//                    return 0;
//                } else {
//                    defaultPrice = priceFloat;
//                }
//
//            }
//
//        }


    public int testProductPriceForSelectedSort(String sortValue) {
        int count = 0, flag = 1;
        List<WebElement> unbxdPrices = getDriver().findElements(By.xpath(unbxdPrice));
        float defaultPrice = 0;
        for (WebElement uP : unbxdPrices) {
            if (sortValue.equals("min_price|asc")) {
                Float priceFloat = Float.parseFloat(uP.getText().replace("$", ""));
                if (priceFloat < defaultPrice) {
                    defaultPrice = 0;
                    return 0;
                } else {
                    defaultPrice = priceFloat;
                }

            } else if (sortValue.equals("min_price|desc")) {
                Float priceFloat = Float.parseFloat(uP.getText().replace("$", ""));
                if (flag == 1) {
                    defaultPrice = priceFloat;
                    flag = 0;
                }
                if (defaultPrice < priceFloat) {
                    flag = 1;
                    return 0;
                } else {
                    defaultPrice = priceFloat;
                }

            }

        }
        return 1;
    }


    public void testWhetherSortIsWorkingInCaseOfFilter() throws InterruptedException
    {
        int response = 0;
        await();
        Select perPageP = new Select(getDriver().findElement(By.xpath(UnbxdProductPerPageDropDown)));
        perPageP.selectByValue("5");
        Thread.sleep(3000);
        Select sortList = new Select(getDriver().findElement(By.xpath(sortByDropdown)));
        List<WebElement> allSortOptions = sortList.getOptions();
        for (int i = allSortOptions.size() - 1; i >= 0; i--) {
            selectSort(sortList, allSortOptions.get(i).getAttribute("value"));
            categoryFacetUnbxd.click();
            response= testProductPriceForSelectedSort(allSortOptions.get(i).getAttribute("value"));
            if (response == 0)
                System.out.println("Fail: Sort By" + allSortOptions.get(i).getText() + "is not working in case of filter.");
        }
        categoryFacetUnbxd.click();
        if (response == 1)
            System.out.println("Pass: Sort by is working in case of filter ");

    }

    public void testWhetherFilterIsWorkingInCaseOfSort() throws InterruptedException
    {
        int response = 0;
        await();
        Select perPageP = new Select(getDriver().findElement(By.xpath(UnbxdProductPerPageDropDown)));
        perPageP.selectByValue("5");
        Thread.sleep(3000);
        Select sortList = new Select(getDriver().findElement(By.xpath(sortByDropdown)));
        List<WebElement> allSortOptions = sortList.getOptions();
        for (int i = allSortOptions.size() - 1; i >= 0; i--) {
            categoryFacetUnbxd.click();
            selectSort(sortList, allSortOptions.get(i).getAttribute("value"));
            response= testProductPriceForSelectedSort(allSortOptions.get(i).getAttribute("value"));
            if (response == 0)
                System.out.println("Fail: Filter is not working for sort by: " + allSortOptions.get(i).getText());
        }
        categoryFacetUnbxd.click();
        if (response == 1)
            System.out.println("Pass: Filter is working for Sort.");

    }


    public void testWhetherSortingIsWorkingInPagination() throws InterruptedException {
        await();
        int res=0;
        Select sortList= new Select(getDriver().findElement(By.xpath(sortByDropdown)));
        List<WebElement>allSortOptions=sortList.getOptions();
        for(int i=allSortOptions.size()-1;i>=0;i--) {
            res=sortInPagination(sortList, allSortOptions.get(i).getAttribute("value"));
            if(res==0)
            {
                System.out.println("Pass: Sort By: "+ sortList.getFirstSelectedOption().getText()+" is not working for pagination");
            }
        }
        if(res==1)
            System.out.println("Pass: Sorting is working in case of pagination");
    }

    public int sortInPagination(Select sortList, String sortValue) throws InterruptedException {
        int response=0;
        await();
        selectSort(sortList,sortValue);
        forwardArrow.click();
        await();
        selectSort(sortList,sortValue);
        return testProductPriceForSelectedSort(sortValue);


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
        System.out.println("Pass: After reload the page, it is getting displayed earlier selected sort.");


    }

//    public void testWhetherSortIsWorkingWhenCopyingTheSameURLInTheNewTab() throws InterruptedException {
//        System.out.println("-------------------------------------------------------------------------");
//        System.out.println(" TEST WHETHER SORT IS WORKING WHEN WE ARE COPYING THE SAME URL IN THE NEW TAB.");
//        System.out.println("-------------------------------------------------------------------------");
//        Select sortList= new Select(getDriver().findElement(By.xpath(sortByDropdown)));
//        String selectedSort=sortList.getFirstSelectedOption().getText();
//        String url=getDriver().getCurrentUrl();
//        ((JavascriptExecutor) getDriver()).executeScript("window.open()");
//        ArrayList<String> tabs = new ArrayList(getDriver().getWindowHandles());
//        getDriver().switchTo().window(tabs.get(1));
//        getDriver().get(url);
//        //getDriver().navigate().to(url);
//        Thread.sleep(5000);
//        //getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//        softAssert.assertEquals(awaitTillElementDisplayed(sortdropDown),"true","Sort By is not getting display");
//        Select sortList1= new Select(getDriver().findElement(By.xpath(sortByDropdown)));
//        //softAssert.assertEquals(selectedSort,sortList1.getFirstSelectedOption().getText(),"Sort is not working when copy the same URL in new tab");
//
//
//
//
//    }
    public void testWhetherSortIsWorkingWhenCopyingTheSameURLInTheNewTab() throws AWTException, InterruptedException {
        Select sortList= new Select(getDriver().findElement(By.xpath(sortByDropdown)));
        String selectedSort=sortList.getFirstSelectedOption().getText();
        String url=getDriver().getCurrentUrl();
        Robot rob = new Robot();
        rob.keyPress(KeyEvent.VK_CONTROL);
        rob.keyPress(KeyEvent.VK_T);
        rob.keyRelease(KeyEvent.VK_CONTROL);
        rob.keyRelease(KeyEvent.VK_T);
        Thread.sleep(3000);
        Set<String> ids = getDriver().getWindowHandles();
        Iterator <String> it = ids.iterator();
        String currentWindow = it.next();
        String newWindow = it.next();
        getDriver().switchTo().window(newWindow);
        getDriver().get(url);
        Select sortList1= new Select(getDriver().findElement(By.xpath(sortByDropdown)));
        if(selectedSort.equals(sortList1.getFirstSelectedOption().getText()))
            System.out.println("Pass: Sort is working when copying the same URL in the new tab");
        else
            System.out.println("Fail: Sort is not working when copying the same URL in the new tab");
    }


}
