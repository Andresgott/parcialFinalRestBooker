package utils;

import constants.BookingEndPoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static constants.BookingEndPoints.BASE_URL;
import static constants.BookingEndPoints.GET_BOOKINGS;

public class RequestBooking
{
    public static Response get(String endpoint){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        Response response = RestAssured
                .when().get(endpoint);

        response.then().log().body();
        return response;
    }

    public static Response getById(String endpoint, String id){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        Response response = RestAssured
                .given().pathParam("id", id)
                .when().get(endpoint);
        response.then().log().body();
        return response;
    }

    public static Response post(String endpoint, String payload){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        Response response = RestAssured
                .given().contentType(ContentType.JSON).body(payload)
                .when().post(endpoint);
        response.then().log().body();
        return response;
    }

    public static Response put(String endpoint, String id, String payload){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        Response response = RestAssured
                .given().contentType(ContentType.JSON).body(payload)
                .and().pathParam("id",id)
                .when().put(endpoint);
        response.then().log().body();
        return response;
    }

    public static Response delete(String endpoint, String id){
        RestAssured.baseURI = BASE_URL;
        Response response = RestAssured
                .given().pathParam("id", id)
                .when().delete(endpoint);
        response.then().log().body();
        return response;
    }

}
