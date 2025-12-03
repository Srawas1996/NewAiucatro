package Steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.base.Base;
import org.example.pages.APIIntegrations;
import org.junit.Assert;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class APIIntegrationSteps extends Base {
    APIIntegrations apiIntegrations = new APIIntegrations();

    @When("the user click on the Connection Card")
    public void theUserClickOnTheConnectionCard() {
        apiIntegrations.clickOnTheConnectionCard();
    }

    @Then("the connection name should be displayed")
    public void theUserCheckTheConnectionName() {
        String actualName = apiIntegrations.checkApiName();
        String expectedName = properties.getProperty("ConnectionName");
        Assert.assertEquals("❌ Connection name does not match", expectedName, actualName);
        System.out.println("✅ Connection name matches successfully: " + actualName);
    }

    @And("all action buttons should be enabled")
    public void theButtonsShouldBeEnable() {
        Assert.assertTrue("❌ Action buttons are not enabled", apiIntegrations.CheckTheButtonDisplayed());
    }

    @Then("The user Click on Clear On the Api Page")
    public void theUserClickOnClear() {
        apiIntegrations.clicksOnClear();
    }

    @And("The user checks if it the api is UnChecked")
    public void theUserChecksIfItTheApiIsUnChecked() {
        Assert.assertEquals("❌ Worng Validation Message", apiIntegrations.saveValidationMessage() , "Saved Successfully");

    }

    @Then("The user Click on Select All")
    public void theUserClickOnSelectAll() {
        apiIntegrations.clicksOnSelectAll();
    }

    @And("The user checks if it the api is checked")
    public void theUserChecksIfItTheApiIsChecked() {
        Assert.assertEquals("❌ Wrong Validation Message",
                apiIntegrations.saveValidationMessage(),
                "Saved Successfully");
    }

    @Then("The user search for exist api connection")
    public void theUserSearchForExistApiConnection() {
        Assert.assertTrue("❌ No Data Found" ,apiIntegrations.validateThatTheSearchIsExist());
    }

    @Then("The user Click on The Test Connection")
    public void theUserClickOnTheTestConnection() {
        String ActualName = apiIntegrations.clickOnTestConnection();
        String ExpectedName = "Connection is Healthy";
        Assert.assertEquals("❌ No Data Found", ExpectedName, ActualName);
    }

    @And("The user Check if the APi is in a untested status")
    public void theUserCheckIfTheAPiIsInAUntestedStatus() {
        Assert.assertTrue(apiIntegrations.checkTheApiInUntestedStatus());
    }

    @Then("the user Check if the api is in a healty status")
    public void theUserCheckIfTheApiIsInAHealtyStatus() {
        Assert.assertTrue(apiIntegrations.checkTheApiInHealthyStatus());
    }

    @And("The open the Api Field")
    public void theOpenTheApiField() {
        apiIntegrations.OpenTheApiFields();
    }

    @Then("The user checks if all the fields are checked")
    public void theUserChecksIfAllTheFieldsAreChecked() {
        Assert.assertTrue("❌ some of the data not checked",
                apiIntegrations.CheckTheFieldAreChecked());
    }

    @Then("The User Change to the Daily Frequent")
    public void theUserChangeToTheDailyFrequent() {
        apiIntegrations.changeTheFrequentToDaily();
        apiIntegrations.saveValidationMessage();
    }

    @Then("the User is Click on Force Sync and check the Validation")
    public void theUserIsClickOnForceSync() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = today.format(formatter);
        Assert.assertEquals("❌The Data not Checked", apiIntegrations.userClickOnForceSync(), "Sync completed at " + formattedDate);

    }

    @Then("the user check if the Data Table is appear")
    public void theUserCheckIfTheDataTableIsAppear() {
        Assert.assertTrue("❌ Force Sync Not Completed Successfully",
                apiIntegrations.userCheckDataTableIsExist());
    }

    @Then("the User Move To API Logs")
    public void theUserMoveToAPILogs() {
        apiIntegrations.switchToAPILogs();
    }

    @Then("The User Checks if Refresh Token is enable")
    public void theUserChecksIfRefreshTokenIsEnable() {
        Assert.assertTrue("❌ The Logs Page didn't open successfully" , apiIntegrations.validateThatTheRefreshTokenIsExist());
    }

    @And("the user unchecks any field")
    public void theUserUnchecksAnyField() {
        apiIntegrations.unCheckedSomeOfTheFields();
    }

    @And("the user saves the changes")
    public void theUserSavesTheChanges() {
        apiIntegrations.saveValidationMessage();
    }

    @When("the user opens the same API again")
    public void theUserOpensTheSameAPIAgain() throws InterruptedException {
        apiIntegrations.OpenTheApiFields();
    }

    @Then("the field should still be unchecked")
    public void theFieldShouldStillBeUnchecked() {
        Assert.assertTrue("Check box is unChecked", apiIntegrations.checkThatTheFieldAreUnchecked());
    }

    @When("The User Click on Clear")
    public void theUserClickOnClearInsideFields() throws InterruptedException {
        apiIntegrations.clearButtonInsideTheFields();
    }


    @When("The user Click on Select All Inside Fields")
    public void theUserClickOnSelectAllInsideFields() throws InterruptedException {
        apiIntegrations.selectAllInsideFields();
    }

    @Then("The User Should Check all fields")
    public void theUserShouldCheckAllFields() {
        Assert.assertTrue(apiIntegrations.checkThatTheFieldAreChecked());
    }

    @When("The User Click Cancel and reopen the changes should not saved")
    public void theUserClickCancelAndReopenTheChangesShouldNotSaved() {
        apiIntegrations =  new APIIntegrations();
        apiIntegrations.cancelButtonInsideFields();
    }

    @Then("The user check if the buttons are disable")
    public void theUserCheckIfTheButtonsAreDisable() {
        Assert.assertTrue("The Buttons are not disable", apiIntegrations.CheckIfTheButtonsAreDisable());
    }
}
