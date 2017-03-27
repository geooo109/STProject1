import java.util.*;
import java.io.*;
public class Branche{
	public void show_branches(String inpath){
		
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
									if(comm2.equals("Author:") == true){
										temp_author = s2.substring(s2.lastIndexOf("<") - 1);
										final_aurthor = s2.substring(comm2.length() + 1);
										vector[3] = final_aurthor.replace(temp_author,"");
										//System.out.println(vector[3]);
									}
									
									/*DATE*/
									else if(comm2.equals("Date:") == true){
										/*3 is the number of spaces in the format*/
										vector[2] =  s2.substring(comm2.length() + 3);
										//System.out.println(vector[2]);
									}
									else if(comm2.equals("commit") == false){
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
}