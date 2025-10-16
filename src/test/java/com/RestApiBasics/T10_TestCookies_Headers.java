package com.RestApiBasics;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
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
public class T10_TestCookies_Headers {
  @Test
  public void testCookies()
  {
	  Response res=given()
	  
			  			.when().get("https://www.google.com/");
	  
	  //get all Cookies in console
	  res.then().log().cookies();
	  
	  //get the value of single cookie
	  String actValue=res.getCookie("AEC");
	  System.out.println("Value is: "+actValue);
	  
	  /*
	   * validation
	   * 
	   * Two cookies value should not be same
	   * 
	   * As Every time we send request to the server, server generate new cookie 
	   */
	  
	  String expValue="AQYzjOf0PXNku0pT6E1Qxe";
	  
//	  Assert.assertFalse(actValue.contains(expValue),"Test Fail when two cookies are same!");
//	  System.out.println("Test Pass when two cookies are not same!");
//	  
	  Assert.assertTrue(!actValue.contains(expValue),"Test Fail when two cookies are same!");
	  System.out.println("Test Pass when two cookies are not same!");
  
	    
  }
  
  
  @Test
  public void testHeaders()
  {
	  Response res=given()
			  
	  					.when().get("https://www.google.com/");
	  
	  //get the all headers
	  res.then().log().headers();
	  
	  //get the header value
	  String header=res.getHeader("Content-Type");
	  System.out.println("Header value is: "+header);
	  
	  //validation
	  String exp="text/html; charset=ISO-8859-1";
	  
	  Assert.assertEquals(header,exp);
	  System.out.println("Header matched!");
	  
	  
	  
  }
  
  
  @Test
  public void logResponse()
  {
	  System.out.println("Request log");
	  Response res=given()
			  			.log().all()
			  
			  					.when().get("https://api.restful-api.dev/objects/7");
	  
	  System.out.println("Response log");
	  
	  //log only header
	  //res.then().log().headers();
	
	  //log only cookies
	  //res.then().log().cookies();
	 
	  //log obly body
	  //res.then().log().body();
	  
	  //log everything from server
	  res.then().log().all();
	  
  }
  
  
  
  
  
  
  
  
  
}
