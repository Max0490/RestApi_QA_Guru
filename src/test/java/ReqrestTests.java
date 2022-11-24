import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class ReqrestTests {

    @Test
    void singleUser() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"));
    }
    @Test
    void singleUserNotFound() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().status()
                .statusCode(404);
    }
    @Test
    void singleResource() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.name", is("fuchsia rose"))
                .body("data.year", is("2001"))
                .body("data.color", is("#C74375"))
                .body("pantone_value", is("17-2031"));
    }
    @Test
    void singleResourseNotFound() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .log().status()
                .statusCode(404);
    }
    @Test
    void idUserTest() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", hasItems(7, 8, 9, 10, 11, 12));
    }

}
