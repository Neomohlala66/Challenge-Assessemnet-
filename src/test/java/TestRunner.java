import Models.Dog;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.specification.*;
import org.junit.*;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.*;

public class TestRunner {
    /**
     * URLs variables.
     */
    private final String baseURL = "https://dog.ceo/api/breeds/image/random";
    private final String allBreedsURL = "/breeds";
    private final String subBreedsURL = "/";
    private final String breedRandomIMG_URL = "/breeds/image/random";

    /**
     * Specifications variables.
     */
    private RequestSpecification requestSpec;
    //private ResponseSpecification responseSpec;

    @Test
    public void get_all_breeds(){
       HashMap<String, List<String>> jsonString = given()
                .header("content-type", "application.json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(baseURL)
                .then().log().all()
                .assertThat()
                .statusCode(200)
               .and()
               .extract().path("");

        System.out.println("*************\n" + jsonString + "\n*************");
    }

    @Test
    public void get_sub_breeds(){
        given().header("content-type", "application.json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(subBreedsURL)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void get_randomImageForSpecific_breed(){
        given().header("content-type", "application.json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(breedRandomIMG_URL)
                .then()
                .assertThat()
                .statusCode(200);
    }
}
