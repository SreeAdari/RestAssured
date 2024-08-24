package API;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
 
given()

content type, add cookies, add authentication, add parameters, set headers etc.... 

When()

get, post, put, delete 

====================================

getUser() :    https://restful-booker.herokuapp.com/booking


createUser():  https://reqres.in/api/users
{
    "name": "morpheus",
    "job": "leader"
}
updateUser():  https://reqres.in/api/users/2
{
    "name": "morpheus",
    "job": "zion resident"
}

deleteUser(): https://reqres.in/api/users/{userid}

204 status code 

================================================
then()

validate status codes, validate response body, extract response, 

*/

public class HTTPRequests {

	int id;

	 //@Test(priority=1)
	public void getUser() {

		given()

				.when().get("https://restful-booker.herokuapp.com/booking")
				.then().statusCode(200)
				//.body("firstname", equalTo(14))
				.log().all(); // It will print all the response body

	}

	@Test(priority = 2)
	public void createUser() {

		HashMap data = new HashMap();
		data.put("firstname", "srinu");
		data.put("lastname", "adari");

		id = given().contentType("application/json").body(data)

				.when().post("https://restful-booker.herokuapp.com/booking").jsonPath().getInt("id");

		// .then()
		// .statusCode(201)
		// .log().all();

	}

	//@Test(priority = 3, dependsOnMethods = { "createUser" })
	public void updateUser() {

		
		        given()

				.when().post("https://reqres.in/api/users/" + id)

				.then().statusCode(200).log().all();

	}
	
	//@Test(priority=4)
	public void deleteUser() {
		
		

		         given()

				.when().post("https://reqres.in/api/users/"+id)

				.then().statusCode(204).log().all();

		
	}

}
