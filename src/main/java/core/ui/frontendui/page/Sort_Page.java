package core.ui.frontendui.page;

import core.ui.page.UiBase;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class Sort_Page extends UiBase {

    @FindBy(xpath = "//select[@data-testid='UNX_unbxdSorter']")
    public FluentWebElement sortOptions;
    @FindBy(xpath = "//input[@class='UNX-searchbox__input']")
    public FluentWebElement searchBox;
    @FindBy(xpath = "//select[@data-testid='UNX_unbxdSorter']")
    public FluentWebElement sortdropDown;
    public String sortByDropdown="//select[@data-testid='UNX_unbxdSorter']";
    public String productTitle="//div[@class='-details']//div";
    public String unbxdPrice="//div[@class='-price']//span";
    @FindBy(xpath = "//button[text()='>']")
    public FluentWebElement forwardArrow;
    @FindBy(xpath = "//span[@class='-pageDescription']")
    public FluentWebElement productDescription;
    @FindBy(xpath = "//button[@class='UNX-pageNavigation__button -selected']")
    public FluentWebElement currentPage;
    public String UnbxdProductPerPageDropDown="//select[@class='UNX-pageSize__dropdown']";
//    @FindBy(xpath = "//body")
//    public FluentWebElement unbxdPage;
    public String unbxdPage="//body";
    public String pageLoad="Keys.F5";



}
