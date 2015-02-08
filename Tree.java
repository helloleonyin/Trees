package trees;
import java.util.*;

public class Tree{
	String identity;
	String species;
	String location;
	String street;
	String cross1;
	String cross2;
	int diameter;
	String borough;
	String zipcode;
	
	public Tree(String id,String st,String xs1,String xs2,int dia,String sp,String bor,String zip) {
		this.identity = id;
		this.street = st;
		this.cross1 = xs1;
		this.cross2 = xs2;
		this.diameter = dia;
		this.species = sp;
		this.borough = bor;
		this.zipcode = zip;
	}
}