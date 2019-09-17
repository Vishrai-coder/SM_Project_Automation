package testBase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.Reporter;

public class Report extends SeleniumSeg
{
	static int Pass = 0;
	static int Fail = 0;
	static long Executionstart = 0;
	static long Executionend =0;
	static String duration ="";
	static int overallPass = 0;
	static int overallFail = 0;
	static long start = 0;
	static long end =0;
	public static long intOverallDuration;
	private static int counter=1;
	private static Calendar calendar=null;
	static Utilities ut = new Utilities();
	static GlobalVariables g = new GlobalVariables();
	private static Utilities suiteConfig = new Utilities();


	public static void createSummaryReportHeader(String Summaryfile) throws IOException
	{
		String topHeader="#3498db";
		String fontColor = "BLACK";
		String TableHeader = "F6E3CE";

		FileOutputStream out;
		PrintStream p; 

		g.setSummaryReportFile(Summaryfile);

		try
		{
			out = new FileOutputStream(Summaryfile);
			p = new PrintStream( out );

			String header="<html><head><title>Automation Execution Result </title></head><Body>";
			header+= "<style type=text/css>"+
					"body {"+
					"height:100%;"+
					"background:-moz-linear-gradient(top, #666, #CCC) no-repeat #ccc;"+
					"background:-webkit-gradient(linear, left top, left bottom, from(#666), to(#ccc)) no-repeat #ccc;"+
					"}"+
					"</style>";
			header +="<p align = center><table border=0 id=table1 width=65% height=31>" +
					"<tr>" +
					"<td COLSPAN = 4 bgcolor = "+topHeader+">";
			header+= "<p align=center><font color="+fontColor+" size=4>"+suiteConfig.getValue("Project", "Test")+" Automation Result For Chrome" + "</font></p>";
			header +="</td>" +
					"</tr>"+
					"<tr>"+
					"<td COLSPAN = 4 bgcolor = #E3F6CE>"+
					"<p align=justify><b><font color="+fontColor+" size=2 face= Verdana>DATE:"+ suiteConfig.getCurrentDatenTime("dd MMMMM yyyy 'at' HH:mm:ss")+
					"</td>" +
					"</tr>";
			header+="<tr bgcolor="+TableHeader+">" +
					"<td align =center><b>TestCase Name</b></td>"+
					"<td align =center><b>Description</b>	</td>"+
					"<td align =center><b>Status</b></td>"+
					"<td align =center><b>Execution Time</b></td>"+
					"</tr>";

			p.println (header);

			p.close();	
		}
		catch (Exception e)
		{
			System.err.println ("Error in creating summary report header");
			System.out.println(e.getMessage());

		}
	}


	public static void createTestCaseReportHeader()
	{
		String topHeader="#3498db";
		String fontColor = "BLACK";
		String TableHeader = "F6E3CE";
		Fail = 0;

		FileOutputStream out;
		PrintStream p; 


		try
		{
			GlobalVariables g = new GlobalVariables();
			out = new FileOutputStream(g.getResultFolderPath()+"\\"+ Reporter.getCurrentTestResult().getName()+".html");


			g.setTestCaseReportFile(g.getResultFolderPath()+"\\"+Reporter.getCurrentTestResult().getName()+".html");

			p = new PrintStream( out );

			String header="<html><head><title>Automation Execution Result</title></head><Body>";
			header +="<p align = center><table border=2 id=table1 width=65% height=31>" +
					"<tr>" +
					"<td COLSPAN = 6 bgcolor = "+topHeader+">";
			header+= "<p align=center><font color="+fontColor+" size=4>"+Reporter.getCurrentTestResult().getName()+" Automation Test Case Details </font></p>";
			header +="</td>" +
					"</tr>";
			header+="<tr bgcolor="+TableHeader+">" +
					"<td align =center><b>Test Steps</b></td>"+
					"<td align =center><b>Description</b>	</td>"+
					"<td align =center><b>Status</b></td>"+
					"<td align =center><b>Time</b></td>"+
					"</tr>";

			p.println (header);

			p.close();	
			start = System.currentTimeMillis();     
		}
		catch (Exception e)
		{
			System.err.println ("Error in creating Test Case Detailed report header");
			System.out.println(e.getMessage());

		}
	}


