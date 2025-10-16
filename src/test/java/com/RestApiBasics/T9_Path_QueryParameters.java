package com.RestApiBasics;
import static io.restassured.RestAssured.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
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
public class T9_Path_QueryParameters {
  @Test
  public void testParameters()
  {
	  RestAssured.baseURI="https://api.restful-api.dev";
	  
	 Response res= given()
			 		.pathParam("path","/objects")
			 		.queryParam("id",3)
			 			.queryParam("id",5)
			 				.queryParam("id",10)
			 				.log().all()
			 			//.when().get("https://api.restful-api.dev/objects?id=3&id=5&id=10");
			 			//.when().get("https://api.restful-api.dev/{path}");
			 				.when().get("/{path}");
	 
	 //log the result
	 res.then().log().body();
	 
	 //assert status code
	 Assert.assertEquals(res.getStatusCode(),200);
	 System.out.println("Status code matched!");
	 
	  
	 System.out.println("------All Ids-----");
	  //get the all ids
	List<String> allIds= res.jsonPath().getList("id");
	
	for(String i:allIds)
	{
		System.out.println(i);
	}
	
	 
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
  }
  
  
}