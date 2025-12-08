package org.example.pages;

import org.example.base.Base;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Scanner;

public class ChatInterface extends Base {

    @FindBy(xpath = "//p[text()='Chat Interface']")
    WebElement chatInterFaceBtn;

    @FindBy(xpath = "//span[text()='Canvas Chatbot']")
    WebElement ClickOnBotNameCanvas;
/*
    @FindBy(xpath = "//span[text()='New Chat']")
    WebElement newChatBtn;
*/
    @FindBy(xpath = "//textarea[@placeholder='Message AiDucator']")
    WebElement userMessage;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/*[name()='svg'][1]")
    WebElement sendMessageBtn;

    @FindBy(xpath = "(//div[contains(@class,'TypingMessage_text-message__QO0zP')])[last()]")
    WebElement botResponse;

    @FindBy(xpath = "(//span[@dir='ltr'][normalize-space()='Today · 11:15 AM'])[last()]")
    WebElement lastResponseTime;

    Actions actions = new Actions(driver);
    public ChatInterface(){
        PageFactory.initElements(driver,this);
    }

    public void clickOnChatInterface(){
        chatInterFaceBtn.click();
    }

    public void sendMessageToTheBot(String messageRequest){
        userMessage.sendKeys(messageRequest);
    }

    public void ClickOnBotNameCanvas() {
        ClickOnBotNameCanvas.click();
    }

    public void sendMessage() throws InterruptedException {
        userMessage.click();
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
    }

    public String responseBackFromTheBot() throws InterruptedException {
        Thread.sleep(10000);
        return botResponse.getText();
    }

}
