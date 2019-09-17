package testHarness;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import repository.SSPageRep;
import testBase.Report;
import testBase.SeleniumSeg;

public class AddToMemoSeg extends SeleniumSeg{

	//Test Data
	static String Datasheet = "Data";
	public static String FirstMobileName;
	public static String SecondMobileName;
	public static String ThirdMobileName;
	public static String ForthMobileName;


	public void addMobilesToFav() throws InterruptedException, IOException
	{
		waitForElementToAppear(SSPageRep.AddMobieToFavLocators.ElectronicsLnk, 10);
		click(SSPageRep.AddMobieToFavLocators.ElectronicsLnk);
		waitForElementToAppear(SSPageRep.AddMobieToFavLocators.MobilePhonesLnk, 10);
		click(SSPageRep.AddMobieToFavLocators.MobilePhonesLnk);
		waitForElementToAppear(SSPageRep.AddMobieToFavLocators.AppleLnk, 10);
		click(SSPageRep.AddMobieToFavLocators.AppleLnk);
		waitForElementToAppear(SSPageRep.AddMobieToFavLocators.ModelDD, 10);
		selectByVisibleText(SSPageRep.AddMobieToFavLocators.ModelDD, "iPhone 7");
		Thread.sleep(2000);
		FirstMobileName = getText(SSPageRep.AddMobieToFavLocators.FirstMobNameLnk);
		click(SSPageRep.AddMobieToFavLocators.FirstChkbox);
		javaScriptScroll(SSPageRep.AddMobieToFavLocators.AddMultiToFavLnk);
		Thread.sleep(2000);
		click(SSPageRep.AddMobieToFavLocators.AddMultiToFavLnk);
		waitForElementToAppear(SSPageRep.AddMobieToFavLocators.AlertOkBtn, 10);
		click(SSPageRep.AddMobieToFavLocators.AlertOkBtn);
		Thread.sleep(2000);
		moveToElement(SSPageRep.AddMobieToFavLocators.MemoLnk);
		click(SSPageRep.AddMobieToFavLocators.MemoLnk);
		waitForElementToAppear(SSPageRep.AddMobieToFavLocators.VerifyFirstAddedMobLnk, 10);
		String MemoListedMobiles = getText(SSPageRep.AddMobieToFavLocators.VerifyFirstAddedMobLnk);
		if(FirstMobileName.equals(MemoListedMobiles))
		{
			Report.LogInfo("VerifyMemoFav","Mobile added to favorite successfully", "PASS");
		}
		else
		{
			Report.LogInfo("VerifyMemoFav","Mobile Not added to favorite ", "FAIL");	
		}
		
		waitForElementToAppear(SSPageRep.AddMobieToFavLocators.CompanyLogo, 10);
		click(SSPageRep.AddMobieToFavLocators.CompanyLogo);
		waitForElementToAppear(SSPageRep.AddMobieToFavLocators.ElectronicsLnk, 10);
		click(SSPageRep.AddMobieToFavLocators.ElectronicsLnk);
		waitForElementToAppear(SSPageRep.AddMobieToFavLocators.MobilePhonesLnk, 10);
		click(SSPageRep.AddMobieToFavLocators.MobilePhonesLnk);
		waitForElementToAppear(SSPageRep.AddMobieToFavLocators.AppleLnk, 10);
		click(SSPageRep.AddMobieToFavLocators.AppleLnk);
		waitForElementToAppear(SSPageRep.AddMobieToFavLocators.ModelDD, 10);
		selectByVisibleText(SSPageRep.AddMobieToFavLocators.ModelDD, "iPhone 7");
		Thread.sleep(2000);
		click(SSPageRep.AddMobieToFavLocators.SecoundChkbox);
		click(SSPageRep.AddMobieToFavLocators.ThirdChkbox);
		click(SSPageRep.AddMobieToFavLocators.ForthChkbox);
		javaScriptScroll(SSPageRep.AddMobieToFavLocators.AddMultiToFavLnk);
		click(SSPageRep.AddMobieToFavLocators.AddMultiToFavLnk);
		waitForElementToAppear(SSPageRep.AddMobieToFavLocators.AlertOkBtn, 10);
		click(SSPageRep.AddMobieToFavLocators.AlertOkBtn);
		moveToElement(SSPageRep.AddMobieToFavLocators.MemoLnk);
		click(SSPageRep.AddMobieToFavLocators.MemoLnk);
		waitForElementToAppear(SSPageRep.AddMobieToFavLocators.VerifyFirstAddedMobLnk, 10);
		selectByVisibleText(SSPageRep.AddMobieToFavLocators.ListDD, "Album");
		ShowSelected();
		//removeAllMemo();
	}

