package com.training.sanity.tests;

import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.RealEstateLoginPOM;
import com.training.pom.RealEstatePostsPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

	public class RealEstateNewPostsTC17 {
		private WebDriver driver;
		private String baseUrl;
		private RealEstateLoginPOM RlloginPOM;
		private RealEstatePostsPOM RlPosts;
		private static Properties properties;
		private ScreenShot screenShot;
	

		@BeforeTest
		public static void setUpBeforeClass() throws IOException {
			properties = new Properties();
			FileInputStream inStream = new FileInputStream("./resources/others.properties");
			properties.load(inStream);
		}

	
		@BeforeClass
		public void setUp() throws Exception {
			driver = DriverFactory.getDriver(DriverNames.CHROME);
			Thread.sleep(10000);
			RlloginPOM = new RealEstateLoginPOM(driver); 
			RlPosts= new RealEstatePostsPOM(driver);
			baseUrl = properties.getProperty("baseURL");
			screenShot = new ScreenShot(driver); 
			// open the browser 
			driver.get(baseUrl);
		}
			
		@AfterClass()
		public void tearDown() throws Exception {
			Thread.sleep(1000);
			driver.quit();
		}
				
		@Test(priority=1)
		public void ValidateLoginTest() {
//			WebDriverWait wait = new WebDriverWait(driver, 70);
//			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@class='sign-in']"))));
			RlloginPOM.clickLoginLink();
			RlloginPOM.sendUserName("admin");
			RlloginPOM.sendPassword("admin@123");
			RlloginPOM.clickSubmitBtn(); 
			screenShot.captureScreenShot("TC17_Dashboard");
			
		}
		
		@Test(priority=2)
		public void ValidateNewPost() throws InterruptedException 
		{
			System.out.println("Add New posts");
			RlPosts.AddPosts("PriyaAPt", " PriyaHome");
			String sExpectedMsg = "Post published. View post";
			String sActualMsg = RlPosts.getpublishedMessage();
			//Verify the published message after new posts
			Assert.assertEquals(sActualMsg,sExpectedMsg);
			System.out.println(sActualMsg);
			screenShot.captureScreenShot("PublishNewPostMessage");
										
		 }
		
		@Test(priority=3)
		public void LogOutTest() throws InterruptedException
		{
			System.out.println("Validate logout functionality");
			WebElement Webadmin = driver.findElement(By.id("wp-admin-bar-my-account"));
			Actions act = new Actions(driver);
			act.moveToElement(Webadmin).build().perform();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//a[@Class='ab-item'][contains(text(),'Log Out')]")).click();
			screenShot.captureScreenShot("TC16_Logout");
			
		}

	
}
