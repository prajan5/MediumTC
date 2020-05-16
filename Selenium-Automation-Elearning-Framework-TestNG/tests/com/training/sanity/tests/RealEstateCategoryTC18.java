package com.training.sanity.tests;
	
	import org.testng.annotations.Test;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.Properties;
	import org.openqa.selenium.By;
	import org.openqa.selenium.By.ByXPath;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeSuite;
	import org.testng.annotations.Test;
	import com.training.generics.ScreenShot;
	import com.training.pom.RealEstateCategoryPOM;
	import com.training.pom.RealEstateLoginPOM;
	import com.training.utility.DriverFactory;
	import com.training.utility.DriverNames;

	public class RealEstateCategoryTC18 {
			private WebDriver driver;
			private String baseUrl;
			private RealEstateLoginPOM RlloginPOM;
			private RealEstateCategoryPOM RlCategory;
			private static Properties properties;
			private ScreenShot screenShot;
			private String sCategoryTitle = "New Launchescategory";
			private String sCategorySlug = "launchcategory";
			private String sCategoryDescrip = "New Launches of vilas, apartments, flats";
		

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
				RlCategory= new RealEstateCategoryPOM(driver);
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
				WebDriverWait wait = new WebDriverWait(driver, 70);
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@class='sign-in']"))));
				RlloginPOM.clickLoginLink();
				RlloginPOM.sendUserName("admin");
				RlloginPOM.sendPassword("admin@123");
				RlloginPOM.clickSubmitBtn(); 
				screenShot.captureScreenShot("TC18_Dashboard");
				System.out.println("Logged in successfully");
			}
			
			@Test(priority=2)
			public void ValidateNewCategory() throws InterruptedException 
			{
				System.out.println("Add category");
				RlCategory.AddCategory(sCategoryTitle,sCategorySlug,sCategoryDescrip);
				screenShot.captureScreenShot("AddCategory");
					
			 }
			
			@Test(priority=3)
			public void SearchCategory()throws InterruptedException 
			{
				System.out.println("Search Catergory by title");
				RlCategory.SearchCategory(sCategoryTitle);
			}
		
			@Test(priority=4)
			public void VerifyCategory()throws InterruptedException 
			{
				System.out.println("Verify Added catergory is in the existing module");
				String sGetTitle = driver.findElement(By.xpath("//tr[1]//td[1]//strong[1]//a[1]")).getText();
				String sGetDesc = driver.findElement(By.xpath("//tr[1]//td[2]")).getText();
			    String sGetslug = driver.findElement(By.xpath("//tr[1]//td[3]")).getText();
			   	Assert.assertEquals(sGetTitle,sCategoryTitle);
				Assert.assertEquals(sGetDesc,sCategoryDescrip);
				Assert.assertEquals(sGetslug,sCategorySlug.toLowerCase());
				screenShot.captureScreenShot("AddedCategory");
			  	
			}				

			@Test(priority=5)
			public void ValidateLogOut() throws InterruptedException
			{
				System.out.println("Validate logout functionality");
				WebElement Webadmin = driver.findElement(By.id("wp-admin-bar-my-account"));
				Actions act = new Actions(driver);
				act.moveToElement(Webadmin).build().perform();
				Thread.sleep(5000);
				driver.findElement(By.xpath("//a[@Class='ab-item'][contains(text(),'Log Out')]")).click();
				screenShot.captureScreenShot("LogoutCategory");
			}
	}		
			
			
		
			
			
		


	

