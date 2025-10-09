package com.RestApiBasics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class T3_APIMonitoring {
  @Test
  public void testAPIResponse()
  {
	  
	  while(true) 
	  {
	  Response res=RestAssured.get("https://www.google.com/");
	  
	  int actCode=res.getStatusCode();
	  
	  if(actCode==200)
	  {
		  System.out.println("Status code is: "+actCode);
	  }
	  
	  }
  }
  
}