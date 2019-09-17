package testSSProject;

import java.io.IOException;

import org.testng.annotations.Test;

import testBase.Report;
import testBase.SeleniumSeg;
import testHarness.AddToMemoSeg;

@Test(singleThreaded=true)
public class VerifyCompareCarsFunctionalityTest extends SeleniumSeg
{
	AddToMemoSeg addMemo = new AddToMemoSeg();
	
	@Test(priority = 2, groups={"testCompareCars"})
	public void testCompareCars() throws IOException
	{
		Report.createTestCaseReportHeader();
		try
		{
			addMemo.compareCars();
		}
		catch(Exception e)
		{
			Report.LogInfo("Exception", "Exception in testCompareCars"+e.getMessage(), "FAIL");
		}
		Report.createTestCaseReportFooter();
		Report.SummaryReportlog("Verify Compare Cars Functionality");
	}
}