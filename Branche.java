import java.util.*;
import java.io.*;

public class Branche{
	private int total;
	
	public Branche(){
		total = 0;
	}

	public void show_branches(String inpath){
		String comm = null;
		int flag = 0;
		int count;
		String commit = null;
		String [] vector = new String [3];
		String command  = "git -C " + inpath + " branch -r";
		try{
	      	Process proc = Runtime.getRuntime().exec(command);
	      	
	      	/*to read the out put of gitcommand*/
	    	BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			/*BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));*/ 
			String s = null;
			while ((s = stdInput.readLine()) != null) {
				vector[0] = s;
				count = 0;
				command = "git -C " + inpath + " log  --pretty=oneline " + s; 
		    	Process proc2 = Runtime.getRuntime().exec(command);
	      	
	      		/*to read the out put of gitcommand*/
	    		BufferedReader stdInput2 = new BufferedReader(new InputStreamReader(proc2.getInputStream()));
	    		BufferedReader temp =  new BufferedReader(new InputStreamReader(proc2.getInputStream()));
				/*BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));*/ 
				String s2 = null;
				String last = null;
				while ((s2 = stdInput2.readLine()) != null) {
					String [] temp_s2 = s2.split(" ");
					command = "git -C " + inpath + " show --summary " + temp_s2[0];
	      			Process proc3 = Runtime.getRuntime().exec(command);
	    			BufferedReader stdInput3 = new BufferedReader(new InputStreamReader(proc3.getInputStream()));
					String s3 = null;
					while ((s3 = stdInput3.readLine()) != null && s3.isEmpty() == false) {
						comm = s3.substring(0, s3.indexOf(" "));
						if(s3.startsWith("Date:") == true)
							last = s3;
						if(count == 0 && s3.startsWith("Date:") == true){
							vector[2] =  s3.substring(comm.length() + 3);
							count = 1;
						}
					}
				}
				comm = "Date:";
				if(last != null)
					vector[1] =  last.substring(comm.length() + 3);
				if(vector[1] !=null)
					System.out.println(vector[0] +","+ vector[1] +","+ vector[2]);
			}

			/*while ((s = stdError.readLine()) != null) {
			    System.out.println(s);
			}*/
		}
		catch(IOException e){
  			e.printStackTrace();
		}

	}

	
	public void show_commits(String inpath,String outpath){
		String command = "git -C " + inpath + " log";
		try{
	      	Process proc = Runtime.getRuntime().exec(command);
	      	
	      	/*to read the out put of gitcommand*/
	    	BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			/*BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));*/ 
			String s = null; 
			String comm = null;
			String[] vector = new String[4];
			vector[1] = null;
			String finish_comm = null;
			String temp_s = null;
			String comp_s = null;
			HFile hf = new HFile(outpath);
			while ((s = stdInput.readLine()) != null) {
				if(s.isEmpty() == false){
					comm = s.substring(0, s.indexOf(" "));
					
					if(comm.equals("commit") == true){
						
						/*GET COMMIT HERE*/
						comm = s.substring(comm.length()+1);
						vector[0] = comm;
						/*NOW PRIN ALL THE DATA FOR THE INPUT*/
						command = "git -C " + inpath +  " show --summary " + comm; 
						String comm2 = null;
						
						try{
					      	Process proc2 = Runtime.getRuntime().exec(command);
					      	
					      	/*to read the out put of gitcommand*/
					    	BufferedReader stdInput2 = new BufferedReader(new InputStreamReader(proc2.getInputStream()));
					    	BufferedReader tempInput2 = new BufferedReader(new InputStreamReader(proc2.getInputStream()));
							/*BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));*/ 
							String s2 = null;
							String temp_author = null;
							String final_aurthor = null;
							String message = null;
							while ((s2 = stdInput2.readLine()) != null) {
						    	if(s2.isEmpty() == false){
									comm2 = s2.substring(0, s2.indexOf(" "));
									
									/*Author*/
									if(s2.startsWith("Author:") == true){
										temp_author = s2.substring(s2.lastIndexOf("<") - 1);
										final_aurthor = s2.substring(comm2.length() + 1);
										vector[3] = final_aurthor.replace(temp_author,"");
										//System.out.println(vector[3]);
									}
									
									/*DATE*/
									else if(s2.startsWith("Date:") == true){
										/*3 is the number of spaces in the format*/
										vector[2] =  s2.substring(comm2.length() + 3);
										//System.out.println(vector[2]);
									}
									else if(s2.startsWith("commit") == false){
										tempInput2 = stdInput2;
										temp_s = tempInput2.readLine();
										if(temp_s == null){
											/*System.out.println("------------------------START---------------------");
											System.out.println("------------------>" + vector[0] + "<----------------------");
											System.out.println(vector[1]);
											System.out.println(vector[2]);
											System.out.println(vector[3]);
											System.out.println("------------------------END-----------------------");
											*/
											hf.insert(vector);
											break;
										}
										if(s2.isEmpty() == true)
											continue;

										if(vector[1] == null){
											vector[1] = s2;
										}
										else{
											vector[1] = vector[1] + "," + s2;
										}
									}
								}
							}
						}
						catch(IOException e){
				  			e.printStackTrace();
				  			//continue;
						}
					}
				}
			}

			/*while ((s = stdError.readLine()) != null) {
			    System.out.println(s);
			}*/
		}
		catch(IOException e){
  			e.printStackTrace();
		}
	}


	public void show_lines_queries(int total_comms,String inpath){
		String comm = null;
		double mo = 0.0;
		total = 0;
		String command = "git -C " + inpath + " log";
		try{
	      	Process proc = Runtime.getRuntime().exec(command);
	      	
	      	/*to read the out put of gitcommand*/
	    	BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));	
	    	String s = null;
	    	while ((s = stdInput.readLine()) != null) {
	    		if(s.isEmpty() == false){
					comm = s.substring(0, s.indexOf(" "));
					if(s.startsWith("commit") == true){
						/*now we have the commit*/
						comm = s.substring(comm.length()+1);
						command =  "git -C " + inpath + " show " + comm;
						/*now we have to execute to found the -- and the ++*/
						Process proc2 = Runtime.getRuntime().exec(command);
						BufferedReader stdInput2 = new BufferedReader(new InputStreamReader(proc2.getInputStream()));	
	    				String s2 = null;
	    				total = 0;
	    				while ((s2 = stdInput2.readLine()) != null) {
	    					if((s2.startsWith("+") == true) || (s2.startsWith("-") == true))
	    						total++;
	    				}
					}
				}
	    	}
	    	mo = ((double)total/total_comms);
	    	System.out.printf("Changes->%.2f\n",mo);
	    }
		catch(IOException e){
	    	e.printStackTrace();
		}
	}
}