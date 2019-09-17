package repository;

public class SSPageRep 
{	
	
	public static class AddMobieToFavLocators
	{
		public static final String MemoFavCountLnk = "@id=mnu_fav_id";
		public static final String SelectAllChk = "@xpath=//table//tr//input";
		public static final String ElectronicsLnk = "@xpath=//a[@title='Electronics']";
		public static final String MobilePhonesLnk = "@xpath=//a[contains(@title,'Mobile phones')]";
		public static final String AppleLnk = "@xpath=//a[contains(@title,'Apple')]";
		public static final String ModelDD = "@xpath=//select[@class='filter_sel']";
		public static final String FirstFavChkbox = "@xpath=//div[contains(text(),'250')]//preceding-sibling::span/input";
		public static final String FirstMobNameLnk = "@xpath=//table[2]//tr[2]//td[3]//a";
		public static final String AddToFavLnk = "@id=a_fav";
		public static final String AlertOkBtn = "@id=alert_ok";
		public static final String MemoLnk = "@xpath=//a[contains(text(),'Memo')]";
		public static final String VerifyFirstAddedMobLnk = "@xpath=//table[1]//tr[2]//td[3]//a";
		public static final String CompanyLogo = "@xpath=//a[@title='Объявления']";
		public static final String FirstChkbox = "@xpath=//table[2]//tr[2]//input[@type='checkbox']";
		public static final String SecoundChkbox = "@xpath=//table[2]//tr[3]//input[@type='checkbox']";
		public static final String ThirdChkbox = "@xpath=//table[2]//tr[4]//input[@type='checkbox']";
		public static final String ForthChkbox = "@xpath=//table[2]//tr[5]//input[@type='checkbox']";
		public static final String ShowSelectedLnk = "@id=show_selected_a";
		public static final String ClearSelectedLnk = "@id=clear_selected_a";
		public static final String AddMultiToFavLnk = "@id=a_fav_sel";
		public static final String VerifySecondAddedMobLnk = "@xpath=//table[1]//tr[3]//input[@type='checkbox']";
		public static final String VerifyThirdAddedMobLnk = "@xpath=//table[1]//tr[4]//input[@type='checkbox']";
		public static final String VerifyForthAddedMobLnk = "@xpath=//table[1]//tr[5]//input[@type='checkbox']";
		public static final String RemoveMultiFrmFavLnk = "@id=del_selected_a";
		public static final String MessageLbl = "@xpath=//td[contains(text(),'Select the messages.')]";
		public static final String ListDD = "@xpath=//select[@class='filter_sel']";
	}
	
	public static class AddCarToFavLocators
	{
		public static final String TransportLnk = "@xpath=//a[@title='Transport']";
		public static final String CarsLnk = "@xpath=//a[contains(@title,'Cars')]";
		public static final String YearDD = "@id=f_o_18_min";
		public static final String ColorDD = "@id=f_o_17";
		public static final String BodyDD = "@id=f_o_32";
		public static final String SearchBtn = "@xpath=//input[@value='Search']";
		public static final String CompareLnk = "@id=m_compare";
		public static final String PrintSelectedLnk = "@id=print_selected_a";
		public static final String SendSelectedLnk = "@id=send_selected_a";
	}
	
}