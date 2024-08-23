package api.endpoints;

import io.restassured.response.Response;
import  io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import api.payload.User;

public class UserEndpoints {
	
	public static Response createuser(User Payload)
	{
		Response res = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(Payload)
				.when().log().all()
				.post(Routes.post_url);
		
		return res;
		
	
	}
	
	public static Response readuser(String username)
	{
		Response res = given()
				.pathParam("username", username)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when().log().all()
				.get(Routes.get_url);
		
		return res;
		
	
	}
	
	public static Response deleteuser(String username)
	{
		Response res = given()
				.pathParam("username", username)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.delete(Routes.delete_url);
		
		return res;
		
	
	}
	
	public static Response updateuser(String username,User  payload)
	{
		Response res = given()
				
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", username)
				.body(payload)
				.when().log().all()
				.put(Routes.put_url);
		
		return res;
		
	
	}

}
