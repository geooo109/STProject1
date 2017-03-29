import java.util.*;
import java.io.*;
//package git;

public class Btc{
	private int brs;
	private int tags;
	private int comms;

	public Btc(){
		brs = 0;
		tags = 0;
		comms = 0;
	}

	/*getters*/
	public int get_brs(){
		return brs;
	}

	public int get_tags(){
		return tags;
	}

	public int get_comms(){
		return comms;
	}

	/*setters*/
	public void set_brs(int n){
		brs = n;
	}

	public void set_tags(int n){
		tags = n;
	}

	public void set_comms(int n){
		comms = n;
	}

	public void set_brs_tags_coms(String inpath){
		/*this command is for breanches*/
		String command = "git -C " + inpath +  " branch -r"; 
		try{
	      	Process proc = Runtime.getRuntime().exec(command);
	      	
	      	/*to read the out put of gitcommand*/
	    	BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			/*BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));*/ 
			String s = null;
			while ((s = stdInput.readLine()) != null) {
		    	brs++;
			}

			/*while ((s = stdError.readLine()) != null) {
			    System.out.println(s);
			}*/
		}
		catch(IOException e){
  			e.printStackTrace();
		}

		/*for the master bracneh --*/
		//brs--;

		/*now this is for the tags*/
		command = "git -C " + inpath +  " tag"; 
		try{
	      	Process proc = Runtime.getRuntime().exec(command);
	      	
	      	/*to read the out put of gitcommand*/
	    	BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			/*BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));*/ 
			String s = null;
			while ((s = stdInput.readLine()) != null) {
		    	tags++;
			}

			/*while ((s = stdError.readLine()) != null) {
			    System.out.println(s);
			}*/
		}
		catch(IOException e){
  			e.printStackTrace();
		}


		/*for the master bracneh --*/
		command = "git -C " + inpath +  " shortlog -s -e -n HEAD"; 
		try{
	      	Process proc = Runtime.getRuntime().exec(command);
	      	
	      	/*to read the out put of gitcommand*/
	    	BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			/*BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));*/ 
			String s = null;
			while ((s = stdInput.readLine()) != null) {
		    	comms++;
			}

			/*while ((s = stdError.readLine()) != null) {
			    System.out.println(s);
			}*/
		}
		catch(IOException e){
  			e.printStackTrace();
		}
	}

	public void print_btc(MFile mf){
		mf.set_totalBranches(brs);
		mf.set_totalTags(tags);
		mf.set_totalCommitters(comms);
	}
}
