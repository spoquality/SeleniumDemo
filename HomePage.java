package com.spoquality.automation.seleniumautomation;

import org.openqa.selenium.By;

public class HomePage
{	
	By signInLink = By.linkText("Sign in");
	By signOutLink = By.linkText("Sign out");
	By signInUserName = By.xpath("//a[@class='account']/span");
	
    public HomePage()
    {
    }

}