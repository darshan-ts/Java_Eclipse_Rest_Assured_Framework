package com.RestApiBasics;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class T8_JsonFileReading {
  @Test
  public void fileTest() throws FileNotFoundException
  {
System.out.println("------------Request Payload JsonFile Technique------------");
	  
	  //request payload
	  //file path
	  File f1=new File(System.getProperty("user.dir")+"//JsonFiles//Booking.json");
	  
	  //file read
	  FileReader fr=new FileReader(f1);
	  
	  //To convert file character data into Json : JSONTokener class
	  JSONTokener token=new JSONTokener(fr);
	  
	  //to structure it into jsonObject: JSONObjectClass
	  JSONObject obj=new JSONObject(token);
	  	  
	  Response res=given()
			  		.header("Content-Type","application/json")
			  		.body(obj.toString())
			  		.when().post("https://restful-booker.herokuapp.com/booking");
	  
	  //log the response
	  res.then().log().body();
	  
	//status code
	  	int actCode=res.getStatusCode();
	  	Assert.assertEquals(actCode,200);
	  	System.out.println("Status code match!.."+actCode);
	  	
	  //get the bookingid from current response
	  	int id=res.jsonPath().getInt("bookingid");
	  	System.out.println("Id is: "+id);
  }
}