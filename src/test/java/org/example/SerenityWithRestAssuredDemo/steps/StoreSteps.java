package org.example.SerenityWithRestAssuredDemo.steps;

import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.json.JSONArray;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StoreSteps {

	private static final String URL = "https://petstore.swagger.io/v2";
	public Response response;
long id;


	@Step("Place order for pet")
	public void placeOrder(int id, int petid, int qnt, String shipdate, String status, boolean complete) {

		JSONArray jsonArray = new JSONArray();
		JSONObject data = new JSONObject();

		data.put("id", id);
		data.put("petId", petid);
		data.put("quantity", qnt);
		data.put("shipDate", shipdate);
		data.put("status", status);
		data.put("complete", complete);

//		jsonArray.put(data);

		response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
				.body(data.toString()).when().post(URL + "/store/order");

	}

	@Step("Find purchase order by id {0}")
	public void findpurchaseorderid(int id) {
		response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
				.when().get(URL + "/store/order/" + id);
	}

	@Step("Gets the inventory")
	public void getInventory(){
		response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
				.when().get(URL + "/store/inventory");
	}

	public void getPurchaseId(){
		id = response.getBody().jsonPath().get("");
	}

	@Step("Verify the status code {0}")
	public void verifyStatusCode(int expectedStatusCode) {
		SerenityRest.restAssuredThat(response -> response.statusCode(expectedStatusCode));
	}
}
