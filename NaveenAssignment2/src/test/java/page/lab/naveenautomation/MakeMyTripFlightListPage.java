package page.lab.naveenautomation;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import base.lab.naveenautomation.Base;
import base.lab.naveenautomation.WrapperMethods;

public class MakeMyTripFlightListPage extends WrapperMethods{
	
	/*
	Author : Mallikarjunaswamy
	Date of Creation : 24_04_2019
	Subject : Naveen Automation Lab Assignment 2
	*/
	
	//Constructor initialization
		public MakeMyTripFlightListPage(WebDriver driver, ExtentTest testInfo){

			this.driver = driver;
			this.testInfo = testInfo;
			PageFactory.initElements(driver, this);

			WebDriverWait wait = new WebDriverWait(driver, 30);

			reportStep("Waiting for the MakeMyTripTrainListPage ", "INFO");

			try {
				wait.until(ExpectedConditions.visibilityOf(nonStopCheckBox));
				reportStep("Successfully landed on the MakeMyTripTrainListPage ", "PASS");

			}catch (Exception e) {

				reportStep(e.getMessage(), "INFO");
				reportStep("Not able to land on the MakeMyTripTrainListPage", "FAIL");
			} 

		}
	
	//Element Declaration :
	
	@FindBy(xpath="(//span[text()='Non Stop'])[1]")
    WebElement nonStopCheckBox;
	
	@FindBy(xpath="//div[@class='fli-list splitVw-listing']/input[@name='splitowJourney']/following-sibling::label/div/span[2]/span")
    List<WebElement> listOfDepatureFlights;
	
	@FindBy(xpath="//div[@class='fli-list splitVw-listing']/input[@name='splitrtJourney']/following-sibling::label/div/span[2]/span")
	List<WebElement> listOfReturnFlights;
	
	@FindBy(xpath="(//span[text()='1 Stop'])[1]")
    WebElement oneStopCheckBox;
	
	@FindBy(xpath="//div[@class='fli-list splitVw-listing']/input[@name='splitowJourney']/following-sibling::label/div/span[2]/span/parent::span/parent::div/following-sibling::div/div[3]/p/span")
    List<WebElement> listOfDepaturePrice;
	
	@FindBy(xpath="//div[@class='fli-list splitVw-listing']/input[@name='splitrtJourney']/following-sibling::label/div/span[2]/span/parent::span/parent::div/following-sibling::div/div[3]/p/span")
	List<WebElement> listOfReturnPrice;
	
	@FindBy(xpath="//span[@class='splitVw-total-fare']")
	WebElement totalPrice;
	
		/* =================   Method Creation starts here    =============          */
	
	/*This function clicks on NonStop check box in Make my trip Flight Results Page*/
	public MakeMyTripFlightListPage clickOnNonStopCheckBox() {
		
		reportStep("About to click on the Non stop Check box ", "INFO");
		if(click(nonStopCheckBox)) {
			
			reportStep("Successfully clicked on the Nonstop Check Box ", "PASS");
		}else {
			
			reportStep("Failed to clicked on the Nonstop Check box ", "Fail");
		}
		
		return this;
	}
	
	/*This method fetches All Depature Flights*/
	public  MakeMyTripFlightListPage getDepatureFlights() {
		
	reportStep(" Depature Flights available  are : ", "INFO");
	reportStep("================================", "INFO");
	
	int countOfDepatureFlight = listOfDepatureFlights.size();
	
	reportStep("Total number of Depature Flights is  : " + countOfDepatureFlight , "INFO");
		
		for(WebElement listOfDepatureFlight  : listOfDepatureFlights) {
			
			String strDepatureFlights = listOfDepatureFlight.getText();
			
			reportStep(strDepatureFlights , "INFO");
		}
		return this;
		
	}
	
	/*This method fetches all the Return Flights*/
	public MakeMyTripFlightListPage getReturnFlights() {

		reportStep(" Return Flights available  are : ", "INFO");
		reportStep("================================", "INFO");

		int countOfReturnFlight = listOfReturnFlights.size();

		reportStep("Total number of Return Flights is  : " + countOfReturnFlight , "INFO");

		for(WebElement returnFlight  : listOfReturnFlights) {

			String strReturnFlights = returnFlight.getText();

			reportStep(strReturnFlights , "INFO");
			
		}
		return this;
	}
	
	/*This method click on 1 Stop Check box in Make my trip Flight results Page*/
	public MakeMyTripFlightListPage clickOnOneStopCheckBox() {
		
		reportStep("About to click on the  1 Stop check bosx", "INFO");
		
		if(click(nonStopCheckBox)) {
			
			reportStep("Successfully clicked on the One Stop Check boX ", "PASS");
		} else {
			
			reportStep("Failed to click on the One Step Check box ", "FAIL");
		}
		
		return this;
	}

