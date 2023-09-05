package org.example.SerenityWithRestAssuredDemo.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.example.SerenityWithRestAssuredDemo.steps.EmployeeSteps;
import org.example.SerenityWithRestAssuredDemo.steps.PetstoreSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(SerenityRunner.class)
public class PetTest {




    @Steps
    PetstoreSteps petstoreSteps;


    @Test
    @Title("Create Pet")
    public void createValidPet() {
        petstoreSteps.createPet("charlizard");
        petstoreSteps.verifyStatusCode(200);
    }

    @Test
    @Title("Update the pet")
    public  void  updatePet(){
        petstoreSteps.createPet("Pokemon");
        petstoreSteps.getId();
        petstoreSteps.updatePet("Heelo");
        petstoreSteps.verifyStatusCode(200);
    }
    @Test
    @Title("get the pet")
    public  void  getPet(){
        petstoreSteps.createPet("Pokemon");
        petstoreSteps.getId();
        petstoreSteps.getPet();
        petstoreSteps.verifyStatusCode(200);
    }

    @Test
    @Title("get the pet with invalid id")
    public  void  getPetInvalid(){
        petstoreSteps.createPet("Pokemon");
        petstoreSteps.getId();
        petstoreSteps.getPetInvalid();
        petstoreSteps.verifyStatusCode(404);
    }
    @Test
    @Title("get pet with status")
    public  void  getPetStatus(){

        petstoreSteps.getStatusPet();
        petstoreSteps.verifyStatusCode(200);
    }
    @Test
    @Title("Deleting the pet")
    public void deletePet()
    {
        petstoreSteps.createPet("Pikachu");
        petstoreSteps.getId();
        petstoreSteps.deletePetWithId();
        petstoreSteps.verifyStatusCode(200);
    }

    @Test
    @Title("Deleting the wioth invalid pet id")
    public void deletePetInv()
    {
        petstoreSteps.createPet("Pikachu");
        petstoreSteps.getId();
        petstoreSteps.deletePetWithIdInvalid();
        petstoreSteps.verifyStatusCode(404);
    }


    @Test
    @Title("Deleting the wioth invalid header")
    public void deletePetInvHeader()
    {
        petstoreSteps.createPet("Pikachu");
        petstoreSteps.getId();
        petstoreSteps.deletePetWithIdInvalid("html");
        petstoreSteps.verifyStatusCode(404);
    }


    @Test
    @Title("Updating the Pet with Id")
    public void UpdatePetWithId(){
        petstoreSteps.createPet("lion");
        petstoreSteps.getId();
        petstoreSteps.updatePetWithId();
        petstoreSteps.verifyStatusCode(200);
    }

    @Test
    @Title("Updating the Pet with invalid Id")
    public void UpdatePetWithIdInvalid(){
        petstoreSteps.createPet("lion");
        petstoreSteps.getId();
        petstoreSteps.updatePetWithIdInvalid();
        petstoreSteps.verifyStatusCode(404);
    }
    @Test
    @Title("Updating the Pet with invalid Header")
    public void UpdatePetWithIdInvalidHeader(){
        petstoreSteps.createPet("lion");
        petstoreSteps.getId();
        petstoreSteps.updatePetWithIdInvalid("json");
        petstoreSteps.verifyStatusCode(415);
    }


}
