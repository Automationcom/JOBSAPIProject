package JobsAllMethods;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import utils.ExcelUtilsJobs;
import utils.jobslUtils;


	
public class JobsPostDataDrVa {
	
	
	@Test(dataProvider="params")
      public void Test_1Post(String JobId, String JobTitle, String JobLocation, String JobCompanyName,String JobType,String JobPostedtime,String JobDescription) {
		
		String s = given().auth().preemptive().basic(jobslUtils.admin,jobslUtils.password )
		.accept(ContentType.JSON)
		.queryParam( "Job Title",JobTitle )
		.queryParam("Job Company Name", JobCompanyName)
		.queryParam("Job Location", JobLocation)
		.queryParam("Job Posted time",JobPostedtime)
		.queryParam("Job Description", JobDescription)
		.queryParam("Job Id",JobId)
		.queryParam("Job Type", JobType).log().all()
		.when()
		.post(jobslUtils.baseURI_endpoint)
				
		.then().statusCode(200)
		.extract().asPrettyString();
		
		
		//data validation
		System.out.println("Respose body:"+ s);
		
		Assert.assertEquals(s.contains(JobId), true);
		Assert.assertEquals(s.contains(JobTitle), true);
		Assert.assertEquals(s.contains(JobLocation), true);
		Assert.assertEquals(s.contains(JobType), true);
		Assert.assertEquals(s.contains(JobDescription), true);
		Assert.assertEquals(s.contains(JobPostedtime), true);
		Assert.assertEquals(s.contains(JobCompanyName), true);
		
		assertThat("Json schema",s.replaceAll("NaN","null"),matchesJsonSchemaInClasspath("JsonSchema.json"));
		}
    


@DataProvider(name="params")
     public String[][] getdata() throws IOException{
	String path = "./ExcelData/TestData.xlsx";
	ExcelUtilsJobs util = new ExcelUtilsJobs(path);
	int rows = util.getRowCOunt("Sheet1");
	int cols = util.getCellCOunt("Sheet1", 1);
	
   String inputData[][] = new String[rows][cols];
   for(int i=1; i<=rows; i++) 
   {
	   for(int j=0; j<cols; j++) 
	   {
		 inputData[i-1][j] = util.getCellData("Sheet1", i, j); 
	   }
	   
   }
	 return inputData;
}

}
