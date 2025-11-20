package Steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.base.Base;
import org.example.pages.APIIntegrations;

import java.time.Duration;


public class APIIntegrationSteps extends Base {
    APIIntegrations apiIntegrations;


    @Then("the user click on the Connection Card")
    public void theUserClickOnTheConnectionCard() {
        apiIntegrations = new APIIntegrations();
        apiIntegrations.clickOnTheConnectionCard();
    }

    @Then("the user check the connection name")
    public void theUserCheckTheConnectionName() {
        apiIntegrations = new APIIntegrations();
        apiIntegrations.CheckingTheName().equals(properties.getProperty("ConnectionName"));
    }

    @And("The Buttons Should be enable")
    public void theButtonsShouldBeEnable() {
        apiIntegrations = new APIIntegrations();
        apiIntegrations.CheckTheButtonDisplayed();
    }

    @Then("The user search for exist api connection")
    public void theUserSearchForExistApiConnection() {
        apiIntegrations = new APIIntegrations();
        apiIntegrations.validateThatTheSearchIsExist();
    }

    @Then("The user Click on The Test Connection")
    public void theUserClickOnTheTestConnection() {
        apiIntegrations = new APIIntegrations();
        apiIntegrations.clickOnTestConnection();
    }

    @Then("The user Click on Select All")
    public void theUserClickOnSelectAll() {
        apiIntegrations = new APIIntegrations();
        apiIntegrations.ClicksOnSelectAll();
    }

    @And("The user checks if it the api is checked")
    public void theUserChecksIfItTheApiIsChecked() {
        apiIntegrations = new APIIntegrations();
        apiIntegrations.checkIfTheApiIsSelected();
    }

    @And("The open the Api Field")
    public void theOpenTheApiField() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        apiIntegrations = new APIIntegrations();
        apiIntegrations.OpenTheApiFields();
    }

    @Then("The user checks if all the fields are checked")
    public void theUserChecksIfAllTheFieldsAreChecked() {
        apiIntegrations = new APIIntegrations();
        apiIntegrations.CheckTheFieldAreChecked();
    }

    @Then("The User Change to the Daily Frequent")
    public void theUserChangeToTheDailyFrequent() {
        apiIntegrations = new APIIntegrations();
        apiIntegrations.changeTheFrequentToDaily();
    }

    @Then("the User is Click on Force Sync and check the Validation")
    public void theUserIsClickOnForceSync() {
        apiIntegrations = new APIIntegrations();
        apiIntegrations.userClickOnForceSync();
    }

    @Then("the User Move To API Logs")
    public void theUserMoveToAPILogs() {
        apiIntegrations = new APIIntegrations();
        apiIntegrations.switchToAPILogs();
    }

    @Then("The User Checks if Refresh Token is enable")
    public void theUserChecksIfRefreshTokenIsEnable() {
        apiIntegrations = new APIIntegrations();
        apiIntegrations.validateThatTheRefreshTokenIsExist();
    }

    @And("The User Checks if View is enable")
    public void theUserChecksIfViewIsEnable() {
        apiIntegrations = new APIIntegrations();
        apiIntegrations.validateThatTheViewButtonIsExist();
    }

    @Then("The user Click on Clear On the Api Page")
    public void theUserClickOnClear() {
        apiIntegrations = new APIIntegrations();
        apiIntegrations.ClicksOnClear();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        apiIntegrations.SaveValidationMessage();
    }

    @And("The user checks if it the api is UnChecked")
    public void theUserChecksIfItTheApiIsUnChecked() {
        apiIntegrations = new APIIntegrations();
        if (apiIntegrations.checkIfTheApiIsUnSelected()){
            System.out.println("The APi is Checked");
        }
        else{
            System.out.println("The APi is UnChecked");
        }
    }

    @And("the user unchecks any field")
    public void theUserUnchecksAnyField() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        apiIntegrations = new APIIntegrations();
        apiIntegrations.unCheckedSomeOfTheFields();
    }

    @And("the user saves the changes")
    public void theUserSavesTheChanges() {
        apiIntegrations = new APIIntegrations();
        apiIntegrations.saveTheFields();
        apiIntegrations.fieldValidationMessage();
    }

    @When("the user opens the same API again")
    public void theUserOpensTheSameAPIAgain() {
        apiIntegrations = new APIIntegrations();
        apiIntegrations.OpenTheApiFields();
    }

    @Then("the field should still be unchecked")
    public void theFieldShouldStillBeUnchecked() {
        apiIntegrations = new APIIntegrations();
        if(apiIntegrations.checkThatTheFieldAreUnchecked()){
            System.out.println("The Field are uncheck Correctly");
        }
        else{
            System.out.println("The Fields Are not changed");
        }
    }

    @When("The User Click on Clear")
    public void theUserClickOnClearInsideFields() throws InterruptedException {
        apiIntegrations = new APIIntegrations();
        apiIntegrations.clearButtonInsideTheFields();
    }


    @When("The user Click on Select All Inside Fields")
    public void theUserClickOnSelectAllInsideFields() throws InterruptedException {
        apiIntegrations =  new APIIntegrations();
        apiIntegrations.selectAllInsideFields();
    }

    @Then("The User Should Check all fields")
    public void theUserShouldCheckAllFields() {
        apiIntegrations =  new APIIntegrations();
        if (apiIntegrations.checkThatTheFieldAreChecked()){
            System.out.println("The Fields Are Checked Correctly");
        }
        else{
            System.out.println("The Fields Are Not Checked");
        }
    }

    @When("The User Click Cancel and reopen the changes should not saved")
    public void theUserClickCancelAndReopenTheChangesShouldNotSaved() {
        apiIntegrations =  new APIIntegrations();
        apiIntegrations.cancelButtonInsideFields();
    }

    @Then("The user check if the buttons are disable")
    public void theUserCheckIfTheButtonsAreDisable() {
        apiIntegrations =  new APIIntegrations();
        if (apiIntegrations.CheckIfTheButtonsAreDisable()){
            System.out.println("The buttons are enable");
        }
        else{
            System.out.println("Buttons are disable");
        }

    }

}
