package org.example.SerenityWithRestAssuredDemo.tests;

import org.example.SerenityWithRestAssuredDemo.steps.PetSwaggerSteps;
import org.junit.Test;
import org.junit.runner.RunWith;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
public class PetSwaggerTest {

	@Steps
	PetSwaggerSteps petSwaggerSteps;

	@Test
	@Title("Create User")
	public void createValidUser() {
		petSwaggerSteps.createUser(0,"test123","test","123","test@abc.com","test123","1234567890",0);
		petSwaggerSteps.verifyStatusCode(200);
		petSwaggerSteps.verifyMessage("ok");
	}

	@Test
	@Title("Get User By Name")
	public void GetValidUser() {
		petSwaggerSteps.GetUser("test123");
		petSwaggerSteps.verifyStatusCode(200);
		petSwaggerSteps.verifyName("test");
		petSwaggerSteps.CheckContentType("application/json");
		//petSwaggerSteps.CheckHeader('ContentType');
	}


	@Test
	@Title("User Login - Success")
	public void Login() {
		petSwaggerSteps.UserLogin("test123","1234567890");
		petSwaggerSteps.verifyStatusCode(200);
		petSwaggerSteps.GetSessionID();
	}

	@Test
	@Title("User Login - Failure")
	public void Login_fail() {
		petSwaggerSteps.UserLogin_fail("","");
		petSwaggerSteps.verifyStatusCode(500);
	}

//	@Test
//	@Title("Logout User")
//	public void Logout() {
//		petSwaggerSteps.logout_user();
//		petSwaggerSteps.verifyStatusCode(200);
//	}


	@Test
	@Title("Change User Details - Success")
	public void ChangeUserDetails() {
		petSwaggerSteps.ModifyDetails("test123",0,"test789","test","789","test@abc.com","test789","1234567890",0);
		petSwaggerSteps.verifyStatusCode(200);
	}

	@Test
	@Title("Delete the user - Success")
	public void DeleteUser_Success() {
		petSwaggerSteps.DeleteUser("test789");
		petSwaggerSteps.verifyStatusCode(200);
	}

	@Test
	@Title("Delete the user - User Not Found")
	public void DeleteUser_UserNotFound() {
		petSwaggerSteps.DeleteUser("t");
		petSwaggerSteps.verifyStatusCode(404);
	}

//	@Test
//	@Title("Delete the user - User Invalid")
//	public void DeleteUser_UserInvalid() {
//		petSwaggerSteps.DeleteUser("t");
//		petSwaggerSteps.verifyStatusCode(400);
//	}
}
