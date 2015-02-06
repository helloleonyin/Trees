public class Tree{
	String species;
	String location;
	String street;
	String cross1;
	String cross1;
	int diameter;
	String borough;
	String zip;

	int size;
	int count;
"""
	public void Tree() {
		dentity = "";
		street = "";
		cross1 = "";
		cross2 = "";
		diameter = 0;
		species = "";
		borough = "";
		zipcode = ""; 
	} """
	public void Tree(id,st,x1s,x2s,dia,sp,bor,zip); {
		this.identity = id;
		this.street = st;
		this.cross1 = xs1;
		this.cross2 = xs2;
		this.diameter = dia;
		this.species = sp;
		this.borough = bor;
		this.zipcode = zip;
	}


//getters
	public int getCount() {
		return count;
	}

	public String getSpecies() {
		return species;
	}

	public int getSize() {
		return size;
	}

	public String getLocation() {
		return location;
	}

//setters
	public void setCount(int ct) {
		this.count = ct;
	}

	public void setSpecies(String sp) {
		this.species = sp;
	}

	public void setLocation(String loc) {
		this.location = loc;
	}

	public void setSize(int sz) {
		this.size = sz;
	}



}