package practiecClases;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.Assert.assertThat;

public class Jobs_getMethod {
	@Test
	void getJobsApi() {
		//Specify baseURI
	RestAssured.baseURI="https://jobs123.herokuapp.com/"; 
    
	//RequestObject
	RequestSpecification httpsRequest= RestAssured.given();
	
	//Response Object
	Response response = httpsRequest.request(Method.GET,"/Jobs");
	
	String responseBody = response.getBody().asString();
	System.out.println("Response Body is :" +responseBody );
	
	JsonPath jsonpath = response.jsonPath();
	Object JobTitle = jsonpath.getString("JobTitle");
	
	
	assertThat("Json schema",responseBody.replaceAll("NaN","null"),matchesJsonSchemaInClasspath("Jobsschema.json") );
	
	 
	 
	 //status code validation
	 int statusCode =response.getStatusCode();
	 Assert.assertEquals(statusCode, 200);
	 System.out.println("Status code is:"+ statusCode);
	 //Assert.assertEquals(statusCode, 201);
	 
	 //status line Verification
	 String statusline = response.getStatusLine();
	 System.out.println("Status line is:"+statusline);
	 Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
	
	}
	

	@Test
	void test_Auth() {
		Response response =given().auth().basic("admin","password").get("https://jobs123.herokuapp.com/Jobs");
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