	public boolean clickOnDepatureFlight(int depatureFlightPosition) {
		
		if((depatureFlightPosition-1) <= listOfDepatureFlights.size()) {

		reportStep("About to click on "+  depatureFlightPosition + " th/d flight from the listed down ", "INFO");
		
		if(click(listOfDepatureFlights.get(depatureFlightPosition-1))) {
			
			reportStep("Successfully clicked on the Depature Flight , which is situated in the position "+ depatureFlightPosition, "PASS");
			
		} else {
			
			reportStep("Successfully clicked on the Depature Flight , which is situated in the position "+ depatureFlightPosition, "FAIL");
		}
		
		return true;
		
		} else {
			
			reportStep("You Entered the Train index which is not available right now. So, You can try this after some time -"
					+ " So By default first Flight is been Selected ", "INFO");
			
			return false;
		
		}
		
	}
	
	/* Return the Position Of Depature and Return Flight Position but not index : If the Depature flight is not availabe for the 
	  required Passed argument parameter it default selects the first flight available flight Randomly  */
	public int[] selectDepatureAndReturnFlightRandomly(int depatureFlightPosition,int returnFlightPostion) {

		if(clickOnDepatureFlight(depatureFlightPosition)) {

			reportStep("About to click on "+  returnFlightPostion + " th/d return flight from the listed ", "INFO");

			if(click(listOfReturnFlights.get(returnFlightPostion-1))) {

				reportStep("Successfully clicked on the Return Flight , which is situated in the position "+ returnFlightPostion, "PASS");

			} else {

				reportStep("Successfully clicked on the Return Flight , which is situated in the position "+ returnFlightPostion, "FAIL");
			}

			return new int[] {depatureFlightPosition,returnFlightPostion};

		} else {

			if(click(listOfReturnFlights.get(returnFlightPostion-1))) {

				reportStep("Successfully clicked on the Return Flight , which is situated in the position "+ returnFlightPostion, "PASS");

			} else {

				reportStep("Successfully clicked on the Return Flight , which is situated in the position "+ returnFlightPostion, "FAIL");
			}

			return new int[] {1,returnFlightPostion};

		}


	}

	/*This method fetches the selected depature Flight Price*/
	public String getSelectedDepatureFlightPrice(int SelectedDepatureFlightPricePosition) {
		
		reportStep("About to get the Depature flight - selected Price amount ", "INFO");
		
		String depatureFlightPrice = getText(listOfDepaturePrice.get(SelectedDepatureFlightPricePosition-1));
		
		reportStep("Depature flight Price is : ", depatureFlightPrice);
		
		return extractOnyNumberFromString(depatureFlightPrice);
		
		
	}
	
	/*This method fetches the selected Return Flight Price*/
	public String getSelectedReturnFlightPrice(int SelectedReturnFlightPricePosition) {

		reportStep("About to get the Return flight - selected Price amount ", "INFO");

		String returnFlightPrice = getText(listOfReturnPrice.get(SelectedReturnFlightPricePosition-1));

		reportStep("Return flight Price is : ", returnFlightPrice);

		return extractOnyNumberFromString(returnFlightPrice);


	}

	/*This method selects the both Depature and Return flight and gets it sum of the amount or price*/
	public int selectBothFlights_And_getTheSumOfItsPrice(int depatureFlightPosition,int returnFlightPostion) {
		
		int[] selectedFlightPositions= selectDepatureAndReturnFlightRandomly(depatureFlightPosition, returnFlightPostion);
		
		String strDepatureAmount = getSelectedDepatureFlightPrice(depatureFlightPosition);
		String strReturnAmount = getSelectedReturnFlightPrice(returnFlightPostion);
		
		int sum = Integer.parseInt(strDepatureAmount) + Integer.parseInt(strReturnAmount);
		
		return sum ;
		
		
	}

	/*This method gives the total amount at the bottom of the Page*/
	public int getTheTotalPrice() {
		
		reportStep("About to get the Total Price at the bottom of the Page ", "INFO");
			
		  String total_Price = getText(totalPrice);
		  
		 return  Integer.parseInt(extractOnyNumberFromString(total_Price));
		  
		}
	
	//Assertion of Flight amount with required data
	public void assertTotalAmountWithDepatureAndReturnFlightPrice(int actual,int expected) {
		
		if(assertValues(actual, expected)) {
			
			reportStep(" successfully asserted the values : Actual : "+ actual +  " Expected :" + expected, "INFO");
		} else {
			
			reportStep(" Assertion got failed : "+ actual +  " Expected :" + expected, "INFO");
		}
		
	}
	
	
	
	
	
	
}
