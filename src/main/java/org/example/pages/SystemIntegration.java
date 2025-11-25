package org.example.pages;


import org.example.base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


public class SystemIntegration extends Base {

    @FindBy(xpath = "//span[text()='Choose a bot']")
    WebElement ClickOnBotList;

    @FindBy(xpath = "(//div[@role='option'])[1]")
    WebElement ClickOnBotName;

    @FindBy(xpath = "//p[text()='Integrations']")
    WebElement ClickOnIntegrations;

    @FindBy(xpath = "//span[text()='Systems']")
    WebElement ClickOnSystem;

    @FindBy(xpath = "//span[text()='New Connection']")
    WebElement ClickOnNewConnection;

    @FindBy(css = "#select-agentType")
    WebElement SystemTypeDropDownList;

    @FindBy(xpath = "//div[normalize-space(text())='Canvas']")
    WebElement SelectCanvas;

    @FindBy(name = "connectionName")
    WebElement SetConnectionName;

    @FindBy(xpath = "//span[text()='AutomationTest123']")
    WebElement ConnectionNameOnCard;

    @FindBy(name = "clientId")
    WebElement SetClientID;

    @FindBy(name = "clientSecret")
    WebElement SetClientSecret;

    @FindBy(name = "baseURl")
    WebElement SetBaseURL;

    @FindBy(xpath = "//span[text()='Connect']")
    WebElement ClickOnConnect;

    @FindBy(xpath = "(//button[@type='button'])[4]")
    WebElement SecondConnect;

    @FindBy(xpath = "//span[text()='Connect']")
    WebElement ClickOnConnectEdit;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/span[2]/div[1]/*[name()='svg'][1]")
    WebElement DeleteButton;

    @FindBy(xpath = "//span[text()='Delete']")
    WebElement ConfirmButton;

    @FindBy(xpath = "//div[text()='Connection Name is required']")
    WebElement ConnectionNameValidation;

    @FindBy(xpath = "//div[text()='Client ID is required']")
    WebElement ClientIDValidation;

    @FindBy(xpath = "//div[text()='Client Secret is required']")
    WebElement ClientSecretValidation;

    @FindBy(xpath = "//div[text()='Enter a valid URL (e.g. https://example.com)']")
    WebElement BaseURLValidation;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/span[1]/div[1]/*[name()='svg'][1]")
    WebElement EditIcon;

    @FindBy(xpath = "//p[text()='Summer School Credit Recovery 22-23']")
    WebElement SelectSemester;

    @FindBy(xpath = "//p[text()='Summer School Credit Recovery 24-25']")
    WebElement SelectSemesterTwo;

    @FindBy(xpath = "//span[text()='Submit']")
    WebElement SubmitTheSemester;

    @FindBy(xpath = "//span[text()='Disconnect']")
     WebElement DisconnectButton;

    @FindBy(xpath = "/html[1]/body[1]/div[2]/div[1]/div[1]/div[4]/button[2]/span[1]")
    WebElement ConfirmDisconnectButton;

    @FindBy(xpath = "//span[text()='Connect']")
    WebElement connectButton;

    @FindBy(xpath = "(//div[@class='toasts-wrapper'])[1]")
    WebElement validationMessage;

    @FindBy(xpath = "//span[text()='Cancel']")
    WebElement CancelButton;

    @FindBy(xpath = "//span[text()='Canvas']")
    WebElement SystemTypeName;

    @FindBy(xpath = "//span[text()='cs2.instructure.com/']")
    WebElement BaseUrlText;

    @FindBy(xpath = "//input[@placeholder = 'Search']")
    WebElement SearchBar;

    @FindBy(xpath = "//p[text()='No System Found']")
    WebElement NoSystemFoundIcon;

    @FindBy(xpath = "//span[text()='Cancel']")
    WebElement cancelButtonNotClickable;

    @FindBy(xpath = "//div[@class='Checkbox_checkbox-wrapper__hAVdY']//p")
    List<WebElement> allSemesters;

    //Actions actions = new Actions(driver);

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

    public String CheckTheMessageIsdisplay() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOf(validationMessage));
            return validationMessage.getText();
    }

    public void DeleteButton(){
        DeleteButton.click();
    }

    public void ConfirmButton(){
        ConfirmButton.click();
    }

    public void CheckValidations(){
        SetConnectionName.click();
        SetClientID.click();
        ConnectionNameValidation.isDisplayed();
        SetClientSecret.click();
        ClientIDValidation.isDisplayed();
        SetBaseURL.click();
        ClientSecretValidation.isDisplayed();
        SetConnectionName.click();
        BaseURLValidation.isDisplayed();
    }

    public void SelectOnSemester(){
        SelectSemester.click();
    }
    public void SubmitTheSemester(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(validationMessage));
        SubmitTheSemester.click();
    }

    public String CheckTheConnectionName(){
        return ConnectionNameOnCard.getText();
    }

    public void EditTheConnection(){
        EditIcon.click();
    }

    public void NameModify(String name){
        SetConnectionName.sendKeys(name);
    }

    public String ValidationCheckEdit(){
        validationMessage.isDisplayed();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(validationMessage));
        return validationMessage.getText();
    }

    public String EditConnect(){
        ClickOnConnectEdit.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(validationMessage));
        return validationMessage.getText();
    }

    public void SelectSecondSemester(){
        SelectSemesterTwo.click();
    }

    public String DeleteValidationMessage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(validationMessage));
        return validationMessage.getText();
    }

    public String DuplicateValidationMessageM(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(validationMessage));
        return validationMessage.getText();
    }

    public void SecondCreationConnect(){
        SecondConnect.click();
    }

    public String SwitchToDisconnectSystem() {
        DisconnectButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(ConfirmDisconnectButton));
        ConfirmDisconnectButton.click();
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait2.until(ExpectedConditions.visibilityOf(validationMessage));
        return validationMessage.getText();
    }

    public String SwitchToConnectSystem()  {
        connectButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(validationMessage));
        return validationMessage.getText();
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

    public boolean semesterButtonAreNotDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(CancelButton));
        return allSemesters.isEmpty() || allSemesters.stream()
                .allMatch(e -> !e.isDisplayed() || !e.isEnabled());
    }
}
