package org.example.SerenityWithRestAssuredDemo.steps;
import org.json.JSONObject;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.json.JSONArray;
import org.hamcrest.Matchers;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.isTrue;

public class PetSwaggerSteps {

	private static final String URL = "https://petstore.swagger.io/v2";
	public Response response;

	@Step("Create a new user")
	public void createUser(int id,String username,String firstname,String lastname,String email,String password,String phone,int status) {
		JSONArray jsonArray = new JSONArray();
		JSONObject data = new JSONObject();

		data.put("id",id);
		data.put("username", username);
		data.put("firstName", firstname);
		data.put("lastName", lastname);
		data.put("email", email);
		data.put("password", password);
		data.put("phone", password);
		data.put("userStatus", status);

		jsonArray.put(data);
		response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
				.body(jsonArray.toString()).when().post(URL + "/user/createWithArray");

	}

	@Step("Verify the status code {0} and verify message {1}")
	public void verifyStatusCode(int expectedStatusCode) {
		SerenityRest.restAssuredThat(response ->
			response.statusCode(expectedStatusCode));

	}

	@Step("Get the user by user name {0}")
	public void GetUser(String Username) {
		JSONObject data = new JSONObject();
		data.put("username", Username);
		response = SerenityRest.given().header("accept", "application/json")
				.when().get(URL + "/user/" + Username);
//				.then().header("Content-Type","application/json");
//		String content_type=SerenityRest.then().extract().header("Content-Type");
//		response.getHeaders().get("Content-Type").getValue();

	}

//	@Step("The user checks for a header")
//	public void CheckHeader(String header) {
//		assertThat(response.getHeaders().hasHeaderWithName(header)).isTrue();
//	}

	@Step("The user checks for a header content type")
	public void CheckContentType(String contentType){
		String v1 = response.getHeaders().get("Content-Type").getValue();
		String v2 = response.getHeaders().getValue("Content-Type");
		assertThat(v2).isEqualTo(contentType);

	}

	@Step("The user does login")
	public void UserLogin(String Username,String Password){
		response = SerenityRest.given().contentType("application/json").header("accept", "application/json")
				.when().get(URL + "/user/login?" + Username +"&" + Password);

	}

	@Step("The user logs out")
	public void logout_user(){
		response = SerenityRest.given().contentType("application/json").header("accept", "application/html")
				.when().get(URL + "/user/logout");

	}


	@Step("The user does failed login")
	public void UserLogin_fail(String Username,String Password){
		response = SerenityRest.given().contentType("application/json").header("accept", "application/html")
				.when().get(URL + "/user/login?" + Username +"&" + Password);

	}

	@Step("Get the session ID for User")
	public void GetSessionID() {
		String responseBody = response.getBody().asString();
		JsonPath jsonPath = new JsonPath(responseBody);
		String message = jsonPath.getString("message");
	}

	@Step("Modify Details of the user")
	public void ModifyDetails(String CurrentUsername,int id,String username,String firstname,String lastname,String email,String password,String phone,int status) {
		JSONObject data = new JSONObject();

		data.put("id",id);
		data.put("username", username);
		data.put("firstName", firstname);
		data.put("lastName", lastname);
		data.put("email", email);
		data.put("password", password);
		data.put("phone", password);
		data.put("userStatus", status);

		response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
				.body(data.toString()).when().put(URL + "/user/" + CurrentUsername);
	}

	@Step("Delete the user")
	public void DeleteUser(String UserName) {
		response = SerenityRest.given().header("accept", "application/json")
				.when().get(URL + "/user/" + UserName);
	}

	@Step("Verify the user name {0}")
	public void verifyName(String expectedName) {
		SerenityRest.restAssuredThat(response -> response.body("firstName", equalTo(expectedName)));
	}


	@Step("Verify the message {0}")
	public void verifyMessage(String expectedMessage) {
		SerenityRest.restAssuredThat(response -> response.body("message", equalTo(expectedMessage)));
	}

}
