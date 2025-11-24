package org.example.pages;

import org.example.base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChatInterface extends Base {

    @FindBy(xpath = "//p[text()='Chat Interface']")
    WebElement chatInterFaceBtn;

    @FindBy(xpath = "//span[text()='New Chat']")
    WebElement newChatBtn;

    @FindBy(xpath = "//textarea[@placeholder='Message AiDucator']")
    WebElement userMessage;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/*[name()='svg'][1]")
    WebElement sendMessageBtn;

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

    public String responseBackFromTheBot() throws InterruptedException {
        Thread.sleep(5000);
        return botResponse.getText();
    }

}
