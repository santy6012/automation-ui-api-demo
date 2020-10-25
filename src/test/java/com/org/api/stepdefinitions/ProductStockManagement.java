package com.org.api.stepdefinitions;

import com.org.api.steplibrary.PetStockLibs;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.InvalidArgumentException;

public class ProductStockManagement {

    @Steps
    private PetStockLibs petStockLibs;

    @Given("^I am authorized to manage store resources$")
    public void i_have_access(){
        //readability
    }

    @When("^I make request to (.*) details for (.*) pets$")
    public void i_make_request(String reqType, String status){
        petStockLibs.makeRequest(reqType, status);
    }

    @Then("^I should be able to get pets with (.*) status only$")
    public void i_see_status(String status){
        petStockLibs.verifyGet(status);
    }

    @When("^I make request to (.*) (?:a new pet|status as sold for a pet|existing pet)$")
    public void i_make_request(String reqType){
        select_request(reqType);
    }

    @Then("^I should be able to create a new pet$")
    public void i_see_new_pet(){
        petStockLibs.verifyCreate();
    }

    @Then("^pet status is changed to (.*)$")
    public void i_see_update(String status){
        petStockLibs.verifyUpdate(status);
    }

    @Then("^I should be able to delete the pet$")
    public void i_see_delete(){
        petStockLibs.verifyDelete();
    }

    private void select_request(String type){
        switch (type.toUpperCase()){
            case "CREATE":
                petStockLibs.makeCreateRequest(type.toUpperCase(), "NA");
                break;
            case "UPDATE":
                petStockLibs.makeUpdate(type.toUpperCase(), "NA");
                break;
            case "DELETE":
                petStockLibs.makeDelete(type.toUpperCase(), "NA");
                break;
            default:
                throw new InvalidArgumentException(type+ " is invalid type of request..");
        }
    }

    /*@When("^I make request to (.*) status as sold for a pet$")
    public void i_make_update(String reqType){ }
    @When("^I make request to (.*) existing pet$")
    public void i_make_delete(String reqType){ }*/

}
