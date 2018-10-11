package principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Utils {
	
	public static void main(String [] args) {
		
		String specialChars[][] = {{"Ã£", "Ãº", "Ã¢", "Ã§", "Ã¡", "Ã©", "Ã­", "Ã³", "Ãª", "Ã´"}, 
				{"ã", "ú", "â", "ç", "á", "é", "í", "ó", "ê", "ô"}};
		
		HashSet<String> consistentList = new HashSet<String>();
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		List<String> listIn = new ArrayList<>();
		List<String> listOut = new ArrayList<>();
		try {
			
			br = new BufferedReader(new FileReader("C:\\SentStrength_Data_portuguese\\SentimentLookupTable_verbs.txt"));
			
			while (br.ready()) {
				listIn.add(br.readLine());
			}
			br.close();
			
			for (String line : listIn) {
				for (int i = 0; i < 10; i++) {
					line = line.replace(specialChars[0][i], specialChars[1][i]);
				}
				consistentList.add(line);
			}
			
			for (String words : consistentList) {
				listOut.add(words);
			}
			Collections.sort(listOut);
			
			bw = new BufferedWriter(new FileWriter("C:\\SentStrength_Data_portuguese\\SentimentLookupTable_verbs_adjusted.txt"));
			for (String line2 : listOut) {
				bw.write(line2);
				bw.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (bw != null) {
					bw.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
