package com.training.pom;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
	
public class RealEstateCategoryPOM {
		private WebDriver driver; 
	
	public RealEstateCategoryPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		
		}
			
	//Locators for Categories functionality
	@FindBy(xpath="//div[contains(text(),'Posts')]")
	public WebElement posts;
	
	@FindBy(linkText="Categories")
	public WebElement category;
	
	@FindBy(xpath = "//input[@id='tag-name']")
	private WebElement name;
	
	@FindBy(xpath = "//input[@id='tag-slug']")
	private WebElement slug;
	
	@FindBy(xpath = "//textarea[@id='tag-description']")
	private WebElement description;
	
	@FindBy(xpath = "//input[@id='submit']")
	private WebElement addNewCategoryBtn;
	
	@FindBy(xpath = "//input[@id='tag-search-input']")
	private WebElement searchTxt;
	
	
	@FindBy(xpath = "//input[@id='search-submit']")
	private WebElement searchcategorybtn;
	
	@FindBy(xpath = "//table[@Class='wp-list-table widefat fixed striped tags']")
	private WebElement  categoryTbl;
	

	
	//Methods for Category functionality
	public void AddCategory(String sName,String sSlug,String sDesc) throws InterruptedException {
		//Click Post menu
		this.posts.click();
		Thread.sleep(1000);
		//Click Categories from submenu
		this.category.click();
		//Enter Name
		this.name.sendKeys(sName);
		Thread.sleep(1000);
		//Enter slug
		this.slug.sendKeys(sSlug);
		Thread.sleep(1000);
		//Enter description
		this.description.click();
		this.description.sendKeys(sDesc);
		Thread.sleep(1000);
		//Click Add New Category button
		this.addNewCategoryBtn.click();
	    }
	

   public void SearchCategory(String sName) throws InterruptedException
   {
	   this.searchTxt.sendKeys(sName);
	   Thread.sleep(1000);
	   this.searchcategorybtn.click();
   }

 
}







