package com.org.ui.steplibrary;

import com.org.ui.pages.CartPage;
import com.org.ui.pages.HomePage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerJourneyLib extends ScenarioSteps {
    private HomePage homePage;
    private CartPage cartPage;

    @Step
    public void launchWebsite(){
        homePage.launchWebSite();
    }

    @Step
    public void isHomePageDisplayed(){
        assertThat(homePage.isWebPageLoaded()).isTrue();
    }

    @Step
    public void clickOn(HomePage.SideTab sideTab){
        homePage.navigateTo(sideTab);
    }

    @Step
    public void isNavigationSuccess(HomePage.SideTab sideTab){
        assertThat(homePage.isNavigateTo(sideTab)).isTrue();
    }

    @Step
    public void add_to_cart(String itemName){
        homePage.addToCart(itemName);
    }

    @Step
    public void go_to_page(HomePage.TopNavTab topNavTab){
        homePage.topNavClick(topNavTab);
    }

    @Step
    public void delete_cart_item(String itemName){
        cartPage.deleteFromCart(itemName);
    }

    @Step
    public void placeOrder(){
        cartPage.placeOrderFromCart();
    }

    @Step
    public void verifyPurchaseAmount(){
        assertThat(cartPage.isAmountMatching()).isTrue();
    }

}
