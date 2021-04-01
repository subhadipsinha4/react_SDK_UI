package core.ui.page;

import static lib.constants.UnbxdConstants.SELENIUM_MAXTIMEOUT;
import static lib.constants.UnbxdConstants.SELENIUM_MINTIMEOUT;

import java.net.URISyntaxException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.fluentlenium.core.Fluent;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.action.FluentDefaultActions;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.github.javafaker.Faker;
import com.google.common.base.Function;


public class UiBase extends FluentPage{

    private static final String CONFIG_FILE = "config.properties";

    @FindBy(css = ".Select-menu-outer .Select-menu .Select-option")
    public FluentList<FluentWebElement> highlightedSearchResults;

    @FindBy(css = ".unx-ajax-call-spinner")
    public FluentWebElement loadingSpinner;

    @FindBy(css = ".progressbar .spinner")
    public FluentWebElement loadingSpinnerTypeAhead;

    @FindBy(css = "div.alert.alert-success")
    public FluentWebElement successMessage;

    @FindBy(css = "div.alert.alert-warning")
    public FluentWebElement alertMessage;

    @FindBy(css = "div.alert-danger")
    public FluentWebElement alertErrorMessage;


    @FindBy(css = "a.delete-link")
    public FluentWebElement deleteInModalWindow;

    @FindBy(css = "a.cancel-button")
    public FluentWebElement cancelInModalWindow;

    @FindBy(css = ".dropdown-menu a")
    public FluentList<FluentWebElement> dropDownValues;

    private static PropertiesConfiguration getPropertiesConfiguration() {
        PropertiesConfiguration config = null;
        try {
            config = new PropertiesConfiguration(loadAndGetResourceLocation(CONFIG_FILE));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return config;
    }

    public static int getMaxTimeout() {
            return SELENIUM_MAXTIMEOUT;
        }

    public static int getMinTimeout() {
            return SELENIUM_MINTIMEOUT;
    }


    private static String loadAndGetResourceLocation(String fileName) throws URISyntaxException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResource(fileName).toString();
    }


    public String getCssLocatorForFluent(FluentWebElement element)
    {

        String val = element.getElement().toString();
        val = val.substring(val.toLowerCase().lastIndexOf("selector:") + 10, val.length() - 1).trim();

        return val;

    }

    public void selectByVisibleText(String text,FluentWebElement selectElement)
    {

        Select dropdown=new Select(selectElement.getElement());
        dropdown.selectByVisibleText(text);
    }

    public void selectByValue(String value, FluentWebElement selectElement)
    {
        Select dropdown=new Select(selectElement.getElement());
        dropdown.selectByValue(value);
    }


    public Fluent click(FluentDefaultActions fluentObject) {
        FluentWebElement element = (FluentWebElement) fluentObject;
        awaitForElementPresence(element);
        try{
            return super.click(fluentObject);
        }
        catch (WebDriverException e){
            e.printStackTrace();
        }

        return this;

    }

