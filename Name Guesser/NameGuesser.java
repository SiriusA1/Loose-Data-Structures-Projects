//Author: Conor McGullam
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class NameGuesser {
	private ArrayList<String> lastNames = new ArrayList<String>();
	public static void main(String[] args) throws IOException {
		NameGuesser guesser = new NameGuesser();
		guesser.readNames();
		guesser.guessName();
	}
	public void readNames() throws IOException, MalformedURLException
	{
		URL url = new URL(
				"https://www2.census.gov/topics/genealogy/1990surnames/dist.all.last");
		Scanner in = new Scanner(url.openStream());
		while(in.hasNextLine()) {
			lastNames.add(in.next());
			in.nextLine();
		}
		Collections.sort(lastNames);
	}
	public void guessName() {
		Scanner ask = new Scanner(System.in);
		int low = 0;
		int high = lastNames.size() - 1;
		int mid = high/2;
		while(high - low > 1) {
			System.out.println("low: " + low);
			System.out.println("mid: " + mid);
			System.out.println("high: " + high);
			System.out.println("Does your name come before \"" + lastNames.get(mid) + "\" in the dictionary? (Y/N)");
			String ans = ask.nextLine();
			if(ans.equalsIgnoreCase("n")) {
				low = mid;
				mid = ((high - low) / 2) + low;
			}else {
				high = mid;
				mid = ((high - low) / 2) + low;
			}
		}
		System.out.println("Is your name \"" + lastNames.get(mid) + "\"? (Y/N)");
		String ans = ask.nextLine();
		if(ans.equalsIgnoreCase("y")) {
			System.out.println("Name was guessed successfully.");
			ask.close();
		}else if(high - low == 1) {
			System.out.println("Is your name \"" + lastNames.get(mid + 1) + "\"? (Y/N)");
			String ans2 = ask.nextLine();
			if(ans2.equalsIgnoreCase("y")) {
				System.out.println("Name was guessed successfully.");
				ask.close();
			}else {
				System.out.println("Name was not guessed or could not be found.");
				ask.close();
			}
		}else {
			System.out.println("Name was not guessed or could not be found.");
			ask.close();
		}
	}
}
