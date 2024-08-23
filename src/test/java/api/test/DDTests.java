package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	@Test(priority=1,dataProvider="Data", dataProviderClass= DataProviders.class)
	public void testpostuser(String id, String username, 
			String fname, String lname, String email, String pwd, String phine)
	{
		User userpayload = new User();
		userpayload.setId(Integer.parseInt(id));
		userpayload.setUsername(username);
		userpayload.setFirtsname(fname);
		userpayload.setLastname(lname);
		userpayload.setEmail(email);
		userpayload.setPassword(pwd);
		userpayload.setPhone(phine);
		
		Response response = UserEndpoints.createuser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}
	@Test(priority=2,dataProvider="UserNames", dataProviderClass= DataProviders.class)
	public void deleteuser(String username)
	{
		Response response=UserEndpoints.deleteuser(username);
		Assert.assertEquals(response.getStatusCode(),200);
	}
	

}