    public Boolean awaitForElementPresence(final FluentWebElement element) {

        Function<Fluent, FluentWebElement> function = new Function<Fluent, FluentWebElement>() {
            public FluentWebElement apply(Fluent fluent) {
                if (element.isDisplayed()) {
                    return element;
                }
                return null;
            }
        };
        try {
            await().atMost(getMaxTimeout()).until(function);
        }
        catch (WebDriverException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public Boolean awaitForPageToLoad() {
        try {
            await().atMost(getMaxTimeout()).withMessage("Waiting for the Page Load is failed").untilPage().isLoaded();
            return true;
        } catch (Exception e) {
            System.out.println("Waiting for the Page Load is failed");
            return false;
        }
    }

    public Boolean awaitForElementNotDisplayed(final FluentWebElement element) {
        Function<Fluent, FluentWebElement> isNotDisplayedFunction = new Function<Fluent, FluentWebElement>() {
            public FluentWebElement apply(Fluent fluent) {
                if (!element.isDisplayed()) {
                    return element;
                }
                return null;
            }
        };
        try {
            await().atMost(getMaxTimeout()).until(isNotDisplayedFunction);
            return true;
        } catch (WebDriverException e) {
            System.out.println("awaitForElementNotDisplayed is failed for element . Exception is : "+ e.getMessage());
            return false;
        }
    }


    public Boolean awaitTillElementDisplayed(FluentWebElement element) {
        int count = 0;
        while (count < 12) {
            try {
                if (!element.isDisplayed())
                    try {
                        threadWait();
                        count++;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;

                    }
                else
                    return true;
            } catch (WebDriverException e) {
                return false;
            }
        }
        System.out.println("awaitTillElementDisplayed is failed for the element ");
        return false;
    }


    public Boolean awaitTillElementHasText(FluentWebElement element,String text){
        try{
            await().atMost(getMaxTimeout()).ignoring(RuntimeException.class).until(getCssLocatorForFluent(element)).containsText(text);
            return true;
        }
        catch(Exception e){
            System.out.println("awaitTillElementHasText is failed for elment ");
            return false;
        }
    }

    public  boolean checkElementPresence(FluentWebElement element)
    {
        try{

            if(element.isDisplayed())
                return true;
            else
                return false;
        }
        catch (NoSuchElementException e){
            return false;
        }

    }

    public void selectHighlitedValueInDropDown()
    {
        Assert.assertTrue(highlightedSearchResults.size()>0,"No Search Results are coming for the given Input");
        highlightedSearchResults.get(0).click();
    }

    public void selectHighlitedValueInDropDown(String value)
    {
                for(FluentWebElement e:highlightedSearchResults)
                {
                    if(e.getTextContent().trim().equalsIgnoreCase(value))
                    {
                        click(e);
                        break;
                    }
                }
    }

    public void selectValueBYMatchingText(String value)
    {
        for(FluentWebElement e:find(".Select-menu-outer .Select-menu .Select-option"))
        {
                if(e.getText().trim().equalsIgnoreCase(value))
                {
                    e.click();
                    break;
                }
        }
    }

    public void unbxdInputBoxSearch(FluentWebElement element,String name){


        try {
            awaitForElementPresence(element);
            element.getElement().sendKeys(name);
            threadWait();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public boolean checkSuccessMessage()
    {
        awaitForElementPresence(successMessage);
        return successMessage.isDisplayed();

    }

    public boolean checkAlertMessage()
    {

        if(!(awaitForElementPresence(alertMessage)))
            return false;
        return alertMessage.isDisplayed();


    }

    public boolean checkAlertErrorMessage()
    {
        if(!awaitForElementPresence(alertErrorMessage))
            return false;
        return alertErrorMessage.isDisplayed();
    }

    public boolean checkModalWindow()
    {
        return findFirst("body").getAttribute("class").contains("modal-open");
    }


    public void threadWait() {
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }


    public void fillDate(String dateValue)
    {
        FluentWebElement calender=getActiveCalender();
        if(dateValue==null || dateValue=="")
        {
            applyCalender(calender).click();
        }

        else {

            String day, month, year, hour, minute, meridiem;
            String[] date = dateValue.split(" ");
            day = date[0];
            month = date[1];
            year = date[2];

            hour = date[3].split(":")[0];
            minute = date[3].split(":")[1];
            meridiem = date[4];

            selectByVisibleText(year, year(calender));
            selectByVisibleText(month, month(calender));

            fillDay(day);

            selectByVisibleText(hour, hour(calender));
            selectByVisibleText(minute, minute(calender));
            selectByVisibleText(meridiem, ampm(calender));

            applyCalender(calender).click();
        }

    }


    public void fillDay(String day)
    {

        for(FluentWebElement eachDay:days(getActiveCalender()))
        {
            if(eachDay.getTextContent().trim().equals(day))
            {
                eachDay.click();
                break;
            }

        }
    }



    public FluentWebElement getActiveCalender()
    {
        for(FluentWebElement calender:find(".daterangepicker"))
        {
            if(calender.isDisplayed())
                return calender;
        }

        return null;
    }

    public FluentWebElement month(FluentWebElement calender)
    {
        return calender.findFirst(".calendar-table .month .monthselect");
    }

    public FluentWebElement year(FluentWebElement calender)
    {
        return calender.findFirst(".calendar-table .month .yearselect");
    }

    public FluentWebElement hour(FluentWebElement calender)
    {
        return calender.findFirst(".calendar-time .hourselect");
    }

    public FluentWebElement minute(FluentWebElement calender)
    {
        return calender.findFirst(".calendar-time .minuteselect");

    }


    public FluentList<FluentWebElement> days(FluentWebElement calender)
    {
        return calender.find(".calendar-table tbody td.available");

    }


    public FluentWebElement applyCalender(FluentWebElement calender)
    {
        return calender.findFirst("button.applyBtn");
    }


    public FluentWebElement ampm(FluentWebElement calender)
    {
        return calender.findFirst(".calendar-time .ampmselect");
    }


    public String getRandomName()
    {
        Faker faker = new Faker();

        return faker.name().firstName();

    }

    public void selectDropDownValue(String searchValue)
    {
        for(FluentWebElement value:dropDownValues)
        {
            if(value.getText().trim().equalsIgnoreCase(searchValue)){
                value.click();
                break;}

        }
    }

    public void clickDropDown(FluentWebElement element,String value)
    {
        if(!element.getText().trim().contains(value))
        {
            element.click();

        }
    }

    public void handleAlert(String action) {

        org.openqa.selenium.Alert alert = getDriver().switchTo().alert();
        switch (action) {

            case "accept":
                alert.accept();
                break;
            case "dismiss":
                alert.dismiss();
                break;
            default:
                return;
        }


    }

}
