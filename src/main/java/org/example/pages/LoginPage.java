package org.example.pages;

import org.example.base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class LoginPage extends Base {
    public LoginPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")
    WebElement email;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement SingInButton;

    @FindBy(css = ".CustomizedToast_toast-title__V4v0S")
    WebElement errorMessageDisplayed;

    @FindBy(css = ".SidebarFooter_bar-footer__wxRzf")
    WebElement ClickOnProfile;

    @FindBy(xpath = "//span[normalize-space()='Logout']")
    WebElement ClickOnLogOut;


    public void performInvalidLogin(String userName, String password) throws InterruptedException {
        Thread.sleep(3000);
        this.email.sendKeys(userName);
        this.password.sendKeys(password);
        this.SingInButton.click();
    }

    public void PerformValidLogin(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.email.sendKeys(properties.get("username").toString());
        this.password.sendKeys(properties.get("password").toString());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.SingInButton.click();

    }

    public void ErrorMessageIsDisplayed(){
        this.errorMessageDisplayed.isDisplayed();
    }

    public void ValidatingCorrectLoginandLogout(){
        this.ClickOnProfile.isEnabled();
        this.ClickOnProfile.click();
        this.ClickOnLogOut.click();
    }
}