	public static void LogInfo(String step,String Description, String Status) throws IOException
	{
		BufferedWriter bw = null;
		String statusColor="";
		String ScreenShotPath = null;
		Utilities ut = new Utilities();
		//Getting the SummaryReportFile from GlobalVariables Class
		GlobalVariables g = new GlobalVariables();
		String TestCaseFile = g.getTestCaseReportFile();

		if (Status.trim().equalsIgnoreCase("PASS"))
		{
			Pass++;
			if(ut.getValue("Screenshot", "false").trim().toUpperCase().equalsIgnoreCase("TRUE"))
			{
				String screenshotFileName = Reporter.getCurrentTestResult().getName() +"_"+step+"_"+counter+"Pass.jpg";;
				ScreenShotPath = g.getResultFolderPath()+"\\Screenshots\\"+screenshotFileName;
				takeScreenShots(webDriver,ScreenShotPath,"webdriver");					
				statusColor = "<a href=..\\"+timestamp+"\\Screenshots\\"+screenshotFileName+" target=_blank>";
				counter++;
			}
			statusColor =  statusColor + "<font color = green>";
		}
		else if (Status.trim().equalsIgnoreCase("FAIL"))
		{
			Fail++;
			String screenshotFileName = Reporter.getCurrentTestResult().getName() +"_"+step+"_"+counter+"Fail.jpg";;
			ScreenShotPath = g.getResultFolderPath()+"\\Screenshots\\"+screenshotFileName;
			//takeScreenShots(webDriver, ScreenShotPath);
			statusColor = "<a href=..\\"+timestamp+"\\Screenshots\\"+screenshotFileName+" target=_blank>";

			if(suiteConfig.getValue("GridMode", "OFF").trim().equalsIgnoreCase("OFF"))
			{
				takeScreenShots(webDriver, ScreenShotPath,"webdriver");
			}else
			{
				takeScreenShots(webDriver, ScreenShotPath,"remote");
			}

			statusColor =statusColor + "<font color = red>";
			counter++;
		}
		else if(Status.trim().equalsIgnoreCase("WARNING"))
		{
			statusColor ="<font color = #8A4B08>";
		}
		else
		{
			statusColor ="<font color = black>";
		}

		try 
		{
			//Opening the SummaryReport File in append mode to add the steps of test summary
			bw = new BufferedWriter(new FileWriter(TestCaseFile, true));
			step = "<tr bgcolor=#D8D8D8>" +
					"<td>"+step+"</a></td>" +
					"<td>"+Description+"</td>" +
					"<td>"+statusColor+Status+"</td>" +
					"<td>"+suiteConfig.getCurrentDatenTime("H:mm:ss")+"</td>" +
					"</tr>";

			bw.write(step);
			bw.newLine();
		}
		catch (Exception e)
		{
			System.out.println("Error While Adding a Log info in Test Case Report File");
			System.out.println(e.getMessage());
		}
		finally
		{
			try
			{
				bw.close();
			}
			catch (Exception e)
			{
				System.out.println("Error While Closing the file object of Test Case Report file");
				System.out.println(e.getMessage());
			}
		}
	}

	public static void createTestCaseReportFooter()
	{
		end = System.currentTimeMillis();
		duration=getFormattedTime(end-start);
		intOverallDuration = intOverallDuration + (end-start);

		BufferedWriter bw = null;
		String step = null;

		//Getting the SummaryReportFile from GlobalVariables Class
		GlobalVariables g = new GlobalVariables();
		String TestCaseFile = g.getTestCaseReportFile();


		try 
		{
			bw = new BufferedWriter(new FileWriter(TestCaseFile, true));
			step="<tr>"+
					"<td COLSPAN = 4 bgcolor = #E3F6CE>"+
					"<p align=right><b><font color=BLACK size=2 face= Verdana>Total Verification Pass: "+Pass+" </b>"+
					"<p align=right><b><font color=BLACK size=2 face= Verdana>Total Verification Fail: "+Fail+" </b>"+
					"<p align=right><b><font color=BLACK size=2 face= Verdana>Total Execution Time: "+duration+" </b>"+
					"</td>"+
					"</tr>";

			bw.write(step);
			bw.newLine();
		}
		catch (Exception e)
		{
			System.out.println("Error While creating footer in Test Case Report File");
			System.out.println(e.getMessage());
		}
		finally
		{
			try
			{
				bw.close();
			}
			catch (Exception e)
			{

			}
		}	      
	}

