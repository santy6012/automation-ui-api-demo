package com.org.ui.pages;


import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelPopup extends Page{

    @FindBy(id="orderModalLabel")
    private WebElementFacade modelTitle;
    @FindBy(id="name")
    private WebElementFacade name;
    @FindBy(id="country")
    private WebElementFacade country;
    @FindBy(id="city")
    private WebElementFacade city;
    @FindBy(id="card")
    private WebElementFacade card;
    @FindBy(id="month")
    private WebElementFacade month;
    @FindBy(id="year")
    private WebElementFacade year;
    @FindBy(xpath="//*[contains(@class,'btn-primary') and text()='Purchase']")
    private WebElementFacade purchaseBtn;
    @FindBy(xpath="//*[contains(@class, 'confirm') and text()='OK']")
    private WebElementFacade okBtn;
    @FindBy(xpath="//*[contains(@class, 'text-muted')]")
    private WebElementFacade orderConfirmationDetail;

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelPopup.class);
    private String purchaseConfirmationDetail;

    public void modelPopupDisplayed(){
        try{
            modelTitle.waitUntilPresent().waitUntilVisible();
            LOGGER.info("Purchase form visible...");
        }catch (Exception e){
            LOGGER.error("Purchase form didn't turn up... pls try...");
            e.printStackTrace();
        }
    }

    public void completeForm(){
        typeInto(name.waitUntilPresent(), "Test Client");
        typeInto(country.waitUntilPresent(), "India");
        typeInto(city.waitUntilPresent(), "Gurgaon");
        typeInto(card.waitUntilPresent(), "1111111111111111");
        typeInto(month.waitUntilPresent(), "October");
        typeInto(year.waitUntilPresent(), "2020");
        clickOn(purchaseBtn.waitUntilPresent().waitUntilClickable());
        element(okBtn).waitUntilPresent();
        purchaseConfirmationDetail = orderConfirmationDetail.getText();
        LOGGER.info("Order purchase info :"+purchaseConfirmationDetail);
        clickOn(okBtn.waitUntilClickable());
    }

    public long readOrderAmount(){
        String desc = purchaseConfirmationDetail;
        String part = desc.substring(desc.indexOf("Amount:"), desc.indexOf("Card"));
        long amount = Long.parseLong(part.replaceAll("[^0-9]","").trim());
        return amount;
    }

    public String readOrderId(){
        String desc = purchaseConfirmationDetail;
        String part = desc.substring(desc.indexOf("Id:"), desc.indexOf("Amount:"));
        String id = part.replaceAll("[^0-9]","").trim();
        return id;
    }

}
