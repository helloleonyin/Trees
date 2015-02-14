package trees;
import java.util.*;
import java.io.*;
/**
 * 
 * @author leon yin
 * @version 1.1
 * @since 02.12.2015
 *
 */
public class Tree{
	/**
	 * This class contains the Tree object constructor as well as several Getter and Setter methods.
	 * The MaxD method is for the 5th manipulation of largest tree. 
	 * The calculations for the largest Tree is found in the TreeInfo class, but the data is pulled from this class
	 */
	int identity;
	String species;
	String street;
	String cross1;
	String cross2;
	int diameter;
	String borough;
	int zipcode;
	
	public Tree(int id,String st,String xs1,String xs2,int dia,String sp,String bor,int zip) {
		this.identity = id;
		this.street = st;
		this.cross1 = xs1;
		this.cross2 = xs2;
		this.diameter = dia;
		this.species = sp;
		this.borough = bor;
		this.zipcode = zip;
	}
	public String getSpecies(){
		return species;
	}
	public int getZip(){
		return zipcode;
	}
	public int getDiameter(){
		return diameter;
	}
	
	public void setSpecies(String species){
		this.species = species;
	}
	
	public String getMaxD(){
		return ("Largest Tree:\n\n\t"+species+" "+diameter+", inches in diameter\n\t"+street+" ("+cross1+" and "+cross2+")\n\t"+zipcode);
	}

}