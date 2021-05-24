package core.ui.frontendui.actions;

import core.ui.frontendui.page.Pagination_Page;
import javafx.scene.control.Pagination;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import javax.xml.ws.WebEndpoint;
import java.util.List;

public class Pagination_Actions extends Pagination_Page {
    SoftAssert softAssert = new SoftAssert();
    //int totalResult=0;
    //public Select sortList= new Select(getDriver().findElement(By.xpath(sortByDropdown)));
    //Select sortList;


    public Pagination_Actions goToWebsite(String url) {
        getDriver().navigate().to(url);
        awaitForPageToLoad();
        return this;
    }
    public void searchQuery(String query) throws InterruptedException {
        await();
        //awaitForPageToLoad();
        //awaitForElementPresence(searchBox);
        click(searchBox);
        searchBox.clear();
        searchBox.fill().with(query);
        searchBox.submit();
        await();
    }

    public void testPaginationIsDisplayingOrNot()
    {
        await();
        softAssert.assertEquals(unbxdPagination.isDisplayed(),true,"Fail: Pagination is not present");
        System.out.println("Pass: pagination is getting displayed");
    }
    public void testPaginationIsWorkingOrNot()
    {
        await();
        int cPage=Integer.parseInt(currentPage.getText());
        goTo2ndPage.click();
        awaitForPageToLoad();
        softAssert.assertEquals(Integer.parseInt(currentPage.getText()),cPage+1,"Fail: Pagination is not working");
        System.out.println("Pass: Pagination is working");
    }

    public void testPaginationBackArrowIsWorking()
    {
        await();
        int oldPageNo=Integer.parseInt(currentPage.getText());
        click(backwardArrow);
        awaitForPageToLoad();
        softAssert.assertEquals(Integer.parseInt(currentPage.getText()),oldPageNo-1,"Pagination back arrow key is not working.");
        System.out.println("Pass: Pagination Back arrow key is working.");
    }
    public  void testPaginationForwardArrowIsWorking(){
        await();
        int oldPageNo=Integer.parseInt(currentPage.getText());
        forwardArrow.click();
        awaitForPageToLoad();
        softAssert.assertEquals(Integer.parseInt(currentPage.getText()),oldPageNo+1,"Forward Arrow key is working");
        System.out.println("Pass: Pagination forward arrow key is working.");
    }

    /* total search result on the page calculation methord*/
    public int totalSearchResult()
    {
        String result = productDescription.getText();
        String[] totalSrcResult = result.split("of");
        int totalResult = Integer.parseInt(totalSrcResult[1].replace("products", "").replace(" ", ""));
        return totalResult;
    }

    public void  testPaginationScenarioInCaseOfLastPageResults() throws InterruptedException {
        //String max="";
        searchQuery(productTitle);
        Thread.sleep(2000);
        int totalResult=totalSearchResult();
        //        String result = productDescription.getText();
//        String[] totalSrcResult = result.split("of");
//        int totalResult = Integer.parseInt(totalSrcResult[1].replace("products", "").replace(" ", ""));
        Select proPerPage = new Select(getDriver().findElement(By.xpath(productPerPageDropDown)));
        //proPerPage.selectByValue("20");
        List<WebElement> allOption = proPerPage.getOptions();
        proPerPage.selectByVisibleText(allOption.get(allOption.size() - 1).getText());
        int maxNum = Integer.parseInt(allOption.get(allOption.size() - 1).getText());
        int i = 0;
        while (i < totalResult / maxNum) {
            clickPage();
            i++;
        }
        List<WebElement> pageArrows = getDriver().findElements(By.xpath(paginationArrowIcon));
        for (int j = 0; j < pageArrows.size()-1; j++) {
            if (pageArrows.get(j).getText() != ">")
                System.out.println("Pass: In case of last page, forward arrow key is not getting displayed.");
            else if (pageArrows.get(j).getText() == ">")
                System.out.println("Fail: In case of last page, forward arrow key is getting displayed, it should not display.");
        }

    }


    public void testSelectedPageHighlightOrNot()
    {
        int oldPageNo=Integer.parseInt(currentPage.getText());
        click(forwardArrow);
        await();
        int newPageNo=Integer.parseInt(currentPage.getText());
        softAssert.assertEquals(newPageNo,oldPageNo+1,"Fail: Selected page is not getting highlighted");
        System.out.println("Pass: Selected page is highlighted.");

    }

    public void testPaginationInCaseOfSort() throws InterruptedException {
        int res=0;
        Select sortList = new Select(getDriver().findElement(By.xpath(sortByDropdown)));
        List<WebElement> allSortOptions=sortList.getOptions();
        for(int i=allSortOptions.size()-1; i>=0;i--)
        {
            res=sortByTest(sortList,allSortOptions.get(i).getAttribute("value"));
            if(res==1)
            {
                System.out.println("Fail: Pagination is not working in case of sort["+allSortOptions.get(i).getText()+"]");
            }
            forwardArrow.click();
            Thread.sleep(2000);
        }
        if(res==0)
            System.out.println("Pass: Pagination is working in case of sort");
    }

