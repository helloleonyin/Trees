package trees;
import java.util.*;

public class TreeList {
	int count = 0; 
	int countS;
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
		
		String spt=tree1.getSpecies(); //populates species ArrayList
		if(!this.listS.contains(spt)){ //if the species is not in the species arraylist, add it with one occurance.
			Species SP = new Species(spt,1);
			this.listS.add(SP);
			countS++;
		}
		else{
			int i = this.listS.indexOf(spt);//the index of the species is found and the count is updated
			listS.get(i).setSPC(listS.get(i).getSPC()+1);
		}

		int zp=tree1.getZip();
		if(!this.listZ.contains(zp)){ //if the species is not in the species arraylist, add it with one occurance.
			ZipCode ZP = new ZipCode(zp,1);
			this.listZ.add(ZP);
			countZ++;
		}
		else{
			int i = this.listZ.indexOf(zp);//otherwise the index of the species is found and the count is updated
			listZ.get(i).setZC(listZ.get(i).getZC()+1);
		}
		count++;
	}
	
	public ArrayList<Tree> getList() {
		return this.list;
	}
	
	public Tree getTree(int x){
		return this.list.get(x);
	}
	public int getCount(){
		return count;
	}
	
	public ArrayList<Species> getListS() {
		return listS;
	}
	
	public void treeTop3(){
		listS.sortBySpecies();
	}
	
	private class ZipCode implements Comparable<ZipCode>{
		int zipCode;
		int zipCount;
		private ZipCode(int zip, int zipC){
			this.zipCode=zip;
			this.zipCount=zipC;		
		}
		private ZipCode getZP(int y){
			return listZ.get(y);
		}
		private int getZP(){
			return zipCode;
		}
		private int getZC(){
			return zipCount;
		}
		private void setZC(int ZPP){
			this.zipCount=ZPP;
		}
		private ArrayList<ZipCode> getListZ() {
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
		private Species(String sp, int spC){
			this.species = sp;
			this.speciesCount=spC;		
		}
		
		private String getSP(){
			return species;
		}
		private int getSPC(){
			return speciesCount;
		}
		private void setSPC(int SC){
			this.speciesCount=SC;
		}
		/*
		public String sortSpecies(){
			int x;
			int mx3=0;
			int mx2=0;
			int mx1=0;
			int mn=0;
			int i3=0;
			int i2=0;
			int i1=0;
			for (x=0;x<countS;x++){
				if (mx3>=listS.get(x).getSPC()){
					mx3=listS.get(x).getSPC();
					i3 = x;
					if (mx3>=mx2){
						int tmx = mx2;
						mx2=mx3;
						mx3= tmx;
						int iT = i2;
						i2 = x;
						i3 = iT;
						if (mx2>=mx1){
							tmx = mx1;
							mx1=mx2;
							mx2= tmx;
							iT = i1;
							i1 = x;
							i2 = iT;
						}
					}
						
				}
			}
			return ("Most Popular Trees:\n\t"+listS.getSP(i1)+" "+mx1+"\n\t"+listS.get(i2)+" "+mx2+"\n\t"+listS.get(i3)+" "+mx3);
		}*/
		

		public ArrayList<Species> listSpecies(){
		    Collections.sort(listS);
		    return listS;
		}
		

		@Override
		public int compareTo(Species other){
			if(this.speciesCount < other.getSPC()){
				return -1;
			}
			else if(this.speciesCount == other.getSPC()){
				return 0;
			}
			else{
				return 1;
			}
		}
		
		public void sortBySpecies(){
			int size = listS.size();
			Species[] spA = new Species[size];
			for (int i=0;i<size;i++){
				spA[i]=listS.get(i);
			}
			Arrays.sort(spA);
		}

		
	}
	
	
}