package principal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TextArchive {
	
	public List getWordsList() {
		
		BufferedReader br = null;
		String word;
		List<String> wordTable = new ArrayList<String>();
		try {
			
			br = new BufferedReader(new FileReader("C:\\SentStrength_Data_english\\SentimentLookupTable.txt"));
			
			while (br.ready()) {
				word = br.readLine();
				if (word.contains("*")) {
					word = word.substring(0, word.indexOf("*", 0));
					//System.out.println(word);
					wordTable.add(word);
				} else {
					word = word.substring(0, word.indexOf("	", 0));
					//System.out.println(word);
					wordTable.add(word.trim());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return wordTable;
	}
	
	public void writeWords() {
		
	}
}
