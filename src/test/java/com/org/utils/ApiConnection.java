package com.org.utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.InvalidArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.org.api.steplibrary.PetStockLibs.petId;
import static com.org.utils.Properties.getApiBaseUrl;
import static com.org.utils.ReqBody.createPetReqBody;
import static com.org.utils.ReqBody.updatePetReqBody;
import static io.restassured.RestAssured.given;

public class ApiConnection {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiConnection.class);

    private static RequestSpecification baseRequest(){
        return given().log().all()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .baseUri(getApiBaseUrl())
                .header("api_key", "special-key");
    }

    public Response request(String reqType, String status){
        Response response;
        switch (reqType.toUpperCase()){
            case "GET":
                response = baseRequest().when().get("/pet/findByStatus?status="+status).then().statusCode(200).extract().response();
                break;
            case "CREATE":
                response = baseRequest().body(createPetReqBody()).when().post("/pet").then().statusCode(200).extract().response();
                break;
            case "UPDATE":
                response = baseRequest().body(updatePetReqBody()).when().put("/pet").then().statusCode(200).extract().response();
                break;
            case "DELETE":
                response = baseRequest().when().delete("/pet/"+(String.valueOf(petId)==null?"0":String.valueOf(petId))).then().statusCode(200).extract().response();
                break;
            default:
                throw new InvalidArgumentException("Incorrect input!!");

        }
        return response;
    }
}
