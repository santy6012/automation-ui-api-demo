package com.org.ui.stepdefinitions;

import com.org.ui.pages.HomePage;
import com.org.ui.steplibrary.CustomerJourneyLib;
import com.org.utils.ProductType;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class CustomerJourney {
    @Steps
    private CustomerJourneyLib customerJourneyLib;

    @Given("^I am on home page$")
    public void i_launch_website(){
        customerJourneyLib.launchWebsite();
        customerJourneyLib.isHomePageDisplayed();
    }

    @When("^I click on (.*) side tab$")
    public void i_click_on(HomePage.SideTab sideTab){
        customerJourneyLib.clickOn(sideTab);
    }

    @Then("^I should be able to see (.*) section$")
    public void i_am_on(HomePage.SideTab sideTab){
        customerJourneyLib.isNavigationSuccess(sideTab);
    }

    @Given("^I am in (.*) section$")
    public void i_am_in_section(HomePage.SideTab sideTab){
        i_launch_website();
        i_click_on(sideTab);
        i_am_on(sideTab);
    }

    @When("^I add (.*) in cart$")
    public void add_to_cart(String itemName){
        customerJourneyLib.add_to_cart(itemName);
    }

    @And("^I go to (.*)$")
    public void go_to_page(HomePage.TopNavTab topNavTab){
        customerJourneyLib.go_to_page(topNavTab);
    }

    @And("^I delete (.*) from cart$")
    public void i_delete_cart_item(String itemName){
        customerJourneyLib.delete_cart_item(itemName);
    }

    @And("^I place order$")
    public void place_order(){
        customerJourneyLib.placeOrder();
    }

    @And("^purchase details should be correct$")
    public void verify_amount(){
        customerJourneyLib.verifyPurchaseAmount();
    }


}
