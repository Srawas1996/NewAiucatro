package org.example.pages;

import org.example.base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;


public class KnowledgeBases extends Base {

    @FindBy(xpath = "//p[normalize-space()='Knowledge Base']")
    WebElement knowledgeBaseButton;

    @FindBy(xpath = "//input[contains(@class,'SearchInput')]")
    WebElement searchBar;

    @FindBy(xpath = "//span[normalize-space()='Create Folder']")
    WebElement createFolderButton;

    @FindBy(xpath = "//span[normalize-space()='Add Data Source']")
    WebElement addDataSourceButton;

    @FindBy(xpath = "//tbody/tr[1]/td[5]/div[1]/span[1]/div[1]//*[name()='svg']")
    WebElement deleteTheFirstElement;

    @FindBy(xpath = "//div[contains(text(),'AutomationFolder')]")
    WebElement goInsideTheFolder;

    @FindBy(xpath = "//div[contains(text(),'AutomationFolder')]")
    WebElement folderName;

    @FindBy(xpath = "//p[normalize-space()='No Data Found']")
    WebElement iconCheck;

    //Folder Window
    @FindBy(name = "folderName")
    WebElement folderNameField;

    @FindBy(xpath = "//span[normalize-space()='Save']")
    WebElement saveFolderName;

    @FindBy(xpath = "//tbody/tr/td[1]/div[1]")
    WebElement docFolderName;

    //Data Source
    @FindBy(xpath = "//input[@id='select-FolderId']")
    WebElement folderLocation;

    @FindBy(xpath = "(//div[@class='toasts-wrapper'])[1]")
    WebElement validationMessage;

    @FindBy(xpath = "//span[normalize-space()='delete']")
    WebElement ConfirmationDelete;

    @FindBy(xpath = "//input[@id='fileInput']")
    WebElement uploadTheFile;

    @FindBy(xpath = "(.//*[normalize-space(text()) and normalize-space(.)='Folder Location'])[1]/following::div[5]")
    WebElement selectTheFolderLocation;

    public KnowledgeBases(){
        PageFactory.initElements(driver,this);
    }

    public void searchMethod(String folderTitleName){
        searchBar.sendKeys(folderTitleName);
    }

    public void iconCheck(){
        iconCheck.isDisplayed();
    }

    public void knowledgeBaseButton(){
        knowledgeBaseButton.click();
    }

    public void folderCreation(){
        createFolderButton.click();
    }

    public void fillTheNameForTheFolder(String folderTitleName) throws InterruptedException {
        folderNameField.sendKeys(folderTitleName);
        Thread.sleep(1000);
        saveFolderName.click();
        Thread.sleep(10000);
        validationMessage.isDisplayed();
    }

    public void checkThatTheFolderIsExist(){
        folderName.isDisplayed();
    }

    public void DeleteTheFolder(){
        deleteTheFirstElement.click();
    }

    public void confirmTheDeleteAndValidationError(){
        ConfirmationDelete.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(validationMessage));
        validationMessage.isDisplayed();
    }

    public void clickOnUploadTheFile(){
        File uploadFile = new File("src/main/files/AI chatbot - Sample Questions 1.docx");
        String absolutePath = uploadFile.getAbsolutePath();
        uploadTheFile.sendKeys(absolutePath);
    }

    public void clickOnFolderLocation(){
        folderLocation.click();
        folderLocation.sendKeys("Automation");

    }

    public void saveTheChanges() throws InterruptedException {
        selectTheFolderLocation.click();
        Thread.sleep(7000);
        saveFolderName.click();
        validationMessage.isDisplayed();
    }

    public void ValidationMessageAppear(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(validationMessage));
        validationMessage.isDisplayed();
    }

    public void addDataSource(){
        addDataSourceButton.click();
    }

    public void goInsideTheFolders(){
        goInsideTheFolder.click();
    }

    public String checkTheFolderName(){
        return docFolderName.getText();
    }
}
