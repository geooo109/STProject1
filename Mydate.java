
import java.io.*;
import java.util.*;


public class Mydate {

		public int day;
		public int week;
		public int month;
		public int year;

		public Mydate(String date)
		{
			int index;
			String s = new String(date);
			String subs;

			index = s.indexOf(" ");
			s = s.substring(index+1);

			index = s.indexOf(" ");
			subs = s.substring(0, index);
			this.month = Mydate.makemonth(subs);
			s = s.substring(index+1);

			index = s.indexOf(" ");
			subs = s.substring(0, index);
			this.day = Mydate.makeday(subs);
			s = s.substring(index+1);

			index = s.indexOf(" ");
			s = s.substring(index+1);

			index = s.indexOf(" ");
			subs = s.substring(0, index);
			this.year = Mydate.makeyear(subs);
			
			this.week = Mydate.makeweek(this.day, this.month, this.year);
			this.day = Mydate.totdays(this.day, this.month, this.year);
			this.month += year*12;


		}

		public static int totdays(int day, int month , int year)
		{
			int totaldays = 0;
			totaldays += year*365 + ((year+2)/4);
			totaldays += getmonthdays(month);
			if ((month > 2 && (year+2)%4 == 0))
				totaldays += 1;
			totaldays += day;
			return totaldays;
		}

		public static int makeweek(int day, int month, int year)
		{
			/*calculate the total days that have passed*
			 *since 1/1/1970*/

			int totaldays = 0;
			totaldays += year*365 + ((year+2)/4);
			totaldays += getmonthdays(month);
			if ((month > 2 && (year+2)%4 == 0))
				totaldays += 1;
			totaldays += day;
			return (totaldays+3)/7;
		}

		public static int getmonthdays(int month)
		{
			if (month == 0)
				return 0;
			if (month == 1)
				return 31;
			if (month == 2)
				return 59;
			if (month == 3)
				return 90;
			if (month == 4)
				return 120;
			if (month == 5)
				return 151;
			if (month == 6)
				return 181;
			if (month == 7)
				return 212;
			if (month == 8)
				return 243;
			if (month == 9)
				return 273;
			if (month == 10)
				return 304;
			if (month == 11)
				return 334;
			return -1;
		}


		public static int makeday(String day)
		{
			return Integer.parseInt(day)-1;

		}

		public static int makemonth(String month)
		{
			if (month.equals("Jan"))
				return 0;
			if (month.equals("Feb"))
				return 1;
			if (month.equals("Mar"))
				return 2;
			if (month.equals("Apr"))
				return 3;
			if (month.equals("May"))
				return 4;
			if (month.equals("Jun"))
				return 5;
			if (month.equals("Jul"))
				return 6;
			if (month.equals("Aug"))
				return 7;
			if (month.equals("Sep"))
				return 8;
			if (month.equals("Oct"))
				return 9;
			if (month.equals("Nov"))
				return 10;
			if (month.equals("Dec"))
				return 11;
			return -1;

		}

		public static int makeyear(String year)
		{
			return Integer.parseInt(year)-1970;
		}



/*
	public static void main(String[] args) {

		String s = "Sat Mar 25 02:05:11 2017 -0300";

		Mydate m = new Mydate(s);

		System.out.println("Day:"+m.day);
		System.out.println("Month:"+m.month);
		System.out.println("Year:"+m.year);
		System.out.println("Week:"+m.week);

		return ;
	}
*/
}




