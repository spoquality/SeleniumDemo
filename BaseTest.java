package com.spoquality.automation.seleniumautomation; 

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {

	//Selenium Web Driver
    FirefoxDriver driver;
    
    //Page Object Model
    HomePage home;
    SignInPage signInPage;
    
    //User dom
    User user;
    
	//TEST 3
    public WebElement FindElement (By by)
	{	
    	return FindElement(by, 30);
	}
    public WebElement FindElement (By by, int seconds)
	{		
		try
		{
			if(by == null || seconds < 0)
				return null;
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			
			if(element != null)
			{
				highlightElement(element);
			}
			return element;	
		}
		catch (TimeoutException e)
		{
			return null;
		}
	}
    //BONUS, 
    private void highlightElement(WebElement element)
	{
		if(element != null)
		{
			//String highlightDebug = Utils.Settings.getInstance().Get("highlightDebug"); 
			//if(highlightDebug.compareToIgnoreCase("true") == 0)
			//{
				for (int i = 0; i < 2; i++) 
				{
					JavascriptExecutor js = (JavascriptExecutor) driver;
					if(element.getAttribute("type") != null)
					{
						if(element.getAttribute("type").compareTo("radio") == 0)
							js.executeScript("arguments[0].parentElement.style.border='2px solid lime'", element);
						else if(element.getAttribute("type").compareTo("checkbox") == 0)
								js.executeScript("arguments[0].parentElement.style.border='2px solid lime'", element);
						else
							js.executeScript("arguments[0].style.border='2px solid lime'", element);
					}
					else
						js.executeScript("arguments[0].style.border='2px solid lime'", element);
				}
			//}
		}
	}

    //TEST 3
    public Boolean isDisplayed(By by)
	{
    	return isDisplayed(by, 30);
	}
    public Boolean isDisplayed(By by, int seconds)
	{
    	//WebElement element = driver.findElement(by);
    	WebElement element = FindElement(by, seconds);
		if(element == null) return false;
		return element.isDisplayed();
	}  
    
	//TEST 4
    public void click(By by)
	{
		click(by, 30);
	}
	public void click(By by, int seconds)
	{
    	//WebElement element = driver.findElement(by);
		WebElement element = FindElement(by, seconds);
		if(element == null)
			assertTrue("Failed to find element.", false);
		else
			element.click();
	}
	public void waitForPageToLoad(By uniqueId)
	{
		waitForPageToLoad(uniqueId, 30);
	}
	public void waitForPageToLoad(By uniqueId, int seconds)
	{
		FindElement(uniqueId, seconds);
	}
	public void setText(By by, String text)
	{
		setText(by, text, 30);
	}
	public void setText(By by, String text, int seconds)
	{
		WebElement element = FindElement(by, seconds);
		if(element == null)
			assertTrue("Failed to find element.", false);
		else
			element.sendKeys(text);
	}
	
	//TEST 5
	public void setSignInPage(User user)
	{
        setText(signInPage.firstName, user.firstName);
        setText(signInPage.lastName, user.lastName);
        setText(signInPage.password, user.password);
        setText(signInPage.addressLine1, user.addressLine1);
        setText(signInPage.addressCity, user.addressCity);
        setText(signInPage.addressState, user.addressState);
        setText(signInPage.addressZip, user.addressZip);
        setText(signInPage.mobilePhone, user.mobilePhone);		
	}
	public String getText(By by)
	{
		return getText(by, 30);
	}	
	public String getText(By by, int seconds)
	{
		WebElement element = FindElement(by, seconds);
		if(element == null)
		{
			return null;
		}
		return element.getText();
	}	

	//TEST 6
	public void signInWithNewAccount(User myUser)
	{
    	//click Sign in link
        click(home.signInLink);
        waitForPageToLoad(signInPage.uniqueIdInitialPage);
        
        //set email and click Create an Account button
        setText(signInPage.emailCreate, myUser.emailAddress);
        click(signInPage.submitCreate);
        waitForPageToLoad(signInPage.uniqueIdCreatingNewAccount);
        
        //fill out all the required fields and click the register button
        setSignInPage(myUser);
        click(signInPage.registerButton);
        
        //sign out
        click(home.signOutLink);  
        waitForPageToLoad(home.signInLink);
        
        //validate that you can sign in with your user
        click(home.signInLink);
        setText(signInPage.signInEmailAddress, user.emailAddress);
        setText(signInPage.password, user.password);
        click(signInPage.signInButton);
        assertTrue(isDisplayed(home.signInUserName));
	}
}
