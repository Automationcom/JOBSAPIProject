package practiecClases;


	
	import org.junit.Before;
	import org.testng.annotations.Test;

	import io.restassured.RestAssured;

	import static io.restassured.RestAssured.*;


	public class JobsPostM {

	@Before

	public void setup() {

	RestAssured.baseURI="https://jobs123.herokuapp.com/";
	//RestAssured.basePath="/Jobs";

	}

	    @Test
	   
	    public void MutlipleParam() {
	   
	    given()
	    .queryParam("Job Id", "4678")
	    .queryParam("Job Location", "SDET")
	    .queryParam("Job Company Name", "Numpy")
	    .queryParam("Job Type", "Permanent")
	    .queryParam("Job Posted time", "30 mins")
	    .queryParam("Job Description", "test")
	    .when()
	    .post("/Jobs/")
	    .then()
	    .statusCode(200).log().all();
	   
	   
	   
	    }
}
