package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.ExtentReportManager;
import io.restassured.response.Response;

public class UserTests extends ExtentReportManager{
	
	
	Faker faker;
	User userpayload;
	//ExtentReportManager Ex = new ExtentReportManager();
	
	public Logger logger;
	
	@BeforeClass
	public void setupdata()
	{
		//Ex.onStart(null);
		faker = new Faker();
		userpayload = new User();
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirtsname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
        logger= LogManager.getLogger(this.getClass());
		
		logger.debug("debugging.....");
		
	}
    
	@Test(priority=1)
	public void testpostuser()
	{
		logger.info("********** Creating user  ***************");
		Response response = UserEndpoints.createuser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("********** User is created***************");
		
	}
	
	@Test(priority=2)
	public void testgettuser()
	{
		Response response = UserEndpoints.readuser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority=3)
	public void testupdateuser()
	
	
	{
		
		userpayload.setFirtsname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		Response response = UserEndpoints.updateuser(this.userpayload.getUsername(),userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
}
