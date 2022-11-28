package com.codiis.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.codiis.amazon.testBase.testBase;

public class SignInPage extends testBase {

	@FindBy(id="ap_email")
	WebElement LoginTextBox;
	
	@FindBy(id="ap_password")
	WebElement PasswordTextBox;
	
	@FindBy(id="continue")
	WebElement ContinueButton;
	
	@FindBy(id="signInSubmit")
	WebElement SignInButton;
	
	public SignInPage() {
		PageFactory.initElements(driver, this);
	}
	
	public LaunchPage NavigateHomePage(String username, String password) {
		LoginTextBox.sendKeys(username);
		ContinueButton.click();
		PasswordTextBox.sendKeys(password);
		SignInButton.click();
		return new LaunchPage();
	}
}
