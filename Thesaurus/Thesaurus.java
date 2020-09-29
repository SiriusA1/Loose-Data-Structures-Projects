import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/**
 * Lab 9A - Thesaurus
 * Author: Conor McGullam
 */
public class Thesaurus {
	Map<String, List<String>> thesaurus;
	File syn = new File("C:\\Users\\Conor\\Desktop\\synonyms.txt");
	Scanner in;
	Scanner inLine;
	public Thesaurus() {
		try {
			in = new Scanner(syn);
			thesaurus = new TreeMap<String, List<String>>();
			while(in.hasNextLine()) {
				inLine = new Scanner(in.nextLine());
				List<String> synonyms = new ArrayList<String>();
				String word = inLine.next();
				while(inLine.hasNext()) {
					String syns = inLine.next();
					synonyms.add(syns);
				}
				thesaurus.put(word.toLowerCase(), synonyms);
				inLine.close();
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Add to the list of synonyms
	 * @param keyword
	 * @param synonyms separated by whitespace
	 * @return true if added to thesaurus
	 */
	public boolean add(String keyword, String synonyms) {
		if(thesaurus.equals(null)) {
			thesaurus = new TreeMap<String, List<String>>();
		}
		keyword = keyword.toLowerCase();
		List<String> synonymList = new ArrayList<String>();
		Scanner synLine = new Scanner(synonyms);
		while(synLine.hasNext()) {
			synonymList.add(synLine.next());
		}
		synLine.close();
		if(thesaurus.containsKey(keyword)) {
			List<String> existingSyns = thesaurus.get(keyword);
			for(String s : synonymList) {
				if(!existingSyns.contains(s)) {
					existingSyns.add(s);
				}
			}
			thesaurus.put(keyword.toLowerCase(), existingSyns);
		}else {
			thesaurus.put(keyword.toLowerCase(), synonymList);
		}
		return true;
	}

	/**
	 * Lookup the synonyms associated with this keyword
	 * @param keyword
	 * @return string of words in alphabetical order or null if not found
	 */
	public String lookup(String keyword) {
		if(thesaurus.containsKey(keyword)) {
			List<String> out = thesaurus.get(keyword);
			Collections.sort(out);
			String output = "";
			for(String s : out) {
				output = output + " " + s;
			}
			return output;
		}else {
			return null;
		}
	}

	/**
	 * Lookup the synonyms associated with this keyword
	 * @param keyword
	 * @return list of words in alphabetical order or null if not found
	 */
	public List<String> lookupToList(String keyword) {
		if(thesaurus.containsKey(keyword)) {
			List<String> out = thesaurus.get(keyword);
			Collections.sort(out);
			return out;
		}else {
			return null;
		}
	}
}
