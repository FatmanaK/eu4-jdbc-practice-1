package apitests;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;


public class CBTrainingWithJsonPath {

    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("cbt_api_url");
    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 20202)
                .when().get("/student/{id}");

        assertEquals(response.statusCode(),200);

        //assign response to jsonpath
        JsonPath jsonPath = response.jsonPath();

        // get values from jsonpath
        System.out.println(jsonPath.getString("students.firstName[0]"));

        String lastName = jsonPath.getString("students.lastName[0]");
        System.out.println(lastName);

        // get phone number
        String phone = jsonPath.getString("students.contact[0].phone");
        System.out.println(phone);

        // get city and zipcode and do assertion
        String city = jsonPath.getString("students.company[0].address.city");
        assertEquals(city,"Chicago");

        int zipcode = jsonPath.getInt("students.company[0].address.zipCode");
        assertEquals(zipcode,60606);


    }
}
