package stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.base.Base;
import org.example.base.ExcelReader;
import org.example.pages.ChatInterface;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ChatInterfaceSteps extends Base {

    ChatInterface chatInterface = new ChatInterface();
    List<File> failedScreenshots = new ArrayList<>();

    @When("the user Navigate to the chat Interface")
    public void theUserNavigateToTheChatInterface(){
        chatInterface.clickOnChatInterface();
    }

    @Then("The user ask any question")
    public void theUserAskAnyQuestion() throws InterruptedException {

        String excelPath = "src/main/files/ChatTest.xlsx";
        Object[][] data = ExcelReader.readExcel(excelPath, "Bau");
        assert data != null;

        int failCount = 0;

        for (Object[] row : data) {
            String question = row[0].toString();
            String key1 = row[1].toString();
            String key2 = row[2].toString();
            String key3 = row[3].toString();

            chatInterface.sendMessageToTheBot(question);
            chatInterface.sendMessage(question);
            Thread.sleep(10000);

            String messageResponse = chatInterface.responseBackFromTheBot().trim();

            boolean match =
                    messageResponse.contains(key1) ||
                            messageResponse.contains(key2) ||
                            messageResponse.contains(key3);

            if (!match || messageResponse.toLowerCase().contains("sorry")) {
                System.out.println("---- MATCH FAILED ----");
                System.out.println("Question: " + question);
                System.out.println("Original Response: " + messageResponse);
                System.out.println("Expected Keys: " + key1 + " | " + key2 + " | " + key3);
                failCount++;

                File screenshot = ChatInterface.capture(driver, "mismatch_" + question.replaceAll("\\W+", "_"));

                // Store file path for sending in email later
                failedScreenshots.add(screenshot);
            } else {
                System.out.println("Response returned as expected \n\tQuestion Ask: " + question);
            }
        }

        System.out.println("===== Test finished =====");
        System.out.println("Total Questions: " + data.length);
        System.out.println("Failed Questions: " + failCount);
    }

    @When("the user navigates to the Bot section and selects a Bot Canvas")
    public void theUserNavigatesToTheBotSectionAndSelectsABotCanvas() {
        chatInterface.ClickOnBotNameCanvas();
    }

    @When("the user Navigate to the chat Interface Canvas")
    public void theUserNavigateToTheChatInterfaceCanvas() {
        chatInterface.clickOnChatInterface();
    }

    @Then("The user ask any question Canvas")
    public void theUserAskAnyQuestionCanvas() throws InterruptedException {

        String excelPath = "src/main/files/ChatTest.xlsx";
        Object[][] data = ExcelReader.readExcel(excelPath, "Canvas");
        assert data != null;

        int failCount = 0;

        for (Object[] row : data) {
            String question = row[0].toString();
            String key1 = row[1].toString();
            String key2 = row[2].toString();
            String key3 = row[3].toString();

            chatInterface.sendMessageToTheBot(question);
            chatInterface.sendMessage(question);
            Thread.sleep(11000);

            String messageResponse = chatInterface.responseBackFromTheBot().trim();

            boolean match =
                    messageResponse.contains(key1) ||
                            messageResponse.contains(key2) ||
                            messageResponse.contains(key3);

            if (!match || messageResponse.toLowerCase().contains("sorry")) {
                System.out.println("---- MATCH FAILED ----");
                System.out.println("Question: " + question);
                System.out.println("Original Response: " + messageResponse);
                System.out.println("Expected Keys: " + key1 + " | " + key2 + " | " + key3);
                failCount++;

                File screenshot = ChatInterface.capture(driver, "mismatch_" + question.replaceAll("\\W+", "_"));
                failedScreenshots.add(screenshot);
            } else {
                System.out.println("Response returned as expected \n\tQuestion Ask: " + question);
            }
        }

        System.out.println("===== Canvas Test finished =====");
        System.out.println("Total Questions: " + data.length);
        System.out.println("Failed Questions: " + failCount);
    }
}