import java.util.*;
public class species{
	public static void main(String[] args) throws Exception{
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
			speciesScanner.nextLine();
			i++;
		}
	}	
}