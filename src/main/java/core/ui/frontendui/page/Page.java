package core.ui.frontendui.page;

import core.ui.page.UiBase;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

public class Page extends UiBase {

    @FindBy(xpath = "(//input[contains(@autocomplete,'off') and contains(@type,'text') or contains(@type,'search')])[1]")
    public  FluentWebElement searchBox;

    @FindBy(xpath = "//*[contains(text(),'Sorry')] | //*[contains(text(),'We found')] | //*[contains(text(),'Search was unable to find')] | //*[contains(text(),'Narrow Your Search By')] | //*[contains(text(),'No result found')] | //*[contains(text(),'Showing')]")
    public FluentWebElement eleSearchMsg;

    //Entire search Msg xpath
    @FindBy(xpath = "//*[@class='candara large fleft unbxd_search_heading']//following::span[@data-unbxd-selector='search-msg'] | //*[@class='unbxd-product-count'] | //*[@id='unbxd_searchresults_msg']")
    public FluentWebElement SearchMsg;

    // invalid msg xpath
    @FindBy(xpath = "//*[@id='unbxd_no_results_text'] | //*[@class='unbxd-no-results'] | //*[@class='unbxd_no_results_text']")
    public FluentWebElement NoSearchMsg;

    //search result xpath
    @FindBy(xpath = "//*[@class='candara large fleft unbxd_search_heading']//following::span[@id='searchReults'] | //*[@id='unbxd_searchresults_msg']//*[3] | //*[@class='unbxd-product-count']")
    public FluentWebElement SearchResult;

    //popup Boscovs
    @FindBy(xpath = "//*[@id='monetate_email_lightbox']//following::a[@href='#close'] ")
    public FluentWebElement monetateEmailPopup;

    //facet_click xpath
    @FindBy(xpath = "(//*[@id='category_uFilter']//following::*[contains(@id,'category_uFilter')])[1] | (//*[@id='v_OrigCat_uFilter']//following::*[contains(@id,'v_OrigCat_uFilter')])[1]  | (//*[@class='facet-option-list']//preceding-sibling::span[contains(@class,'facet-val')])[1]")
    public FluentWebElement filter_facet;

    @FindBy(xpath = "//*[@class='unbxd_selected_facets'] | //*[@class='facet_reset'] | //*[@class='selected-facet-desc']")
    public FluentWebElement filterAppliedMsg;

            //pegination
 //   @FindBy(xpath = "//nav[@id='paging']//button[@data-page='1' and @class='sel']")
//    public FluentWebElement page1_selected;

//    @FindBy(xpath = "(//nav[@id='paging']//button)[2]")
//    public FluentWebElement page2;
//
//    @FindBy(xpath = "//nav[@id='paging']//button[@data-page='2']")
//    public FluentWebElement page2_actual;

}