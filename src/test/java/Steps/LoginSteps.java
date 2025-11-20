package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.base.Base;
import org.example.pages.LoginPage;

import java.time.Duration;

public class LoginSteps extends Base {

    LoginPage loginPage;
    @Given("user open website and login to the systems")
    public void user_Open_Website(){
        lanuchBrowser();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @When("User fill email as {string} and {string} and click login")
    public void userFillEmailAsAndAndClickLogin(String userName, String Password) throws InterruptedException {
        loginPage = new LoginPage();
        loginPage.performInvalidLogin(userName,
                Password);

    }

    @Then("Error message should appear")
    public void errorMessageShouldAppear() {
        loginPage.ErrorMessageIsDisplayed();
    }

    @When("user fill the data")
    public void userFillTheData() {
        loginPage = new LoginPage();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage.PerformValidLogin();
    }

    @Then("Validate you are in the dashboard")
    public void validateYouAreInTheDashboard() {

        loginPage.ValidatingCorrectLoginandLogout();
    }
}
