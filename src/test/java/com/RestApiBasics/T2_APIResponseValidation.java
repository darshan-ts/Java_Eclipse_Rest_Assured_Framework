package com.RestApiBasics;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class T2_APIResponseValidation {
  @Test
  public void testResponse() 
  {
	  
	  RestAssured.baseURI="https://reqres.in";
	 Response res= RestAssured.given().header("x-api-key","reqres-free-v1").get("/api/users/2");
	 
	 
	 //validate status code 
	 int actCode=res.getStatusCode();
	 Assert.assertEquals(actCode,200,"Status code is not matched: "+actCode);
	 System.out.println("Status code is matched...: "+actCode);
	 
	 //status message -OK
	 String statusLine=res.getStatusLine();
	 System.out.println(statusLine);
	 Assert.assertTrue(statusLine.contains("OK"));
	 System.out.println("Status message is pass...");
	 
	 //test header
	 System.out.println(res.getHeader("Content-Type"));
	 String expHeader="application/json";
	 Assert.assertTrue(res.getHeader("Content-Type").contains(expHeader));
	 System.out.println("Header pass...!");
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	  
	  
	  
  }
}