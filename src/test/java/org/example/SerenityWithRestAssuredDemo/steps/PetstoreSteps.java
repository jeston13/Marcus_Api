package org.example.SerenityWithRestAssuredDemo.steps;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.JSONArray;
import org.json.JSONObject;

import static org.hamcrest.Matchers.equalTo;

public class PetstoreSteps {


    private String api_key = "1693905351426";
    /*

    {
  "id": 0,
  "username": "Botmaster69",
  "firstName": "Bot",
  "lastName": "Master",
  "email": "botmaster69@master.com",
  "password": "Botmaster69",
  "phone": "1234567890",
  "userStatus": 0
}


     */
    private long id;
    private static final String URL = "https://petstore.swagger.io/v2";
    public Response response;
    @Step("Create a new Pet")
    public void createPet(String name) {

        JSONArray jsonArray = new JSONArray();
        JSONObject data = new JSONObject();
        JSONObject category = new JSONObject();
        JSONObject tag = new JSONObject();


        tag.put("id",0);
        tag.put("name","Fire type");


        category.put("id",0);
        category.put("name",name);

        data.put("id",0);
        data.put("photoUrls", new JSONArray().put("\"https://fastly.picsum.photos/id/572/200/200.jpg?hmac=YFsNUCQc2Dfz_5O0HY8HmDfquz04XrdcpJ0P4Z7plRY\""));
        data.put("tags", new JSONArray().put( tag));
        data.put("status", "available");
        data.put("category",category);


        response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
                .body(data.toString()).when().post(URL + "/pet");

    }
    @Step("to update the pet")
    public void updatePet( String name){
        JSONArray jsonArray = new JSONArray();
        JSONObject data = new JSONObject();
        JSONObject category = new JSONObject();
        JSONObject tag = new JSONObject();


        tag.put("id",0);
        tag.put("name","Fire type");


        category.put("id",0);
        category.put("name",name);

        data.put("id",(int) id );
        data.put("photoUrls", new JSONArray().put("\"https://fastly.picsum.photos/id/572/200/200.jpg?hmac=YFsNUCQc2Dfz_5O0HY8HmDfquz04XrdcpJ0P4Z7plRY\""));
        data.put("tags", new JSONArray().put( tag));
        data.put("status", "available");
        data.put("category",category);


        response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
                .body(data.toString()).when().put(URL + "/pet");
    }


    @Step("Get pet using id")
    public void getPet(){
        response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
                .when().get(URL + "/pet/"+id);
    }
    @Step("Get pet using invalid id")
    public void getPetInvalid(){
        response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
                .when().get(URL + "/pet/"+0);
    }

    @Step("Get pet using invalid id")
    public void getPetInvalid(String content){
        response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/"+content)
                .when().get(URL + "/pet/"+0);
    }

    @Step("Get pet using availibility")
    public void getStatusPet(){
        response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json").param("status","[\"sold\"]")
                .when().get(URL + "/pet/findByStatus");
    }

    @Step("Update pet with id")
    public void updatePetWithId(){
        JSONObject jobject = new JSONObject();

        response = SerenityRest.given().contentType("application/x-www-form-urlencoded")
                .when().formParam("name","Tiger").formParam("status","sold")
                .post(URL + "/pet/"+id);
    }

    @Step("Update pet with invalid id")
    public void updatePetWithIdInvalid(){
        JSONObject jobject = new JSONObject();

        response = SerenityRest.given().contentType("application/x-www-form-urlencoded")
                .when().formParam("name","Tiger").formParam("status","sold")
                .post(URL + "/pet/"+1);
    }

    @Step("Update pet with invalid headers")
    public void updatePetWithIdInvalid(String content){
        JSONObject jobject = new JSONObject();

        response = SerenityRest.given().contentType("application/"+content)
                .when().formParam("name","Tiger").formParam("status","sold")
                .post(URL + "/pet/"+1);
    }

    @Step("Delete Pet With Id")
    public void deletePetWithId(){
        response = SerenityRest.given().contentType("application/x-www-form-urlencoded")
                .header("api_key",api_key)
                .when()
                .delete(URL + "/pet/"+id);
    }

    @Step("Delete Pet With invalid Id")
    public void deletePetWithIdInvalid(){
        response = SerenityRest.given().contentType("application/x-www-form-urlencoded")
                .header("api_key",api_key)
                .when()
                .delete(URL + "/pet/"+0);
    }


    @Step("Delete Pet With invalid headers")
    public void deletePetWithIdInvalid(String content){
        response = SerenityRest.given().contentType("application/"+content)
                .header("api_key",api_key)
                .when()
                .delete(URL + "/pet/"+0);
    }
    public void getId(){
        JsonPath rs = response.getBody().jsonPath();
        id = rs.get("id");
    }





    @Step("Verify the status code {0}")
    public void verifyStatusCode(int expectedStatusCode) {
        SerenityRest.restAssuredThat(response -> response.statusCode(expectedStatusCode));
    }

}
