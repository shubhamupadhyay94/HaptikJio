package automation.stepdefinitions;

import automation.pages.AddItemToCartPage;
import automation.pages.CartItemPage;
import automation.pages.SearchItemPage;
import automation.testrunner.ParallelRun;
import framework.driver.DriverUtilities;
import framework.exception.UIActionException;
import framework.uiaction.UIAction;
import framework.utilities.CommonUtilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WindowType;
import org.testng.Assert;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SearchItemStep {

    SearchItemPage searchItemPage = new SearchItemPage(DriverUtilities.getDriver());
    AddItemToCartPage addItemToCartPage = new AddItemToCartPage(DriverUtilities.getDriver());
    CartItemPage cartItemPage = new CartItemPage(DriverUtilities.getDriver());

    List<String> watchNames=null;
    List<String> itemPrice =null;
    List<String> searchItemBandMaterial =null;
    boolean status=false;

    @Given("I am on the amazon home page")
    public void i_am_on_the_amazon_home_page() throws UIActionException {
        Assert.assertEquals(searchItemPage.verifyHomePage(),true);
    }
    @Given("I search for {string} in search box")
    public void i_search_for_in_search_box(String string) throws UIActionException {
        searchItemPage.enterSearchItem(ParallelRun.map.get("searchItem"));
        searchItemPage.clickOnSearchButton();
    }
    @Given("I select price range between {int} and {int}")
    public void i_select_price_range_between_and(Integer int1, Integer int2) throws UIActionException {

        searchItemPage.clickOnPriceRange();
    }
    @Given("I verify search mobile price range is between {int} and {int}")
    public void i_verify_search_mobile_price_range_is_between_and(Integer minValue, Integer maxValue) throws UIActionException {
       List<String> price= searchItemPage.getWatchPriceList();
        Collections.sort(price);
        int min = Integer.parseInt(price.get(0).replaceAll("[^0-9]",""));
        int max =Integer.parseInt(price.get(price.size()-1).replaceAll("[^0-9]",""));
        Assert.assertTrue(min>=minValue,"Price range filter is not working");
        Assert.assertTrue(max<=maxValue,"Price max value filter is not working");

    }
    @Given("I select band material as {string}")
    public void i_select_band_material_as(String string) throws UIActionException, InterruptedException {

        searchItemBandMaterial = searchItemPage.getListOfBandMaterial();
        status= searchItemBandMaterial.contains("Silicone");
        if(status){
            searchItemPage.clickOnSiliconeBand();
        }
    }


    @Given("I click on first watch from searched list")
    public void i_click_on_first_watch_from_searched_list() throws UIActionException, InterruptedException {
        watchNames= searchItemPage.getWatchNames();
       itemPrice = CommonUtilities.getListOfNumber(searchItemPage.getWatchPriceList());
        String parentWindow = DriverUtilities.getDriver().getWindowHandle();
        searchItemPage.clickOnFirstItem();

        Set<String> childWindow = DriverUtilities.getDriver().getWindowHandles();
        for(String window:childWindow){
            if(window!=parentWindow){
              DriverUtilities.getDriver().switchTo().window(window);
            }
        }
        Thread.sleep(5000);

    }
    @Given("I verify the band material in new open tab")
    public void i_verify_the_band_material_in_new_open_tab() throws UIActionException {
        if (status) {
            Assert.assertTrue(addItemToCartPage.verifyWatchBand());
        }
    }

    @Given("I add the watch in cart")
    public void i_add_the_watch_in_cart() throws UIActionException {
        addItemToCartPage.addItemToCart();

    }
    @Given("I verify the right watch is added into cart")
    public void i_verify_the_right_watch_is_added_into_cart() throws UIActionException {
       int count = cartItemPage.getCartItemCount();
       Assert.assertTrue(count>=1);
        cartItemPage.clickOnCartDetails();
      List<String> itemNames= cartItemPage.listOfItemName();

      //verify the price of item
      Assert.assertEquals(itemPrice.get(0),cartItemPage.listOfItemPrice().get(0).replaceAll("[.0]+$", "").replaceAll("[^0-9]",""));

    }
    @When("I click on proceed to buy")
    public void i_click_on_proceed_to_buy() throws UIActionException {
        cartItemPage.clickOnProceedToCheckout();
    }
    @Then("I should be on amazon login page")
    public void i_should_be_on_amazon_login_page() throws UIActionException {
    Assert.assertEquals(cartItemPage.isEmailTextBox(),true);
    }
}
