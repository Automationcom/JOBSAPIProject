package JobsAllMethods;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import utils.ExcelUtilsJobs;
import utils.jobslUtils;

public class JobsputDrVa {
	
	@Test(dataProvider="putname")
	void putTest(String JobId, String JobTitle) {
	String response = given().auth().preemptive().basic(jobslUtils.admin,jobslUtils.password)
	.accept(ContentType.JSON)
	
	.queryParam("Job Title", JobTitle )
	
	.queryParam("Job Id",JobId)
	.log().all()
	.when()
	.put(jobslUtils.baseURI_endpoint)
			
	.then().statusCode(200)
	.extract().asPrettyString();
	
	System.out.println("Respose body:"+ response);
	
	//String responseBody = response.getBody().asString();
	
	Assert.assertEquals(response.contains(JobTitle),true);
	Assert.assertEquals(response.contains(JobId),true);
	
	assertThat("Json schema",response.replaceAll("NaN","null"),matchesJsonSchemaInclasspath("JsonSchema.json"));
	}
	
	private void assertThat(String string, String replaceAll, Object matchesJsonSchemaInclasspath) {
		// TODO Auto-generated method stub
		
	}

	private Object matchesJsonSchemaInclasspath(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@DataProvider(name="putname")
    public String[][] getdata() throws IOException{
	String path = "./ExcelData/TestData.xlsx";
	ExcelUtilsJobs util = new ExcelUtilsJobs(path);
	int rows = util.getRowCOunt("Sheet2");
	int cols = util.getCellCOunt("Sheet2", 1);
	
  String putData[][] = new String[rows][cols];
  for(int i=1; i<=rows; i++) 
  {
	   for(int j=0; j<cols; j++) 
	   {
		 putData[i-1][j] = util.getCellData("Sheet2", i, j); 
	   }
	   
  }
	   
  return putData;
	}	
}

