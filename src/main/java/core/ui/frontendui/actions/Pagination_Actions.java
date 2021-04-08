package core.ui.frontendui.actions;

import core.ui.frontendui.page.Pagination_Page;
import javafx.scene.control.Pagination;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import javax.xml.ws.WebEndpoint;
import java.util.List;

public class Pagination_Actions extends Pagination_Page {
    SoftAssert softAssert = new SoftAssert();

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



    public void  testPaginationScenarioInCaseOfLastPageResults() throws InterruptedException {
        //String max="";
        searchQuery(productTitle);
        Thread.sleep(2000);
        String result = productDescription.getText();
        String[] totalSrcResult = result.split("of");
        int totalResult = Integer.parseInt(totalSrcResult[1].replace("products", "").replace(" ", ""));
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


}
