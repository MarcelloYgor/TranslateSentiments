package principal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Main {

	public static void main(String [] args) {
				
		//translateArchive();
		
		removeDuplicates();
	}
	
	public static void translateArchive() {
		
		String file = "C:\\SentStrength_Data_portuguese\\SentimentLookupTable.txt";
		
		List<String>[] wordList;
		List<String> wordsTranslated;
		
		TextArchive txtAr = new TextArchive();
		Translator trl = new Translator();
		
		
		wordList = txtAr.getWordsList("C:\\SentStrength_Data_english\\SentimentLookupTable.txt");
		wordsTranslated = trl.translateList(wordList[0]);
		Collections.sort(wordsTranslated);
		
		txtAr.writeWords(wordsTranslated, wordList[1], file);
	}
	
	public static void removeDuplicates() {
		
		String file = "C:\\SentStrength_Data_portuguese\\SentimentLookupTable_verbs.txt";
		
		List<String>[] wordList;
		List<String> aux = new ArrayList<>();
		List<String> aux2 = new ArrayList<>();
		HashSet<String> consistentList = new HashSet<String>();
		List<String> verbs;
		
		TextArchive txtAr = new TextArchive();
		
		wordList = txtAr.getWordsList("C:\\SentStrength_Data_portuguese\\SentimentLookupTable.txt");
		
		aux2 = wordList[0];
		
		for (int i = 0; i < wordList[0].size(); i++) {
			aux.add(wordList[0].get(i) + "%" + i + "@");
		}
		
		wordList[0] = aux;
		
		System.out.println(wordList[0].size());
		
		verbs = conjugateVerbs(aux2);
		
		for (String verb : verbs) {
			wordList[0].add(verb);
		}
		
		for (String word : wordList[0]) {
			consistentList.add(word);
		}
		
		wordList[0] = new ArrayList<String>(consistentList);
		Collections.sort(wordList[0]);
		
		txtAr.writeWords(wordList[0], wordList[1], file);
	}
	
	public static List<String> conjugateVerbs(List<String> wordList) {
		
		Conjugator conj = new Conjugator();
		return conj.conjugate(wordList);
	}
}