	public void compareCars() throws InterruptedException, IOException
	{
		click(SSPageRep.AddMobieToFavLocators.CompanyLogo);
		waitForElementToAppear(SSPageRep.AddCarToFavLocators.TransportLnk, 10);
		click(SSPageRep.AddCarToFavLocators.TransportLnk);
		waitForElementToAppear(SSPageRep.AddCarToFavLocators.CarsLnk, 10);
		click(SSPageRep.AddCarToFavLocators.CarsLnk);
		waitForElementToAppear(SSPageRep.AddCarToFavLocators.YearDD, 10);
		selectByVisibleText(SSPageRep.AddCarToFavLocators.YearDD, "2018");
		selectByVisibleText(SSPageRep.AddCarToFavLocators.ColorDD, "Black");
		selectByVisibleText(SSPageRep.AddCarToFavLocators.BodyDD, "Sedan");
		click(SSPageRep.AddMobieToFavLocators.FirstChkbox);
		click(SSPageRep.AddMobieToFavLocators.SecoundChkbox);
		javaScriptScroll(SSPageRep.AddMobieToFavLocators.ShowSelectedLnk);
		click(SSPageRep.AddMobieToFavLocators.ShowSelectedLnk);
		waitForElementToAppear(SSPageRep.AddCarToFavLocators.CompareLnk, 10);
		click(SSPageRep.AddCarToFavLocators.CompareLnk);
		Thread.sleep(3000);
		String winHandleBefore = webDriver.getWindowHandle();
		for(String winHandle : webDriver.getWindowHandles()){
			webDriver.switchTo().window(winHandle);
		}
		Thread.sleep(3000);
		webDriver.close();
		webDriver.switchTo().window(winHandleBefore);

		waitForElementToAppear(SSPageRep.AddMobieToFavLocators.AddMultiToFavLnk, 10);
		javaScriptScroll(SSPageRep.AddMobieToFavLocators.AddMultiToFavLnk);
		click(SSPageRep.AddMobieToFavLocators.AddMultiToFavLnk);
		waitForElementToAppear(SSPageRep.AddMobieToFavLocators.AlertOkBtn, 10);
		click(SSPageRep.AddMobieToFavLocators.AlertOkBtn);
		waitForElementToAppear(SSPageRep.AddCarToFavLocators.PrintSelectedLnk, 10);
		click(SSPageRep.AddCarToFavLocators.PrintSelectedLnk);

		ArrayList<String> tabs2 = new ArrayList<String> (webDriver.getWindowHandles());
		webDriver.switchTo().window(tabs2.get(1));
		Thread.sleep(3000);
		webDriver.close();
		webDriver.switchTo().window(tabs2.get(0));
		Thread.sleep(2000);
		
		waitForElementToAppear(SSPageRep.AddCarToFavLocators.SendSelectedLnk, 10);
		click(SSPageRep.AddCarToFavLocators.SendSelectedLnk);

		ArrayList<String> tabs3 = new ArrayList<String> (webDriver.getWindowHandles());
		webDriver.switchTo().window(tabs3.get(1));
		Thread.sleep(3000);
		webDriver.close();
		webDriver.switchTo().window(tabs3.get(0));
		Thread.sleep(2000);
	}


	public void ShowSelected() throws InterruptedException, IOException
	{
		Thread.sleep(2000);
		List<WebElement> allCheckbox = webDriver.findElements(By.xpath("//input[@name='mid[]']"));
		for(WebElement el : allCheckbox){
			if(!el.isSelected())
				el.click();
			break;
		}
		waitForElementToAppear(SSPageRep.AddMobieToFavLocators.ShowSelectedLnk, 10);
		click(SSPageRep.AddMobieToFavLocators.ShowSelectedLnk);
		click(SSPageRep.AddMobieToFavLocators.RemoveMultiFrmFavLnk);
		moveToElement(SSPageRep.AddMobieToFavLocators.MemoLnk);
		click(SSPageRep.AddMobieToFavLocators.MemoLnk);
	}

	public void removeAllMemo() throws InterruptedException, IOException
	{
		Thread.sleep(2000);
		List<WebElement> allCheckbox = webDriver.findElements(By.xpath("//input[@name='mid[]']"));
		for(WebElement el : allCheckbox){
			if(!el.isSelected())
				el.click();
		}
		Thread.sleep(2000);
		click(SSPageRep.AddMobieToFavLocators.RemoveMultiFrmFavLnk);
		waitForElementToAppear(SSPageRep.AddMobieToFavLocators.MessageLbl, 10);
		verifyElement(SSPageRep.AddMobieToFavLocators.MessageLbl);
	}
}