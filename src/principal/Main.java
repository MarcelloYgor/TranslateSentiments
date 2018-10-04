package principal;

import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String [] args) {
		
		List<String>[] wordList;
		List<String> wordsTranslated;
		
		TextArchive txtAr = new TextArchive();
		Translator trl = new Translator();
		
		wordList = txtAr.getWordsList();
		wordsTranslated = trl.translateList(wordList[0]);
		Collections.sort(wordsTranslated);
		
		txtAr.writeWords(wordsTranslated, wordList[1]);
	}
}
