package apitests;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class hrApiWithPath {


    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("hr_api_url");
    }

    @Test
    public void getCountriesWithPath(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        assertEquals(response.statusCode(),200);

        //print limit
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        String firstCountryId = response.path("items.country_id[0]");
        System.out.println("firstCountryId = " + firstCountryId);


        String secondCountryName = response.path("items.country_name[1]");
        System.out.println("secondCountryName = " + secondCountryName);

        String hrefLink2 = response.path("items.links[2].href[0]");
        System.out.println("hrefLink2 = " + hrefLink2);

        //get all countries
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        //assert that all region_ids are equal to 2
        List<Integer> regionIds = response.path("items.region_id");
        for (int regionId : regionIds) {
            System.out.println(regionId);
            assertEquals(regionId,2);
        }
    }

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParams("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("IT_PROG"));


        //make sure we have only IT_PROG as a job_id
        List<String> jobIDs = response.path("items.job_id");
        for (String jobID : jobIDs) {
            assertEquals(jobID,"IT_PROG");
        }

    }


}
