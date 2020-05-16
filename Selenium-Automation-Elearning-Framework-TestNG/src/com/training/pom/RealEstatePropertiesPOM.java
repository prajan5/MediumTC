package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
	
public class RealEstatePropertiesPOM {
		private WebDriver driver; 
	
	public RealEstatePropertiesPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		
		}
	
	//Locators for Posts functionality
	@FindBy(xpath="//div[contains(text(),'Properties')]")
	public WebElement properties;
	
	@FindBy(linkText="Add New")
	public WebElement Addnew;
	
	@FindBy(id ="title")
	public WebElement propertytitle;
	
	@FindBy(id ="content")
	public WebElement content;
	
	@FindBy(xpath = "//input[@class='button button-primary button-large' and @value='Publish']")
	private WebElement publishBtn;
	
	
	@FindBy(xpath="//div//p[contains(text(),'Post published')]")
	public WebElement publishMessage;
	
	
		
	//Methods for Posts functionality
	public void clickPropertieslink() throws InterruptedException {
			this.properties.click();
		    }
	
	public void clickAddNew() throws InterruptedException {
		this.Addnew.click();
	
	    }
	public void sendTitle(String sPropTitle) {
		this.propertytitle.clear();
		this.propertytitle.sendKeys(sPropTitle);
	}
	
	public void sendContent(String sContent) {
		this.content.clear(); 
		this.content.sendKeys(sContent); 
	}
	  
	public void clickpublishBtn() throws InterruptedException {
		this.publishBtn.click();
	    }

	    
     //To get the published message after New Posts
	public String getpublishedMessage() throws InterruptedException {
		Thread.sleep(1000);
		String sMsg = this.publishMessage.getText();
		return sMsg;
	
	}
	
}






