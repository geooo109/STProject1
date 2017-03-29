/*branches info*/
public class Binfo{
	private String br;
	private String creation_date; /*created date*/
	private String date_last_mod; /*last modification day*/

	public Binfo(){
		br = null;
		creation_date = null;
		date_last_mod = null;
	}

	/*getters*/
	public String get_br(){
		return br;
	}

	public String get_creation_date(){
		return creation_date;
	}

	public String get_date_last_mod(){
		return date_last_mod;
	}

	/*setters*/
	public void set_br(String n){
		br = n;
	}

	public void set_creation_date(String n){
		creation_date = n;
	}

	public void set_date_last_mod(String n){
		date_last_mod = n;
	}
	
}