
//for calculating the % of commits/writer
//

import java.util.*;

import java.io.*;


public class CStats {

	Hashtable<String, Integer> hashtable;
	int total;

	public CStats() {
		total = 0;
		hashtable = new Hashtable<String, Integer>();

	}

	public int insert(String name, int value)
	{
		Integer v = hashtable.get(name);
		if (v == null)
			v = new Integer(-1);
		v += 1;

		hashtable.put(name, v);
		total += 1;
		return 0;
	}

	public Set<String> getNames()
	{
		return hashtable.keySet();
	}

	public float percen(String name)
	{
		Integer v = hashtable.get(name);
		if (v == null)
			return (float)-1.0;
		return (float)v/total;
	}

/*

	public static void main(String[] args)
	{
		int total = 10000;
		CStats stat = new CStats();
		Random ran = new Random();

		while(total > 0) {
			total--;
			int x = ran.nextInt(10);
			String name = x + "";
			stat.insert(name,ran.nextInt(100));
		}

		Set<String> set = stat.getNames() ;

		for (String s : set) {
			float perc = stat.percen(s);
			System.out.println(perc*100+"%");
		}
	}
*/
}