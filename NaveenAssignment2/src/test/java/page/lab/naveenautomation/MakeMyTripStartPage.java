package page.lab.naveenautomation;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import base.lab.naveenautomation.Base;
import base.lab.naveenautomation.WrapperMethods;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MakeMyTripStartPage extends WrapperMethods{
	
	/*
	Author : Mallikarjunaswamy
	Date of Creation : 24_04_2019
	Subject : Naveen Automation Lab Assignment 2
	*/
	
	
	//Constructor initialization
	public MakeMyTripStartPage(WebDriver driver, ExtentTest testInfo){

		this.driver = driver;
		this.testInfo = testInfo;
		PageFactory.initElements(driver, this);

		WebDriverWait wait = new WebDriverWait(driver, 30);

		reportStep("Waiting for the Make My Trip StartPage ", "INFO");

		try {
			wait.until(ExpectedConditions.visibilityOf(roundTripRadioBtn));
			reportStep("Successfully landed on the MakeMyTripStartPage ", "PASS");

		}catch (Exception e) {

			reportStep(e.getMessage(), "INFO");
			reportStep("Not able to land on the MakeMyTripStartPage", "FAIL");
		} 

	}
	
	
	//Element Declaration :
	
	@FindBy(xpath="//li[text()='Round Trip']")
    WebElement roundTripRadioBtn;
	
	@FindBy(id="fromCity")
    WebElement fromCitySearch;
	
	@FindBy(xpath="//input[@placeholder='From']")
    WebElement fromCityInput;

	@FindBy(xpath="//p[text()='Delhi, India']")
    WebElement fromDelhiSuggestion;
	
	@FindBy(id="toCity")
    WebElement toCitySearch;
	
	@FindBy(xpath="//input[@placeholder='To']")
    WebElement toCityInput;

	@FindBy(xpath="//p[text()='Bangalore, India']")
    WebElement toBangaloreSuggestion;
	
	@FindBy(xpath="//a[text()='Search']")
    WebElement searchBtn;
	
	/* =================   Method Creation starts here    =============          */
	
	/*Clicks on Round Trip Button */
	public MakeMyTripStartPage clickRoundTripRadioButton() {
		
		reportStep("About to click on the Round Trip Radio Button ", "INFO");
		
		if(click(roundTripRadioBtn)) {
			
			reportStep("Succeessfully clicked on the Round Trip Button ", "PASS");
		}else {
			
			reportStep("Failed to click on the Round Trip Button ", "FAIL");
		}
		
		return this;
		
	}

	/*Clicks on From CitySearch Field*/
	public MakeMyTripStartPage clickOnFromCitySearchField() {

		reportStep("About to click on the From City Search Field", "INFO");

		if(click(fromCitySearch)) {

			reportStep("Successfully clicked on the From City Search ", "PASS");

		}else {

			reportStep("Failed to Click on the From City Search ", "FAIL");
		}
		
		return this;
	}

	/*Enter Depature Place as Delhi*/
	public MakeMyTripStartPage enterDepaturePlace(String depaturePlace) {

		reportStep("About to enter the city : Depature Place is as : "+ depaturePlace, "INFO");

		if(sendKeys(fromCityInput, depaturePlace)) {

			reportStep("Successfully entered the Depature place as :"+ depaturePlace, "PASS");
		}else {
			reportStep("Failed to enter the Depature place as : "+ depaturePlace, "FAIL");
		}
		
		return this;
		
	}

	/*Click on the Suggestion - Delhi*/
	public MakeMyTripStartPage clickOnDepaturePlaceSuggestion() {

		reportStep("About to click on the Depature Place from suggestion ", "INFO");

		if (click(fromDelhiSuggestion)) {

			reportStep("Successfully clicked on the Delhi from Suggestion ", "PASS");
		}else {

			reportStep("Failed to click on Delhi from Suggestion  ", "FAIL");

		}
		
		return this;
	}

	/*Clicks on To CitySearch Field*/
	public MakeMyTripStartPage clickOnToCitySearchField() {

		reportStep("About to click on the To City Search Field", "INFO");

		if(click(toCitySearch)) {

			reportStep("Successfully clicked on the To City Search ", "PASS");

		}else {

			reportStep("Failed to Click on the To City Search ", "INFO");
		}
		
		return this;
		
	}

	/*Enter Return Place as Bangalore*/
	public MakeMyTripStartPage enterReturnPlace(String depaturePlace) {

		reportStep("About to enter the city : Return Place is as : "+ depaturePlace, "INFO");

		if(sendKeys(toCityInput, depaturePlace)) {

			reportStep("Successfully entered the Return place as :"+ depaturePlace, "PASS");
		}else {
			reportStep("Failed to enter the Return place as : "+ depaturePlace, "FAIL");
		}
		
		return this;
	}

	/*Click on the return Place suggestion - Bangalore*/
	public MakeMyTripStartPage clickOnReturnPlaceSuggestion() {

		reportStep("About to click on the Return Place from suggestion ", "INFO");

		if (click(toBangaloreSuggestion)) {

			reportStep("Successfully clicked on the Bangalore from Suggestion ", "PASS");
		}else {

			reportStep("Failed to click on Bangalore from Suggestion  ", "FAIL");

		}
		
		return this;
	}

	/*Select the Current date in the Calendar*/
	public MakeMyTripStartPage clickOnDepatureDateAsCurrentDate() {

		reportStep("About to click on the Depature date as the Current Date ", "INFO");
		Object currentDate = getCurrentDate_E_MMM_dd_YYYY();

		//Xpath is dynamically created by passing the current date
		String currentDateXpath = "//div[@aria-label='"+currentDate+"']";

		WebElement currenteDateElement = driver.findElement(By.xpath(currentDateXpath));

		if(click(currenteDateElement)) {
			reportStep("Successfully clicked on the Current Date ", "PASS");

		} else {
			reportStep("Failed to click on hte Current Date from the Calendar object ", "FAIL");
		}
		
		return this;
	}

	/*Select the Date after Seven Days*/
	public MakeMyTripStartPage clickOnReturnDateAsAfterSevenDaysFromCurrentDate() {

		reportStep("About to click on the Depature date as the Current Date ", "INFO");
		Object dateAfterSevenDays = getDateAfterSevenDays_E_MMM_dd_YYYY();

		//Xpath is dynamically created by passing the current date
		String currentDateXpath = "//div[@aria-label='"+dateAfterSevenDays+"']";

		WebElement currenteDateElement = driver.findElement(By.xpath(currentDateXpath));

		if(click(currenteDateElement)) {
			reportStep("Successfully clicked on the Return  Date after seven days ", "PASS");
		}else {
			reportStep("Failed to click on the Return  Date after seven days from the Calendar object ", "FAIL");
		}

		return this;
	}

	/*Click on Search Button*/
	public MakeMyTripFlightListPage clickOnSearchButton() {
		
		reportStep("About to click on the Search Button ", "INFO");
		
		if(click(searchBtn)) {
			
			reportStep("Successfully clicked on the search button ", "PASS");
		} else {
			
			reportStep("Failed to click on the search button ", "FAIL");
		}
		
		return  new MakeMyTripFlightListPage(driver, testInfo);
		
	}


}
