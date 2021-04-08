package core.ui.frontendui.page;

import core.ui.page.UiBase;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Pagination_Page extends UiBase {

    @FindBy(xpath = "//input[@class='UNX-searchbox__input']")
    public FluentWebElement searchBox;
    @FindBy (xpath = "//div[@class='UNX-pageNavigation__container']")
    public FluentWebElement unbxdPagination;
    @FindBy(xpath = "//button[@data-testid='UNX_pageNumber2']")
    public FluentWebElement goTo2ndPage;
    @FindBy(xpath = "//button[text()='>']")
    public static FluentWebElement forwardArrow;
    @FindBy(xpath = "//button[text()='<']")
    public FluentWebElement backwardArrow;
    @FindBy(xpath = "//button[@class='UNX-pageNavigation__button -selected']")
    public static FluentWebElement currentPage;

    public String paginationArrowIcon="//button[@class='UNX-pageNavigation__button -action']";
    public String productPerPageDropDown="//select[@class='UNX-pageSize__dropdown']";
    @FindBy(xpath = "//span[@class='-pageDescription']")
    public FluentWebElement productDescription;
    @FindBy(xpath="//div[@class='UNX-pageNavigation__container']")
    public  FluentWebElement pagination;
    public static String paginations="//div[@class='UNX-pageNavigation__container']";
    public String productTitle="Beverly Hills Polo Club";
    public String pagenumber="//button[@class='UNX-pageNavigation__button']";
    @FindBy(xpath="//button[@class='UNX-pageNavigation__button']")
    public WebElement pageNumber;
    public static List<WebElement> page;

    public void clickPage() throws InterruptedException {
            forwardArrow.click();
            Thread.sleep(2000);
    }




}
