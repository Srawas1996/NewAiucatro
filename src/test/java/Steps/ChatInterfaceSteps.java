package Steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.base.Base;
import org.example.base.ExcelReader;
import org.example.pages.ChatInterface;
import org.junit.Assert;
import okhttp3.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.stream.Stream;


public class ChatInterfaceSteps extends Base {

    ChatInterface chatInterface = new ChatInterface();

    @When("the user Navigate to the chat Interface")
    public void theUserNavigateToTheChatInterface(){
        chatInterface.clickOnChatInterface();
    }

    @Then("The user ask any question")
    public void theUserAskAnyQuestion() throws InterruptedException {

        String excelPath = "C:/Users/SalimAlRawas/Desktop/ChatTest.xlsx";

        Object[][] data = ExcelReader.readExcel(excelPath,"Bau");
        assert data != null;
        for(Object[] row : data) {
            String question = row[0].toString();
            String key1 = row[1].toString();
            String key2 = row[2].toString();
            String key3 = row[3].toString();
            chatInterface.sendMessageToTheBot(question);
            chatInterface.sendMessage();
            Thread.sleep(7000);
            String messageResponse = chatInterface.responseBackFromTheBot().trim();
            if (messageResponse.contains("Sorry")){
                System.out.println("Something went wrong");
                break;
            }
            boolean contains =
                    messageResponse.toLowerCase().contains(key1.toLowerCase()) ||
                    messageResponse.toLowerCase().contains(key2.toLowerCase()) ||
                    messageResponse.toLowerCase().contains(key3.toLowerCase());
            Assert.assertTrue("Bot response does NOT contain expected keywords for question: " + question + " " +
                    messageResponse,contains);
            System.out.println("Response Are return as expected \n\tQuestion Ask: " + question);
        }
    }
}
