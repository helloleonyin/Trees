import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parse {
	public static void main(String[] args){
		String treeFile = "BrooklynTree.csv";
		BufferedReader FileReader = null;

		final String DELIMITER = "\t";
		
		try{
			String treeLine ] "";
			FileReader = new BufferedReader(new FileReader(treeFile));
			//read file
			while ((line = FileReader.readLine()) != null){
				String[] tokens = line.split(DELIMITER);
				for(String token : tokens){
					System.out.println(token);
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				FileReader.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}