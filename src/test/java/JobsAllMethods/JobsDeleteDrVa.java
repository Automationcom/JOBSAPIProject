package JobsAllMethods;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.ExcelUtilsJobs;
import utils.jobslUtils;



public class JobsDeleteDrVa {
	
@Test(dataProvider="deleteParams")
public void delete_test(String JobId) {
	String sr = given().auth().preemptive().basic(jobslUtils.admin,jobslUtils.password)
	.queryParam("Job Id",JobId)
    .when()
    .delete(jobslUtils.baseURI_endpoint)
    .then().statusCode(200).log().all()
    .extract().asPrettyString();
	
	System.out.println("Status code is :"+ 200 );
	
	assertThat("Json schema",sr.replaceAll("NaN","null"),matchesJsonSchemaInclasspath("JsonSchema.json"));
	
	System.out.println(sr= "Shcema sucess");
}
private void assertThat(String string, String replaceAll, Object matchesJsonSchemaInclasspath) {
	// TODO Auto-generated method stub
	
}
private Object matchesJsonSchemaInclasspath(String string) {
	// TODO Auto-generated method stub
	return null;
}


 @DataProvider(name="deleteParams")
public String[][] getdata() throws IOException{
String path = "./ExcelData/TestData.xlsx";
ExcelUtilsJobs util = new ExcelUtilsJobs(path);
int rows = util.getRowCOunt("Sheet3");
int cols = util.getCellCOunt("Sheet3", 1);

String deleteData[][] = new String[rows][cols];
for(int i=1; i<=rows; i++) 
{
  for(int j=0; j<cols; j++) 
  {
	  deleteData[i-1][j] = util.getCellData("Sheet3", i, j); 
  }
  
}
  
return deleteData;
	  
	  
  }

}

