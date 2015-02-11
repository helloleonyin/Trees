package trees;
import java.io.*;
import java.util.*;
//TODO: Read file correctly
//TODO: Compare array correctly.

public class TreeInfo {
	/**
	 * This class is used to read an input file and write the output file.
	 * It is a variation of Joanna's "ReadFile" and "WriteFile class"
	 * 
	 * @author Leon Yin
	 * @return output file with 5 calculated scores.
	 * @throws IOException 
	 *
	 */
	// If the file cannot be opened the Scanner constructor throws
	// an exception. The program either has to catch it and handle it, or
	// re-throw it (as it is done in this main()).
	
	public static void main(String[] args) throws IOException {
		String inputFile = "TestTree";
		
		//Create arraylist
		TreeList tList = new TreeList();
		TreeList tu =  new TreeList();
		
		//create a File object (this can be done even if the file 
		//does not exist on disk)
		java.io.File filein = new java.io.File(inputFile+".csv");
		//create a File object (this does not create a file on disk yet)
		java.io.File fileout = new java.io.File(inputFile+".out");
		
		if (!filein.exists()){
			System.err.println("No such input file");
			System.exit(1);
		}
		System.out.println("Test1");

		//create a Scanner object for the file (this fails if the input
		//file does not exist on disk) 
		Scanner input = new Scanner(filein);

		//create a PrintWriter object (this creates the actual file on disk)
		//(if the file exists, it gets overwritten)
		java.io.PrintWriter output = new java.io.PrintWriter(fileout);

		
		//create species as a parallel array.
		java.io.File speciesFile = new java.io.File("species_list.txt");
		if (!speciesFile.exists()){
			System.err.println("No such file species list");
			System.exit(1);
		}
		
		Scanner speciesScanner1 = new Scanner(speciesFile);
		
		int speciesLen = 0;
		speciesScanner1.nextLine();
		while (speciesScanner1.hasNextLine()) {
			speciesLen++;
		    speciesScanner1.nextLine();
		}
		speciesScanner1.close();
		
		System.out.println(speciesLen);
		
		Scanner speciesScanner = new Scanner(speciesFile);

		String[] sp1 = new String[speciesLen];
		String[] sp2 = new String[speciesLen];
		int[] sp3 = new int[speciesLen];
		int[] sp4 =new int[3];
		String[] sp5 = new String[3];
		
		int i = 0;
		int q=0;
		int qq=0;
		String SA;

		speciesScanner.nextLine();
		while (speciesScanner.hasNext()){
			String sLine =speciesScanner.nextLine();
			String[] sArray=sLine.split(" ");
			SA=sArray[0]; //stores species abbreviation as string
			String SS=sArray[1]; //stores species full name as string
			for (q=2;q<sArray.length;q++){
				SS+=(" "+sArray[q]); //checks for other words in the full name outside the first word.
			}
			sp1[qq]=SA; //populates an array for abbreviations
			sp2[qq]=SS.toString(); //populates a parallel array for the full name of each tree.
			qq++;
		}
					
		
		input.nextLine(); //ignore the category tabs
		while (input.hasNext()){
			boolean validator = true;//to determine if each line is useable to create Tree objects
			String line; 
			line = input.nextLine(); //turns each line of the CSV into a string
			String[] tL=line.split(","); //parses the string into an array
			if (tL.length == 9){ //checks for correct length of array
				for(i=0;i<tL.length;i++){
					if (tL[i].equals("")){ //searches for invalid data
						validator = false; //returns false if the line is incomplete.
					}
				}
			}
			if (validator == true){ //creates new tree objects based on the results of the scanner class.
				Tree TempTree = new Tree(Integer.parseInt(tL[0]),tL[1],tL[2],tL[3],Integer.parseInt(tL[5]),tL[6],tL[7],Integer.parseInt(tL[8]));
				tList.add1(TempTree);
			}
		}
		//converts species abreviations to their full name
		int tListLen = tList.getCount();
		int y;
		int z;
		for (y=0;y<tListLen;y++){
			for (z=0;z<speciesLen;z++){
				if(tList.getTree(y).getSpecies().equals(sp1[z])){
					tList.getTree(y).setSpecies(sp2[z]);
					System.out.println(tList.getTree(y).getSpecies());
					
				} 
				
			}
		}
		
		/*
		//System.out.println(tList.getTree(0).getSpecies());
		//make list contains
		int zz=0;
		int yy=0;
		for(zz=0;zz<tListLen;zz++){
			for(yy=0;yy<speciesLen;yy++){
				if(tList.getTree(zz).getSpecies().equals(sp2[yy])){
					int index=Arrays.asList(sp2).indexOf(tList.getTree(zz).getSpecies());
					System.out.println(index+" "+tList.getTree(zz).getSpecies());
					sp3[index]= sp3[index]+1;
					System.out.println(sp3[index]);
					if (sp3[index]>=sp4[2]){
						sp4[2] = sp3[index];
						sp5[2] = tList.getTree(yy).getSpecies();
						if (sp3[index]>=sp4[1]){
							sp4[1] = sp3[index];
							sp5[1] = tList.getTree(yy).getSpecies();
							if (sp3[index]>=sp4[0]){
								sp4[0] = sp3[index];
								sp5[0] = tList.getTree(yy).getSpecies();
							}
						}
					}
					
				}
			}
			
		}*/
		
		System.out.println(sp4[0] + sp5[0]);
		//System.out.println(tList.sortSpecies());//FIX
		
		//finds largest diameter tree
		int max = tList.getTree(0).getDiameter();
		int n;
		int maxL =0; // max L is the index of the tree with the largest diameter.
		
		for (n=0;n < tListLen;n++){
			if (max <= tList.getTree(n).getDiameter()){
				maxL = n;
			}	
		}
		
		System.out.println(tList.sortBySpecies());
		
		//System.out.println("The largest tree: \n\n\t"+ tList.getTree(maxL).getSpecies()+", "+max+" inches in diameter\n\t"+ tList.getTree(maxL).getAddress());
		System.out.println(tList.getCount()+" count");
		//TODO: Loops to get values in each arraylist to compare
		
		output.print("test");
		output.print(tList.getTree(maxL).getMaxD());
		
		//System.out.println("a : " + Collections.frequency(tList, "ZESE"));
	output.close();
	input.close();
	speciesScanner.close();
	}
}
