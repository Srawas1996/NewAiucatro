package Steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.base.Base;
import org.example.base.ExcelReader;
import org.example.pages.ChatInterface;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import okhttp3.*;
import java.text.Normalizer;


public class ChatInterfaceSteps extends Base {

    ChatInterface chatInterface = new ChatInterface();

    @When("the user Navigate to the chat Interface")
    public void theUserNavigateToTheChatInterface(){
        chatInterface.clickOnChatInterface();
    }

    @Then("The user ask any question")
    public void theUserAskAnyQuestion() throws InterruptedException {

        String excelPath = "src/main/files/ChatTest.xlsx";

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
            String cleanResponse = normalizeArabic(messageResponse);
            String k1 = normalizeArabic(key1);
            String k2 = normalizeArabic(key2);
            String k3 = normalizeArabic(key3);

            boolean match = arabicKeywordMatch(cleanResponse, k1, k2, k3);

            if (!match) {
                System.out.println("---- MATCH FAILED ----");
                System.out.println("Question: " + question);
                System.out.println("Original Response: " + messageResponse);
                System.out.println("Normalized Response: " + cleanResponse);
                System.out.println("Expected Keys: " + k1 + " | " + k2 + " | " + k3);
            }

            Assert.assertTrue("Bot response does NOT contain expected keywords for question: " + question + " " +
                    messageResponse,match);
            System.out.println("Response Are return as expected \n\tQuestion Ask: " + question);
        }
    }

    @NotNull
    public static String normalizeArabic(@NotNull String text) {

        if (text == null) return "";

        // Normalize Unicode
        text = Normalizer.normalize(text, Normalizer.Form.NFKC);

        // Remove diacritics / tashkeel (َ ِ ُ etc.)
        text = Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        // Remove tatweel ـ
        text = text.replace("\u0640", "");

        // Remove zero-width and directional marks
        text = text.replaceAll("[\\u200E\\u200F\\u202A-\\u202E\\u00A0]", " ");

        // Remove punctuation (Arabic + English)
        text = text.replaceAll("[\\p{Punct}«»؟؛،…“”‘’]", " ");

        // Clean spaces
        text = text.replaceAll("\\s+", " ").trim().toLowerCase();

        return text;
    }

    /* ------------------------------------
       MULTI-STRATEGY ARABIC MATCH FUNCTION
       ------------------------------------ */
    public static boolean arabicKeywordMatch(@NotNull String response, @NotNull String... keys) {

        String respNoSpace = response.replaceAll("\\s+", "");

        for (String k : keys) {
            if (k == null || k.trim().isEmpty()) continue;

            String key = k.trim().toLowerCase();
            String keyNoSpace = key.replaceAll("\\s+", "");

            // Strategy 1: direct contains
            if (response.contains(key)) return true;

            // Strategy 2: token match (big words only)
            for (String token : key.split(" ")) {
                if (token.length() > 2 && response.contains(token)) return true;
            }

            // Strategy 3: match without spaces (fixes Excel invisible chars)
            if (respNoSpace.contains(keyNoSpace)) return true;
        }
        return false;
    }

}
