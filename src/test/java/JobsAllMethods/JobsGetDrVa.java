package JobsAllMethods;

import java.io.IOException;


import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.Assert.assertThat;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ExcelUtilsJobs;
import utils.jobslUtils;

public class JobsGetDrVa {
	@Test(dataProvider="getAll")
	public void getJobsApi(String JobId, String JobTitle, String JobLocation, String JobCompanyName,String JobType,String JobPostedtime,String JobDescription) {
		//Specify baseURI
	RestAssured.baseURI= jobslUtils.BaseURI; 
    
	//RequestObject
	RequestSpecification httpsRequest= RestAssured.given();
	
	//Response Object
	Response response = httpsRequest.request(Method.GET,"/Jobs");
	
	String responseBody = response.getBody().asString();
	System.out.println("Response Body is :" +responseBody );
	
	
	 
	 //status code validation
	 int statusCode =response.getStatusCode();
	 Assert.assertEquals(statusCode, 200);
	 System.out.println("Status code is:"+ statusCode);
	 //Assert.assertEquals(statusCode, 201);
	 
	    Assert.assertEquals(responseBody.contains(JobId), true);
		Assert.assertEquals(responseBody.contains(JobTitle), true);
		Assert.assertEquals(responseBody.contains(JobLocation), true);
		Assert.assertEquals(responseBody.contains(JobType), true);
		Assert.assertEquals(responseBody.contains(JobDescription), true);
		Assert.assertEquals(responseBody.contains(JobPostedtime), true);
		Assert.assertEquals(responseBody.contains(JobCompanyName), true);
		
		assertThat("Json schema",responseBody.replaceAll("NaN","null"),matchesJsonSchemaInClasspath("JsonSchema.json"));
		System.out.println(responseBody);
		
	}
		
	/**@Test
	public void test_Auth() {
		Response response =given().auth().basic("admin","password").get("https://jobs123.herokuapp.com/Jobs");
	} **/

	
	@DataProvider(name="getAll")
	public String[][] getdata() throws IOException{
	String path = "./ExcelData/TestData.xlsx";
	ExcelUtilsJobs util = new ExcelUtilsJobs(path);
	int rows = util.getRowCOunt("Sheet1");
	int cols = util.getCellCOunt("Sheet1", 1);
	
	String getAlltData[][] = new String[rows][cols];
	   for(int i=1; i<=rows; i++) 
	   {
		   for(int j=0; j<cols; j++) 
		   {
			   getAlltData[i-1][j] = util.getCellData("Sheet1", i, j); 
		   }
		   
	   }
		   
	   return getAlltData;
}
}

