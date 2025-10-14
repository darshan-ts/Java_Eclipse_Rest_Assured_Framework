package com.RestApiBasics;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.RestApiBasics.POJOClasses.AuthPojo;
import com.RestApiBasics.POJOClasses.BookingDatesPojo;
import com.RestApiBasics.POJOClasses.BookingPojo;
import com.RestApiBasics.POJOClasses.PartailBooking;

import io.restassured.response.Response;
/*
 * given():Prerequisite
 * ---------------------------------
 * Request payload,header,path parameter,query parameter,Authorization
 * 
 * when():Request-endpoint
 * ------------------------------------
 * GET,POST,PUT,PATCH,DELETE
 * 
 * then():validate
 * ---------------------------------
 * status code,status message, cookies, header, response time,response payload
 */
public class T7_APIChaining 
{
	public int bookingId;
	public String tokenValue;
	
	
	
  @Test(priority=1)
  public void createNewBooking() 
  {
	  System.out.println("----------Create new Booking------------");
	  //Request payload
	  BookingDatesPojo dates=new BookingDatesPojo();
	  dates.setCheckin("2025-10-14");
	  dates.setCheckout("2025-10-15");
	  
	  BookingPojo data=new BookingPojo();
	  data.setFirstname("Darshan");
	  data.setLastname("T S");
	  data.setTotalprice(999);
	  data.setDepositpaid(true);
	  data.setBookingdates(dates);
	  data.setAdditionalneeds("lunch");
	  
	  
	  Response res=given()
			  		.header("Content-Type","application/json")
			  		.body(data)
	  
			  		.when().post("https://restful-booker.herokuapp.com/booking");
	  
	  //log the response
	  res.then().log().body();
	  
	  
	  //status code should be 200
	  Assert.assertEquals(res.getStatusCode(),200);
	  System.out.println("Status code is Matched!..."+res.getStatusCode());
	  
	  //get the booking id and store it into variable
	  
	  bookingId=res.jsonPath().getInt("bookingid");
	  System.out.println("New booking created with id: "+bookingId);
	  
  }
  
  @Test(priority=2)
  public void getBookingDetails()
  {
	  System.out.println("----------Get the deatils of new Booking------------");

	  Response res=given()
			  			.when().get("https://restful-booker.herokuapp.com/booking/"+bookingId);
	  
	  //log the response
	  res.then().log().body();
	  
	  //Assert for status code
	  Assert.assertEquals(res.getStatusCode(),200);
	  System.out.println("Status code matched!.."+res.getStatusCode());
	  
	  System.out.println("Booking deatils for id: "+bookingId);
	  
	  
	  
  }
  
  @Test(priority=3)
  public void createToken()
  {
	  System.out.println("----------Generating new Token------------");

	  //request payload
	  AuthPojo data=new AuthPojo();
	  data.setUsername("admin");
	  data.setPassword("password123");
	  
	 Response res= given()
			 		.header("Content-Type","application/json")
			 		.body(data)
	  	
			 			.when().post("https://restful-booker.herokuapp.com/auth");
	  
	  //log the response
	 res.then().log().body();
	 
	 //get the token store it and reuse
	 tokenValue=res.jsonPath().getString("token");
	 System.out.println("New Token generated: "+tokenValue);
	 
  }
  
  @Test(priority=4)
  public void fullUpdateBooking()
  {
	  System.out.println("----------Updating booking for same id with token------------");
	  //Request payload
	  BookingDatesPojo dates=new BookingDatesPojo();
	  dates.setCheckin("2025-10-16");
	  dates.setCheckout("2025-10-17");
	  
	  
	  BookingPojo data=new BookingPojo();
	  data.setFirstname("Amit");
	  data.setLastname("Sharma");
	  data.setTotalprice(998);
	  data.setDepositpaid(true);
	  data.setBookingdates(dates);
	  data.setAdditionalneeds("dinner");
	  
	  Response res=given()
			  			.header("Content-Type","application/json")
			  				.header("Accept","application/json")
			  					.header("Cookie","token="+tokenValue)
			  						.body(data)
	  
			  							.when().put("https://restful-booker.herokuapp.com/booking/"+bookingId);
	  
	  //log the response
	  res.then().log().body();
	  
	  //Assertion for status code
	  Assert.assertEquals(res.getStatusCode(),200);
	  System.out.println("Status code matched!..."+res.getStatusCode());
	  
	  //firstname should be Amit
	  String fname=res.jsonPath().getString("firstname");
	  Assert.assertEquals(fname,"Amit");
	  System.out.println("First name matched!");
	 
	  
  }
  
  
  @Test(priority=5)
  public void partialBookingUpdate()
  {
	  System.out.println("----------Updating Partail booking for same id with token------------");

//	  BookingPojo data=new BookingPojo();
//	  data.setFirstname("Kiran");
//	  data.setLastname("Shahu");//properties will become null so can't read
	  
	  PartailBooking data=new PartailBooking();
	  data.setFirstname("Kiran");
	  data.setLastname("Shahu");
	  
	  Response res=given()
			  			.header("Content-Type","application/json")
			  					.header("Accept","application/json")
			  						.header("Cookie","token="+tokenValue)
			  						 .body(data)
					
			  							.when().patch("https://restful-booker.herokuapp.com/booking/"+bookingId);
	  
	  //log the response 
	  res.then().log().body();
  }
  
  
  @Test(priority=6)
  public void deleteSameBooking()
  {
	  System.out.println("----------Deleting new created booking------------");

	 Response res= given()
			 	.header("Content-Type","applcation/json")
	  			.header("Cookie","token="+tokenValue)
	  			.when().delete("https://restful-booker.herokuapp.com/booking/"+bookingId);
	  
	 //assert status code
	 Assert.assertEquals(res.getStatusCode(),201);
	 System.out.println("Booking deleted!");
  }
  
  
  
  
  
  
  
  
  
  
  
}