package core.ui.frontendui.page;

import core.ui.page.UiBase;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

public class Pagination_Page extends UiBase {
    @FindBy(xpath = "//input[@class='UNX-searchbox__input']")
    public FluentWebElement searchBox;



    @FindBy(xpath = "//button[text()='<']")
    public FluentWebElement backwardArrow;
    @FindBy(xpath = "//button[@class='UNX-pageNavigation__button -selected']")
    public FluentWebElement currentPage;
    @FindBy(xpath="//div[@class='UNX-pageNavigation__container']")
    public  FluentWebElement pagination;
    @FindBy(xpath = "//button[@data-testid='UNX_pageNumber2']")
    public FluentWebElement goTo2ndPage;
    public String productTitle="Beverly Hills Polo Club";
    public String productPerPageDropDown="//select[@class='UNX-pageSize__dropdown']";
    @FindBy(xpath = "//span[@class='-pageDescription']")
    public FluentWebElement productDescription;
    @FindBy(xpath = "//button[text()='>']")
    public static FluentWebElement forwardArrow;
    public String paginationArrowIcon="//button[@class='UNX-pageNavigation__button -action']";
    public void clickPage() throws InterruptedException {
        forwardArrow.click();
        Thread.sleep(2000);
    }


}
