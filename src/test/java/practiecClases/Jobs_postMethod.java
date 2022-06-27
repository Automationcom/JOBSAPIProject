package practiecClases;
import java.net.http.HttpRequest;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Jobs_postMethod {
	@Test
	public void CreatePost() {
	RestAssured.baseURI	= "https://jobs123.herokuapp.com";
	
	RequestSpecification httpsRequest= RestAssured.given();
	
	JSONObject requestParam = new JSONObject();
	// This is  Pavan   video
	
	requestParam.put( "Job Title", "SDET");
	requestParam.put("Job Company Name", "Computer Enterprises, Inc.");
	requestParam.put("Job Location", "NAGHA");
	requestParam.put("Job Posted time","30 min");
	requestParam.put("Job Description", "This Is Important for every one");
	requestParam.put("Job Id","4567");
	requestParam.put("Job Type", "COntract");
	
	httpsRequest.header("Content-Type","application/joson");
	
	httpsRequest.body(requestParam.toJSONString());
	
	Response response = httpsRequest.request(Method.POST,"/Jobs");
	
	String resbody = response.getBody().asString();
	System.out.println("Res body:"+ resbody);
	
	
}
}
