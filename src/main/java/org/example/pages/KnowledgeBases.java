package org.example.pages;

import org.example.base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;


public class KnowledgeBases extends Base {

    @FindBy(xpath = "//p[normalize-space()='Knowledge Base']")
    WebElement knowledgeBaseButton;

    @FindBy(xpath = "//input[contains(@class,'SearchInput')]")
    WebElement searchBar;

    @FindBy(xpath = "//span[normalize-space()='Create Folder']")
    WebElement createFolderButton;

    @FindBy(xpath = "//span[normalize-space()='Add Data Source']")
    WebElement addDataSourceButton;

    @FindBy(xpath = "(//*[name()='svg'][contains(@class,'Component_icon__Tvnr7 Component_delete-icon__EmjRB')])[1]")
    WebElement deleteTheFirstElement;

    @FindBy(xpath = "//tbody/tr[1]")
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

    @FindBy(xpath = "//div[contains(@class,'Component_imgCont__Xo3N6')]")
    WebElement docFolderName;

    //Data Source
    @FindBy(xpath = "//input[@id='select-FolderId']")
    WebElement folderLocation;

    @FindBy(xpath = "//p[@class='CustomizedToast_toast-title__V4v0S']")
    WebElement deleteValidationMessage;

    @FindBy(xpath = "//span[normalize-space()='delete']")
    WebElement ConfirmationDelete;

    @FindBy(xpath = "//input[@id='fileInput']")
    WebElement uploadTheFile;

    @FindBy(xpath = "(//div[contains(@class,'')][normalize-space()='AutomationFolder'])[3]")
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

    public void fillTheNameForTheFolder(String folderTitleName){
        folderNameField.sendKeys(folderTitleName);
        saveFolderName.click();
    }

    public void checkThatTheFolderIsExist(){
        folderName.isDisplayed();
    }

    public void DeleteTheFolder(){
        deleteTheFirstElement.click();
    }

    public void confirmTheDeleteAndValidationError(){
        ConfirmationDelete.click();
        deleteValidationMessage.isDisplayed();
    }

    public void clickOnUploadTheFile(){
        File uploadFile = new File("C:\\Users\\SalimAlRawas\\Downloads\\AI chatbot - Sample Questions 1.docx");
        String absolutePath = uploadFile.getAbsolutePath();
        uploadTheFile.sendKeys(absolutePath);
    }

    public void clickOnFolderLocation(){
        folderLocation.click();
        selectTheFolderLocation.click();
    }

    public void saveTheChanges(){
        saveFolderName.click();
    }

    public void ValidationMessageAppear(){
        deleteValidationMessage.isDisplayed();
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
