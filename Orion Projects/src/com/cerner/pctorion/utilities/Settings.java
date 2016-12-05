/**
 * This class is to set all initial values, initialize browser, initialize extent report
 * @author jk048034
 * @date : 02Dec2016
 */

package com.cerner.pctorion.utilities;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class Settings {

	public ExtentReports extent;
	public ExtentTest test;
	public WebDriver browser;
	public String browserName;
	public String url;
	public TestSettings testsettings;
	Properties properties;
	public TestUtils testutils;

	public static String formatter = TestUtils.getTimeStamp();
	public static String projectFolderPath =TestUtils.getRelativePath();//System.getProperty("user.dir");

	@BeforeTest
	public void setup () {
		extent = ExtentManger.Instance(extent);

		//reading data from properties file
		initializeSettings();

		browserName=testsettings.getBrowserConfigurations();
		url=testsettings.getApplicationURL();

		//initializing and invoking browser
		initializeBrowser(browserName);

		browser.get(url);
		(new WebDriverWait(browser, 30)).until(ExpectedConditions.elementToBeClickable(By.linkText("Login")));

	} 

	@AfterTest
	public void tearDown() {
		extent.endTest(test);
		extent.flush();
		extent.close();
		browser.quit();
	}

	/**
	 * This method initializes the TestSettings object with all the settings in the framework.properties file.
	 */
	private void initializeSettings() {
		testsettings=new TestSettings();
	}

	/**
	 * This method identifies which browser driver needs to be initiated as per browser value set in framework.properties file.
	 * @param browserName
	 * @author jk048034
	 */
	public void initializeBrowser(String browserName) {
		String CurrentOS=whichOS();

		if(CurrentOS.equals("/Windows/")){
			browser=	getDriverWindowsOS(browserName);
		}
		if(CurrentOS.equals("Mac OS X")){

			browser=getDriver(browserName);
		}
		browser.manage().window().maximize();
	}


	/**
	 * This method identifies which OS is running on system
	 * @return OS
	 * @author jk048034
	 */
	public String whichOS(){
		String OS = System.getProperty("os.name");
		System.out.println("OS "+OS);
		return OS;
	}

	/**
	 * This method identifies which browser driver needs to be initiated for Mac OS.
	 * @param browserName
	 * @return driver
	 * @author jk048034
	 */
	public WebDriver getDriver(String browserName) {
		WebDriver driver = null;

		try {
			if (browserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			}
			if(browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath()+"/ExternalLibraries/BrowserSpecificDrivers/chromedriver");
				driver=new ChromeDriver();
			}
			if(browserName.equalsIgnoreCase("iexplore") || browserName.equalsIgnoreCase("ie")){
				System.setProperty("webdriver.ie.driver", TestUtils.getRelativePath()+"/ExternalLibraries/BrowserSpecificDrivers/IEDriverServer");
				driver=new InternetExplorerDriver();
			}
			if(browserName.equalsIgnoreCase("safari")){

				SafariOptions safariOptions = new SafariOptions();
				safariOptions.setUseCleanSession(true);
				driver=new SafariDriver(safariOptions);
			}

			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

		} catch (Exception exception) {
			System.out.println(exception);
			//report.reportException("getDriver", new RuntimeException(exception.getMessage()));
		} 	
		return driver;
	}


	/**
	 * This method identifies which browser driver needs to be initiated for Windows OS.
	 * @param browserName
	 * @return driver
	 * @author jk048034
	 */
	public WebDriver getDriverWindowsOS(String browserName) {
		WebDriver driver = null;

		try {
			if (browserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			}
			if(browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath()+"/ExternalLibraries/BrowserSpecificDrivers/chromedriver.exe");
				driver=new ChromeDriver();
			}
			if(browserName.equalsIgnoreCase("iexplore") || browserName.equalsIgnoreCase("ie")){
				System.setProperty("webdriver.ie.driver", TestUtils.getRelativePath()+"/ExternalLibraries/BrowserSpecificDrivers/IEDriverServer.exe");
				driver=new InternetExplorerDriver();
			}
			if(browserName.equalsIgnoreCase("safari")){

				SafariOptions safariOptions = new SafariOptions();
				safariOptions.setUseCleanSession(true);
				driver=new SafariDriver(safariOptions);
			}

			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

		} catch (Exception exception) {
			System.out.println(exception);
		} 	
		return driver;
	}
}
