package com.spoquality.automation.seleniumautomation;

import java.util.Random;

public class User {

	String emailAddress;
	
	String firstName;
	String lastName;
	String password;
	
	String addressLine1;
	String addressCity;
	String addressState;
	String addressZip;
	String addressCountry;
	
	String mobilePhone;
	
	public User()
	{
		emailAddress = "otto@maschion" + getRandomInteger(999999, 999999999).toString() + ".com"; 
		firstName = "Otto";
		lastName = "Maschion";
		password = "SillyBunny123!";
		addressLine1 = "2300 Garden Rd";
		addressCity = "Monterey";
		addressState = "CA ";
		addressZip = "93940";
		mobilePhone = "831-911-1234";    	
	}


	private Integer getRandomInteger(int minSize, int maxSize)
	{
		Random rand = new Random();
		return rand.nextInt((maxSize + 1) - minSize) + minSize;
	}
}
