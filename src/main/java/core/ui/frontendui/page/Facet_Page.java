package core.ui.frontendui.page;

import core.ui.page.UiBase;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Facet_Page extends UiBase {

    //search result xpath
    @FindBy(xpath = "//*[@class='candara large fleft unbxd_search_heading']//following::span[@id='searchReults'] | //*[@id='unbxd_searchresults_msg']//*[3] | (//*[@id='left-nav']//following::span)[1] |(//*[@class='toolbar-number'])[3]")
    public FluentWebElement SearchResult;

    //facet_click xpath
    @FindBy(xpath = "(//*[@id='category_uFilter']//following::*[contains(@id,'category_uFilter')])[1] | (//*[@id='v_OrigCat_uFilter']//following::*[contains(@id,'v_OrigCat_uFilter')])[1] | (//*[@class='search-facet'])[1] |(//*[@data-filter='category-filter']//following::*[@class='count'])[1]")
    public FluentWebElement filter_facet;

    @FindBy(xpath = "//*[@class='unbxd_selected_facets'] | //*[@class='facet_reset'] | (//*[@class='clear-all'])[1] |//*[@class='clear']")
    public FluentWebElement filterAppliedMsg;

    @FindBy(xpath = "(//*[@class='search-facet']//preceding-sibling::h2)[1] | (//*[contains(@id,'v_OrigCat_uFilter')]//following::label)[1] |(//*[@data-filter='category-filter']//preceding-sibling::*[@class='name'])[1]")
    public FluentWebElement facet_name;

    @FindBy(xpath ="//*[@class='remove']//following::*[@class='selection'] |//*[@data-id='v_OrigCat_uFilter' and @class='selected'] |//*[@class='level-1 active']//*[@class='name'] ")
    public FluentWebElement FacetName_verify;
}