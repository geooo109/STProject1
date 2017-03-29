import java.util.*;
import java.io.*;

public class Branche{
	private int total;
	private int total_commits;
	
	public Branche(){
		total = 0;
		total_commits = 0;
	}

	public void total_commits_found(String inpath){
		if(total_commits > 0)
			return;

		String command = "git -C " + inpath + " rev-list --count --all";
		try{
	      	Process proc = Runtime.getRuntime().exec(command);
	      	
	      	/*to read the out put of gitcommand*/
	    	BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			/*BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));*/ 
			String s = stdInput.readLine();
			total_commits = Integer.parseInt(s);
			System.out.println("assssssssssssssssssssssssssssssssssssss");
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return;
	}

	public void prt_total_commits(String inpath){
		total_commits_found(inpath);
		System.out.println("Total commits ->" + total_commits);
	}

	public void show_branches(String inpath, MFile mf){
		String comm = null;
		int flag = 0;
		int count;
		String commit = null;
		String [] array = new String [4];
		String command  = "git -C " + inpath + " branch -r";
		try{
	      	Process proc = Runtime.getRuntime().exec(command);
	      	
	      	/*to read the out put of gitcommand*/
	    	BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			/*BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));*/ 
			String s = null;
			while ((s = stdInput.readLine()) != null) {
				array[0] = s;
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
							array[2] =  s3.substring(comm.length() + 3);
							count = 1;
						}
					}
				}
				comm = "Date:";
				if(last != null)
					array[1] =  last.substring(comm.length() + 3);
				if(array[1] !=null) {
					array[3] = new String(array[0]+"tab.html");
					mf.insert_branch(array[0], array[1], array[2], array[3]);
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

	/*for d*/
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
			//HFile hf = new HFile(outpath);
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
											//hf.insert(vector);
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


	public void show_lines_queries(int total_comms,String inpath, MFile mf){
		String comm = null;
		float mo = (float)0.0;
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
	    	mo = ((float)total/total_comms);
	    	mf.set_avgLinesChanged(mo);
	    }
		catch(IOException e){
	    	e.printStackTrace();
		}
	}

	public void show_commit_queries(String inpath, MFile mf){
		/*here are the total commtis in a var of the class*/
		try{
			CStats h = new CStats();
			String final_aurthor = null;
			String comm2 = null;
			String temp_author = null;	
			
			String command  = "git -C " + inpath + " branch -r";
			Process proc = Runtime.getRuntime().exec(command);
	    	BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String s = null;
			s = stdInput.readLine(); //go after the head//
			while ((s = stdInput.readLine()) != null) {
				command = "git -C " + inpath + " log " + s;
				Process proc8 = Runtime.getRuntime().exec(command);
	    		BufferedReader stdInput8 = new BufferedReader(new InputStreamReader(proc8.getInputStream()));
				String s8 = null;
				while ((s8 = stdInput8.readLine()) != null) {
					if(s8.startsWith("commit") == true){
						total_commits++;
					}
				}
			}
			System.out.println("Total commits ->"+total_commits);
			mf.set_totalCommits(total_commits);
			/*---------------------E2------------------------*/
			command =  "git -C " + inpath + " log";
	      	Process proc2 = Runtime.getRuntime().exec(command);
	      	/*to read the out put of gitcommand*/
	    	BufferedReader stdInput2 = new BufferedReader(new InputStreamReader(proc2.getInputStream()));	
	    	String s2 = null;
	    	while ((s2 = stdInput2.readLine()) != null) {
	    		if(s2.isEmpty() == false){
		    		comm2 = s2.substring(0, s2.indexOf(" "));			
					/*Author*/
					if(s2.startsWith("Author:") == true){
						temp_author = s2.substring(s2.lastIndexOf("<") - 1);
						final_aurthor = s2.substring(comm2.length() + 1);
						h.insert(final_aurthor.replace(temp_author,""),1);
					}
				}
	   		}
	   		Set<String> set = h.getNames() ;
			for (String s3 : set) {
				float perc = h.percen(s3);
				mf.insert_author(s3, perc*(float)100.0);
			}
			/*------------------------E3----------------------*/
			command  = "git -C " + inpath + " branch -r";
			CStats h2 = new CStats(); 
			int count_commits = 0;
			Process proc3 = Runtime.getRuntime().exec(command);
			/*to read the out put of gitcommand*/
	    	BufferedReader stdInput3 = new BufferedReader(new InputStreamReader(proc3.getInputStream()));
			/*BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));*/ 
			String s4 = null;
			s4 = stdInput3.readLine(); //go after the head//
			while ((s4 = stdInput3.readLine()) != null) {
				command = "git -C " + inpath + " log " + s4;
				Process proc4 = Runtime.getRuntime().exec(command);
				/*to read the out put of gitcommand*/
	    		BufferedReader stdInput4 = new BufferedReader(new InputStreamReader(proc4.getInputStream()));
				/*BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));*/ 
				String s5 = null;
				while ((s5 = stdInput4.readLine()) != null) {
					if(s5.startsWith("commit") == true){
						count_commits++;
					}
				}
				/*remove the space*/
				s4 = s4.replace(" ","");
				float perc3 = (((float)count_commits/total_commits)*(float)100.0);
				mf.insert_branch2(s4,perc3);
				
				count_commits = 0;
			/*---------------------E4-----------------------------*/
				command =  "git -C " + inpath + " log";
		      	Process proc5 = Runtime.getRuntime().exec(command);
		      	/*to read the out put of gitcommand*/
		    	BufferedReader stdInput5 = new BufferedReader(new InputStreamReader(proc5.getInputStream()));	
		    	String s6 = null;
		    	while ((s6 = stdInput5.readLine()) != null) {
		    		if(s6.isEmpty() == false){
			    		comm2 = s6.substring(0, s6.indexOf(" "));			
						/*Author*/
						if(s6.startsWith("Author:") == true){
							temp_author = s6.substring(s6.lastIndexOf("<") - 1);
							final_aurthor = s6.substring(comm2.length() + 1);
							h2.insert(final_aurthor.replace(temp_author,""),1);
						}
					}
		   		}
		   		System.out.println("------------------FOR THE BRNACH --------------------> "+ s4);
		   		Set<String> set2 = h2.getNames();
		   		System.err.println(s4);
				System.err.println(s4);
		   		mf.createBranchFile(s4);
				for (String s7 : set2) {
					float perc2 = h2.percen(s7);
					mf.branchFile_insert(s7, perc2*100);
					System.out.println(s7 +" "+ perc2*(float)100.0+"%");
				}
				mf.branchFile_close();
			}
	    }
	    catch(IOException e){
	    	e.printStackTrace();
	    }
	}
}