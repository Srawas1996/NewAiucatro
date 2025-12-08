package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.base.Base;
import org.example.pages.KnowledgeBases;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class knowledgeBaseSteps extends Base {
    KnowledgeBases knowledgeBases;

    @Then("search for exist Knowledge base")
    public void searchForExistKnowledgeBase(){
        knowledgeBases = new KnowledgeBases();
        knowledgeBases.searchMethod("AutomationFolder");
    }

    @And("The user Clicks On The Knowledge Base")
    public void theUserClicksOnTheKnowledgeBase() {
        knowledgeBases = new KnowledgeBases();
        knowledgeBases.knowledgeBaseButton();
    }

    @And("check the knowledge base is exist")
    public void checkTheKnowledgeBaseIsExist() {
        knowledgeBases = new KnowledgeBases();
        knowledgeBases.checkThatTheFolderIsExist();
    }

    @Then("search for None exist Knowledge base")
    public void searchForNoneExistKnowledgeBase() {
        knowledgeBases = new KnowledgeBases();
        knowledgeBases.searchMethod("Not exist Folder");
    }

    @And("check the Icon is appear")
    public void checkTheIconIsAppear() {
        knowledgeBases = new KnowledgeBases();
        knowledgeBases.iconCheck();
    }

    @Then("Click on Create Folder")
    public void clickOnCreateFolder() {
        knowledgeBases= new KnowledgeBases();
        knowledgeBases.folderCreation();
    }

    @And("Fill all the information and click save")
    public void fillAllTheInformationAndClickSave() throws InterruptedException {
        knowledgeBases= new KnowledgeBases();
        knowledgeBases.fillTheNameForTheFolder("AutomationFolder");
    }

    @Then("Check the Validation Error")
    public void checkTheValidationError() {
        knowledgeBases= new KnowledgeBases();
        knowledgeBases.checkThatTheFolderIsExist();
    }

    @When("The User click on Delete Icon")
    public void theUserClickOnDeleteIcon() {
        knowledgeBases= new KnowledgeBases();
        knowledgeBases.DeleteTheFolder();
    }

    @Then("Click on Confirmation Delete and Check the validation message")
    public void clickOnConfirmationDelete() {
        knowledgeBases= new KnowledgeBases();
        knowledgeBases.confirmTheDeleteAndValidationError();
    }


    @And("The user select the folder location")
    public void theUserSelectTheFolderLocation() {
        knowledgeBases= new KnowledgeBases();
        knowledgeBases.clickOnUploadTheFile();
        knowledgeBases.clickOnFolderLocation();

    }

    @When("the user click save")
    public void theUserClickSave() throws InterruptedException {
        knowledgeBases= new KnowledgeBases();
        Thread.sleep(5000);
        knowledgeBases.saveTheChanges();
    }

    @Then("the user should check if the folder is uploaded")
    public void theUserShouldCheckIfTheFolderIsUploaded() throws InterruptedException {
        knowledgeBases= new KnowledgeBases();
        Thread.sleep(10000);
        knowledgeBases.ValidationMessageAppear();
    }

    @Then("Click on add Data Source")
    public void clickOnAddDataSource() {
        knowledgeBases= new KnowledgeBases();
        knowledgeBases.addDataSource();
    }

    @Then("The user clicks on the folder")
    public void theUserClicksOnTheFolder() {
        knowledgeBases= new KnowledgeBases();
        knowledgeBases.goInsideTheFolders();
        knowledgeBases.checkTheFolderName().equals("AI chatbot - Sample Questions 1.docx");
    }
}
