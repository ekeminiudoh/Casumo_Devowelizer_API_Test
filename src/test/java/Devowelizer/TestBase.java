package Devowelizer;

import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

public class TestBase {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

}
