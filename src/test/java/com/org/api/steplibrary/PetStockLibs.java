package com.org.api.steplibrary;

import com.org.utils.ApiConnection;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PetStockLibs extends ScenarioSteps {
    private static final Logger LOGGER = LoggerFactory.getLogger(PetStockLibs.class);

    private ResultData resultData;
    private ApiConnection apiConnection = new ApiConnection();
    private List<String> statusValueSet;
    private List<Long> petIdList;
    private Response response;
    public static long petId;

    @Step
    public void makeRequest(String reqType, String status){
        statusValueSet = apiConnection.request(reqType, status).path("status");
        petIdList = apiConnection.request(reqType, status).path("id");
        petId = petIdList.get(0);
        LOGGER.info("Status of pets: "+statusValueSet);
        LOGGER.info("PetId selected for further transactions: "+petId);
    }

    @Step
    public void verifyGet(String status){
        assertThat(statusValueSet.stream().allMatch(l -> l.equalsIgnoreCase(status))).isTrue();
    }

    @Step
    public void makeCreateRequest(String reqType, String status){
        response = apiConnection.request(reqType, status);
    }

    @Step
    public void verifyCreate(){
        LOGGER.info("id: "+response.path("id")+", name: "+response.path("name")+", status: "+response.path("status"));
        assertThat(response.path("status").toString().equalsIgnoreCase("available")).isTrue();
    }

    @Step
    public void makeUpdate(String reqType, String status){
        response = apiConnection.request(reqType, status);
    }

    @Step
    public void verifyUpdate(String status){
        LOGGER.info("id: "+response.path("id")+", name: "+response.path("name")+", status: "+response.path("status"));
        assertThat(response.path("status").toString().equalsIgnoreCase(status)).isTrue();
    }

    @Step
    public void makeDelete(String reqType, String status){
        response = apiConnection.request(reqType, status);
    }

    @Step
    public void verifyDelete(){
        LOGGER.info("code: "+response.path("code")+", message: "+response.path("message"));
        assertThat(Integer.parseInt(response.path("code").toString())==200 && response.path("message").toString().equalsIgnoreCase(String.valueOf(petId))).isTrue();
    }
}
