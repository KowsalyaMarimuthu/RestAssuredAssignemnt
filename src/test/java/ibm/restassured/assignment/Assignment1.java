package ibm.restassured.assignment;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.json.simple.JSONObject;

public class Assignment1 {

	
	@DataProvider(name="assignmentdataprovider")
	public Object[][] dataProvider1(){
		Object[][] obj = new Object[2][9];
		obj[0][0]=1;
		obj[0][1]="kowss";
		obj[0][2]="kowsalya";
		obj[0][3]="prem";
		obj[0][4]="Chennai";
		obj[0][5]="kowsalya@gmail.com";
		obj[0][6]="XXXXX";
		obj[0][7]="9876543210";
		obj[0][8]=0;
		obj[1][0]=2;
		obj[1][1]="kavs";
		obj[1][2]="kaviya";
		obj[1][3]="priya";
		obj[1][4]="Bangalore";
		obj[1][5]="kaviya@gmail.com";
		obj[1][6]="XXXXX";
		obj[1][7]="9807545324";
		obj[1][8]=0;
		
		return obj;
		
	}
	
	@DataProvider(name="demotestdata")
	public Object[][] fetchtestdata() throws IOException
	{
		Object[][] obj  = AssignmentData.testdata();
		
		return obj;
		
	}
	@Test(priority = 1,enabled=true,dataProvider = "demotestdata")
	public void createUser(String id, String username, String fname, String lname, String place, String email, String password, String phone, String userstatus, ITestContext value) {
	
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		JSONObject createObject = new JSONObject();
		createObject.put("id",id);
		createObject.put("username",username);
		createObject.put("firstname", fname);
		createObject.put("lastname",lname);
		createObject.put("place", place);
		createObject.put("email", email);
		createObject.put("password", password);
		createObject.put("phone", phone);
		createObject.put("userstatus", userstatus);
		given().
			contentType(ContentType.JSON).
			body(createObject.toJSONString()).
		when().
			post("/user").
		then().
			statusCode(200).log().all();
		System.out.println("-------------Created User-------------");
		
	}
	
	@Test(priority = 2, enabled = true)
	public void getUser(ITestContext value) {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		String userName = "kowss";
		given().
			get("/user/"+userName).
		then().
			statusCode(200).log().all();
		System.out.println("-------------Get User Details-------------");
	}
	
	@Test(priority = 3, enabled = true)
	public void modifyUser() {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		JSONObject modifyObject = new JSONObject();
		String modifyUsername = "kowss";
		
		modifyObject.put("firstname", "kowsalya");
		modifyObject.put("lastname","prem");
		modifyObject.put("place", "Chennai");
		modifyObject.put("email", "kowsalya@gmail.com");
		modifyObject.put("password", "XXXXX");
		modifyObject.put("phone", "9876543210");
		modifyObject.put("userstatus", 0);
		given().
			contentType(ContentType.JSON).
			body(modifyObject.toJSONString()).
		when().
			put("/user/"+modifyUsername).
		then().statusCode(200).log().all();
		System.out.println("-------------Modified User-------------");
}
	
	@Test(priority = 4, enabled = true)
	public void getModifiedUserDetails(ITestContext value) {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		String userName = "kowss";
		given().
			get("/user/"+userName).
		then().
			statusCode(200).log().all();
		System.out.println("-------------Get Modified User-------------");
	}
	
	@Test(priority = 5, enabled = true)
	public void deleteUser(ITestContext value) {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		String userName = "kowss";
		given().
			delete("/user/"+userName).
		then().
			statusCode(200).log().all();
		System.out.println("-------------Deleted User-------------");
	}
	
	
	@Test(priority = 6, enabled = true)
	public void login() {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		given().
			queryParam("username", "testuser").
			queryParam("password", "testpassword").
		when().
			get("/user/login").
		then().
			statusCode(200).log().all();
		System.out.println("-------------Successfully Logged In-------------");
	}
	
	@Test(priority = 7, enabled = true)
	public void logout() {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		given().
			get("/user/logout").
		then().
			statusCode(200).log().all();
		System.out.println("-------------Successfully Logged Out-------------");
	}
}
