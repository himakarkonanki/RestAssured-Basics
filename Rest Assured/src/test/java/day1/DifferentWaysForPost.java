package day1;

import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DifferentWaysForPost {

    //Post using HashMap
    @Test(enabled = false)
    void createUserUsingHashMap(){
        HashMap<String, String> data= new HashMap<>();
        data.put("name","Himakar");
        data.put("job","Software Engineer");

        given()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(data)

                .when()
                .post("https://reqres.in/api/users")

        .then()
                .statusCode(201)
                .log().all();

    }

    //Post using JSONObject
    //org.json
    @Test(enabled = false)
    void createUserUsingJSONObject(){
        JSONObject js=new JSONObject();
        js.put("name","Prasanna Kumar");
        js.put("job","Developer");

        given()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(js.toString())

                .when()
                .post("https://reqres.in/api/users")

                .then()
                .statusCode(201)
                .body("name",equalTo("Prasanna Kumar"))
                .log().all();
    }

    //Post using POJO Class
    @Test(enabled = true)
    void createUserUsingPOJO(){
        User user= new User();
        user.setName("Raghu");
        user.setLocation("Hyderabad");
        user.setPhone("8105XXX09a");

        given()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(user)

                .when()
                .post("https://reqres.in/api/users")

                .then()
                .statusCode(201)
                .log().all();
    }

}
