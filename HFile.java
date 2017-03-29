//
//
//class for formating HTML File
// author
// Dimitris Halatsis
// sdi1400219


import java.util.*;
import java.io.*;

import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;

import java.nio.charset.Charset;




public class HFile {

	BufferedWriter file;
	final String retlink;

	public static void main(String[] args)
	{
		HFile hf = new HFile("out.html", "origin/aima3python2", "parent");
		String[] info = {"c8e22e696158f10a1a471cb4251d065daeca5da7",
			"Antonis Maronikolakis", " Sat Mar 25 08:13:58 2017", 
			"Fixed Notebook Typos (#397)"};
		hf.insert(info);
		hf.finish();
		return;
	}
	/*
	 * @ params
	 * name : the name of the file to be created by the class 
	 * branch: the branch name that corresponds to the file
	 * mainfile: the mainfile , used for the return link
	 */

	public HFile(String name, String branch, String mainfile)
	{
		//We we're suppossed to import the html file
		//TODO

		Charset charset = Charset.forName("UTF-8");

		BufferedWriter file;

		this.retlink = "<p class =\"sansserif\"><a href = \""+mainfile+"\"> Back </a></p>";
		try {
			this.file = Files.newBufferedWriter(Paths.get(name), charset);
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
			return ;
		}

		try {
			this.file.write(html_initialiser);
			this.file.write("<h1>Branch: "+branch+"</h1>");
			this.file.write(table_initialiser);
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
			return ;

		}


	}
	/*
	 *Inserts a table entry into the html file
	 */

	public int insert(String[] info)
	{
		try {
			this.file.write("<tr><td>" + info[0]+"</td><td>" + info[1]
			+ "</td><td>"+ info[2]+ "</td><td>"+ info[4]+ "</td><td>"+info[3]+"</td></tr>");
		}catch (IOException x) {
			System.err.format("IOException: %s%n", x);
			return -1;

		}
		return 0;

	}
	/*
	 *Finishes the html file and closes the file descriptor
	 */

	public int finish()
	{

		try {
			this.file.write("</table>");
			this.file.write(retlink);
			this.file.write("</html>");
			this.file.close();
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
			return -1;

		}
		return 0;
	}


	private static final String table_initialiser = 
"<table style=\"width:100%\"><tr><th>Id</th><th>Message</th><th>Date</th> <th>Tags</th><th>Author</th></tr>";
	private static final String html_initialiser = 
"<!DOCTYPE html><html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"mystyle.css\"></head>";
	
}




