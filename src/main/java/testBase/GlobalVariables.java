package testBase;

import java.util.HashMap;

public class GlobalVariables {
	private static String RelativePath = null;
	private static String SummaryReportFile = null;
	private static String TestCaseReportFile = null;
	private static String ResultPath = null;
	private static boolean VerificationFlag = true;
	private static String browser = null;
	private static String currentTestcase = null;
	private static String Environment = null;
	private static String TestSuitePath=null;
	
	public static HashMap<String, String> hash = new HashMap<String, String>();
	
	public GlobalVariables() {
		VerificationFlag = true;
	}

	public void setCurrentTestcase(String test) {
		currentTestcase = test;
	}

	public String getCurrentTestcase() {
		return currentTestcase;
	}

	public void setEnvironment(String env) {
		Environment = env;
	}

	public String getEnvironment() {
		return Environment;
	}
	public void setRelativePath(String path) {
		RelativePath = path;
	}

	public String getRelativePath() {
		return RelativePath;
	}

	public void setSummaryReportFile(String summaryfile) {

		SummaryReportFile = summaryfile;
	}

	public String getSummaryReportFile() {

		return SummaryReportFile;
	}

	public void setTestCaseReportFile(String testcasefile) {
		TestCaseReportFile = testcasefile;
	}

	public String getTestCaseReportFile() {
		return TestCaseReportFile;
	}

	public void setResultFolderPath(String path) {
		ResultPath = path;
	}

	public String getResultFolderPath() {
		return ResultPath;
	}

	public void setVerificationFlag(boolean flag) {
		VerificationFlag = flag;
	}

	public boolean getVerificationFlag() {
		return VerificationFlag;
	}

	public void setBrowser(String br) {
		browser = br;
	}

	public String getBrowser() {
		return browser;
	}

	public void setTestSuitePath(String path) {
		TestSuitePath = path;
	}

	public String getTestSuitePath() {
		return TestSuitePath;
	}
}
