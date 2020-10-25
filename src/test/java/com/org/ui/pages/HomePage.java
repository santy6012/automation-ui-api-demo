package com.org.ui.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.org.utils.Properties.getWebDriverBaseUrl;

public class HomePage extends Page{
    @FindBy(id="nava")
    private WebElementFacade logoId;
    @FindBy(xpath = "//*[contains(@class,'btn') and text()='Add to cart']")
    private WebElementFacade addToCartBtn;

    private static final Logger LOGGER = LoggerFactory.getLogger(HomePage.class);

    public enum SideTab{
        PHONE, LAPTOP, MONITOR;
    }

    public enum TopNavTab{
        HOME, CONTACT, ABOUTUS, CART, LOGIN, SIGNUP;
    }

    public void launchWebSite(){
        openUrl(getWebDriverBaseUrl());
        LOGGER.info("Web launch started...");
    }

    public boolean isWebPageLoaded(){
        return logoId.waitUntilPresent().isDisplayed() && getDriver().getTitle().equalsIgnoreCase("store");
    }

    public void navigateTo(SideTab sideTab){
        String xpath;
        switch (sideTab){
            case PHONE:
                xpath = "//*[@id='itemc' and text()='Phones']";
                break;
            case LAPTOP:
                xpath = "//*[@id='itemc' and text()='Laptops']";
                break;
            case MONITOR:
                xpath = "//*[@id='itemc' and text()='Monitors']";
                break;
            default:
                throw new InvalidArgumentException(sideTab+" invalid, pls try with correct input...");
        }
        clickOn(element(By.xpath(xpath)).waitUntilPresent().waitUntilClickable());
        LOGGER.info("Navigation started...");
    }

    public boolean isNavigateTo(SideTab sideTab){
        String screenIdentifier;
        switch (sideTab){
            case PHONE:
                screenIdentifier = "#tbodyid img";
                break;
            case LAPTOP:
                screenIdentifier = "#tbodyid .hrefch";
                break;
            case MONITOR:
                screenIdentifier = "#tbodyid .hrefch";
                break;
            default:
                throw new InvalidArgumentException(sideTab+" invalid, pls try with correct input...");
        }
        return element(screenIdentifier).waitUntilPresent().isDisplayed();
    }

    public void addToCart(String itemName){
        clickOn(element(By.xpath("//*[text()='"+itemName+"']")).waitUntilPresent().waitUntilClickable());
        element(By.xpath("//*[@class='name' and text()='"+itemName+"']")).waitUntilPresent();
        clickOn(addToCartBtn.waitUntilPresent().waitUntilClickable());
        waitABit(5000);
        acceptAlertAndNavigateToHome();
    }

    private void acceptAlertAndNavigateToHome(){
        getDriver().switchTo().alert().accept();
        clickOn(element(By.xpath("//*[@class='nav-link' and contains(@href, 'index')]")).waitUntilPresent().waitUntilClickable());
    }

    public void topNavClick(TopNavTab topNavTab){
        String tabXpath, screenIdentifier;
        switch (topNavTab){
            case HOME:
                tabXpath = "//*[@class='nav-link' and contains(@href, 'index')]";
                screenIdentifier = "";
                break;
            case CONTACT:
                tabXpath = "//*[@class='nav-link' and text()='Contact']";
                screenIdentifier = "";
                break;
            case ABOUTUS:
                tabXpath = "//*[@class='nav-link' and text()='About us']";
                screenIdentifier = "";
                break;
            case CART:
                tabXpath = "//*[@class='nav-link' and text()='Cart']";
                screenIdentifier = "//*[contains(@class, 'btn') and text()='Place Order']";
                break;
            case LOGIN:
                tabXpath = "//*[@class='nav-link' and text()='Log in']";
                screenIdentifier = "";
                break;
            case SIGNUP:
                tabXpath = "//*[@class='nav-link' and text()='Sign up']";
                screenIdentifier = "";
                break;
            default:
                throw new InvalidArgumentException(topNavTab+" invalid, pls try with correct input...");
        }
        clickOn(element(By.xpath(tabXpath)).waitUntilPresent().waitUntilClickable());
        try {
            element(By.xpath(screenIdentifier)).waitUntilPresent();
            LOGGER.info("Navigation success...");
        }catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException e){
            e.printStackTrace();
            LOGGER.error("Something went wrong...");
        }
    }

}
