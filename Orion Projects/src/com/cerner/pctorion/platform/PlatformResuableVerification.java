/**
 * Class for platform verification reusable functions 
 * @author jk048034
 * @date : 02-Dec-2016
 */
package com.cerner.pctorion.platform;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cerner.pctorion.utilities.Page;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * @author jk048034
 *@date : 02-Dec-2016
 */
public class PlatformResuableVerification extends Page{
	public PlatformResuableVerification(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/*
	 * Author: JK048034
	 * Function: This method verifies patient data in patient search screen
	 */	
	public PlatformResuableVerification verifyPatientDetails(String patientName , String attributeVerify,ExtentTest test,String formatter, String projectFolderPath)
	{
		try{

			List<WebElement> allPatients = driver.findElements(By.cssSelector("div.ion-patient-search-result-detail > h5"));

			for (WebElement patient : allPatients) {
				String actualName = patient.getText();
				if (patientName.equalsIgnoreCase(actualName)) {

					passTestCase( test, attributeVerify, formatter, projectFolderPath);

				}else{

					failTestCase( test, attributeVerify, formatter, projectFolderPath);
				}
				break;
			}	
		}
		catch(Exception e){
			System.out.println(e.getMessage());

		}
		return this;
	}
}
