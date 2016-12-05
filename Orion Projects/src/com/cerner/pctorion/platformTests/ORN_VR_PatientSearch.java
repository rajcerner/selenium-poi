/**
 * ORN_VR_PatientSearch test case
 * @author jk048034
 * @date : 02Dec2016
 */

package com.cerner.pctorion.platformTests;

import org.testng.annotations.Test;

import com.cerner.pctorion.platform.LandingPage;
import com.cerner.pctorion.platform.LoginPage;
import com.cerner.pctorion.platform.PatientSearchPage;
import com.cerner.pctorion.platform.PlatformResuableVerification;
import com.cerner.pctorion.utilities.DataTable;
import com.cerner.pctorion.utilities.Settings;


public class ORN_VR_PatientSearch extends Settings {

	@Test
	public void VRPatientSearch() throws InterruptedException{

		test = extent.startTest("VRPatientSearch", "Verify Patient search");
		

		DataTable datatable=new DataTable("ORN_VR_PatientSearch");//ORN_VR_PatientSearch


		String patientFullname = datatable.getValue("Fullname");		//"DODDS, BRIAN";
		String homeNo=datatable.getValue("HomeNumber");				//"Home: (888) 663-1919";
		String mobileNo=datatable.getValue("MobileNumber");			//"Mobile: --9"; //changed data to fail testcase
		String ssn=datatable.getValue("SSN");					//"SSN: XXX-XX-7777";
		String pcp=datatable.getValue("PCP");				//"PCP: Dickey, Doug";
		String agedob=datatable.getValue("AgeDOB");				//"60 years M DOB: 05/25/1956"; //age, gender, dob


		//step1: click on login page
		LandingPage landingpage = new LandingPage(browser);
		landingpage.initialLoginButton();

		//step 2: enter login credentials 
		LoginPage loginpage= new LoginPage(browser);
		
		loginpage.enterUsernamePassword("jw027642", "asdf");

		//step 3: click on login/submit button
		loginpage.clickMPlusLoginButton();

		//step 4: enter patient name in search field 
		PatientSearchPage patientsearchpage= new PatientSearchPage(browser);
		
		patientsearchpage.enterPatientsearchString(patientFullname);

		//step 5: verify patient data
		PlatformResuableVerification platformresuableverification = new PlatformResuableVerification(browser);
		
		platformresuableverification.verifyPatientDetails(patientFullname, patientFullname, test, formatter, projectFolderPath)
		.verifyPatientDetails(patientFullname, homeNo, test, formatter, projectFolderPath)
		.verifyPatientDetails(patientFullname, mobileNo, test, formatter, projectFolderPath)
		.verifyPatientDetails(patientFullname, ssn, test, formatter, projectFolderPath)
		.verifyPatientDetails(patientFullname, pcp, test, formatter, projectFolderPath)
		.verifyPatientDetails(patientFullname, agedob, test, formatter, projectFolderPath);
		
		//step 6: select patient
		patientsearchpage.selectPatient(patientFullname);
	}

}



