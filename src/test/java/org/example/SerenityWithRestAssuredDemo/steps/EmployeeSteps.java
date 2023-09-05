package org.example.SerenityWithRestAssuredDemo.steps;

import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.json.JSONArray;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class EmployeeSteps {

	private static final String URL = "https://petstore.swagger.io/v2";
	public Response response;



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
	public void purchaseOrder(int id) {
		response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
				.when().get(URL + "/store/order" + id);
	}
//

//	@Step("Delete purchase order by id {0}")
//	public void deletePurchaseOrder(int id) {
//		response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
//				.when().delete(URL + "/store/order/" + id);
//	}
//

//	@Step("Return pet inventories by status: {0}")
//	public void getPetInventoriesByStatus(String status) {
//		response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json").queryParam("status", status)
//				.when().get(URL + "/store/inventory");
//	}







	@Step("Verify the status code {0}")
	public void verifyStatusCode(int expectedStatusCode) {
		SerenityRest.restAssuredThat(response -> response.statusCode(expectedStatusCode));
	}
//
	@Step("Find purchase order by id {0}")
	public void verifyId(int expectedId) {
		SerenityRest.restAssuredThat(response -> response.body("id", equalTo(expectedId)));
	}
//
//	@Step("Verify the user name {0}")
//	public void verifyName(String expectedName) {
//
//		SerenityRest.restAssuredThat(response -> response.body("data.employee_name", equalTo(expectedName)));
//	}
//
//	@Step("Verify the user salary {0}")
//	public void verifySalary(int expectedSalary) {
//		SerenityRest.restAssuredThat(response -> response.body("data.employee_salary", equalTo(expectedSalary)));
//	}
//
//	@Step("Verify the user age {0}")
//	public void verifyAge(int expectedAge) {
//		SerenityRest.restAssuredThat(response -> response.body("data.employee_age", equalTo(expectedAge)));
//	}
//
//	@Step("Verify the message {0}")
//	public void verifyMessage(String expectedMessage) {
//		SerenityRest.restAssuredThat(response -> response.body("message", equalTo(expectedMessage)));
//	}

}
