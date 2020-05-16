package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
	
public class RealEstateAddRegionPropertyPOM {
		private WebDriver driver; 
	
	public RealEstateAddRegionPropertyPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		
		}
	
	//Locators for Posts functionality
	@FindBy(xpath="//div[contains(text(),'Properties')]")
	public WebElement properties;
	
	@FindBy(linkText="Add New")
	public WebElement Addnew;
	
	@FindBy(xpath="//div[@id='region-adder']/a[contains(text(),'+ Add New Region')]")
	public WebElement AddnewRegionlink;		
	
	@FindBy(xpath="//p[@id='region-add']/input[@id= 'newregion']")
	public WebElement NewRegiontxt;	
	
	
	@FindBy(xpath=("//select[@id= 'newregion_parent']"))
	public WebElement ParentRegiondropdown;	
	
	@FindBy(xpath=("//input[@class = 'button category-add-submit' and @value = 'Add New Region']"))
	public WebElement AddNewRegionbtn;		
	
	@FindBy(id ="title")
	public WebElement propertytitle;
	
	@FindBy(id ="content")
	public WebElement content;

	@FindBy(xpath = "//input[@class='button button-primary button-large' and @value='Publish']")
	public WebElement publishBtn;
	
	@FindBy(xpath="//div//p[contains(text(),'Post published')]")
	public WebElement publishMessage;
				
	//Methods 
	public void clickPropertieslink() throws InterruptedException {
		this.properties.click();
	
	    }
	
	//Click Add New to add property 
	public void clickAddNew() throws InterruptedException {
		this.Addnew.click();
	
	    }
	
	//Click Add New Region link
	public void clickAddNewRegionlink() throws InterruptedException {
		this.AddnewRegionlink.click();
	
	    }
	
	//Enter Feature 
	public void EnterRegion(String sRegion) throws InterruptedException {
		this.NewRegiontxt.click();
		this.NewRegiontxt.sendKeys(sRegion);
	
	    }
	
	//Select parent Region
	public void selectParentRegion(String sParentRegion) throws InterruptedException {
		
			Select objselect = new Select(driver.findElement(By.xpath("//select[@id= 'newregion_parent']")));
			List<WebElement> wegetallRegions = objselect.getOptions();
			for (WebElement weEachRegion :wegetallRegions)
		    {
				String sValue = weEachRegion.getText();
				if (sValue.equalsIgnoreCase(sParentRegion))
		        {
				  objselect.selectByVisibleText(sValue);
					break;
		      	}
		
		    }
	  }
	
	//Click Add New Region textbox
	public void clickAddNewRegionbtn() throws InterruptedException {
		this.AddNewRegionbtn.click();
	}
	
	//Click Add New Region  txt
		public void clickAddRegiontxt() throws InterruptedException {
			this.NewRegiontxt.click();
		
		    }
	//To enter Property Title
	public void sendTitle(String sPropTitle) {
		this.propertytitle.clear();
		this.propertytitle.sendKeys(sPropTitle);
	}
	//To enter content in body of text
	public void sendContent(String sContent) {
		this.content.clear(); 
		this.content.sendKeys(sContent); 
	}
	
		public void clickpublishBtn() throws InterruptedException {
		this.publishBtn.click();
			    }
	   
     //To get the published message 
	public String getpublishedMessage() throws InterruptedException {
		Thread.sleep(1000);
		String sMsg = this.publishMessage.getText();
		return sMsg;
	
	}
	
}






