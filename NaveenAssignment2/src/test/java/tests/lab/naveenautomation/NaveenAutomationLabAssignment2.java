package tests.lab.naveenautomation;

import org.testng.annotations.Test;

import base.lab.naveenautomation.Base;
import base.lab.naveenautomation.WrapperMethods;
import page.lab.naveenautomation.MakeMyTripFlightListPage;
import page.lab.naveenautomation.MakeMyTripStartPage;

public class NaveenAutomationLabAssignment2 extends WrapperMethods {

	/*
	 * Author : Mallikarjunaswamy 
	 * Subject : Naveen Automation lab Assignment 2
	 * Date :24/04/2019
	 * BaseURL - https://www.makemytrip.com
	 * 
	 * 
	 */	
	@Test
	public void test() {
		
		/*
		 * Page object model with TestNG and Page factory extended with extent report
		 * Kindly find the Extent report after the execution
		 */
		
		reportStep("About to start the Naveen Automation Second Assignment : ", "INFO");
		
		MakeMyTripFlightListPage objMakeMyTripFlightDetailsPage = 
		new MakeMyTripStartPage(driver, testInfo).
		clickRoundTripRadioButton().
		clickOnFromCitySearchField().
		enterDepaturePlace("Delhi").
		clickOnDepaturePlaceSuggestion().
		clickOnToCitySearchField().
		enterReturnPlace("Bangalore").
		clickOnReturnPlaceSuggestion().
		clickOnDepatureDateAsCurrentDate().
		clickOnReturnDateAsAfterSevenDaysFromCurrentDate().
		clickOnSearchButton().
		getDepatureFlights().
		getReturnFlights().
		clickOnNonStopCheckBox(). //selecte NonStop
		getDepatureFlights().
		getReturnFlights().
		clickOnNonStopCheckBox().//Unselect non stop
		clickOnOneStopCheckBox(). //Select one stop
		getDepatureFlights().
		getReturnFlights().
		clickOnOneStopCheckBox(); //Unselcte one stop
		
		System.out.println("==============================  2,4  combination =====================================================");
		reportStep("==============  2,4  combination ========", "INFO");
		//2,4 combinaiton of depature and return ticket price validation
		//using the same reference :
		//Some times during night depature flights will not be availble for the 
		//required position flight in that case by default first flight will be selected and return those selected values:
		int[] arrPosition = objMakeMyTripFlightDetailsPage.
		selectDepatureAndReturnFlightRandomly(2, 4);
		
		//selected depature flight index passed  to fetch the priceS
		int sumOfDepature_ReturnFlight = objMakeMyTripFlightDetailsPage.
		selectBothFlights_And_getTheSumOfItsPrice(arrPosition[0], arrPosition[1]);
		
		int totalPrice = objMakeMyTripFlightDetailsPage.getTheTotalPrice();
		
		objMakeMyTripFlightDetailsPage.assertTotalAmountWithDepatureAndReturnFlightPrice(sumOfDepature_ReturnFlight, totalPrice);
		
		System.out.println("==============================  3 , 1   combination =====================================================");
		reportStep("==============  3 , 1  combination ========", "INFO");

		//3,1 combinaiton of depature and return ticket price validation
		//using the same reference :
		//Some times during night depature flights will not be availble for the 
		//required position flight in that case by default first flight will be selected and return those selected values:
		 arrPosition = objMakeMyTripFlightDetailsPage.
				selectDepatureAndReturnFlightRandomly(3, 1);

		//selected depature flight index passed  to fetch the priceS
		 sumOfDepature_ReturnFlight = objMakeMyTripFlightDetailsPage.
				selectBothFlights_And_getTheSumOfItsPrice(arrPosition[0], arrPosition[1]);

		 totalPrice = objMakeMyTripFlightDetailsPage.getTheTotalPrice();

		objMakeMyTripFlightDetailsPage.assertTotalAmountWithDepatureAndReturnFlightPrice(sumOfDepature_ReturnFlight, totalPrice);

		System.out.println("==============================  Thank you Naveen :) =====================================================");

		
	}
	
	
}
