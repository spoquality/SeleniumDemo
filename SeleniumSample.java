package com.spoquality.automation.seleniumautomation;
 
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
 
public class SeleniumSample extends BaseTest
{
         
    @Before
    public void setup()
    {
    	String url = "http://automationpractice.com/index.php";
        driver = new FirefoxDriver();
        driver.get(url);
        
        home = new HomePage(); 
        signInPage = new SignInPage();
        
        user = new User();
    }
    @After
    public void teardown()
    {
        driver.close();
    }
     
    @Test
    public void simpleSeleniumTestCase()
    {
        driver.get("https://www.google.com");
        By searchButtonLocator = By.xpath("//input[@name='btnK']");
        WebElement searchButton = driver.findElement(searchButtonLocator);
        String searchButtonText = searchButton.getAttribute("value");
        Assert.assertArrayEquals("Search button text was incorrect",
                new String[] {"Google Search"},
                new String[] {searchButtonText});
    }

    //Test 1: validate that sign in link appears on the home page
    @Test
    public void validate_that_the_sign_in_link_exists()
    { 	
    	//launch browser and navigate to the home page url
    	String url = "http://automationpractice.com/index.php";
        driver.get(url);
        
        //validate that the sign in link exists on the page
        By signInLinkBy = By.linkText("Sign in");
        WebElement signInLink = driver.findElement(signInLinkBy);
        assertTrue("Sign in link is not displayed on the page", signInLink.isDisplayed());
    }
    
    //Test 2: validate that the sign in link navigates you to the sign in page
    @Test
    public void test_that_the_sign_in_link_takes_the_user_to_the_sign_in_page()
    {        
    	//launch browser and navigate to the home page url
    	String url = "http://automationpractice.com/index.php";
        driver.get(url);
        
        //click the sign in link
        By signInLinkBy = By.linkText("Sign in");
        WebElement signInLink = driver.findElement(signInLinkBy);
        signInLink.click();
        
        //Assert that the sign in page loads
        String signInUrl = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
        assertTrue("Found wrong url.", driver.getCurrentUrl().equals(signInUrl));
        By createAnAccountBy = By.id("SubmitCreate");
        WebElement createAnAccountButton = driver.findElement(createAnAccountBy);
        assertTrue("Failed to find the the Create an Account button.", createAnAccountButton.isDisplayed());
    }
    
    //Test 3: Validate that the user can initiate the create a new account workflow
    @Test
    public void test_that_user_can_enter_an_email_address_on_create_an_account_page_and_the_page_will_load_the_appropriate_controls()
    {
    	//click Sign in link
        //By signInLinkBy = By.linkText("Sign in");	//CREATE POM
        WebElement signInLink = driver.findElement(home.signInLink);
        signInLink.click();
        
        //set email and click Create an Account button
        String email = "otto@maschion.com";
        WebElement  emailAddressTextBox = driver.findElement(signInPage.emailCreate);	//THIS LINE FAILS TOO, SO USE DEBUG UNTIL AFTER CREATION OF FINDELEMENT THEN UPDATE 
        emailAddressTextBox.sendKeys(email);
        WebElement  createAnAccountButton = driver.findElement(signInPage.submitCreate);
        createAnAccountButton.click();
        
        //Assert that we get to the Create an Account page
        WebElement firstName = driver.findElement(signInPage.firstName); //THIS LINE WILL FAIL AS THE OBJECT ISN'T DISPLAYED...YET
        //WebElement firstName = FindElement(signInPage.firstName, 30);
        assertTrue("Failed to find the First Name field.", firstName.isDisplayed());
    }
    
    //Test 4: Validate that a user who fills out the minumum amoutn of information successfully creates an account
    @Test
    public void test_that_a_user_can_register_with_minimum_information()
    {
    	//click Sign in link
        click(home.signInLink);
        waitForPageToLoad(signInPage.uniqueIdInitialPage);
        
        //set email and click Create an Account button
        setText(signInPage.emailCreate, user.emailAddress);
        click(signInPage.submitCreate);
        waitForPageToLoad(signInPage.uniqueIdCreatingNewAccount);
        
        //fill out all the required fields and click the register button
        setText(signInPage.firstName, user.firstName);
        setText(signInPage.lastName, user.lastName);
        setText(signInPage.password, user.password);
        setText(signInPage.addressLine1, user.addressLine1);
        setText(signInPage.addressCity, user.addressCity);
        setText(signInPage.addressState, user.addressState);
        setText(signInPage.addressZip, user.addressZip);
        setText(signInPage.mobilePhone, user.mobilePhone);
        click(signInPage.registerButton);
        
        //Verify that create account was successful
        String loggedInUrl = "http://automationpractice.com/index.php?controller=my-account";
        assertTrue("Expecting: " + loggedInUrl + "\nFound: " + driver.getCurrentUrl(), driver.getCurrentUrl().equals(loggedInUrl));
        assertTrue("Failed to find sign out link.", isDisplayed(home.signOutLink));        
    }
        
    //Test5: Validate that a new user account can register and then sign in
    @Test
    public void newly_created_user_can_log_into_the_site()
    {
    	//click Sign in link
        click(home.signInLink);
        waitForPageToLoad(signInPage.uniqueIdInitialPage);
        
        //set email and click Create an Account button
        setText(signInPage.emailCreate, user.emailAddress);
        click(signInPage.submitCreate);
        waitForPageToLoad(signInPage.uniqueIdCreatingNewAccount);
        
        //fill out all the required fields and click the register button
        setSignInPage(user);
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
        assertTrue(getText(home.signInUserName).equals(user.firstName + " " + user.lastName));        
    }

}
