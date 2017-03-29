

import java.util.*;

import java.io.*;



/*class for computing statistics for commiters*/


class Commstat {

	
	HashMap<String, ArrayList<HashSet<Integer>> > hashtable;
	int nkeys;

	public Commstat() {
		this.hashtable = new HashMap<String, ArrayList<HashSet<Integer>> >();
		this.nkeys = 0;
	}

	public int insert(String name, String date)
	{
		/*First transform the date into the 
		 *required format*/
		ArrayList<HashSet<Integer>> setarray;
		Mydate dt = new Mydate(date);

		setarray = this.hashtable.get(name);

		if (setarray == null) {
			setarray = new ArrayList<HashSet<Integer>>();
			setarray.add(0, new HashSet<Integer>());
			setarray.add(1, new HashSet<Integer>());
			setarray.add(2, new HashSet<Integer>());

			setarray.get(0).add(dt.day);
			setarray.get(1).add(dt.week);
			setarray.get(2).add(dt.month);
			this.hashtable.put(name,setarray);
			return 0;

		}

		if (setarray.get(0).contains(dt.day) == false)
		setarray.get(0).add(dt.day);
		if (setarray.get(1).contains(dt.week) == false)
		setarray.get(1).add(dt.day);
		if (setarray.get(2).contains(dt.month) == false)
		setarray.get(2).add(dt.month);
		
		this.hashtable.put(name,setarray);
		return 0;
		
	}

	public Set<String> allNames()
	{
		return hashtable.keySet();
	}

	public ArrayList<Integer> totals(String name)
	{
		ArrayList<HashSet<Integer>> setarray;

		setarray = this.hashtable.get(name);

		if (setarray == null)
			return null;
		ArrayList<Integer> x = new ArrayList<Integer>();

		x.add(0,setarray.get(0).size());
		x.add(1,setarray.get(1).size());
		x.add(2,setarray.get(2).size());

		return x;

	}
}