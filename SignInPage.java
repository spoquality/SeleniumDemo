package com.spoquality.automation.seleniumautomation;

import org.openqa.selenium.By;

public class SignInPage
{
	By uniqueIdInitialPage;
	By uniqueIdCreatingNewAccount;
	
	//CREATE A NEW ACCOUNT
	By emailCreate = By.id("email_create");
	By submitCreate = By.id("SubmitCreate");

	//Your Personal Information
	By mr = By.id("id_gender1");
	By mrs = By.id("id_gender2");
	By firstName = By.id("customer_firstname");
	By lastName = By.id("customer_lastname");
	By Email = By.id("PLACEHOLDER");
	By password = By.id("passwd");
	By dobMonth = By.id("PLACEHOLDER");
	By dobDay = By.id("days");
	By dobYear = By.id("PLACEHOLDER");	
	By signUpForOurNewsletter = By.id("newsletter");
	By receiveSpecialOffersFromOurPartners = By.id("PLACEHOLDER");
	
	//Your Address
	By addressFirstName = By.id("PLACEHOLDER");
	By addressLastName = By.id("PLACEHOLDER");
	By addressCompany = By.id("PLACEHOLDER");
	By addressLine1 = By.id("address1");
	By addressLine2 = By.id("PLACEHOLDER");
	By addressCity = By.id("city");
	By addressState = By.id("id_state");
	By addressZip = By.id("postcode");
	By addressCountry = By.id("PLACEHOLDER");	
	By additionalInformation = By.id("PLACEHOLDER");
	By homePhone = By.id("PLACEHOLDER");
	By mobilePhone = By.id("phone_mobile");
	By assignAnAddressaliAsForFutureReference = By.id("alias");

	By registerButton = By.id("submitAccount");	
	
	//Sign in section
	By signInEmailAddress = By.id("email");
	By signInPassword = By.id("passwd");
	By signInButton = By.id("SubmitLogin");
	By signInUserName = By.xpath("//a[@class='account']/span");
	
    public SignInPage()
    {
    	uniqueIdInitialPage = emailCreate;
    	uniqueIdCreatingNewAccount = firstName;
    }

}
