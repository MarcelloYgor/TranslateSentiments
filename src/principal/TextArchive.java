package principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class TextArchive {
	
	public List[] getWordsList() {
		
		BufferedReader br = null;
		String line;
		String sentiment;
		String classifier;
		List<String> sentimentTable = new ArrayList<>();
		List<String> classifierTable = new ArrayList<>();
		List<String>[] splitResult = new List[2];
		try {
			
			br = new BufferedReader(new FileReader("C:\\SentStrength_Data_english\\SentimentLookupTable.txt"));
			
			while (br.ready()) {
				line = br.readLine();
				if (line.contains("*")) {
					sentiment = line.substring(0, line.indexOf("*", 0));
					classifier = line.substring(line.indexOf("*", 0));
					sentimentTable.add(sentiment.trim());
					classifierTable.add(classifier);
					//System.out.println(line + " : " + sentiment + classifier);
				} else {
					sentiment = line.substring(0, line.indexOf("	", 0));
					classifier = line.substring(line.indexOf("	", 0));
					sentimentTable.add(sentiment.trim());
					classifierTable.add(classifier);
					//System.out.println(line + " : " + sentiment + classifier);
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
		splitResult[0] = sentimentTable;
		splitResult[1] = classifierTable;
		return splitResult;
	}
	
	public void writeWords(List<String> wordTable, List<String> classifierTable) {
		
		BufferedWriter bw = null;
		int classifier;
		try {
			bw = new BufferedWriter(new FileWriter("C:\\SentStrength_Data_portuguese\\SentimentLookupTable.txt"));
			for (String word : wordTable) {
				classifier = Integer.parseInt(word.substring(word.indexOf("%") + 1, word.indexOf("@")));
				word = word.substring(0, word.indexOf("%", 0));
				bw.write(word + classifierTable.get(classifier));
				bw.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