    public int sortByTest(Select sortOpt, String value) throws InterruptedException {
        sortOpt.selectByValue(value);
        Thread.sleep(2000);
        int newPageNo=Integer.parseInt(currentPage.getText());
        if(newPageNo==1){ return 0;}else return 1;

    }
    public void testPaginationInCaseOfFilter() throws InterruptedException {
        await();
        searchQuery(unbxdSearchQuery);
        forwardArrow.click();
        Thread.sleep(2000);
        UnbxdCategoryFacet.click();
        //Thread.sleep(2000);
        int newPageNo=Integer.parseInt(currentPage.getText());
        softAssert.assertEquals(newPageNo,1,"Fail: Pagination in case Of filter is not working");
        System.out.println("Pass: Pagination in case of filter is working");


    }

    public void testNumberOfItemsPerPageOption() throws InterruptedException {
        await();
        softAssert.assertEquals(unbxdPageSizeDropDown.isDisplayed(),true,"Fail: Pagination drop down is not getting displayed");
        Select pageSize=new Select(getDriver().findElement(By.xpath(unbxdPageSizeDropdown)));
        List<WebElement>allPageSizeOptions=pageSize.getOptions();
        System.out.println("Per page size options are");
        for(int i=0;i<allPageSizeOptions.size();i++)
            System.out.print("["+allPageSizeOptions.get(i).getText()+"]");
        System.out.println("");
    }
    public void testNumberOfItemsPerPageOptionWorkingOrNot() throws InterruptedException {
        searchQuery(unbxdSearchQuery);
        int totalResult=totalSearchResult();
        Select pageSize=new Select(getDriver().findElement(By.xpath(unbxdPageSizeDropdown)));
        List<WebElement>allPageSizeOptions=pageSize.getOptions();
        for(int i=allPageSizeOptions.size()-1;i>=0;i--)
        {
            pageSize.selectByVisibleText(allPageSizeOptions.get(i).getText());
            Thread.sleep(2000);
            int pageSizeSRP=Integer.parseInt(allPageSizeOptions.get(i).getText());
            if(totalResult>=pageSizeSRP)
            {
                List<WebElement>productPresentOnthePage=getDriver().findElements(By.xpath(unbxdProductOnPage));
                softAssert.assertEquals(productPresentOnthePage.size(),pageSizeSRP,"Fail: Number of items per page options is not working");
                if(productPresentOnthePage.size()==pageSizeSRP)
                    System.out.println("Pass: Number of items per page options is working["+pageSizeSRP+"]");
                else
                    System.out.println("Fail: Number of items per page options is not working for page size:["+pageSizeSRP+"]");
            }
            else { System.out.println("Skip: Test case get a skip for page size:["+pageSizeSRP+"], because search result value is:["+totalResult+"]"); }

        }
    }

    public void testSelectedPageOptionIsShowingAsHighlighted()
    {
        await();
        Select pageSize=new Select(getDriver().findElement(By.xpath(unbxdPageSizeDropdown)));
        pageSize.selectByVisibleText("15");
        awaitForPageToLoad();
        if(pageSize.getFirstSelectedOption().getText().equals("15")) { System.out.println("Pass: Selected page option is showing as highlighted"); }
        else {System.out.println("Fail: Selected page option is not showing as highlighted");}

    }

    public void testFooterDisplayingPaginationOptionOrNot()
    {
        await();
        List<WebElement>paginationCount=getDriver().findElements(By.xpath(paginationUnbxd));
        if(paginationCount.get(1).isDisplayed())
            System.out.println("Pass: Footer pagination is getting displayed");
        else
            System.out.println("Fail: Footer pagination is not getting displayed");
    }

    public void testWhetherFooterPaginationIsWorkingOrNot(){

        List<WebElement>paginationForwordKeys=getDriver().findElements(By.xpath(paginationForwordKey));
        int oldPageNo=Integer.parseInt(currentPage.getText());
        paginationForwordKeys.get(1).click();
        Assert.assertEquals(Integer.parseInt(currentPage.getText()),oldPageNo+1,"Fail: Footer pagination is not working");
//        if(Integer.parseInt(currentPage.getText())==oldPageNo+1)
//            System.out.println("Pass: Footer pagination is working.");
//        else
//            System.out.println("Fail: Footer pagination is not working.");
    }

    public void testCaseOfOnePageResults() throws InterruptedException {
        await();
        searchQuery(queryForSingleProduct);
        if(!pageValue.getText().equals("1--1"))
            System.out.println("Pass: Should not display as 1--1 page option");
        else
            System.out.println("Fail: In case of one page, display as 1--1 page option");


    }

}
