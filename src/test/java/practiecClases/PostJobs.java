package practiecClases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import static io.restassured.RestAssured.*;

public class PostJobs {
	@Test
	void Test_1Post(String JobId, String JobTitle, String JobLocation, String JobCompanyName,String JobType,String JobPostedtime,String JobDescription) {
		
		String s = given().auth().preemptive().basic("admin", "password")
		.accept(ContentType.JSON)
		.queryParam( "Job Title", "SDET")
		.queryParam("Job Company Name", "Computer Enterprises, Inc.")
		.queryParam("Job Location", "NAGHALAND")
		.queryParam("Job Posted time","30 min")
		.queryParam("Job Description", "This Is Important to every one")
		.queryParam("Job Id","45600")
		.queryParam("Job Type", "COntract").log().all()
		.when()
		.post("https://jobs123.herokuapp.com/Jobs")
				
		.then().statusCode(200)
		.extract().asPrettyString();
		
		
		//data validation
		
		System.out.println("Respose body:"+ s);
		//Verify the headers form response
		

		//System.out.println("Header content-Length:"+ s);
		//System.out.println("Header Server:"+ s);
		
		Assert.assertEquals(s.contains("45600"), true);
		Assert.assertEquals(s.contains("SDET"), true);
		Assert.assertEquals(s.contains("NAGHALAND"), true);
		Assert.assertEquals(s.contains("COntract"), true);
		Assert.assertEquals(s.contains("This Is Important to every one"), true);
		Assert.assertEquals(s.contains("30 min"), true);
		Assert.assertEquals(s.contains("Computer Enterprises, Inc."), true);
		//Assert.assertEquals(s.contains("14075"), true);
		
		//Assert.assertEquals(s.contains("gunicorn"), true);
		
		
		  
		  
		  
	}

}
