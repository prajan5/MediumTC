package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.training.generics.ScreenShot;
	
public class RealEstateLoginPOM {
	
	private WebDriver driver; 
	private ScreenShot screenShot;
	
	public RealEstateLoginPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		
		}
	
	//locators for Login 
	@FindBy(xpath="//a[@class='sign-in']")
	private WebElement LoginRegister; 
	
	@FindBy(id="user_login")
	private WebElement userName;	
	
	@FindBy(id="user_pass")
	private WebElement password;
	
	@FindBy(name="login")
	private WebElement SubmitBtn; 
	
	//Methods for Login page 
	
	public void clickLoginLink()
	{
		this.LoginRegister.click();
		
	}
		
	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	
	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	
	public void clickSubmitBtn() {
		this.SubmitBtn.click(); 
	}


	


	
	
	
	        
	}
	
	






