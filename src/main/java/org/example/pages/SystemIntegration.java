package org.example.pages;


import org.example.base.Base;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SystemIntegration extends Base {
    @FindBy(xpath = "//div[contains(@class,'Sidebar_dropdown-container')]")
    WebElement ClickOnBotList;

    @FindBy(xpath = "//div[@role='option']//span[normalize-space(text())='real dashboard Test External']")
    WebElement ClickOnBotName;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/nav[1]/div[1]/div[4]/div[1]/div[1]/div[6]/div[1]/p[1]")
    WebElement ClickOnIntegrations;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/nav[1]/div[1]/div[4]/div[1]/div[1]/div[7]/div[1]/a[1]/span[1]")
    WebElement ClickOnSystem;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/p[1]")
    WebElement CheckTheMessage;

    @FindBy(xpath = "//span[normalize-space()='New Connection']")
    WebElement ClickOnNewConnection;

    @FindBy(xpath = "/html[1]/body[1]/div[2]/div[1]/div[1]/div[3]/form[1]/div[1]/div[2]")
    WebElement SystemTypeDropDownList;

    @FindBy(xpath = "//div[normalize-space(text())='Canvas']")
    WebElement SelectCanvas;

    @FindBy(xpath = "/html[1]/body[1]/div[2]/div[1]/div[1]/div[3]/form[1]/div[2]/div[1]/div[1]/input[1]")
    WebElement SetConnectionName;

    @FindBy(xpath = "/html[1]/body[1]/div[2]/div[1]/div[1]/div[3]/form[1]/div[3]/div[1]/div[1]/input[1]")
    WebElement SetClientID;

    @FindBy(xpath = "/html[1]/body[1]/div[2]/div[1]/div[1]/div[3]/form[1]/div[4]/div[1]/div[1]/input[1]")
    WebElement SetClientSecret;

    @FindBy(xpath = "/html[1]/body[1]/div[2]/div[1]/div[1]/div[3]/form[1]/div[5]/div[1]/div[1]/input[1]")
    WebElement SetBaseURL;

    @FindBy(xpath = "(//button[@type='button'])[3]")
    WebElement ClickOnConnect;

    @FindBy(xpath = "(//button[@type='button'])[4]")
    WebElement SecondConnect;

    @FindBy(xpath = "//span[normalize-space()='Connect']")
    WebElement ClickOnConnectEdit;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/span[2]")
    WebElement DeleteButton;

    @FindBy(xpath = "//span[normalize-space()='Delete']")
    WebElement ConfirmButton;

    @FindBy(xpath = "//div[normalize-space()='Connection Name is required']")
    WebElement ConnectionNameValidation;

    @FindBy(xpath = "//div[normalize-space()='Client ID is required']")
    WebElement ClientIDValidation;

    @FindBy(xpath = "//div[normalize-space()='Client Secret is required']")
    WebElement ClientSecretValidation;

    @FindBy(xpath = "//div[normalize-space()='Enter a valid URL (e.g. https://example.com)']")
    WebElement BaseURLValidation;

    @FindBy(css = ".AgentCard_action-icon__S4oVl[width='21']")
    WebElement EditIcon;

    @FindBy(xpath = "(//span[@class='Checkbox_checkbox-square__51ruu undefined'])[5]")
    WebElement SelectSemester;

    @FindBy(xpath = "(//span[@class='Checkbox_checkbox-square__51ruu undefined'])[4]")
    WebElement SelectSemesterTwo;

    @FindBy(xpath = "//span[normalize-space()='Submit']")
    WebElement SubmitTheSemester;

    @FindBy(css = ".AgentCard_title__nUGLR")
    WebElement ConnectionName;

    @FindBy(name = "connectionName")
    WebElement ConnectionNameModify;

    @FindBy(css = ".CustomizedToast_toast-title__V4v0S")
    WebElement ValidationMessage;

    @FindBy(xpath = "//p[@class='CustomizedToast_toast-title__V4v0S']")
    WebElement DeletionValidation;

    @FindBy(css = ".CustomizedToast_toast-title__V4v0S")
    WebElement DuplicateValidationMessage;

    @FindBy(xpath = "//span[normalize-space()='Disconnect']")
    WebElement DisconnectButton;

    @FindBy(xpath = "(//span[@class='Button_label__i00gY'][normalize-space()='Disconnect'])[2]")
    WebElement ConfirmDisconnectButton;

    @FindBy(css = ".CustomizedToast_toast-title__V4v0S")
    WebElement DisconnectValidationMessage;

    @FindBy(xpath = "//span[normalize-space()='Connect']")
    WebElement connectButton;

    @FindBy(css = ".CustomizedToast_toast-title__V4v0S")
    WebElement connectValidationMessage;

    @FindBy(xpath = "//span[normalize-space()='Cancel']")
    WebElement CancelButton;

    @FindBy(css = "div[class='AgentCard_items-container__c9a0a'] div:nth-child(1) span:nth-child(2)")
    WebElement SystemTypeName;

    @FindBy(css = "div[class='AgentCard_action-card-body__IBvj1'] div:nth-child(2) span:nth-child(2)")
    WebElement BaseUrlText;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")
    WebElement SearchBar;

    @FindBy(xpath = "//p[normalize-space()='No System Found']")
    WebElement NoSystemFoundIcon;

    @FindBy(xpath = "//span[normalize-space()='Cancel']")
    WebElement cancelButtonNotClickable;



    Actions actions = new Actions(driver);

    public SystemIntegration(){
        PageFactory.initElements(driver,this);
    }

    public void ClickOnBotDropDownList(){
        ClickOnBotList.click();
    }

    public void ClickOnBotName() {
        ClickOnBotName.click();
    }

    public void ClickOnIntegrations() {
        ClickOnIntegrations.click();
    }

    public void ClickOnSystem() {
        ClickOnSystem.click();
    }

    public void CheckTheMessage() {
        CheckTheMessage.getText().equals("No Data Available");
    }

    public void ClickOnNewConnection() {
        ClickOnNewConnection.click();
    }

    public void SystemTypeDropDownList(){
        SystemTypeDropDownList.click();
    }

    public void SelectCanvas() {
        SelectCanvas.click();
    }

    public void SetConnectionName(){
        SetConnectionName.sendKeys(properties.getProperty("ConnectionName"));
    }

    public void setSetClientID(){
        SetClientID.sendKeys(properties.getProperty("ConnectionID"));
    }

    public void SetClientSecret() {
        SetClientSecret.sendKeys(properties.getProperty("ClientSecretCanvas"));
    }

    public void SetBaseURL(){
        SetBaseURL.sendKeys(properties.getProperty("BaseURLCanvas"));
    }

    public void setInvalidClientID(){
        SetClientID.sendKeys("2");
    }

    public void setClickOnConnect(){
        ClickOnConnect.click();
    }

    public void CheckTheMessageIsdisplay() {
        CheckTheMessage.isDisplayed();
    }

    public void DeleteButton(){
        DeleteButton.click();
    }

    public void ConfirmButton(){
        ConfirmButton.click();
    }

    public void CheckValidations(){
        SetConnectionName.click();
        actions.sendKeys(Keys.TAB).perform();
        ConnectionNameValidation.isDisplayed();
        SetClientID.click();
        actions.sendKeys(Keys.TAB).perform();
        ClientIDValidation.isDisplayed();
        SetClientSecret.click();
        actions.sendKeys(Keys.TAB).perform();
        ClientSecretValidation.isDisplayed();
        SetBaseURL.click();
        actions.sendKeys(Keys.TAB).perform();
        BaseURLValidation.isDisplayed();
    }

    public void SelectOnSemester(){
        SelectSemester.click();
    }
    public void SubmitTheSemester(){
        SubmitTheSemester.click();
    }

    public String CheckTheConnectionName(){
        return ConnectionName.getText();
    }

    public void EditTheConnection(){
        EditIcon.click();
    }

    public void NameModify(String name){
        ConnectionNameModify.sendKeys(name);
    }

    public void ValidationCheckEdit(){
        ValidationMessage.isDisplayed();
    }

    public void EditConnect(){
        ClickOnConnectEdit.click();
    }

    public void SelectSecondSemester(){
        SelectSemesterTwo.click();
    }

    public void DeleteValidationMessage(){
        DeletionValidation.isDisplayed();
    }

    public void DuplicateValidationMessageM(){
        DuplicateValidationMessage.isDisplayed();
    }

    public void SecondCreationConnect(){
        SecondConnect.click();
    }

    public void SwitchToDisconnectSystem(){
        DisconnectButton.click();
        ConfirmDisconnectButton.click();
        DisconnectValidationMessage.isDisplayed();
    }

    public void SwitchToConnectSystem(){
        connectButton.click();
        connectValidationMessage.isDisplayed();
    }

    public void UserClickCancel(){
        CancelButton.click();
    }

    public String GetBaseUrlTest(){
        return BaseUrlText.getText();
    }

    public String GetSystemType(){
        return SystemTypeName.getText();
    }

    public void SearchForConnection(String connectionName){
        SearchBar.sendKeys(connectionName);
    }

    public void IconFormNoSystemFound(){
        NoSystemFoundIcon.isDisplayed();
    }

    public String CancelButtonIsDisable(){
        if (cancelButtonNotClickable.isEnabled()){
            return "Passed";
        }
        else {
            return "Failed";
        }
    }

}
