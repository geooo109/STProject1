//file for formating the main html file
// author
// Dimitris Halatsis
// sdi1400219




import java.util.*;
import java.io.*;

import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;

import java.nio.charset.Charset;

public class MFile {


/*
 * @class fields
 * name         : name of the file formulated
 * branch_table : used for printing the meta data (avg , etc) first
 * file         : the "file descriptor"
 *
 */

	String name;
	final String retlink;
	BufferedWriter file;
	BufferedWriter bfile;
	Vector<String> branch_table;
	Vector<String> author_table;
	Vector<String> branch2_table;
	Vector<String> br_aut_table;
	int remaining;
	int totalFiles;
	int totalLines;
	int totalBranches;
	int totalTags;
	int totalCommitters;
	int totalLinesChanged;
	int totalCommits;


	public MFile(String name)
	{
		this.branch_table = new Vector<String>();
		this.branch2_table = new Vector<String>();
		this.author_table = new Vector<String>();
		bfile = null;
		this.retlink = "<p class =\"sansserif\"><a href = \""+name+"\"> Back </a></p>";

		//Maybe excludes the following 2 lines
		this.author_table.addElement(aut_table_initialiser);
		this.branch_table.addElement(branch_table_initialiser);
		this.branch2_table.addElement(branch2_table_init);
		this.remaining = 4;
		this.name = name;
		this.totalFiles = -1;
		this.totalLines = -1;
		this.totalBranches = -1;
		this.totalTags = -1;
		this.totalCommitters = -1;
		this.totalLinesChanged = -1;
		this.totalCommits = -1;


		file = null;
	}

	public int createBranchFile(String name)
	{
		Charset charset = Charset.forName("US-ASCII");
		if (this.bfile != null) {
			System.err.println("Attempted to open file before closing last!\n");
			return -1;
		}

		try {
			this.bfile = Files.newBufferedWriter(Paths.get(name+"prc.html"), charset);
			this.bfile.write(html_initialiser);
			this.bfile.write("<h1>Branch: "+name+"</h1>");
			this.bfile.write(aut_table_initialiser);
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
			return -1;
		}
		return 0;
	}

	public int BranchFile_insert(String name, float perc)
	{
		if (this.bfile == null)
			return -1;
		try {
			this.bfile.write("<tr><td>"+name+"</td><td>"+perc+"</td></tr>");
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
			return -1;

		}

		return 0;
	}

	public int BranchFile_close()
	{
		if (bfile == null)
			return -1;
		try {
			this.bfile.write("</table>");
			this.bfile.write(retlink);
			this.bfile.write("</html>");
			this.bfile.close();
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
			return -1;

		}
		this.bfile = null;
		return 0;
	}

	public int insert_branch2(String name, float perc)
	{
		branch2_table.addElement("<tr><td>"+name+"</td><td>"+perc+
			"</td><td><a href=\""+name+"prc.html\">Link</a></td></tr>");
		return 0;
	}

	public int insert_author(String name,float perc)
	{
		author_table.addElement("<tr><td>"+name+"</td><td>"+perc+"</td></tr>");
		return 0;
	}

	public int insert_branch(String name,String sdate, String edate,
		String filename)
	{
		author_table.addElement("<tr><td>"+name+"</td><td>"
		+sdate+"</td><td>"+edate+"</td><td><a href= \""+filename+"\">Log</a></td></tr>");
		return 0;
	}

	public int set_totalLines(int val)
	{	this.totalLines = val;
		return 0;}

	public int set_totalFiles(int val)
	{	this.totalFiles = val;
		return 0;}


	public int set_totalBranches(int val)
	{	this.totalBranches = val;
		return 0;}

	public int set_totalTags(int val)
	{	this.totalTags = val;
		return 0;}

	public int set_totalCommitters(int val)
	{	this.totalCommitters = val;
		return 0;}

	public int set_totalLinesChanged(int val)
	{	this.totalLinesChanged = val;
		return 0;}

	public int set_totalCommits(int val)
	{	this.totalCommits = val;
		return 0;}

	private static final String html_initialiser = 
"<!DOCTYPE html><html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"mystyle.css\"></head>";
	private static final String branch2_table_init = 
"<table style=\"width:100%\"><tr><th>Branch</th><th>Link</th></tr>";

	private static final String branch_table_initialiser = 
"<table style=\"width:100%\"><tr><th>Branch</th><th>Created</th><th>Last update</th><th>Log</th></tr>";
	private static final String aut_table_initialiser = 
"<table style=\"width:100%\"><tr><th>Author</th><th>Percentage</th></tr>";

}