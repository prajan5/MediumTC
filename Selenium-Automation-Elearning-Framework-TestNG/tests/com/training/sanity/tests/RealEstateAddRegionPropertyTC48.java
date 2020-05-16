package com.training.sanity.tests;

import org.testng.annotations.Test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.RealEstateAddFeaturePropertyPOM;
import com.training.pom.RealEstateAddRegionPropertyPOM;
import com.training.pom.RealEstateLoginPOM;
import com.training.pom.RealEstatePostsPOM;
import com.training.pom.RealEstatePropertiesPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

	public class RealEstateAddRegionPropertyTC48 {
		private WebDriver driver;
		private String baseUrl;
		private RealEstateLoginPOM RlloginPOM;
		private RealEstateAddRegionPropertyPOM RlAddNewRegion;
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
			RlAddNewRegion= new RealEstateAddRegionPropertyPOM(driver);
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
			RlloginPOM.clickLoginLink();
			RlloginPOM.sendUserName("admin");
			RlloginPOM.sendPassword("admin@123");
			RlloginPOM.clickSubmitBtn(); 
			screenShot.captureScreenShot("TC48_Dashboard");
		}
		
		@Test(priority=2)
		public void ValidateNewProperty() throws InterruptedException, AWTException 
		{
			String sNewRegion = "Region3";
            System.out.println("Add New properties");
            //Click properties link
            RlAddNewRegion.clickPropertieslink();
			//Click Add new sub menu
            RlAddNewRegion.clickAddNew();
			//click Add New Region link at the right
            RlAddNewRegion.clickAddNewRegionlink();
			//Enter Region 
            RlAddNewRegion.EnterRegion(sNewRegion);
			//Select Parent Region
            RlAddNewRegion.selectParentRegion("PriyaParentRegion");
			Thread.sleep(1000);
			RlAddNewRegion.clickAddNewRegionbtn();
			Thread.sleep(1000);
			RlAddNewRegion.clickAddRegiontxt();
		    //Press F5	to refresh	to add Region to Region list	
			Robot r= new Robot();
			r.keyPress(KeyEvent.VK_F5);
			r.keyRelease(KeyEvent.VK_F5);
			Thread.sleep(1000);
			//Enter Title   
			RlAddNewRegion.sendTitle("RegionTitle");
			//Enter Content
			RlAddNewRegion.sendContent("RegionContent");
			Thread.sleep(1000);
		    //select added Region checkbox in Region list
//			driver.findElement(By.xpath("//ul[@id='regionchecklist']//li[contains(@id,'region')]//label[contains(text(),'"+sNewRegion+"')]")).click();
//			Thread.sleep(10000);
			WebElement checkboxRegion=driver.findElement(By.xpath("//label[contains(text(), '"+sNewRegion+"')]/input[@type='checkbox' and @name = 'tax_input[region][]']"));
			checkboxRegion.click();
			Thread.sleep(50000);
			System.out.println(sNewRegion+"state is"+checkboxRegion.isSelected());
			//Click publish button	
			RlAddNewRegion.clickpublishBtn();
		    //Verify the published message after new posts
			String sExpectedMsg = "Post published. View post";
			String sActualMsg = RlAddNewRegion.getpublishedMessage();
			Assert.assertEquals(sActualMsg,sExpectedMsg);
			System.out.println(sActualMsg);
			screenShot.captureScreenShot("TC48_PublishRegionMessage");
								
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
			screenShot.captureScreenShot("TC48_Logout");
		}
		
		
	}
		


	

