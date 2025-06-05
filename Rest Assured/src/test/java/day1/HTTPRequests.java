package day1;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class HTTPRequests{

    int id;

    @Test(priority = 1)
    void getUser(){

                when()
                    .get("https://reqres.in/api/users?page=2")

                .then()
                    .statusCode(200)
                    .body("page", equalTo(2))
                    .log().all();
    }

    @Test(priority=2)
    void createUser() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "Himakar");
        data.put("job", "SDET");

        RestAssured.baseURI = "https://reqres.in";

        id=given()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(data)

                .when()
                .post("/api/users")
                .jsonPath().getInt("id");

//                .then()
//                .statusCode(201)
//                .log().all();



    }

    @Test(dependsOnMethods = {"createUser"})
    void updateUser(){
        HashMap<String, String > data= new HashMap<>();
        data.put("name", "john");
        data.put("job", "teacher");

        given()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(data)

                .when()
                    .put("https://reqres.in/api/users/" + id)


                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 4)
    void deleteUser(){
        given()
                .header("x-api-key", "reqres-free-v1")

                .when()
                    .delete("https://reqres.in/api/users/"+id)

                .then()
                .statusCode(204)
                .log().all();
    }


}
