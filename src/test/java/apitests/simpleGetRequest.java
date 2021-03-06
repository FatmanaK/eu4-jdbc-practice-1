package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;


public class simpleGetRequest {

    String hrUrl = "http://52.87.216.245:1000/ords/hr/regions";

    @Test
    public void test1(){

        Response response = RestAssured.get(hrUrl);

        //print the status code
        System.out.println("response.statusCode() = " + response.statusCode());

        //print the json body
        response.prettyPrint();
    }

    /*
        Given accept type is json
        When user sends get request to regions endpoint
        Then response status code must be 200
        and body is json format
     */
    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                            .when().get(hrUrl);

        //Verify Status code is 200
        Assert.assertEquals(response.statusCode(),200);

        //Verify content-type is application/json
        System.out.println("response.contentType() = " + response.contentType());
        Assert.assertEquals(response.contentType(),"application/json");
    }

    @Test
    public void test3(){
        RestAssured.given().accept(ContentType.JSON)
                    .when().get(hrUrl)
                    .then().assertThat().statusCode(200)
                    .and().contentType("application/json");
    }

    /*
        Given accept type is json
        When user sends get request to regions/2
        Then response status code must be 200
        and body is json format
        and response body contains Americas
     */

    @Test
    public void test4(){
      /* Response response1 = RestAssured.given().accept(ContentType.JSON)
                                       .when().get(hrUrl+"/2")
                                       .then().statusCode(200)
                                       .and().contentType("application/json");*/


        Response response = given().accept(ContentType.JSON)
                .when().get(hrUrl + "/2");

        //verify status code
        Assert.assertEquals(response.getStatusCode(),200);

        //verify content type
        Assert.assertEquals(response.contentType(),"application/json");

        //verify body contains Americas
        Assert.assertTrue(response.body().asString().contains("Americas"));
    }
}