	public static void SummaryReportlog(String testDescription)
	{
		BufferedWriter bw = null;
		String step = null;
		String status=null;
		String statuscolor=null;

		//Getting the SummaryReportFile from GlobalVariables Class
		GlobalVariables g = new GlobalVariables();
		String SummaryReportFile = g.getSummaryReportFile();

		if (Fail>0)
		{
			overallFail++;
			status = "FAIL";
			statuscolor = "<font color = red><B>";
			Fail=0;

		}
		else if (Pass > 0)
		{
			overallPass++;
			status = "PASS";
			statuscolor = "<font color = green><B>";
			Pass=0;

		}
		else
		{
			status = "INFO";
			statuscolor = "<font color = black><B>";
		}

		try 
		{
			//Opening the SummaryReport File in append mode to add the steps of test summary
			bw = new BufferedWriter(new FileWriter(SummaryReportFile, true));
			step = "<tr bgcolor=#D8D8D8>" +
					"<td><a href='"+Reporter.getCurrentTestResult().getName()+".html" + "'" + "target=" + "about_blank" + ">"+Reporter.getCurrentTestResult().getName()+"</a></td>" +
					"<td>"+testDescription+"</td>" +
					"<td>"+statuscolor+status+"</td>" +
					"<td>"+duration+"</td>" +
					"</tr>";

			bw.write(step);
			bw.newLine();
		}
		catch (Exception e)
		{

		}
		finally
		{
			try
			{
				bw.close();
			}
			catch (Exception e)
			{
				System.out.println("Error While Closing the file object of Summary Report file");
				System.out.println(e.getMessage());
			}
		}
	}

	public static void createSummaryReportFooter()
	{
		BufferedWriter bw = null;
		String step = null;

		//Getting the SummaryReportFile from GlobalVariables Class
		GlobalVariables g = new GlobalVariables();
		String SummaryReportFile = g.getSummaryReportFile();


		try 
		{
			//Opening the SummaryReport File in append mode to add the steps of test summary
			bw = new BufferedWriter(new FileWriter(SummaryReportFile, true));
			step="<tr>"+
					"<td COLSPAN = 4 bgcolor = #E3F6CE>"+
					"<p align=justify><b><font color=BLACK size=2 face= Verdana>Total Execution Time: "+getFormattedTime(intOverallDuration)+"</b>"+
					"<p align=justify><b><font color=BLACK size=2 face= Verdana>Total Pass: "+overallPass+" </b>"+
					"<p align=justify><b><font color=BLACK size=2 face= Verdana>Total Fail: "+overallFail+" </b>"+
					"</td>"+
					"</tr>";

			bw.write(step);
			bw.newLine();
		}
		catch (Exception e)
		{
			System.out.println("Error while creating footer in Summary Report File");
			System.out.println(e.getMessage());
		}
		finally
		{
			try
			{
				bw.close();
			}
			catch (Exception e)
			{

			}
		}

	}

	public static String getFormattedTime(long time)
	{
		long timeMillis = time;   
		long time1 = timeMillis / 1000;   
		String seconds = Integer.toString((int)(time1 % 60));   
		String minutes = Integer.toString((int)((time1 % 3600) / 60));   
		String hours = Integer.toString((int)(time1 / 3600));   
		for (int i = 0; i < 2; i++) {   
			if (seconds.length() < 2) {   
				seconds = "0" + seconds;   
			}   
			if (minutes.length() < 2) {   
				minutes = "0" + minutes;   
			}   
			if (hours.length() < 2) {   
				hours = "0" + hours;   
			}   
		}  
		return hours+":Hr "+minutes+":Min "+seconds+":Sec";
	}

	public static void takeScreenShots(WebDriver driver,String path,String type)
	{
		try
		{
			if(type.trim().toUpperCase().equalsIgnoreCase("REMOTE"))
			{

				WebDriver augmentedDriver = new Augmenter().augment(driver);
				File screenshotFile = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshotFile, new File(path));
			}else
			{
				File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshotFile, new File(path));
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}	 

}


