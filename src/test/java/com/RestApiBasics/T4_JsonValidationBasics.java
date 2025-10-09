package com.RestApiBasics;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class T4_JsonValidationBasics {
  @Test
  public void testJsonBody() 
  {
	  
	  Response res=RestAssured.given().header("x-api-key","reqres-free-v1").get("https://reqres.in/api/users/2");
	  
	 System.out.println(res.asPrettyString());
	 
	 //id is 2
	 int id=res.jsonPath().getInt("data.id");
	 Assert.assertEquals(id,2);
	 System.out.println("Id matched!");
	 
	 //"first_name": "Janet"
	 String name=res.jsonPath().getString("data.first_name");
	 Assert.assertTrue(name.contains("Janet"));
	  
	  
	 //url should contains https?
	 String url=res.jsonPath().getString("support.url");
	 Assert.assertTrue(url.contains("https"));
	 System.out.println("Url matched!");
	 
	  //log the response in console
	  //res.then().log().headers();
	  
	  //res.then().log().body();
	  
	  //res.then().log().all();
	  
  }
}