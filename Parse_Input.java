import java.util.*;
import java.io.*;
//package git;

public class Parse_Input{

	/*this functions is to COUNT ALL THE FILES */
	public int get_total_files(String inpath){
		
		/*this commadns give us all the files*/
		String command = "git -C " + inpath + " ls-files";
		int count = 0; 
		try{
	      	Process proc = Runtime.getRuntime().exec(command);
	      	
	      	/*to read the out put of gitcommand*/
	    	BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			/*BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));*/ 
			String s = null;
			while ((s = stdInput.readLine()) != null) {
		    	count++;
			}

			/*while ((s = stdError.readLine()) != null) {
			    System.out.println(s);
			}*/
		}
		catch(IOException e){
  			e.printStackTrace();
		}

		return count;
	}

	/*this function reads the lines of all the files*/
	public long  get_total_lines(String inpath){
		/*get the files*/
		long  count = 0;
		String command = "git -C " + inpath + " ls-files";
		FileReader f = null;
		BufferedReader br = null; 
		char c;
		try{
	      	Process proc = Runtime.getRuntime().exec(command);
	      	
	      	/*to read the out put of gitcommand*/
	    	BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			
			/*BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));*/ 
			String s = null;
			String line = null;
			while ((s = stdInput.readLine()) != null) {
				try{
					s = inpath + "/" + s;
					f = new FileReader(s);
					br = new BufferedReader(f);
			    	
					while ((line = br.readLine()) != null){
						// Print the content on the console
					  	count++;
					}
					br.close();
				}
				catch(IOException e){
					continue;
				}
			}
		}
		catch(IOException e){
  			e.printStackTrace();
		}
		return count;
	}

	public static void main (String[] args){
        /*get the input from command line*/
        String inpath = args[0];
        String outpath = args[1];
        System.out.println(inpath);
        System.out.println(outpath);

        /*create instance of class*/
        Parse_Input P = new Parse_Input();
        Btc b = new Btc();
        Branche bra = new Branche();

        /*a*/
        /*here is to count the files in the repository*/
        int count = P.get_total_files(inpath);
        System.out.println("Total files  = " + count);

        /*b*/
        /*THE tota lines of all progs in the repository*/
        long total_lines = P.get_total_lines(inpath);
        System.out.println("Total lines of all files is = " + total_lines);

        /*c*/
        /*get all the other*/
        b.set_brs_tags_coms(inpath);
        b.print_btc();

        /*e*/
        /*print gits*/
        bra.show_commits(inpath,outpath);

        /*fro branches d*/
        bra.show_branches(inpath);

        /*for g*/
        bra.show_lines_queries(b.get_comms(),inpath);
 		//bra.ptr_total_commits(inpath);
 		bra.show_commit_queries(inpath);
    }
}