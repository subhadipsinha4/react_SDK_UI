package core.ui.frontendui.actions;

import core.ui.frontendui.page.Pagination_Page;
import javafx.scene.control.Pagination;
import org.testng.asserts.SoftAssert;

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
        awaitForPageToLoad();
        awaitForElementPresence(searchBox);
        click(searchBox);
        searchBox.clear();
        searchBox.fill().with(query);
        searchBox.submit();
        await();
    }

    public void testPaginationIsDisplayingOrNot()
    {
        await();
        await();

    }
}
