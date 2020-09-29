import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
//Author: Conor McGullam

public class WordIndexing {
	private TreeMap<String, ArrayList<Integer>> dictionary;
	public WordIndexing(String path) {
		this.dictionary = new TreeMap<String, ArrayList<Integer>>();
		File words = new File(path);
		try {
			Scanner in = new Scanner(words);
			int line = 0;
			while(in.hasNextLine()) {
				Scanner in2 = new Scanner(in.nextLine());
				while(in2.hasNext()) {
					String inWord = in2.next();
					if(inWord.length() > 3) {
						String noPunctWord = inWord.replaceAll("\\W", "");
						String formattedWord = noPunctWord.toLowerCase();
						if(this.dictionary.containsKey(formattedWord)) {
							this.dictionary.get(formattedWord).add(line);
						}else {
							ArrayList<Integer> temp = new ArrayList<Integer>();
							temp.add(line);
							this.dictionary.put(formattedWord, temp);
						}
					}
				}
				line = line + 1;
				in2.close();
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void printDictionary() {
		String first = this.dictionary.firstKey();
		System.out.println(first + " - " + this.dictionary.get(first).toString());
		String key = this.dictionary.higherKey(first);
		while(key != this.dictionary.lastKey()) {
			System.out.println(key + " - " + this.dictionary.get(key).toString());
			key = this.dictionary.higherKey(key);
		}
		String last = this.dictionary.lastKey();
		System.out.println(last + " - " + this.dictionary.get(last).toString());
	}
	public static void main(String[] args) {
		WordIndexing test = new WordIndexing("C:\\Users\\Conor\\Desktop\\words.txt");
		test.printDictionary();
	}
}
