package automation.pages;

import framework.exception.UIActionException;
import framework.uiaction.UIAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CartItemPage {


    private WebDriver driver;

    //locators
    private final By cartCount = By.id("nav-cart-count");

    private final By proceedToCheckout = By.name("proceedToRetailCheckout");

    private final By cartItemName = By.cssSelector("span[class=\"a-truncate-cut\"]");

    private final By cartItemPrice = By.cssSelector("span[class=\"a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold\"]");

    private final By email = By.id("ap_email");

    UIAction uiAction = null;

    //Constructor
    public CartItemPage(WebDriver driver)
    {
        this.driver = driver;
        uiAction =  new UIAction(driver);
    }

    public int getCartItemCount() throws UIActionException{
        return Integer.parseInt(uiAction.getText(cartCount));
    }

    public void clickOnCartDetails() throws UIActionException{
         uiAction.click(cartCount);
    }


    public void clickOnProceedToCheckout() throws UIActionException {
         uiAction.click(proceedToCheckout);
    }

    public List<String> listOfItemName() throws UIActionException {
        return uiAction.getListOfString(cartItemName);
    }

    public List<String> listOfItemPrice() throws UIActionException {
        return uiAction.getListOfString(cartItemPrice);
    }

    public boolean isEmailTextBox() throws UIActionException {
        return uiAction.isDisplayed(email);
    }

}
