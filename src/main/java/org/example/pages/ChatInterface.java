package org.example.pages;

import org.example.base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Scanner;

public class ChatInterface extends Base {

    @FindBy(xpath = "//p[text()='Chat Interface']")
    WebElement chatInterFaceBtn;

/*
    @FindBy(xpath = "//span[text()='New Chat']")
    WebElement newChatBtn;
*/

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/span[1]/*[name()='svg'][1]")
    WebElement StartButton;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]")
    WebElement StopButton;

    @FindBy(xpath = "//textarea[@placeholder='Message AiDucator']")
    WebElement userMessage;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/*[name()='svg'][1]")
    WebElement sendMessageBtn;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/*[name()='svg'][1]/*[name()='path'][1]")
    WebElement voiceChatSendMessage;

    @FindBy(xpath = "(//div[contains(@class,'BotResponse_res-container__MOeVG')])[last()]")
    WebElement botResponse;


    public ChatInterface(){
        PageFactory.initElements(driver,this);
    }

    public void clickOnChatInterface(){
        chatInterFaceBtn.click();
    }

    public void sendMessageToTheBot(String messageRequest){
        userMessage.sendKeys(messageRequest);
    }

    public void sendMessage(){
        sendMessageBtn.click();
    }
    public void voiceSendMessage(){
        voiceChatSendMessage.click();
    }

    public String responseBackFromTheBot() throws InterruptedException {
        Thread.sleep(7000);
        return botResponse.getText();
    }

    public void setStartButton(){
        StartButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(StopButton));
    }

    public void setStopButton(){
        StopButton.click();
    }



}
