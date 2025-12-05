package stepdefs;

import io.cucumber.java.*;
import org.example.base.Base;

public class Hocks extends Base {
    @Before
    public void runBeforeAnyScenario(){
    }


    @After
    public void runAfterAnyScenario(){
        driver.quit();
    }
}
