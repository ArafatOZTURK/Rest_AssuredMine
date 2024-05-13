import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class _01_ApiTest {

    @Test
    public void test1() {


        given().when().then();
    }

    @Test
    public void StatusCodeTest() {


        given().when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().all()
                .statusCode(200)
        ;
    }

    @Test
    public void ContentTypeTest() {

        given().when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
        ;

    }

    @Test
    public void checkCountryInResponseBody() {

        given().when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()
                .statusCode(200)
                .body("country", equalTo("United States"))
        ;
    }

    @Test
    public void checkCountryInResponseBody1() {
        given()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()
                .body("places[0].state", equalTo("California"))

        ;
    }

    @Test
    public void checkHasItem() {
        // Soru : "http://api.zippopotam.us/tr/01000"  endpoint in dönen
        // place dizisinin herhangi bir elemanında  "Dörtağaç Köyü" değerinin
        // olduğunu doğrulayınız

        given()
                .get("http://api.zippopotam.us/tr/01000")
                .then()
                .log().body()
                .body("places.'place name'", hasItem("Dörtağaç Köyü"))
        ;
    }


    }




}
