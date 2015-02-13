package trees;
import java.util.*;

public class TreeList {
	int count = 0; 
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
		this.list.add(tree1);

		String spt=tree1.getSpecies();
		int LSS= listS.size(); // listS size
		
		if(LSS==0){
			Species tSP1 = new Species(spt,1);
			listS.add(tSP1);
			countS++;
		}
		else{
			boolean HEY=false;
			int x;
			for (x=0;x<LSS;x++){
				if (listS.get(x).getSP().equals(spt)){
					listS.get(x).setSPC(listS.get(x).getSPC()+1);//increments speciesCount.
					HEY= true;
					break;
				}
			}
			if(HEY==false) {
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
			boolean HEY=false;
			int x;
			for (x=0;x<LZS;x++){
				if (listZ.get(x).getZP()==zpt){
					listZ.get(x).setZC(listZ.get(x).getZC()+1);//increments speciesCount.
					HEY= true;
					break;
				}
			}
			if(HEY==false) {
				listZ.add(new ZipCode(zpt,1)); //add new species to listZ with count 1
				countZ++;
			}
		}
		count++;
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
		return count;
	}
	
	
	public class ZipCode implements Comparable<ZipCode>{
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
		public int getZP(){
			return zipCode;
		}
		public int getZC(){
			return zipCount;
		}
		public void setZC(int ZPP){
			this.zipCount=ZPP;
		}
		public ArrayList<ZipCode> getListZ() {
			return listZ;
		}
		
		@Override
		public int compareTo(ZipCode other){
			if(this.zipCount < other.getZC()){
				return -1;
			}
			else if(this.zipCount == other.getZC()){
				return 0;
			}
			else{
				return 1;
			}
		}
	}
	public class Species implements Comparable<Species>{
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
		
		/*
		public Species[] sortBySpecies(){
			int size = listS.size();
			Species[] spA = new Species[size]; //make new array of Species
			for (int i=0;i<size;i++){
				spA[i]=listS.get(i); //populate the array with unique species
			}
			Arrays.sort(spA); //sort the array based on compareTo
			//return (spA[0].getSP());
		} */

		
	}
	
	
}