package automation.pages;

import framework.exception.UIActionException;
import framework.uiaction.UIAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddItemToCartPage {

    private WebDriver driver;

    //locators
    private final By addToCart = By.id("add-to-cart-button");


    //form header
    private final By verifyband = By.xpath("//span[@class=\"a-size-base\"]  [contains(text(),\"Silicone\")]");

    UIAction uiAction = null;

    //Constructor
    public AddItemToCartPage(WebDriver driver)
    {
        this.driver = driver;
        uiAction =  new UIAction(driver);
    }


    public boolean verifyWatchBand() throws UIActionException {
        return uiAction.isDisplayed(verifyband);
    }


    public void addItemToCart() throws UIActionException {
        uiAction.click(addToCart);
    }


}
