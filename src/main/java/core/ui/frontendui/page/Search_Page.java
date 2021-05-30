package core.ui.frontendui.page;

import core.ui.page.UiBase;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Search_Page extends UiBase {

    public int sleepTime=3000;
    @FindBy(xpath = "//input[@class='UNX-searchbox__input']")
    public FluentWebElement searchBox;
    public String UnbxdsearchBox="//input[@class='UNX-searchbox__input']";
    @FindBy(xpath = "//input[@placeholder]")
    public FluentWebElement placeHolder;
    public String unbxdSearchQuery="Jacket";
    @FindBy(xpath = "//button[@class='UNX-searchbox__button']")
    public FluentWebElement searchBoxClick;
    @FindBy(xpath = "//div[@class='UNBXD-searchTitle__container']//span[@class='-query']")
    public FluentWebElement searchResultQuery;
    public String searchQuery="*";
    public String infiniteScrollSearchQuery="boot";
    @FindBy(xpath = "//div[@class='UNBXD-searchTitle__container']")
    public FluentWebElement searchResultFormat;
    @FindBy(xpath = "//div[@class='UNX-spellCheck__item']")
    public FluentWebElement didYouMean;
    @FindBy(xpath = "//div[@class='UNX-noProducts__container']")
    public FluentWebElement noResultFound;
    public String invalidQuery="nfkjergbre";
    public String blankSpace=" ";
    @FindBy(xpath = "//span[@data-testid='UNX_listBtn']")
    public  FluentWebElement listViewClick;
    @FindBy(xpath = "//span[@data-testid='UNX_gridBtn']")
    public  FluentWebElement gridViewClick;
    @FindBy(xpath = "//div[text()='blue']")
    public FluentWebElement colorFacet;
    public String viewValidate="class";
    public String ClickViewExpected="UNX-viewType__option -selected";
    @FindBy(xpath = "//button[@data-testid='UNX_pageNumber2']")
    public FluentWebElement goTo2ndPage;
    @FindBy(xpath = "//div[@class='UNX-pageSize__container']")
    public FluentWebElement pageSizeContainer;
    @FindBy(xpath = "//div[@class='UNX-sortby__container']")
    public FluentWebElement sortByContainer;
    @FindBy(xpath = "//div[@class='UNX-searchFacet__container']")
    public FluentWebElement facetContainer;
    @FindBy(xpath = "//div[@class='UNX-products__container']")
    public FluentWebElement productContainer;
    public String productsContainer="//div[@class='UNX-products__container']";
    public String UnbxdProductContainer="//div[@class='UNX-productCard__container']";
    public String UnbxdProductImage="//img[@class='-image']";
    public String UnbxdProductTitle="//div[@class='-title']";
    @FindBy(xpath = "//div[@class='UNX-products__container']//div[@class='-price']")
    public FluentWebElement ProductPrice;
    public String UnbxdProductPrice="//div[@class='-price']";
    public String perPageProduct="//select[@class='UNX-pageSize__dropdown']";
    @FindBy (xpath = "//a[@class='UNX-product-card UNX-grid-card']")
    public FluentWebElement productsPerPage;
    public String productsPerPageCount="//img[@data-uniqueid]";
    public String sortByDropdown="//select[@data-testid='UNX_unbxdSorter']";
    public String sortByDropDownInfinteScroll="//select[@name='pageSize']";
    public String pageSizeValue="5";
    //@FindBy(xpath="//div[@class='-details']//div")
    //public FluentWebElement productTitle;
    public String productTitle="//div[@class='-details']//div";
    public String productPrice="//div[@class='-details']//div[@class='-price']//span";
    public String productDetails="//div[@class='-details']";
    @FindBy(xpath = "//span[@class='-pageDescription']")
    public FluentWebElement productDescription;
    @FindBy(xpath = "//button[@class='-clear']")
    public FluentWebElement clearButton;
    @FindBy(xpath = "//span[@class='-strike']")
    public FluentWebElement strickenPrice;
    public String uniqueId="UFO01329";
    @FindBy(xpath = "//div[@data-uniqueid]")
    public FluentWebElement uniqueIDPath;
    @FindBy(xpath = "//div[@class='UNX-spellCheck__item']")
    public FluentWebElement spellErrorMessage;
    public String spellMistakeSingleWorld="swirt";
    @FindBy(xpath = "//span[@data-testid='UNX_spellCheck']")
    public FluentWebElement spellCorrectWord;
    @FindBy(xpath = "//div[@class='UNX-productCard__container']//a")
    public FluentWebElement getProductDetails;
    public String unbxdProductsTitle="//div[@class='-title']";
    public String productsPrice="//div[@class='-price']";
    public String productsStrickenPrice="//div[@class='-details']//div[@class='-price']//span[@class]";
    public String productsImage="//div[@class='UNX-productCard__container']";
    public String spellCorrectSingleWord="shirt";
    public String specialCharacterQuery="Shirt & jacket";
    public String twoWordQuery="red shirt";
    public String threeWordQuery="mad blue shirt";
    public String lowerCaseQuery="shirt";
    public String combinationQuery="ReD SHiRt";
    public String twoWordSpellMistake="Rod Shrt";
    public String threeWordSpellMistake="Mam Biue Shiet";
    public String UnbxdStrickenPrice="//span[@class='-strike']";
    public String unbxdColorQuerySearch="white dress";
    public String baseURL="https://search.unbxd.io/1c8c3ff5d59cf85f256311199bad47bc/prod-cookieskids-com808271562744920/search?q=";
    public String baseURL2="https://search.unbxd.io/1c8c3ff5d59cf85f256311199bad47bc/prod-cookieskids-com808271562744920/search?q=";
    public String infinitePageUrl="https://5j1xw.csb.app/";
    public static String infiniteScrollURl="https://j56sm.csb.app/";
    public String infiniteScrollQuery="Black shoes";
    public String spellErrorUrl="https://search.unbxd.io/1c8c3ff5d59cf85f256311199bad47bc/prod-cookieskids-com808271562744920/search?q=";
    public static String  urlQuickViewAddToCart="https://c7ff5.csb.app/";
    public static String urlColorVar="https://0o84m.csb.app/";
    @FindBy(xpath = "//div[@class='-quickView']")
    public FluentWebElement quickView;
    @FindBy(xpath = "//div[@class='-addToCart']")
    public FluentWebElement addToCartSRP;
    @FindBy(xpath = "//div[@class='-swatch__container']")
    public FluentWebElement colorSwitch;
    public String unbxdColorSwitch="//div[@class='UNX-swatch__item']//img";
    public String unbxdProductImgURL="//div[@class='details']//img";
    public String unbxdProductUrlImageLink="//a//img[@src]";

}