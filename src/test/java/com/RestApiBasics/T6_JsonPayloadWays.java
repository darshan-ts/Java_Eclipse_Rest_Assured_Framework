package com.RestApiBasics;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
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

public class T6_JsonPayloadWays
{
  @Test(priority=1)
  public void createPayloadUsigBasic() 
  {
	  System.out.println("------------Request Payload Basic Copy paste Technique------------");
	  //copy paste
	  Response res=given()
			  		.header("x-api-key","reqres-free-v1")
			  		.header("Content-Type","application/json")
			  			.body("{\n"
			  					+ "    \"name\": \"Priyanka\",\n"
			  					+ "    \"job\": \"SDET\"\n"
			  					+ "}")
	  
			  				.when().post("https://reqres.in/api/users");
	  
	  //log the response
	  res.then().log().body();
	  
	  //validate status code
	  Assert.assertEquals(res.getStatusCode(),201);
	  System.out.println("Status code is Matched!");
	  
	  //job=SDET
	  String job=res.jsonPath().getString("job");	 
	  Assert.assertEquals(job,"SDET");
	  System.out.println("Job matched!");
	  
  }
  
  @Test(priority=2)
  public void testPayloadUsingHashMap()
  {
	  System.out.println("------------Request Payload HashMap Technique------------");

	 //Request paylaod
	  HashMap<String,Object> data=new HashMap<String,Object>();
	  data.put("name","Priyanka");
	  data.put("job","SDET");
	  
	  Response res=given()
			  		.header("x-api-key","reqres-free-v1")
			  			.header("Content-Type","application/json")
			  				.body(data)
	
			  					.when().post("https://reqres.in/api/users");
	  
	  
	  //log the response
	  res.then().log().body();
	  
	  Assert.assertEquals(res.getStatusCode(),201);
	  System.out.println("Status code is Matched!");
	  
	  //job=SDET
	  String job=res.jsonPath().getString("job");	 
	  Assert.assertEquals(job,"SDET");
	  System.out.println("Job matched!");
	  
	  
	  
	  
  }
}