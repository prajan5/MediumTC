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
	
public class RealEstateAddFeaturePropertyPOM {
		private WebDriver driver; 
	
	public RealEstateAddFeaturePropertyPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		
		}
	
	//Locators for Posts functionality
	@FindBy(xpath="//div[contains(text(),'Properties')]")
	public WebElement properties;
	
	@FindBy(linkText="Add New")
	public WebElement Addnew;
	
	@FindBy(xpath="//div[@id='property_feature-adder']/a[contains(text(),'+ Add New Feature')]")
	public WebElement AddnewFeaturelink;		
	
	@FindBy(xpath="//p[@id='property_feature-add']/input[@id= 'newproperty_feature']")
	public WebElement NewFeaturetxt;	
	
	
	@FindBy(xpath=("//select[@id= 'newproperty_feature_parent']"))
	public WebElement ParentFeaturedropdown;	
	
	@FindBy(xpath=("//input[@type='button' and @id = 'property_feature-add-submit']"))
	public WebElement AddNewFeaturebtn;		
			
	
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
	
	//Click Add New Feature link
	public void clickAddNewFeaturelink() throws InterruptedException {
		this.AddnewFeaturelink.click();
	
	    }
	
	//Enter Feature 
	public void EnterFeature(String sFeature) throws InterruptedException {
		this.NewFeaturetxt.click();
		this.NewFeaturetxt.sendKeys(sFeature);
	
	    }
	
	//Select parent Feature
	public void selectParentFeature(String sParentFeature) throws InterruptedException {
		
			Select objselect = new Select(driver.findElement(By.xpath("//select[@id= 'newproperty_feature_parent']")));
			List<WebElement> wegetallFeatures = objselect.getOptions();
			for (WebElement weEachFeature :wegetallFeatures)
		    {
				String sValue = weEachFeature.getText();
				if (sValue.equalsIgnoreCase(sParentFeature))
		        {
				  objselect.selectByVisibleText(sValue);
					break;
		      	}
		
		    }
	  }
	
	//Click Add New Feature textbox
	public void clickAddNewFeaturebtn() throws InterruptedException {
		this.AddNewFeaturebtn.click();
	}
	
	//Click Add New Feature txt
		public void clickAddFeaturetxt() throws InterruptedException {
			this.NewFeaturetxt.click();
		
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






