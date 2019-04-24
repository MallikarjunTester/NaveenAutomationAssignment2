package base.lab.naveenautomation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class Base {

	/*
	 * Author : Mallikarjunaswamy
	 * Sub : Naveen Automation Labs Assignment
	 * Date :24/04/2019
	 * This class is base class
	 */
	
	//Variable declaration
	public WebDriver driver;
	public static ExtentReports reports; 
	public  ExtentTest testInfo,node;
	public  ExtentHtmlReporter htmlReporter;
	public DesiredCapabilities capabilities;
	public DesiredCapsManager desiredCapsManager 		= new DesiredCapsManager();
	public static String platfromName = null;
	String testName ="";

	//XMLReader.xml declaration
	public static XMLReader objXMLReader = new XMLReader(System.getProperty("user.dir") + "/input/testdata.xml");
	public static List<Hashtable<String, String>> testdata = objXMLReader.getDataAsList("Framework");
	
	// get the system Os programatically
	
	public void determineSystemOs() {

		platfromName = System.getProperty("os.name");

		if(platfromName.toLowerCase().contains("window")) {

			platfromName = "windows";

		}else if (platfromName.toLowerCase().contains("mac")) {

			platfromName = "mac";

		}else if (platfromName.toLowerCase().contains("unix")) {

			platfromName = "unix";

		}

		System.out.println("System OS is : "+  platfromName);
	
		
	}
	
	//Before Test
	@BeforeTest
	public void setup() throws InterruptedException {
		
		// Set HTML reporting file location
		Date objNewDateFolder = new Date();
		SimpleDateFormat dateFormat_Folder = new SimpleDateFormat("yyyy_MM_dd");
		File file = new File(
		System.getProperty("user.dir") + "/results/" + dateFormat_Folder.format(objNewDateFolder));
		file.mkdir();
		String strDatenow = dateFormat_Folder.format(objNewDateFolder);

		// created result file with time stamp in date folder at resultsfolder
		Date objNewTimeFile = new Date();
		SimpleDateFormat dateFormat_File = new SimpleDateFormat("yyyy_MM_dd @ HH_mm_SS");

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/results/" + strDatenow + "/"
				+ dateFormat_File.format(objNewTimeFile) + ".html");
		
		//htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+ "results/Automation.html"));
		htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir")+ "/extent-config.xml"),true);
		reports = new ExtentReports();
		reports.setSystemInfo("Environment", platfromName);
		reports.setSystemInfo("selenium testing by", "Malikarjunaswamy");
		reports.setSystemInfo("selenium version", "10.0");
		reports.attachReporter(htmlReporter);
		
	}

	//BeforeMethods
	@BeforeMethod(alwaysRun = true)
	@Parameters(value = { "browser"})
	public void beforeMethodDoThis( String browser,Method method) {
		
		testName = method.getName();
		testInfo = reports.createTest(testName);
		driver = desiredCapsManager.getDesiredCapabilities(browser,driver);

		//Enter URL :
		try {
			reportStep("About to Launch the URL : " + TestURLS.URL , "INFO");
			//Launch the URL :
			driver.get(TestURLS.URL);
			//Maximize:
			reportStep("Maximize the window ..." , "INFO");
			driver.manage().window().maximize();
			//Implicit wait :
			reportStep("Set the Implicit wait for 30 seconds" , "INFO");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			

		}catch(Exception e) {
			
			//testInfo.info("Not able to enter the URL : " + TestURLS.URL );
		}
		

	}

	//AfterMethod
	@AfterMethod
	public void afterMethod(ITestResult result) {

		driver.quit();
			
	}

	//AfterTest
	@AfterTest
	public void cleanUp() {
		
		reports.flush();
	}

	//Take screenshot
	public long takeScreenShot() {
		
		try {
			
			Thread.sleep(1000);
			
		} catch (InterruptedException e1) {
			
			e1.printStackTrace();
		}
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		try {
			File srcFiler = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFiler, new File("./rep/img/" + number + ".png"));
		} catch (WebDriverException e) {
			e.printStackTrace();
			System.out.println("The browser has been closed.");
		} catch (Exception e) {
			System.out.println("The snapshot could not be taken");
		}
		return number;
	}

	//LogReport
	public void reportStep(String desc, String status, boolean bSnap)  {

		MediaEntityModelProvider img = null;
		if(bSnap && !status.equalsIgnoreCase("INFO")){

			long snapNumber = 100000L;
			snapNumber = takeScreenShot();
			try {
				img = MediaEntityBuilder.createScreenCaptureFromPath
						(System.getProperty("user.dir") + "/report/img/"+snapNumber+".png").build();
			} catch (IOException e) {
				
			}
		}
		if(status.equalsIgnoreCase("PASS")) {
			testInfo.pass(desc, img);			
		}else if (status.equalsIgnoreCase("FAIL")) {
			testInfo.fail(desc, img);
			throw new RuntimeException();
		}else if (status.equalsIgnoreCase("WARNING")) {
			testInfo.warning(desc, img);
		}else if (status.equalsIgnoreCase("INFO")) {
			testInfo.info(desc);
		}							
	}

	//LogReportHelpermethod
	public void reportStep(String desc, String status) {
		reportStep(desc, status, true);
	}

	
}
