package com.org.ui.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.org.utils.Properties.getWebDriverBaseUrl;

public class CartPage extends Page{

    @FindBy(xpath="//*[contains(@class, 'btn') and text()='Place Order']")
    private WebElementFacade placeOrderBtn;
    @FindBy(id="totalp")
    private WebElementFacade totalPriceLabel;

    private static final Logger LOGGER = LoggerFactory.getLogger(CartPage.class);
    private long purchaseAmountExpected;

    private ModelPopup modelPopup = new ModelPopup();

    public void deleteFromCart(String itemName){
        clickOn(element(By.xpath("//*[text()='"+itemName+"']/following-sibling::td[2]/a")).waitUntilPresent().waitUntilClickable());
        waitABit(5000);
        element("#tbodyid .success").waitUntilPresent();
    }

    public void placeOrderFromCart(){
        purchaseAmountExpected = Long.parseLong(totalPriceLabel.getText().trim());
        LOGGER.info("expected price: "+purchaseAmountExpected);
        clickOn(placeOrderBtn.waitUntilPresent().waitUntilClickable());
        fillPurchaseOrderForm();
    }

    private void fillPurchaseOrderForm(){
        modelPopup.modelPopupDisplayed();
        modelPopup.completeForm();
        LOGGER.info("Purchase id: "+modelPopup.readOrderId());
        LOGGER.info("Purchase amount: "+modelPopup.readOrderAmount());
    }

    public boolean isAmountMatching(){
        return (purchaseAmountExpected==modelPopup.readOrderAmount()) ? true : false;
    }

}
