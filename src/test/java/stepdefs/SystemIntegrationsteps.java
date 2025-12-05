package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.base.Base;
import org.example.pages.LoginPage;
import org.example.pages.SystemIntegration;
import org.junit.Assert;

import java.time.Duration;


public class SystemIntegrationsteps extends Base {
    LoginPage loginPage;
    SystemIntegration systemIntegration;


    @Given("the user is logged in to the website")
    public void theUserIsLoggedInToTheWebsite() {
        lanuchBrowser();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage();
        loginPage.PerformValidLogin();
    }


    @When("the user navigates to the Bot section and selects a Bot")
    public void theUserNavigatesToTheBotSectionAndSelectsABot() {
        systemIntegration = new SystemIntegration();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        systemIntegration.ClickOnBotDropDownList();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        systemIntegration.ClickOnBotName();
    }

    @And("the user clicks on Integration and then System")
    public void theUserClicksOnIntegrationAndThenSystem() {
        systemIntegration = new SystemIntegration();
        systemIntegration.ClickOnIntegrations();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        systemIntegration.ClickOnSystem();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @And("the user clicks on Add New Connection")
    public void theUserClicksOn() {
        systemIntegration = new SystemIntegration();
        systemIntegration.ClickOnNewConnection();
    }

    @And("the user fills in all required fields with valid information")
    public void theUserFillsInAllRequiredFieldsWithValidInformation() {
        systemIntegration = new SystemIntegration();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        systemIntegration.SystemTypeDropDownList();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        systemIntegration.SelectCanvas();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        systemIntegration.SetConnectionName();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        systemIntegration.setSetClientID();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        systemIntegration.SetClientSecret();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        systemIntegration.SetBaseURL();
    }

    @And("the user submits the form")
    public void theUserSubmitsTheForm() {
        systemIntegration = new SystemIntegration();
        systemIntegration.setClickOnConnect();
        String messageValidation = systemIntegration.CheckTheMessageIsdisplay();
        Assert.assertEquals("Connection validation message is wrong",
                "Credentials are invalid. Please enter the correct credentials.",
                messageValidation);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Then("the system should create the new connection successfully")
    public void theSystemShouldCreateTheNewConnectionSuccessfully() {
        systemIntegration = new SystemIntegration();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String messageValidation = systemIntegration.CheckTheMessageIsdisplay();
        Assert.assertEquals("Connection validation message is wrong", "Success", messageValidation);
    }

    @Then("Leave the fields Empty")
    public void leaveTheFieldsEmpty() {
        systemIntegration = new SystemIntegration();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        systemIntegration.SystemTypeDropDownList();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        systemIntegration.SelectCanvas();
        systemIntegration.CheckValidations();
    }


    @Then("Click on the Delete Button")
    public void clickOnTheDeleteButton() {
        systemIntegration = new SystemIntegration();
        systemIntegration.DeleteButton();
    }

    @And("Click Confirm")
    public void clickConfirm() {
        systemIntegration = new SystemIntegration();
        systemIntegration.ConfirmButton();
    }

    @And("the user clicks on Edit System Connection")
    public void theUserClicksOnEditSystemConnection() {
        systemIntegration = new SystemIntegration();
        systemIntegration.EditTheConnection();
    }

    @When("the user modifies one or more field values")
    public void theUserModifiesOneOrMoreFieldValues() {
        systemIntegration = new SystemIntegration();
        systemIntegration.NameModify("New");
        String validationMessage = systemIntegration.EditConnect();
        Assert.assertEquals("Wrong Validation Message","Success",validationMessage);
    }

    @Then("a confirmation or validation popup message should appear")
    public void aConfirmationOrValidationPopupMessageShouldAppear() {
        systemIntegration = new SystemIntegration();
        String validationMessage = systemIntegration.ValidationCheckEdit();
        Assert.assertEquals("Wrong Validation Message","Success",validationMessage);
    }


    @And("the user selects the Semester from the dropdown")
    public void theUserSelectsTheSemesterFromTheDropdown() {
        systemIntegration = new SystemIntegration();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        systemIntegration.SelectOnSemester();
        systemIntegration.SubmitTheSemester();
    }

    @And("the user should see the newly created connection listed in the System Connections table")
    public void theUserShouldSeeTheNewlyCreatedConnectionListedInTheSystemConnectionsTable() {
        systemIntegration = new SystemIntegration();
        String ExpectedData = properties.getProperty("ConnectionName");
        String ActualData = systemIntegration.CheckTheConnectionName();
        Assert.assertEquals("Wrong Connection Name" , ExpectedData, ActualData);
    }

    @And("the user should see the edited created connection listed in the System Connections table")
    public void theUserShouldSeeTheEditedCreatedConnectionListedInTheSystemConnectionsTable() {
        systemIntegration = new SystemIntegration();
        String ExpectedData = properties.getProperty("ConnectionName")+"New";
        String ActualData = systemIntegration.CheckTheConnectionName();
        Assert.assertEquals("Wrong Connection Name" , ExpectedData, ActualData);
    }

    @And("the user edit selects the Semester from the dropdown")
    public void theUserEditSelectsTheSemesterFromTheDropdown() {
        systemIntegration = new SystemIntegration();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        systemIntegration.SelectOnSemester();
        systemIntegration.SelectSecondSemester();
        systemIntegration.SubmitTheSemester();
    }

    @And("Check the Delete Message")
    public void checkTheDeleteMessage() {
        systemIntegration = new SystemIntegration();
        String deleteValidationMessage = systemIntegration.DeleteValidationMessage();
        Assert.assertEquals("Wrong Validation Message", "Integrated System is successfully deleted.",  deleteValidationMessage);

    }

    @Then("the user check the duplicate Message")
    public void theUserCheckTheDuplicateMessage() {
        systemIntegration = new SystemIntegration();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        String validationMessage = systemIntegration.DuplicateValidationMessageM();
        Assert.assertEquals("Wrong Validation Message", "A connection with these credentials already exists" ,  validationMessage);
        System.out.println("Correct Validation Message:" + validationMessage);
    }

    @And("the user clicks on Add New Connection Duplicate")
    public void theUserClicksOnConnection() {
        systemIntegration = new SystemIntegration();
        systemIntegration.ClickOnNewConnection();
    }

    @And("the user submits the formTwo")
    public void theUserSubmitsTheFormTwo() {
        systemIntegration = new SystemIntegration();
        systemIntegration.SecondCreationConnect();

    }

    @Then("The user Switch the system to Disconnect System")
    public void theUserSwitchTheSystemToDisconnectSystem() {
        systemIntegration = new SystemIntegration();
        String validationMessage = systemIntegration.SwitchToDisconnectSystem();
        Assert.assertEquals("Wrong Validation Message", "System diconnected successfully", validationMessage);
    }

    @Then("The user Switch the system to connect System")
    public void theUserSwitchTheSystemToConnectSystem() {
        systemIntegration = new SystemIntegration();
        String validationMessage = systemIntegration.SwitchToConnectSystem();
        Assert.assertEquals("Wrong Validation Message", "System connected successfully", validationMessage);
    }

    @Then("the user clicks cancel")
    public void theUserClicksCancel() {
        systemIntegration = new SystemIntegration();
        systemIntegration.UserClickCancel();
    }

    @Then("the user search for exist Connection")
    public void theUserSearchForExistConnection() {
        systemIntegration = new SystemIntegration();
        systemIntegration.SearchForConnection(properties.getProperty("ConnectionName"));
        String ExpectedData = properties.getProperty("ConnectionName");
        String ActualData = systemIntegration.CheckTheConnectionName();
        Assert.assertEquals("Wrong Connection Name" , ExpectedData, ActualData);
    }

    @Then("the user search for None exist Connection")
    public void theUserSearchForNoneExistConnection() {
        systemIntegration = new SystemIntegration();
        systemIntegration.SearchForConnection("None Exist Name");
        systemIntegration.IconFormNoSystemFound();
    }

    @Then("The user check if the details are exist in the connection")
    public void theUserCheckIfTheDetailsAreExistInTheConnection() {
        systemIntegration = new SystemIntegration();
        String ExpectedDataURL = properties.getProperty("BaseURLCanvas");
        String ActualDataURL = systemIntegration.GetBaseUrlTest();
        String ExpectedDataSystemType = "Canvas";
        String ActualDataSystemType = systemIntegration.GetSystemType();
        Assert.assertEquals("Wrong Url" , ExpectedDataURL, ActualDataURL);
        System.out.println("Correct URL");
        Assert.assertEquals("Wrong System Type" , ExpectedDataSystemType, ActualDataSystemType);
        System.out.println("Correct System Type");
    }

    @And("the user fills in all required fields with Invalid information")
    public void theUserFillsInAllRequiredFieldsWithInvalidInformation() {
        systemIntegration = new SystemIntegration();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        systemIntegration.SystemTypeDropDownList();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        systemIntegration.SelectCanvas();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        systemIntegration.SetConnectionName();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        systemIntegration.setInvalidClientID();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        systemIntegration.SetClientSecret();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        systemIntegration.SetBaseURL();
    }

    @And("the user Check if there Semesters that are exist")
    public void theUserCheckIfThereSemestersThatAreExist() {
        systemIntegration = new SystemIntegration();
        boolean noVisibleSemesterButtons = systemIntegration.semesterButtonAreNotDisplayed();

        Assert.assertTrue("Some semester buttons are still visible or clickable", noVisibleSemesterButtons);
        System.out.println("No visible/clickable semester buttons? " + noVisibleSemesterButtons);
    }

    @And("the user submits invalid form")
    public void theUserSubmitsInvalidForm() {
        systemIntegration = new SystemIntegration();
        systemIntegration.setClickOnConnect();
    }
}
