package com.RestApiBasics;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.testng.Assert;
import org.testng.annotations.Test;

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
public class T5_FirstBDDTest
{
  @Test
  public void SingleObjectTest()
  {
	  /*
	  given()
	  
	  .when().get("https://api.restful-api.dev/objects/7")
	  
	  .then().statusCode(200)
	  .body("id",equalTo("7"))
	  .log().body();
	  */
	  
	  Response res=given()
	  
			  		.when().get("https://api.restful-api.dev/objects/7");
	  
	  //log the response
	  res.then().log().body();
	  
	  //test for status code
	  int actCode=res.getStatusCode();
	  Assert.assertEquals(actCode,200);
	  System.out.println("Status code is: "+actCode);
	  
	  //Json body validation
	  String id=res.jsonPath().getString("id");
	  Assert.assertEquals(id,"7");
	  System.out.println("Id matched!");
	  
	  //"Hard disk size": "1 TB"
	  
	  String hd=res.jsonPath().getString("data['Hard disk size']");
	  System.out.println(hd);
	  Assert.assertTrue(hd.contains("TB"));
	  
  }
  
  
  @Test
  public void testBDDJsonValidation()
  {
	  //get the list of users
	  Response res=given()
			  			.header("x-api-key","reqres-free-v1")
	  
			  				.when().get("https://reqres.in/api/users?page=2");
	  
	  
	  //log the response
	  res.then().log().body();
	  
	  
	  //validation
	  Assert.assertEquals(res.getStatusCode(),200);
	  System.out.println("Status code is matched..."+res.getStatusCode());
	  
	  //page:2
	  int pageNo=res.jsonPath().getInt("page");
	  Assert.assertEquals(pageNo,2);
	  System.out.println("Page Number is :"+pageNo);
	  
	  
	  // "first_name": "George"
	  String fname=res.jsonPath().getString("data[4].first_name");
	  Assert.assertEquals(fname,"George");
	  System.out.println("Firts name is matched..."+fname);
	  
	  
	  //List of all ids getList()
	  List<Integer> allIds=res.jsonPath().getList("data.id");
	  System.out.println("Total number of ids: "+allIds.size());
	  
	  
	  for(Integer i:allIds)
	  {
		  System.out.println(i);
	  }
	  
	  System.out.println("------------------");

	  //all email
	  List<String> allEmail=res.jsonPath().getList("data.email");
	  
	  for(String i:allEmail)
	  {
		  System.out.println(i);
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  
  }
  
  
  
}