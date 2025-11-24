package org.example.pages;

import org.example.base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class APIIntegrations extends Base {

    // Locators ::
    @FindBy(xpath = "//span[text()='AutomationTest123']")
    WebElement connectionCard;

    @FindBy(xpath = "//p[contains(@class,'AgentHeader_title') and contains(text(),'AutomationTest')]")
    WebElement connectionName;

    // APi Selection
    @FindBy(xpath = "//span[text()='Sync All']")
    WebElement syncAllBtn;

    @FindBy(xpath = "//input[@placeholder='Search']")
    WebElement apiSearchBar;

    @FindBy(xpath = "//span[text()='Select All']")
    WebElement selectAllBtn;

    @FindBy(xpath = "//*[text()='Clear']")
    WebElement clearBtn;

    @FindBy(xpath = "//*[text()='Save']")
    WebElement saveBtn;

    @FindBy(xpath = "(//div[@class='toasts-wrapper'])[1]")
    WebElement validationMessage;

    // APi Locators
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/span[1]")
    WebElement dataTableBtn;

    @FindBy(xpath = "//*[text()='Force Sync']")
    WebElement forceSyncBtn;

    @FindBy(xpath = "//*[text()='Test Connection']")
    WebElement testConnectionBtn;

    @FindBy(xpath = "//div[contains(@class,'Select_select-display')]//input[@value='Manual']")
    WebElement changeTheFrequentSync;

    @FindBy(xpath = "//div[text()='Daily']")
    WebElement changeToDailyFrequent;

    @FindBy(xpath = "//div[text()='Untested']")
    WebElement unTestedConnection;

    @FindBy(xpath = "//div[text()='Healthy']")
    WebElement healthyConnection;

    @FindBy(xpath = "//span[contains(text(),'GET /api/v1/calendar_events')]")
    WebElement apiFields;

    @FindBy(xpath = "//div[contains(@class,'AgentRow_left-column')]//input[@type='checkbox']")
    WebElement checkBoxApi;

    @FindBy(xpath = "//*[text()='Calendar Events']")
    WebElement apiSearchCheck;

    // Api Fields Locator
    @FindBy(xpath = "(//span[contains(@class,'Button_label')][text()='Select All'])[2]")
    WebElement selectAllFieldsBtn;

    @FindBy(xpath = "(//span[contains(@class,'Button_label')][text()='Clear'])[2]")
    WebElement clearFieldsBtn;

    @FindBy(xpath = "(//span[text()='Cancel'])[1]")
    WebElement cancelFieldsBtn;

    @FindBy(xpath = "//*[text()='Start Time']")
    WebElement checkBoxBtn;

    @FindBy(xpath = "(//span[contains(@class,'Button_label')][text()='Save'])[2]")
    WebElement saveFieldsBtn;

    //----- Api Logs Locators
    @FindBy(xpath = "//button[normalize-space()='API Logs']")
    WebElement apiLogsTab;

    @FindBy(xpath = "//span[text()='Refresh Logs']")
    WebElement checkTheRefreshTokenIsExist;

    @FindBy(xpath = "//*[text()='View']")
    WebElement apiLogsViewBtn;


    public APIIntegrations(){
        PageFactory.initElements(driver,this);
    }
    public void clickOnTheConnectionCard(){
        connectionCard.click();
    }

    public String checkApiName() {
        return connectionName.getText();
    }

    public String saveValidationMessage() {
        saveBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(validationMessage));
        return validationMessage.getText();
    }

    public boolean CheckTheButtonDisplayed() {
        return saveBtn.isEnabled()
                && selectAllBtn.isEnabled()
                && clearBtn.isEnabled()
                && syncAllBtn.isEnabled()
                && apiLogsTab.isDisplayed();
    }

    public void clicksOnClear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(apiFields));
        clearBtn.click();
    }

    public void clicksOnSelectAll() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(apiFields));
        selectAllBtn.click();
    }

    public boolean validateThatTheSearchIsExist() {
        apiSearchBar.sendKeys("Calendar Events");
        return apiSearchCheck.isDisplayed();
    }

    public String clickOnTestConnection() {
        testConnectionBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(validationMessage));
        return validationMessage.getText();
    }

    public boolean checkTheApiInUntestedStatus(){
        return unTestedConnection.isDisplayed();
    }
    public boolean checkTheApiInHealthyStatus(){
        return healthyConnection.isDisplayed();
    }

    public void OpenTheApiFields(){
        apiFields.click();
    }

    public boolean CheckTheFieldAreChecked(){
        return checkBoxBtn.isSelected() &&
                selectAllFieldsBtn.isDisplayed() &&
                clearFieldsBtn.isDisplayed() && cancelFieldsBtn.isDisplayed();
    }

    public void changeTheFrequentToDaily(){
        changeTheFrequentSync.click();
        changeToDailyFrequent.click();
    }

    public String userClickOnForceSync() {
        forceSyncBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(validationMessage));
        return validationMessage.getText();
    }

    public boolean userCheckDataTableIsExist(){
        driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(dataTableBtn));
        return dataTableBtn.isDisplayed();
    }

    public void switchToAPILogs(){
        apiLogsTab.click();
    }

    public boolean validateThatTheRefreshTokenIsExist(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(apiLogsViewBtn));
        return checkTheRefreshTokenIsExist.isDisplayed() &&
                apiLogsViewBtn.isDisplayed();
    }

    public void unCheckedSomeOfTheFields(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(checkBoxBtn));
        checkBoxBtn.click();
        saveFieldsBtn.click();
    }

    public boolean checkThatTheFieldAreUnchecked(){
        return !checkBoxBtn.isSelected();
    }

    public boolean checkThatTheFieldAreChecked(){
        return checkBoxApi.isSelected();
    }

    public void cancelButtonInsideFields(){
        cancelFieldsBtn.click();
    }

    public void clearButtonInsideTheFields() {
        clearFieldsBtn.click();
    }

    public void selectAllInsideFields() {
        selectAllFieldsBtn.click();

    }

    public boolean CheckIfTheButtonsAreDisable(){
        return !forceSyncBtn.isSelected() && !testConnectionBtn.isSelected();
    }


}
