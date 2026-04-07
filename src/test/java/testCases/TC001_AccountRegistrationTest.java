package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	@Test
	public void verify_account_registration()
	{
		logger.info("******* Starting TC001_AccountRegistrationTest ********");
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on myAccount link..  ");
		hp.clickRegister();
		logger.info("Clicked on Register link..  ");
		AccountRegistrationPage regpage= new AccountRegistrationPage(driver);
		logger.info("providing customer details..  ");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");
		regpage.setTelephone(randomeNumbers());
		String Password = randomeAlphaNumeric();
		regpage.setPassword(Password);
		regpage.setConfirmPassword(Password);
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		logger.info("Validating expected message.. ");
		String confmsg=regpage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else 
		{
			logger.error("Test Failed..");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			
			Assert.fail();
		}
	}
}

