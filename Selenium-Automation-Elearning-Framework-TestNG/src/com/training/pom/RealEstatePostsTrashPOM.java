package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
	
public class RealEstatePostsTrashPOM {
		private WebDriver driver; 
	
	public RealEstatePostsTrashPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		
		}
	
	//Locators for Posts functionality
	@FindBy(xpath="//div[contains(text(),'Posts')]")
	public WebElement posts;
	
	//to select post from All posts
	@FindBy(xpath="//tr[1]//th[1]//input[1]")
	public WebElement selectposts;
	
	//To click Trash link
	@FindBy(xpath="//a[contains(text(),'Trash')]")
	public WebElement trashposts;
		
	
	@FindBy(xpath="//div/p[contains(text(),'1 post moved')]")
	public WebElement publishTrashMessage;
	
		
	//Methods for Posts functionality
	public void clickPostslink() throws InterruptedException {
		//Click Post menu
		this.posts.click();
		Thread.sleep(1000);
	
	    }
	
     //To get the published message after New Posts
	public String getpublishedMessage() {
		String sMsg = this.publishTrashMessage.getText();
		return sMsg;
	}
	
}






