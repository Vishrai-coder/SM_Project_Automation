package testSSProject;

import java.io.IOException;

import org.testng.annotations.Test;

import testBase.Report;
import testBase.SeleniumSeg;
import testHarness.AddToMemoSeg;

@Test(singleThreaded=true)
public class VerifyAddMobToFavFunctionalityTest extends SeleniumSeg
{
	AddToMemoSeg addMemo = new AddToMemoSeg();

	@Test(priority = 1, groups={"testAddToFavorite"})
	public void testAddToFavorite() throws IOException
	{
		Report.createTestCaseReportHeader();
		try
		{
			addMemo.addMobilesToFav();
		}
		catch(Exception e)
		{
			Report.LogInfo("Exception", "Exception in testAddToFavorite"+e.getMessage(), "FAIL");
		}
		Report.createTestCaseReportFooter();
		Report.SummaryReportlog("Verify Add To Favorite Functionality");
	}
}