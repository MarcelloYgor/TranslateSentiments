package principal;

import java.util.List;

public class Main {

	public static void main(String [] args) {
		
		List<String> wordList;
		
		TextArchive txtAr = new TextArchive();
		Translator trl = new Translator();
		
		wordList = txtAr.getWordsList();
		trl.translateList(wordList);
	}
}
