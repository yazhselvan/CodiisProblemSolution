package com.codiis.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.codiis.amazon.testBase.testBase;

public class CartPage extends testBase {
	
	@FindBy(xpath = "//input[@type='submit' and @data-feature-id = 'proceed-to-checkout-action']")
	WebElement ProceedToBuy;
	
	@FindBy(xpath = "(//input[@Value='Delete'])[2]")
	WebElement DeleteItem;

	public CartPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void DeleteSecondItem() {
		DeleteItem.click();
	}
	
	public void ClickProceedtoBuy() {
		ProceedToBuy.click();
	}
	

	
}
