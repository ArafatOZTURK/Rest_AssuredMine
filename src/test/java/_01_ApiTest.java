import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

import javax.swing.plaf.PanelUI;

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


    @Test
    public void bodyArrayHasSizeTest() {
        // Soru : "http://api.zippopotam.us/us/90210"  endpoint in dönen
        // place dizisinin dizi uzunluğunun 1 olduğunu doğrulayınız.

        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().all()
                //.statusCode(200);
                .body("places", hasSize(1))
        ;

    }

    @Test
    public void CombiningTest() {


        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().all()
                .statusCode(200)
                .body("places", hasSize(1))
                .body("places[0].state", equalTo("California"))
                .body("places[0].'place name'", equalTo("Beverly Hills"))
        ;
    }

    @Test
    public void pathParamTest(){
        given()
                .pathParam("ulke", "us")
                .pathParam("postaKodu", "90210")
                .log().uri()

                .when()
                .get("http://api.zippopotam.us/{ulke}/{postaKodu}")

                .then()
                .log().body();

    }
    @Test
    public void queryParamTest(){
        //https://gorest.co.in/public/v1/users?page=1
        given()
                .param("page", 3)
                .log().uri()

                .when()
                .get("https://gorest.co.in/public/v1/users")
                // veya .get("https://gorest.co.in/public/v1/users?page=1")
                .then()
                .log().body()
                .body("data", hasSize(10))

                ;
    }

    @Test
    public void queryParamTest2() {
        // https://gorest.co.in/public/v1/users?page=3
        // bu linkteki 1 den 10 kadar sayfaları çağırdığınızda response daki donen page degerlerinin
        // çağrılan page nosu ile aynı olup olmadığını kontrol ediniz.

        for (int i = 1; i <= 10; i++) {


            given()
                    .param("page", i)
                    .log().uri()


                    .when()
                    .get("https://gorest.co.in/public/v1/users")
                    .then()
                    .body("meta.pagination.page",equalTo(i))

            ;

        }
    }

}





