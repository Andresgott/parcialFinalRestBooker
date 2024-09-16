package stepDefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.BookingEndPoints;
import entities.Booking;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import utils.RequestBooking;

import java.util.List;

import static constants.BookingEndPoints.GET_BOOKINGS;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;

public class BookingSteps
{
    Response response;
    public BookingSteps(){
        RestAssured.registerParser("text/plain", Parser.JSON);
    }

    @And(": I perform a GET call to the booking endpoint")
    public void get(){this.response = RequestBooking.get("/booking");}

    @And(": I verify that the body does not have size {int}")
    public void iVerifyThatBodyNotHaveSize(Integer size) {
        ((ValidatableResponse)((ValidatableResponse)this.response.then()).assertThat()).body("size()", Matchers.not(size), new Object[0]);
    }

    @And(": I verify that the status code is {int}")
    public void iVerifyStatusCodeIs(int statusCode) {
        ((ValidatableResponse)((ValidatableResponse)this.response.then()).assertThat()).statusCode(statusCode);
    }

    @And(": I perform a GET by id call to the booking endpoint with {string}")
    public void getById(String id){this.response = RequestBooking.getById("/booking/{id}",id);}

    @And(": I perform a POST request with data")
    public void post(DataTable bookingInfo) throws JsonProcessingException {
        //data: {"Andres","Gottlieb","1111","true","nothing","2024-01-01","2024-01-10"}
        List<String> data = bookingInfo.transpose().asList(String.class);
        Booking booking = new Booking();
        Booking.BookingDates bookingDates = new Booking.BookingDates();
        booking.setFirstName(data.get(0));
        booking.setLastName(data.get(1));
        booking.setTotalPrice(Integer.parseInt(data.get(2)));
        booking.setDepositPaid(Boolean.parseBoolean(data.get(3)));
        bookingDates.setCheckIn(data.get(4));
        bookingDates.setCheckOut(data.get(5));
        booking.setBookingDates(bookingDates);

        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(booking);

        response = RequestBooking.post("/booking",payload);
    }

    @And(": The message is {string}")
    public void verifyResponseMessage(String message){
        response.prettyPrint();
        response.then().assertThat().body("message", Matchers.equalTo(message));
    }

    @And(": I perform PUT request with data")
    public void Put(DataTable bookingInfo) throws JsonProcessingException {
        //data: {"442","Andres","Gottlieb","1111","true","nothing","2024-01-01","2024-01-10"}
        List<String> data = bookingInfo.transpose().asList(String.class);
        Booking booking = new Booking();
        Booking.BookingDates bookingDates = new Booking.BookingDates();
        booking.setFirstName(data.get(1));
        booking.setLastName(data.get(2));
        booking.setTotalPrice(Integer.parseInt(data.get(3)));
        booking.setDepositPaid(Boolean.parseBoolean(data.get(4)));
        bookingDates.setCheckIn(data.get(5));
        bookingDates.setCheckOut(data.get(6));
        booking.setBookingDates(bookingDates);

        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(booking);

        response = RequestBooking.put("/booking/{id}",data.get(0),payload);

    }





}
