package Steps;

import io.cucumber.java.*;
import org.example.base.Base;

import java.util.Scanner;

public class Hocks extends Base {
    @Before
    public void runBeforeAnyScenario(){
    }


    @After
    public void runAfterAnyScenario(){
        driver.quit();
    }
}
