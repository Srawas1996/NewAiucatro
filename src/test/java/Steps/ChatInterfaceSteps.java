package Steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.base.Base;
import org.example.base.ExcelReader;
import org.example.pages.ChatInterface;
import org.junit.Assert;

public class ChatInterfaceSteps extends Base {

    ChatInterface chatInterface = new ChatInterface();


    @When("the user Navigate to the chat Interface")
    public void theUserNavigateToTheChatInterface(){
        chatInterface.clickOnChatInterface();
    }

    @Then("The user ask any question")
    public void theUserAskAnyQuestion() throws InterruptedException {

        String excelPath = "C:/Users/SalimAlRawas/Desktop/ChatTest.xlsx";
        Object[][] data = ExcelReader.readExcel(excelPath,"Sheet1");
        assert data != null;
        for(Object[] row : data) {
            String question = row[0].toString();
            String key1 = row[1].toString();
            String key2 = row[2].toString();
            String key3 = row[3].toString();
            chatInterface.sendMessageToTheBot(question);
            chatInterface.sendMessage();
            Thread.sleep(5000);
            String messageResponse = chatInterface.responseBackFromTheBot().trim();
            boolean contains =
                    messageResponse.toLowerCase().contains(key1.toLowerCase()) ||
                    messageResponse.toLowerCase().contains(key2.toLowerCase()) ||
                    messageResponse.toLowerCase().contains(key3.toLowerCase());
            Assert.assertTrue("Bot response does NOT contain expected keywords for question: " + question + " " +
                    messageResponse,contains);
            System.out.println("Response Are return as expected");
        }
    }

}
