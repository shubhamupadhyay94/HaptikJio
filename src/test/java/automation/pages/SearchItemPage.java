package automation.pages;

import framework.exception.UIActionException;
import framework.uiaction.UIAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchItemPage {

    private WebDriver driver;

    private final By searchItem = By.id("twotabsearchtextbox");

    //form details
    private final By searchButton = By.id("nav-search-submit-button");
    private final By contactName = By.id("recipient-name");
    private final By priceRange = By.xpath("//span[text()=\"₹10,000 - ₹20,000\"]");
    private final By selectSilicone = By.cssSelector("li[aria-label=\"Silicone\"] div");
    private final By getAllWatchPrice = By.cssSelector("span[class=\"a-price-whole\"]");
    private final By getAllWatchNames = By.cssSelector("span[class=\"a-size-base-plus a-color-base a-text-normal\"]");
    private final By getListOfBandMaterial=By.cssSelector("ul[aria-labelledby=\"p_n_material_browse-title\"] a span[dir=\"auto\"]");


    UIAction uiAction = null;

    //Constructor
    public SearchItemPage(WebDriver driver)
    {
        this.driver = driver;
        uiAction =  new UIAction(driver);
    }


    public String enterSearchItem(String item) throws UIActionException {
    return uiAction.sendKeys(searchItem,item);
    }

    public boolean verifyHomePage() throws UIActionException {
        return uiAction.isDisplayed(searchItem);
    }


    public void clickOnSearchButton( ) throws UIActionException {
         uiAction.click(searchButton);
    }

    public void clickOnPriceRange( ) throws UIActionException {
        uiAction.click(priceRange);
    }

    public List<String> getListOfBandMaterial( ) throws UIActionException {
       return uiAction.getListOfString(getListOfBandMaterial);
    }

    public boolean clickOnSiliconeBand( ) throws UIActionException, InterruptedException {
        uiAction.scrollIntoView(selectSilicone);
        Thread.sleep(2000);
        if(uiAction.isDisplayed(selectSilicone)){
            uiAction.click(selectSilicone);
        }

        return uiAction.isSelected(selectSilicone);
    }

    public List<String> getWatchPriceList( ) throws UIActionException {
      return uiAction.getListOfString(getAllWatchPrice);
    }

    public void clickOnFirstItem() throws UIActionException {
         uiAction.click(getAllWatchPrice);
    }

    public List<String> getWatchNames()throws UIActionException{
        return uiAction.getListOfString(getAllWatchNames);
    }
}
