package core.ui.frontendui.page;

import core.ui.page.UiBase;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

public class Pagination_Page extends UiBase {
    @FindBy(xpath = "//input[@class='UNX-searchbox__input']")
    public FluentWebElement searchBox;



}
