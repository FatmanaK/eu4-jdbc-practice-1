package apitests;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class hrApiWithJsonPath {

    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("hr_api_url");
    }

    @Test
    public void test1() {
        Response response = get("/countries");

        //Assign to JsonPath
        JsonPath jsonPath = response.jsonPath();
        String secondCountryName = jsonPath.getString("items.country_name[1]");
        System.out.println(secondCountryName);

        //get all country IDs
        List<String> countryIDs = jsonPath.getList("items.country_id");
        System.out.println(countryIDs);

        //get all country name where region id equals to 2
        List<String> countryNamesWithRegionId2 = jsonPath.getList("items.findAll{it.region_id==2}.country_name");
        System.out.println(countryNamesWithRegionId2);
    }

    @Test
    public void test2() {
        Response response = given().queryParam("limit", 107)
                .when().get("/employees");

        JsonPath jsonPath = response.jsonPath();

        //get me all email of employees who is working as IT_PROG
        List<String> email = jsonPath.getList("items.findAll{it.job_id==\"IT_PROG\"}.email");
        System.out.println(email);

        //get me all firstname of emplyoees who is making more than 10000
        List<String> firstname = jsonPath.getList("items.findAll{it.salary>1000}.first_name");
        System.out.println(firstname);

        //get me first name of who is making highest salary
        String kingName = jsonPath.getString("items.max{it.salary}.first_name");
        System.out.println(kingName);

    }

}