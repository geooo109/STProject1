//file for formating the main html file
// author
// Dimitris Halatsis
// sdi1400219




import java.util.*;
import java.io.*;

import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;



public class MFile {


/*
 * @class fields
 * name         : name of the file formulated
 * branch_table : used for printing the meta data (avg , etc) first
 * file         : the "file descriptor"
 *
 */

	String name;
	BufferedWriter file;
	Vector<String> branch_table;
	Vector<String> author_table;
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
		this.author_table = new Vector<String>();
		this.br_aut_table = new Vector<String>();
		//Maybe excludes the following 2 lines
		author_table.addElement(aut_table_initialiser);
		branch_table.addElement(branch_table_initialiser);
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

	public int insert_author(String name,float perc, int perDay, int perWeek, int perMonth)
	{
		author_table.addElement("<tr><td>"+name+"</td><td>"+perc+"</td><td>"
		+perDay+"</td><td>"+perWeek+"</td><td>"+perMonth+"</td></tr>");
		return 0;
	}

	public int insert_branch(String name, float perc,String sdate, String edate,
		String filename)
	{
		author_table.addElement("<tr><td>"+name+"</td><td>"+perc+"</td><td>"
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


	private static final String branch_table_initialiser = 
"<table style=\"width:100%\"><tr><th>Branch</th><th>Percentage</th><th>Created</th><th>Last update</th><th>Log</th></tr>";
	private static final String aut_table_initialiser = 
"<table style=\"width:100%\"><tr><th>Author</th><th>Percentage</th><th>Commits/Day</th><th>Commits/Week</th><th>Commits/Month</th></tr>";

}