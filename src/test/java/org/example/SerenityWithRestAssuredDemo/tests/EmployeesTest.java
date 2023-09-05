package org.example.SerenityWithRestAssuredDemo.tests;

import org.example.SerenityWithRestAssuredDemo.steps.EmployeeSteps;
import org.junit.Test;
import org.junit.runner.RunWith;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
public class EmployeesTest {

	@Steps
	EmployeeSteps employeeSteps;

	//@Test
//	@Title("Get User")
//	public void verifyValidUser() {
//		employeeSteps.sendUser(1);
//		employeeSteps.verifyStatusCode(200);
//		employeeSteps.verifyId(1);
//		employeeSteps.verifyName("Tiger Nixon");
//		employeeSteps.verifyAge(61);
//		employeeSteps.verifySalary(320800);
//		employeeSteps.verifyMessage("Successfully! Record has been fetched.");
//
//	}

	@Test
	@Title("Place Order")
	public void createValidUser() {
		employeeSteps.placeOrder(5,0,0,"2023-09-05T08:46:06.081+0000", "placed", true);
		employeeSteps.verifyStatusCode(200);
		employeeSteps.verifyId(5);

	}

	@Test
	@Title("Purchase by id")
	public void purchasePetById() {
		employeeSteps.purchaseOrder();
		employeeSteps.verifyStatusCode(200);
		employeeSteps.verifyId();
	}

}
