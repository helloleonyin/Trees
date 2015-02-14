package trees;
import java.util.*;
/**
 * 
 * @author leon yin
 * @version 1.1
 * @since 02.12.2015
 *
 */
public class TreeList {
	/**
	 * This class organizes each Tree object into the TreeList ArrayList.
	 * This class contains methods to add Trees to the TreeList, as well as return the TreeList when called.
	 * This class contains additional constructors for the objects Species and ZipCode in order to make manipulations 1-4 easier.
	 * 
	 */
	int countT = 0; 
	int countS = 0;
	int countZ;
	ArrayList<Tree> list;
	ArrayList<ZipCode> listZ;
	ArrayList<Species> listS;
	
	
	public TreeList() {
		this.list = new ArrayList<Tree>();
		this.listS = new ArrayList<Species>();
		this.listZ = new ArrayList<ZipCode>();
	}

	public void add1(Tree tree1){
		/**
		 * This class not only populates the TreeList ArrayList with Tree Objects,
		 * but also populates the Species ArrayList and the ZipCode ArrayList.
		 * 
		 */
		this.list.add(tree1);
		String spt=tree1.getSpecies(); //temporary species name
		int LSS= listS.size(); // listS size
		
		if(LSS==0){
			Species tSP1 = new Species(spt,1);
			listS.add(tSP1);
			countS++;
		}
		else{
			boolean uniqueSP=true;
			int x;
			for (x=0;x<LSS;x++){
				if (listS.get(x).getSP().equals(spt)){
					listS.get(x).setSPC(listS.get(x).getSPC()+1);//increments speciesCount.
					uniqueSP= false;
					break;
				}
			}
			if(uniqueSP==true) {
				listS.add(new Species(spt,1)); //add new species to listS with count 1
				countS++;
			}
		}
		int zpt=tree1.getZip();
		int LZS= listZ.size(); // listZ size
		
		if(LZS==0){
			ZipCode tZP = new ZipCode(zpt);
			listZ.add(tZP);
			countZ++;
		}
		else{
			boolean uniqueZ=true;
			int x;
			for (x=0;x<LZS;x++){
				if (listZ.get(x).getZP()==zpt){
					listZ.get(x).setZC(listZ.get(x).getZC()+1);//increments speciesCount.
					uniqueZ= false;
					break;
				}
			}
			if(uniqueZ==true) {
				listZ.add(new ZipCode(zpt,1)); //add new species to listZ with count 1
				countZ++;
			}
		}
		countT++;
	}
	
	//Getter Functions
	public ArrayList<Tree> getList() {
		return this.list;
	}
	
	public ArrayList<Species> getListS() {
		return listS;
	}
	
	public ArrayList<ZipCode> getListZ() {
		return listZ;
	}
	
	public Tree getTree(int x){
		return this.list.get(x);
	}
	
	public int getCountS(){
		return countS;
	}
	public int getCountZ(){
		return countZ;
	}
	public int getCount(){
		return countT;
	}
	
	public class ZipCode implements Comparable<ZipCode>{
		/**
		 * This class contains the ZipCode constructor as well as several Getter and Setter functions.
		 * <p>
		 * The ZipCode object is necessary to store information containing unique zipcodes and their respective frequencies.
		 * The ZipCode ArrayList can be used to sort the ZipCode objects according to their frequency (zipCount).
		 * <p>
		 * @Override the compareTo override is used so that the ZipCode objects can be sorted numerically
		 * by their frequency rather than numerically for their zipCode name.
		 */
		int zipCode;
		int zipCount;
		public ZipCode(int zip, int zipC){
			this.zipCode=zip;
			this.zipCount=zipC;		
		}
		public ZipCode(int zip){
			this.zipCode=zip;	
		}
		
		public ZipCode getZP(int y){
			return listZ.get(y);
		}
		//getters
		public int getZP(){
			return zipCode;
		}
		public int getZC(){
			return zipCount;
		}
		public ArrayList<ZipCode> getListZ() {
			return listZ;
		}
		//setters
		public void setZC(int ZPP){
			this.zipCount=ZPP;
		}
		@Override
		public int compareTo(ZipCode other){
			if(this.zipCount < other.getZC()){
				return 1;
			}
			else if(this.zipCount == other.getZC()){
				return 0;
			}
			else{
				return -1;
			}
		}
	}
	public class Species implements Comparable<Species>{
		/**
		 * This class contains the Spcecies constructor as well as several Getter and Setter functions.
		 * <p>
		 * The Species object is necessary to store information containing unique species names and their respective frequencies.
		 * The Species ArrayList can be used to sort the Species objects according to their frequency (speciesCount).
		 * <p>
		 * @Override the compareTo override is used so that the Species objects can be sorted numerically
		 * by their frequency rather than alphabetically for their species name.
		 */
		String species;
		int speciesCount;
		public Species(String sp, int spC){
			this.species = sp;
			this.speciesCount=spC;		
		}
		
		public Species(String sp){
			this.species = sp;	
		}
		
		public String getSP(){
			return species;
		}
		public int getSPC(){
			return speciesCount;
		}
		
		public void setSPC(int SC){
			this.speciesCount=SC;
		}
		
		public void setSP(String SP){
			this.species=SP;
		}
		
		public ArrayList<Species> listSpecies(){
		    Collections.sort(listS);
		    return listS;
		}

		@Override
		public int compareTo(Species other){
			if(this.speciesCount < other.getSPC()){
				return 1;
			}
			else if(this.speciesCount == other.getSPC()){
				return 0;
			}
			else{
				return -1;
			}
		}		
	}
}