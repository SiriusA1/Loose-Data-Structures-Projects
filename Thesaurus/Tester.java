package lab9;
import java.io.*;
import java.util.*;
/**
 * Lab 9A - Thesaurus tester
 */
public class Tester {
	public static void main(String[] args) throws FileNotFoundException {
		Thesaurus thesaurus = new Thesaurus();
		Scanner synonymList = new Scanner(new File("C:\\Users\\Conor\\Desktop\\synonyms.txt"));
		while (synonymList.hasNextLine()) {
			String keyword = synonymList.next();
			String synonyms = synonymList.nextLine().trim();
			boolean status = thesaurus.add(keyword, synonyms);
			System.out.println(keyword + (status ? " not" : "") + " added");
		}
		// Look up some words
		Scanner wordList = new Scanner("bad crooked keep look stop relentless");
				while (wordList.hasNext()) {
					String word;
					String synonyms = thesaurus.lookup(word = wordList.next());
					System.out.println(word + " - "
							+ ((synonyms == null) ? "none found" : synonyms));
				}
				// Test the lookupToList method
				List<String> list = thesaurus.lookupToList("look");
				System.out.println("List output test\n" + list);
	}
}