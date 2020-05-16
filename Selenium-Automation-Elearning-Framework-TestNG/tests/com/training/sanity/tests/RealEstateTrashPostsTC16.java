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
import com.training.generics.ScreenShot;
import com.training.pom.RealEstateLoginPOM;
import com.training.pom.RealEstatePostsTrashPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

	@Test
	public class RealEstateTrashPostsTC16 {
		private WebDriver driver;
		private String baseUrl;
		private RealEstateLoginPOM RlloginPOM;
		private  RealEstatePostsTrashPOM RlPostsTrash;
		private static Properties properties;
		private ScreenShot screenShot;
	

		@BeforeSuite
		public static void setUpBeforeClass() throws IOException {
			properties = new Properties();
			FileInputStream inStream = new FileInputStream("./resources/others.properties");
			properties.load(inStream);
		}

	
		@BeforeClass
		public void setUp() throws Exception {
			driver = DriverFactory.getDriver(DriverNames.CHROME);
			RlloginPOM = new RealEstateLoginPOM(driver); 
			RlPostsTrash = new RealEstatePostsTrashPOM(driver);
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
		public void ValidateLogin() {
			System.out.println("Validate login functionality");
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@class='sign-in']"))));
			RlloginPOM.clickLoginLink();
			RlloginPOM.sendUserName("admin");
			RlloginPOM.sendPassword("admin@123");
			RlloginPOM.clickSubmitBtn(); 
			screenShot.captureScreenShot("TC16_Dashboard");
			System.out.println("Logged in successully");
		}
		
	     	@Test(priority=2)
			public void ValidateTrashPost() throws InterruptedException 
			{
	     		System.out.println("Validate Trash functionality");
			    //Clicking on posts
				RlPostsTrash.clickPostslink();
		     	//To select check box  in the first row
				driver.findElement(By.xpath("//tr[1]//th[1]//input[1]")).click();
				Thread.sleep(5000);
				System.out.println("Click row actions - Trash");
				driver.findElement(By.linkText("Trash")).click();
				String sExpectedMsg = "1 post moved to the Trash. Undo";
			    String sActualMsg = RlPostsTrash.getpublishedMessage();
			     //Verify the published message after deleting
				Assert.assertEquals(sActualMsg,sExpectedMsg);
				System.out.println(RlPostsTrash.getpublishedMessage());
				screenShot.captureScreenShot("PublishTrashMessage");
					
				}
	     	
	     	
	     	@Test(priority=3)
			public void VaidateLogOut() throws InterruptedException
			{
				System.out.println("Validate logout functionality");
				WebElement Webadmin = driver.findElement(By.id("wp-admin-bar-my-account"));
				Actions act = new Actions(driver);
				act.moveToElement(Webadmin).build().perform();
				Thread.sleep(5000);
				driver.findElement(By.xpath("//a[@Class='ab-item'][contains(text(),'Log Out')]")).click();
				screenShot.captureScreenShot("LogoutTrashpost");
				
			}
		 }
		
	
		
		
	
		
		
	

