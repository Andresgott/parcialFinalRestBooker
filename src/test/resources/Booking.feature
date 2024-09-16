Feature: Tests of the whole RestfulBooker database
  Background: Checking that the endpoints do their job
    @run
    Scenario: checking if the get booking gets me all the list of bookings
      Given : I perform a GET call to the booking endpoint
      And : I verify that the body does not have size 0
      And : I verify that the status code is 200
    @run
    Scenario: checking if the get booking by id gets me the booking i'm looking for if the id exists
      Given : I perform a GET by id call to the booking endpoint with "4006"
      And : I verify that the body does not have size 0
      And : I verify that the status code is 200
    @run
    Scenario: checking if the get booking by id send a not found code if the id doesn't exist
      Given : I perform a GET by id call to the booking endpoint with "aaa"
      And : I verify that the status code is 404
    @run
    Scenario Outline: checking if the post booking is working properly when i send the expected data
      Given : I perform a POST request with data
        | <firstname> | <lastname> | <totalprice> | <depositpaid> | <aditionalneeds> | <checkin> | <checkout> |
      And : I verify that the body does not have size 0
      And : I verify that the status code is 200
      Examples:
        | firstname | lastname | totalprice | depositpaid | aditionalneeds | checkin    | checkout   |
        | andres    | gottlieb | 1111       | true        | nothing        | 2024-01-01 | 2024-01-10 |

    @run
    Scenario Outline: checking if the post booking sends code 400 of bad request when I don't send a firstname
      Given : I perform a POST request with data
        | <firstname> | <lastname> | <totalprice> | <depositpaid> | <aditionalneeds> | <checkin> | <checkout> |
      And : I verify that the status code is 400
      Examples:
        | firstname | lastname | totalprice | depositpaid | aditionalneeds | checkin    | checkout   |
        |           | gottlieb | 1111       | true        | nothing        | 2024-01-01 | 2024-01-10 |

    @run
    Scenario: checking if the put booking sends code 200 of ok when i update with the correct data
      Given : I perform PUT request with data
        | 442 | Andres | gottlieb | 1111 | true | nothing | 2024-01-01 | 2024-01-10 |
      And : I verify that the status code is 200



