package ch.helvetia.evn.controller.it;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class CLSAdapterServiceITest {

    @Test
    public void testHelloEndpoint() {
        given()
//                .pathParam("name", "")
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("hello"));
    }

}