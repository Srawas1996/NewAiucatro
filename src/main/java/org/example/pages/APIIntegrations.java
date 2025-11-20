package org.example.pages;

import org.example.base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class APIIntegrations extends Base {

    @FindBy(xpath = "//*/text()[normalize-space(.)='AutomationTest123']/parent::*")
    WebElement clickOnTheCard;

    @FindBy(css = "p[class='AgentHeader_title__M+tpz undefined']")
    WebElement CheckTheNameOfTheConnection;

    @FindBy(xpath = "//span[normalize-space()='Save']")
    WebElement SaveButton;

    @FindBy(xpath = "//span[normalize-space()='Select All']")
    WebElement SelectAllButton;

    @FindBy(xpath = "//span[normalize-space()='Clear']")
    WebElement ClearButton;

    @FindBy(xpath = "//span[normalize-space()='Sync All']")
    WebElement SyncAllButton;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")
    WebElement SearchBar;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/label[1]/span[1]")
    WebElement SelectFirstCheckBox;

    @FindBy(xpath = "//div[contains(@class,'TabComponent_tabContent__rNexW')]//div[2]//div[2]//div[2]//div[1]//div[1]//div[2]")
    WebElement ChangeTheFrequentSync;

    @FindBy(xpath = "(.//*[normalize-space(text()) and normalize-space(.)='Manual'])[1]/following::div[1]")
    WebElement changeToDailyFrequent;

    @FindBy(xpath = "//div[contains(@class,'TabComponent_tabContent__rNexW')]//div[2]//div[2]//div[2]//button[2]//span[2]")
    WebElement forceSyncFirstOne;

    @FindBy(xpath = "//div[@class='AgentRow_custom__GR+uU AgentRow_api__agSjP']")
    WebElement validateThatTheSearchIsExist;

    @FindBy(xpath = "//div[contains(@class,'TabComponent_tabContent__rNexW')]//div[2]//div[2]//div[2]//button[1]//span[2]")
    WebElement testConnectionButton;

    @FindBy(xpath = "//p[contains(@class,'CustomizedToast_toast-title__V4v0S')]")
    WebElement checkTheValidationMessage;

    @FindBy(xpath = "/html[1]/body[1]/div[2]/div[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/label[1]/span[1]")
    WebElement CheckTheFieldsAreChecked;

    @FindBy(xpath = "//p[@class='CustomizedToast_toast-title__V4v0S']")
    WebElement checkTheValidationMessageForFrequent;

    @FindBy(xpath = "//p[@class='CustomizedToast_toast-title__V4v0S']")
    WebElement CheckTheForceSyncValidation;

    @FindBy(xpath = "//div[@class='AgentRow_sync-container__X324M']//span//*[name()='svg']")
    WebElement CheckIfTheDataTableIsAppear;

    @FindBy(xpath = "//button[normalize-space()='API Logs']")
    WebElement moveToAPILogs;

    @FindBy(xpath = "//span[normalize-space()='Refresh Logs']")
    WebElement checkTheRefreshTokenIsExist;

    @FindBy(xpath = "//tbody/tr[2]/td[7]/div[1]/button[1]/span[1]")
    WebElement checkIfTheViewButtonIsEnable;

    @FindBy(xpath = "//p[@class='CustomizedToast_toast-title__V4v0S']")
    WebElement savedValidationMessage;

    @FindBy(xpath = "//span[contains(text(),'GET /api/v1/calendar_events — Lists calendar event')]")
    WebElement openTheApiFields;

    @FindBy(xpath = "//div[@class='AgentRow_modalActions__9hdd6']//span[@class='Button_label__i00gY'][normalize-space()='Select All']")
    WebElement SelectAllInsideFields;

    @FindBy(xpath = "//div[contains(@class,'AgentRow_modalActions__9hdd6')]//span[contains(@class,'Button_label__i00gY')][normalize-space()='Clear']")
    WebElement ClearInsideFields;

    @FindBy(xpath = "//span[normalize-space()='Cancel']")
    WebElement CancelInsideFields;

    @FindBy(xpath = "//div[contains(@class,'Modal_actions__IgdeB')]//span[contains(@class,'Button_label__i00gY')][normalize-space()='Save']")
    WebElement SaveButtonInsideFields;

    @FindBy(xpath = "//p[contains(text(),'Unique ID of the event Type: Integer. Primary Key ')]")
    WebElement CheckBoxButtonInsideTheFields;

    @FindBy(xpath = "//p[contains(@class,'CustomizedToast_toast-title__V4v0S')]")
    WebElement fieldsSavedValidationMessage;

    public APIIntegrations(){
        PageFactory.initElements(driver,this);
    }
    public void clickOnTheConnectionCard(){
        clickOnTheCard.click();
    }

    public String CheckingTheName(){
        return CheckTheNameOfTheConnection.getText();
    }

    public void CheckTheButtonDisplayed(){
        SaveButton.isEnabled();
        SelectAllButton.isEnabled();
        ClearButton.isEnabled();
        SyncAllButton.isEnabled();
    }

    public void validateThatTheSearchIsExist(){
        SearchBar.sendKeys("All Courses for User");
        validateThatTheSearchIsExist.isDisplayed();
    }

    public void clickOnTestConnection(){
        testConnectionButton.click();
        checkTheValidationMessage.isDisplayed();
    }

    public void ClicksOnSelectAll(){
        SelectAllButton.click();
        SaveButton.click();
    }

    public void checkIfTheApiIsSelected(){
        SelectFirstCheckBox.isSelected();
    }

    public void OpenTheApiFields(){
        openTheApiFields.click();
    }

    public void CheckTheFieldAreChecked(){
        CheckTheFieldsAreChecked.isSelected();
    }

    public void changeTheFrequentToDaily(){
        ChangeTheFrequentSync.click();
        changeToDailyFrequent.click();
        SaveButton.click();
        checkTheValidationMessageForFrequent.isDisplayed();
    }

    public void userClickOnForceSync(){
        forceSyncFirstOne.click();
        CheckTheForceSyncValidation.isDisplayed();
        CheckIfTheDataTableIsAppear.isDisplayed();
    }

    public void switchToAPILogs(){
        moveToAPILogs.click();
    }

    public void validateThatTheRefreshTokenIsExist(){
        checkTheRefreshTokenIsExist.isDisplayed();
    }

    public void validateThatTheViewButtonIsExist(){
        checkIfTheViewButtonIsEnable.isDisplayed();
    }

    public void ClicksOnClear(){
        SelectFirstCheckBox.isDisplayed();
        ClearButton.click();
        SaveButton.click();
    }

    public void SaveValidationMessage(){
        savedValidationMessage.isDisplayed();
    }

    public boolean checkIfTheApiIsUnSelected(){
        return SelectFirstCheckBox.isSelected();
    }

    public void unCheckedSomeOfTheFields(){
        CheckBoxButtonInsideTheFields.click();
    }

    public void saveTheFields(){
        SaveButtonInsideFields.click();
    }

    public void fieldValidationMessage(){
        fieldsSavedValidationMessage.isDisplayed();
    }

    public boolean checkThatTheFieldAreUnchecked(){
        return !CheckBoxButtonInsideTheFields.isSelected();
    }

    public boolean checkThatTheFieldAreChecked(){
        return CheckBoxButtonInsideTheFields.isSelected();
    }

    public void cancelButtonInsideFields(){
        CancelInsideFields.click();
    }

    public void clearButtonInsideTheFields() throws InterruptedException {
        ClearInsideFields.click();
        Thread.sleep(2000);
    }

    public void selectAllInsideFields() throws InterruptedException {
        SelectAllInsideFields.click();
        Thread.sleep(2000);
    }

    public boolean CheckIfTheButtonsAreDisable(){
        return !(forceSyncFirstOne.isEnabled() && testConnectionButton.isDisplayed());
    }


}
