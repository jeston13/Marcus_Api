package org.example.SerenityWithRestAssuredDemo.tests;

import org.example.SerenityWithRestAssuredDemo.steps.StoreSteps;
import org.junit.Test;
import org.junit.runner.RunWith;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
public class StoreTest {

	@Steps
	StoreSteps StoreSteps;

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
		StoreSteps.placeOrder(5,0,0,"2023-09-05T08:46:06.081+0000", "placed", true);
		StoreSteps.verifyStatusCode(200);
		StoreSteps.verifyId(5);

	}

	@Test
	@Title("Find Purchar order id")
	public void findOrderId(){
		StoreSteps.findpurchaseorderid(5);
		StoreSteps.verifyStatusCode(200);
	}

	@Test
	@Title("Find Purchar order id")
	public void findOrderIdInvalid(){
		StoreSteps.findpurchaseorderid(100);
		StoreSteps.verifyStatusCode(404);
	}

	@Test
	@Title("Gets All inventory")
	public  void getInventory(){
		StoreSteps.getInventory();
		StoreSteps.verifyStatusCode(200);
	}

//	@Test
//	@Title("Purchase by id")
//	public void purchasePetById() {
//		employeeSteps.purchaseOrder();
//		employeeSteps.verifyStatusCode(200);
//		employeeSteps.verifyId();
//	}

}
