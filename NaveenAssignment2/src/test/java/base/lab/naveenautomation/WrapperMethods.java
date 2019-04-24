package base.lab.naveenautomation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class WrapperMethods extends Base {

	//Enter the URL : 
	public boolean launchUrl(String url) {

		try {
			reportStep("About to Launch the URL : "+ url, "INFO");
			driver.get(url);
			return true;
		}catch(Exception e) {
			reportStep("Not able to enter the URL : " +url ,"INFO");
			reportStep("While Enter the URL " + e.getMessage(),"FAIL");
			return false;
		}
	}
	
	//Click On Element
	public boolean click(WebElement element) {

		//System.out.println("About to click the element "+ element);
		try {
			element.click();
			return true;
		}catch(Exception e) {
			return false;

		}

	}
	
	//Enter the Value into the input filds :
	public boolean sendKeys(WebElement element,String value) {
		try {
			element.sendKeys(value);
			return true;
		}catch (Exception e) {

			return false;
		}
	}

	//Get Text from the WebElement
	public String getText(WebElement element) {
		
		 return element.getText();
		
	}
	
	//Get Attribute Value as passing the key
	public String getAttributeValue(WebElement element,String key) {
		
		return element.getAttribute(key);
	}

	//Extract or Fetch only the Numbers form the String.
	public String extractOnyNumberFromString(String str) {
		
		  return  str.replaceAll("[^0-9]", "");
	}

	//Assert the Value
	public static boolean assertValues(String actual, String expected) {
		try {
			Assert.assertEquals(actual, expected);
			return true;
		}catch(Exception e) {
			return false;

		}

	}
	
	//Assert the Value
		public static boolean assertValues(int actual, int expected) {
			try {
				Assert.assertEquals(actual, expected);
				return true;
			}catch(Exception e) {
				return false;

			}

		}

	public String getCurrentDate_E_MMM_dd_YYYY() {

		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("E MMM dd YYYY");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("IST"));
		Calendar objCalender  		= Calendar.getInstance();
		Date currentDate   			= objCalender.getTime();
		String strCurrentDate  = dateFormatGmt.format(currentDate);

		System.out.println(strCurrentDate);
		reportStep("Current date is : " + strCurrentDate , "INFO");
		
		return strCurrentDate;

	}

	public String getDateAfterSevenDays_E_MMM_dd_YYYY() {

		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("E MMM dd YYYY");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("IST"));
		Calendar objCalender  		= Calendar.getInstance();
		objCalender.add(Calendar.DATE, 7);
		Date currentDate   			= objCalender.getTime();
		String strAfterSevenDays  = dateFormatGmt.format(currentDate);

		System.out.println(strAfterSevenDays);
		reportStep("After 7 Days -  Date is : " + strAfterSevenDays , "INFO");
		
		return strAfterSevenDays;

	}



}
