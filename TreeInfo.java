package trees;
import java.io.*;
import java.util.*;
/**
 * 
 * @author leon yin
 * @version 1.1
 * @since 02.12.2015
 *
 */
public class TreeInfo {
	/**
	 * This program takes a CVS file of Tree data from NYC open data and returns 5 calculated statistics
	 * <p>
	 * The input file is read, parsed. and valid data is used to create unique Tree objects.
	 * The Tree objects are then put into an ArrayList TreeList.
	 * Documentation of the Tree and TreeList class can be found in their respective class files.
	 * This class manipulates the Tree objects in 5 ways.
	 * 
	 * 1. Most popular Tree
	 * 2. Least popular Tree
	 * 3. Greenest Zipcode
	 * 4. Least Green Zipcode
	 * 5. Largest Tree.
	 * 
	 * Calculations 1-4 are top/lowest 3 values.
	 * Calculation 5 is just one value.
	 * <p>
	 * 
	 * @param args containing a CSV containing tree data for trees in one of the 5 boros on NY.
	 * @return output.out with the 5 calculated scores.
	 * @throws IOException for input files that do not exist.
	 *
	 */
	
	public static void main(String[] args) throws IOException {
		String inputFile = args[2];
		//create a File object for the input file
		java.io.File filein = new java.io.File(inputFile);
		if (!filein.exists()){
			System.err.println("No such input file");
			System.exit(1);
		}
		String outputFile = inputFile.replace(".csv","");
		//create a Scanner object for the input file.
		Scanner input = new Scanner(filein);
		
		//create a File object for the outputfile
		java.io.File fileout = new java.io.File(outputFile+".out");
		//create a PrintWriter for the output file.
		java.io.PrintWriter output = new java.io.PrintWriter(fileout);

		/**
		 * this function populates two parallel arrays, 
		 * one for the species list abbreviations (sp1) 
		 * which occur in in the species_list.txt file,
		 * the second array corresponds to the abbreviation's full name (sp2).
		 */
		java.io.File speciesFile = new java.io.File("species_list.txt");
		if (!speciesFile.exists()){
			System.err.println("No such file species list");
			System.exit(1);
		}
		
		//this counts the number of unique species in the species input file.
		Scanner speciesScanner1 = new Scanner(speciesFile);
		
		int speciesLen = 0;
		speciesScanner1.nextLine();
		while (speciesScanner1.hasNextLine()) {
			speciesLen++;
		    speciesScanner1.nextLine();
		}
		speciesScanner1.close();
		
		//this is where the arrays are populated
		Scanner speciesScanner = new Scanner(speciesFile);

		String[] sp1 = new String[speciesLen];
		String[] sp2 = new String[speciesLen];
		int remainingLine;
		int indexSP=0;
		speciesScanner.nextLine(); //skip category tabs
		while (speciesScanner.hasNext()){
			String sLine =speciesScanner.nextLine();
			String[] sArray=sLine.split(" ");
			String tempSP1=sArray[0]; //stores species abbreviation as string
			String temptSP2=sArray[1]; //stores species full name as string
			for (remainingLine=2;remainingLine<sArray.length;remainingLine++){
				temptSP2+=(" "+sArray[remainingLine]); //checks for other words in the full name outside the first word.
			}
			sp1[indexSP]=tempSP1; //populates an array for abbreviations
			sp2[indexSP]=temptSP2.toString(); //populates a parallel array for the full name of each tree.
			indexSP++;
		}		
		/**
		 * This function is what creates the Tree objects and populates the TreeList arraylist.
		 * The boolean Validator is intended to weed out Trees with incomplete information
		 * The boolean zipValidator is intended to stop files with unintended commas producing 9 objects in the string list.
		 * The tree object takes in 8 of the 9 variables parsed from the input file.
		 */
		TreeList tList = new TreeList();
		int i = 0;
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
				boolean zipValidator= true;
				try { 
					Integer.parseInt(tL[8]); 
				} catch(NumberFormatException e) { 
					zipValidator= false; 
				}
				
				if (validator == true && zipValidator ==true){ //creates new tree objects based on the results of the scanner class.
					Tree TempTree = new Tree(Integer.parseInt(tL[0]),tL[1],tL[2],tL[3],Integer.parseInt(tL[5]),tL[6],tL[7],Integer.parseInt(tL[8]));
					tList.add1(TempTree);
				}
			}
		}
		/**
		 * This function reads each species parameter in each Tree object and converts the abbreviated species name to the correct species name.
		 */
		int tListLen = tList.getCount();
		int y;
		int z;
		for (y=0;y<tListLen;y++){
			for (z=0;z<speciesLen;z++){ //iterates through indexes of the species abbreviation array. 
				if(tList.getTree(y).getSpecies().equals(sp1[z])){
					tList.getTree(y).setSpecies(sp2[z]);					
				} 
			}
		}
		/**
		 * This function reads each species parameter in each Species object and converts the 
		 * abbreviated species name to the correct species name.
		 */
		int countS = tList.getListS().size(); // the size of the Species arrayList.
		for (y=0;y<countS;y++){
			for (z=0;z<speciesLen;z++){
				if(tList.getListS().get(y).getSP().equals(sp1[z])){
					tList.getListS().get(y).setSP(sp2[z]);
				} 	
			}
		}
		/**
		 * This function finds the largest diameter by iterating through tList and compares the diameter 
		 * of the tree in question with the previous tree. 
		 * The value of the largest diameter is stored as max.
		 * maxL stores the index of the largest tree
		 */
		int maxSize = tList.getTree(0).getDiameter();
		int maxIndex =0; // max L is the index of the tree with the largest diameter.
		int n;
		
		for (n=0;n < tListLen;n++){
			if (maxSize <= tList.getTree(n).getDiameter()){
				maxIndex = n;
			}	
		}
		
		int SC = tList.getCountS()-1; //Species ArrayList Count
		int ZC = tList.getCountZ()-1; //ZipCode ArrayList Count
		
		Collections.sort(tList.getListS()); //sorts the Species ArrayList according to the compare to Override (speciesCount).
		Collections.sort(tList.getListZ()); //sorts the ZipCode ArrayList according to the compare to Override (ZipCount)
		
		//Four of the Five "Top 3" calculations are done here, the if statement is to account for ties, allowing a top fourth candidate.
		if (tList.getListS().get(0).getSPC()==(tList.getListS().get(1).getSPC()) || tList.getListS().get(1).getSPC()==(tList.getListS().get(2).getSPC()) || tList.getListS().get(2).getSPC()==(tList.getListS().get(3).getSPC())){
			output.print("Most popular Trees:\n\n\t"+tList.getListS().get(0).getSP()+" "+tList.getListS().get(0).getSPC()+"\n\t"+tList.getListS().get(1).getSP()+" "+tList.getListS().get(1).getSPC()+"\n\t"+tList.getListS().get(2).getSP()+" "+tList.getListS().get(2).getSPC()+"\n\t"+tList.getListS().get(3).getSP()+" "+tList.getListS().get(3).getSPC()+"\n\n");
		}
		else{
			output.print("Most popular Trees:\n\n\t"+tList.getListS().get(0).getSP()+" "+tList.getListS().get(0).getSPC()+"\n\t"+tList.getListS().get(1).getSP()+" "+tList.getListS().get(1).getSPC()+"\n\t"+tList.getListS().get(2).getSP()+" "+tList.getListS().get(2).getSPC()+"\n\n");
		}
		if (tList.getListS().get(SC).getSPC()==(tList.getListS().get(SC-1).getSPC()) || tList.getListS().get(SC-1).getSPC()==(tList.getListS().get(SC-2).getSPC()) || tList.getListS().get(SC-2).getSPC()==(tList.getListS().get(SC-3).getSPC())){
			output.print("Least popular Trees:\n\n\t"+tList.getListS().get(SC).getSP()+" "+tList.getListS().get(SC).getSPC()+"\n\t"+tList.getListS().get(SC-1).getSP()+" "+tList.getListS().get(SC-1).getSPC()+"\n\t"+tList.getListS().get(SC-2).getSP()+" "+tList.getListS().get(SC-2).getSPC()+"\n\t"+tList.getListS().get(SC-3).getSP()+" "+tList.getListS().get(SC-3).getSPC()+"\n\n");
		}
		else{
			output.print("Least popular Trees:\n\n\t"+tList.getListS().get(SC).getSP()+" "+tList.getListS().get(SC).getSPC()+"\n\t"+tList.getListS().get(SC-1).getSP()+" "+tList.getListS().get(SC-1).getSPC()+"\n\t"+tList.getListS().get(SC-2).getSP()+" "+tList.getListS().get(SC-2).getSPC()+"\n\n");
		}
		
		if (tList.getListZ().get(0).getZC()==(tList.getListZ().get(1).getZC()) || tList.getListZ().get(1).getZC()==(tList.getListZ().get(2).getZC()) || tList.getListZ().get(2).getZC()==(tList.getListZ().get(3).getZC())){
			output.print("Most Green Zipcode:\n\n\t"+tList.getListZ().get(0).getZP()+" "+tList.getListZ().get(0).getZC()+"\n\t"+tList.getListZ().get(1).getZP()+" "+tList.getListZ().get(1).getZC()+"\n\t"+tList.getListZ().get(2).getZP()+" "+tList.getListZ().get(2).getZC()+"\n\t"+tList.getListZ().get(3).getZP()+" "+tList.getListZ().get(3).getZC()+"\n\n");
		}
		else{
			output.print("Most Green Zipcode:\n\n\t"+tList.getListZ().get(0).getZP()+" "+tList.getListZ().get(0).getZC()+"\n\t"+tList.getListZ().get(1).getZP()+" "+tList.getListZ().get(1).getZC()+"\n\t"+tList.getListZ().get(2).getZP()+" "+tList.getListZ().get(2).getZC()+"\n\n");
		}
		
		if (tList.getListZ().get(ZC).getZC()==(tList.getListZ().get(ZC-1).getZC()) || tList.getListZ().get(ZC-1).getZC()==(tList.getListZ().get(ZC-2).getZC()) || tList.getListZ().get(ZC-2).getZC()==(tList.getListZ().get(ZC-3).getZC())){
			output.print("Least Green Zipcode:\n\n\t"+tList.getListZ().get(ZC).getZP()+" "+tList.getListZ().get(ZC).getZC()+"\n\t"+tList.getListZ().get(ZC-1).getZP()+" "+tList.getListZ().get(ZC-1).getZC()+"\n\t"+tList.getListZ().get(ZC-2).getZP()+" "+tList.getListZ().get(ZC-2).getZC()+"\n\t"+tList.getListZ().get(ZC-3).getZP()+" "+tList.getListZ().get(ZC-3).getZC()+"\n\n");
		}
		else{
			output.print("Least Green Zipcode:\n\n\t"+tList.getListZ().get(ZC).getZP()+" "+tList.getListZ().get(ZC).getZC()+"\n\t"+tList.getListZ().get(ZC-1).getZP()+" "+tList.getListZ().get(ZC-1).getZC()+"\n\t"+tList.getListZ().get(ZC-2).getZP()+" "+tList.getListZ().get(ZC-2).getZC()+"\n\n");
		}
		
		output.print(tList.getTree(maxIndex).getMaxD());
		
	//The scanners and writers are closed.
	output.close();
	input.close();
	speciesScanner.close();
	}
}
