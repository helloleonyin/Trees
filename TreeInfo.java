package trees;
import java.io.*;
import java.util.*;

public class TreeInfo{
	/**
	 * This class is used to read an input file and write the output file.
	 * It is a variation of Joanna's "ReadFile" and "WriteFile class"
	 * 
	 * @author Leon Yin
	 *
	 */
	// If the file cannot be opened the Scanner constructor throws
	// an exception. The program either has to catch it and handle it, or
	// re-throw it (as it is done in this main()).
	
	public static void main(String[] args) throws IOException {
		
		TreeList tList = new TreeList();
		
		//create a File object (this can be done even if the file 
		//does not exist on disk)
		java.io.File filein = new java.io.File("TestTree.csv");
		//create a File object (this does not create a file on disk yet)
		java.io.File fileout = new java.io.File("TestTrees.out");
		
		if (!filein.exists()){
			System.err.println("No such file");
			System.exit(1);
		}
		if (fileout.exists()) {
			System.out.println("File already exists");
			System.exit(0);
		}

		//create a Scanner object for the file (this fails if the input
		//file does not exist on disk) 
		Scanner input = new Scanner(filein);

		//create a PrintWriter object (this creates the actual file on disk)
		//(if the file exists, it gets overwritten)
		java.io.FileWriter output = new java.io.FileWriter(fileout);

		//create species as a parallel array.
		java.io.File speciesFile = new java.io.File("species_list.txt");
		if (!speciesFile.exists()){
				System.err.println("No such file");
				System.exit(1);
			}
		String[] sp1 = new String[120];
		String[] sp2 = new String[120];
		Scanner speciesScanner = new Scanner(speciesFile);
		int i = 0;
		while (speciesScanner.hasNext()){
			sp1[i]=speciesScanner.next().toString();
			sp2[i]=speciesScanner.next().toString();
			sp2[i]+=speciesScanner.next().toString();
			sp2[i]+=speciesScanner.next().toString();
			sp2[i]+=speciesScanner.next().toString();
			speciesScanner.nextLine();
			i++;
		}

		//read data from the input file (same methods as we used for reading
		//from the user) 
		while (input.hasNext()) {
			String id = input.next().;
			String st = input.next();
			String xs1 = input.next();
			String xs2 = input.next();
			input.next();
			int dia= input.nextInt();
			String sp = input.next();
			for i in range(len(sp1)){
				if sp.equals(sp1[i]){
					sp=sp2[i];
				}
			}
			String bor = input.next();
			String zip = input.next();
			tList.add1(new Tree(id,st,xs1,xs2,dia,sp,bor,zip));
			input.nextLine();
			System.out.println(zip);//test
		}

		// Close the file
		input.close();
		output.close();
	}

}