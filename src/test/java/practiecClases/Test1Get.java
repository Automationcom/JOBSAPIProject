package practiecClases;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
//import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class Test1Get {
	@Test
 void test_1() {
	 Response response = RestAssured.get("https://jobs123.herokuapp.com/Jobs");
	 
	 System.out.println(response.getStatusCode());
	 System.out.println(response.getContentType());
	 System.out.println(response.getBody());
	 System.out.println(response.getTime());
	 System.out.println(response);
	 
	 int statusCode =response.getStatusCode();
	 Assert.assertEquals(statusCode, 200);
	// Assert.assertEquals(statusCode, 201);
 }
	@Test
	void test_2(){
		given().get("https://jobs123.herokuapp.com/Jobs").then().statusCode(200);
		//given() .get("https://jobs123.herokuapp.com/Jobs") .then().statusCode(201);
	}
	@Test
	void test_3() {
		Response response =given().auth().basic("admin","password").get("https://jobs123.herokuapp.com/Jobs");
	}
}
