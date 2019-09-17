package testBase;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class SeleniumSeg {
	public static WebDriver webDriver = null;
	protected static String timestamp=null;
	private static Utilities suiteConfig = new Utilities();
	static GlobalVariables g = new GlobalVariables();
	static ReadExcel readExcel = new ReadExcel();
	static String path1=System.getProperty("user.dir")+"/src/test/resources/TestData.xlsx";
	static String Datasheet = "URL";

	@BeforeSuite(alwaysRun = true)
	public void openBrowser() throws InterruptedException{
		try {
			File file = new File("./src/main/resources/chromedriver.exe");
			String path = file.toString();

			System.setProperty("webdriver.chrome.driver", path);
			webDriver = new ChromeDriver();
			Thread.sleep(1000);
			webDriver.manage().window().maximize();
			webDriver.get(readExcel.getDataFromCell(path1, Datasheet, 1, 1));

			timestamp = "RegressionSuite_"+suiteConfig.getCurrentDatenTime("MM-dd-yyyy")+"_"+suiteConfig.getCurrentDatenTime("hh-mm-ss_a");

			String path1 = new File(".").getCanonicalPath();
			g = new GlobalVariables();
			g.setRelativePath(path1);

			String resultPath = path1+"//Results//"+timestamp;
			String ScreenshotsPath = resultPath+"//Screenshots";
			g.setResultFolderPath(resultPath);

			new File(resultPath).mkdirs();
			new File(ScreenshotsPath).mkdirs();
			String SummaryReportfile = resultPath+"\\Index.html";

			Report.createSummaryReportHeader(SummaryReportfile);
			Report.createTestCaseReportHeader();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@AfterSuite(alwaysRun = true)
	public void closeWebdriver() {
		Report.createTestCaseReportHeader();
		webDriver.quit();
		Report.createTestCaseReportFooter();
		Report.SummaryReportlog("After Suite Run after exceution");
		Report.createSummaryReportFooter();
	}

	protected WebElement findWebElement(String locator) {
		WebElement webElement = null;
		if (!isBlankOrNull(locator)) {
			try {
				if (locator.startsWith("@id")) {
					webElement = SeleniumSeg.webDriver.findElement(By.id(removeStart(locator, "@id=")));
				} else if (locator.startsWith("@name")) {
					webElement = SeleniumSeg.webDriver.findElement(By.name(removeStart(locator, "@name=")));
				} else if (locator.startsWith("@linkText")) {
					webElement = SeleniumSeg.webDriver.findElement(By.linkText(removeStart(locator, "@linkText=")));
				} else if (locator.startsWith("@partialLinkText")) {
					webElement = SeleniumSeg.webDriver
							.findElement(By.partialLinkText(removeStart(locator, "@partialLinkText=")));
				} else if (locator.startsWith("@xpath")) {
					webElement = SeleniumSeg.webDriver.findElement(By.xpath(removeStart(locator, "@xpath=")));
				} else if (locator.startsWith("@css")) {
					webElement = SeleniumSeg.webDriver.findElement(By.cssSelector(removeStart(locator, "@css=")));
				} else if (locator.startsWith("@className")) {
					webElement = SeleniumSeg.webDriver.findElement(By.className(removeStart(locator, "@className=")));
				}

			} catch (NoSuchElementException e) {
				// e.printStackTrace();
			} catch (RuntimeException e) {
				e.printStackTrace();
				((JavascriptExecutor) webDriver).executeScript("arguments[0].style.border='2px solid green'", webElement);
			}
		}
		((JavascriptExecutor) webDriver).executeScript("arguments[0].style.border='2px solid green'", webElement);
		return webElement;
	}

	public static boolean isBlankOrNull(String str) {
		return str == null;
	}

	public static String removeStart(String str, String remove) {
		String returnStr = "";
		if (isBlankOrNull(str) || isBlankOrNull(remove)) {
			returnStr = str;
		}
		if (str.startsWith(remove))
			returnStr = str.substring(remove.length());
		return returnStr;
	}

	public void waitForElementToAppear(String locator, int sec) throws InterruptedException, IOException {
		for (int i = 0; i < sec; i++) 
		{
			if (isVisible(locator)) {
				Thread.sleep(500);
				break;
			} else {
				Thread.sleep(1000);
			}
		}
		//Thread.sleep(1000);
	}

	public void verifyElement(String locator) throws InterruptedException, IOException {
		if (isVisible(locator)) 
		{
			Report.LogInfo("Click",""+locator +" Is Clicked Successfully", "PASS");
		} 
		else 
		{
			Report.LogInfo("Click","<font color=red>"+locator +" Is not Present on Screen</font>", "FAIL");
		}
	}

	public void waitForElementToDisappear(String locator) throws InterruptedException, IOException {
		for (int i = 0; i < 60; i++) 
		{
			if (isVisible(locator) == false) {
				break;
			} else {
				Thread.sleep(1000);
			}
		}
	}

	public boolean isVisible(String locator) throws InterruptedException, IOException {
		boolean result = true;
		try {
			if (findWebElement(locator).isDisplayed() == true) {
				result = true;
			}
			else {
				result = false;
			}
		}
		catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean isEnabled(String locator) throws InterruptedException {
		boolean result = true;
		try {
			if (findWebElement(locator).isEnabled() == true) {
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public void sendKeys(String locator, String textValue) throws IOException {
		try
		{
			WebElement webElement = null;
			if (!isBlankOrNull(locator)) {
				webElement = findWebElement(locator);
				webElement.sendKeys(textValue);
			}	
			Report.LogInfo("sendKeys",textValue +" Entered into "+ locator, "PASS");
		}
		catch(Exception e)
		{
			Report.LogInfo("sendKeys","<font color=red>"+locator +" Is not Present on Screen</font>", "FAIL");
		}
	}

	public void clear(String locator) {
		WebElement webElement = findWebElement(locator);
		webElement.clear();
	}
	public void click(String locator) throws IOException {
		try
		{
			WebElement webElement = findWebElement(locator);
			webElement.click();
			Report.LogInfo("Click","\""+locator +"\" Is Clicked Successfully", "PASS");
		}
		catch(Exception e)
		{
			Report.LogInfo("Click","<font color=red>"+locator +" Is not Present on Screen</font>", "FAIL");
		}
	}
	
	public String getText(String locator) throws IOException {
		String text = null;
		try
		{
			WebElement webElement = findWebElement(locator);
			text = webElement.getText();
			Report.LogInfo("Click","\""+locator +"\" Is Clicked Successfully", "PASS");
		}
		catch(Exception e)
		{
			Report.LogInfo("Click","<font color=red>"+locator +" Is not Present on Screen</font>", "FAIL");
		}
		return text;
	}

	public void javaScriptclick(String locator) 
	{
		try
		{
			WebElement element = findWebElement(locator);
			((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",  element);
		}catch(Exception e)
		{
		}
	}


	public void javaScriptScroll(String locator) 
	{
		try
		{
			WebElement element = findWebElement(locator);
			((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(500); 
		}catch(Exception e)
		{
		}
	}

	public void selectByValue(String locator, String value) 
	{
		try
		{
			Select element = new Select(findWebElement(locator));
			element.selectByValue(value);
		}catch(Exception e)
		{
		}
	}

	public void selectByIndex(String locator, int value) 
	{
		try
		{
			Select element = new Select(findWebElement(locator));
			element.selectByIndex(value);
		}catch(Exception e)
		{
		}
	}

	public void selectByVisibleText(String locator, String value) 
	{
		try
		{
			Select element = new Select(findWebElement(locator));
			element.selectByVisibleText(value);
		}catch(Exception e)
		{
		}
	}

	public void moveToElement(String locator) 
	{
		try
		{
			Actions action = new Actions(webDriver);
			action.moveToElement(findWebElement(locator)).build().perform();
		}catch(Exception e)
		{
		}
	}

	public String selectGetOption(String locator) 
	{
		String SelectedText = null;
		try
		{
			Select element = new Select(findWebElement(locator));
			WebElement option =  element.getFirstSelectedOption();
			SelectedText = option.getText();
		}catch(Exception e)
		{
		}
		return SelectedText;
	}
}
