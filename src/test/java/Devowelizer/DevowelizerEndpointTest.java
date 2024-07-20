package Devowelizer;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DevowelizerEndpointTest extends TestBase{

    @Test(priority = 1, description = "Validate removal of vowels from a string", retryAnalyzer = RetryAnalyzer.class)
    public void testRemoveVowels() {
        String input = "ekemini";
        Response response = RestAssured
                .given()
                .when()
                .get("/" + input);

        // Assert that the status code is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");

        // Assert that the response body contains the correct text without vowels
        String expectedResponse = "kmn";
        Assert.assertEquals(response.getBody().asString(), expectedResponse, "Response body should be 'kmn'");
    }


    @Test(priority = 2, description = "Validate an empty string as input", retryAnalyzer = RetryAnalyzer.class)
    public void TC_02() {
        String input = "";
        Response response = RestAssured
                .given()
                .when()
                .get("/" + input);

        // Assert that the status code is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        // Assert that the response body is a default string
        Assert.assertTrue(response.getBody().asString().contains("Send GET to /:input"),
                "Response body should contain 'Send GET to /:input'");
    }

    @Test(priority = 3, description = "Validate a single vowel character as input", retryAnalyzer = RetryAnalyzer.class)
    public void TC_03() {
        String input = "o";
        Response response = RestAssured
                .given()
                .when()
                .get("/" + input);

        // Assert that the status code is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");

        // Assert that the response body is an empty string
        Assert.assertEquals(response.getBody().asString(), "", "Response body should be empty");
    }

    @Test(priority = 4, description = "Validate non vowel input", retryAnalyzer = RetryAnalyzer.class)
    public void TC_04() {
        String input = "knckk";
        Response response = RestAssured
                .given()
                .when()
                .get("/" + input);

        // Assert that the status code is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        // Assert that the response body matches the input as there are no vowels
        Assert.assertEquals(response.getBody().asString(), input, "Response body should be 'knckk'");
    }

    @Test(priority = 5, description = "Validate Special character as input")
    public void TC_05() {
        String input = "!";
        Response response = RestAssured
                .given()
                .when()
                .get("/" + input);
        Assert.assertEquals(response.getStatusCode(), 500, "Status code should be 500 for special characters");
    }

    @Test(priority = 6, description = "Validate numerical input")
    public void TC_06() {
        String input = "123456";
        Response response = RestAssured
                .given()
                .when()
                .get("/" + input);
        // Assert that the status code is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        // Assert that the response body matches the input as there are no vowels
        Assert.assertEquals(response.getBody().asString(), input, "Response body should be '123456'");

    }
}