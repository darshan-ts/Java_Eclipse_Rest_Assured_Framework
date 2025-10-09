package com.RestApiBasics;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class T1_FirstAPITest {
  @Test
  public void testSingleUser() 
  {
	  /*
	   * RestAssured is class through which we can pass request to server
	   * 
	   * Response is interface in RestAssured framework
	   */
	  
	  //RestAssured.get("https://api.restful-api.dev/objects/7")
	  Response res=RestAssured.given().header("x-api-key","reqres-free-v1").get("https://reqres.in/api/users/2");
	  
	  System.out.println("Status code is: "+res.getStatusCode());
	  System.out.println("Status line is: "+res.getStatusLine());
	  System.out.println("Response time is: "+res.getTimeIn(TimeUnit.MILLISECONDS));
	  System.out.println("------Raw type(String) body-----");
	  System.out.println(res.asString());
	  System.out.println("------json response-------");
	  System.out.println(res.asPrettyString());
	  System.out.println("Header detail: "+res.getHeader("Content-type"));
	  
	  
	    
  }
}