package com.RestApiBasics;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.RestApiBasics.POJOClasses.AuthPojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class T11_Serialization_Deserialization {
  @Test
  public void testSerialization() throws JsonProcessingException
  {
	  //Java Object(POJO) into JSON
	  
	  AuthPojo auth=new AuthPojo();
	  auth.setUsername("Darshan T S");
	  auth.setPassword("test123");
	  
	  
	  //convert into json
	  ObjectMapper obj=new ObjectMapper();
	  
	  String jsonObj=obj.writerWithDefaultPrettyPrinter().writeValueAsString(auth);
	  
	  System.out.println(jsonObj);
	  
	  
	  
  }
  
  
  
  @Test 
  public void testDeserialization() throws JsonMappingException, JsonProcessingException
  {
	  
	  String jsondata="{\n"
	  		+ "  \"username\" : \"Darshan\",\n"
	  		+ "  \"password\" : \"test123\"\n"
	  		+ "}";
	  
	  //convert json into pojo
	  ObjectMapper obj=new ObjectMapper();
	  
	  AuthPojo authobj=obj.readValue(jsondata,AuthPojo.class);
	  
	  //validation
	  String name=authobj.getUsername();
	  Assert.assertEquals(name,"Darshan");
	  System.out.println("User name matched!");
	  
	  
	  
  }
}