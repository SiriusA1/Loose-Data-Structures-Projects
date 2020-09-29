import jsjf.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
//Author: Conor McGullam

public class BoggleCipher {
	private ArrayList<BinaryTreeNode<String[]>> boggleList;
	private BinaryTreeNode<String[]> root;
	public BoggleCipher(String keyFile) {
		//create list
		try {
			File boggle = new File(keyFile);
			Scanner in = new Scanner(boggle);
			this.boggleList = new ArrayList<BinaryTreeNode<String[]>>();
			while(in.hasNextLine()) {
				Scanner inLine = new Scanner(in.nextLine());
				String dieNum = inLine.next();
				ArrayList<String> letters = new ArrayList<String>();
				while(inLine.hasNext()) {
					letters.add(inLine.next());
				}
				for(int i = 0; i < letters.size(); i++) {
					String[] nodeArray = {dieNum + i, letters.get(i)};
					this.boggleList.add(new BinaryTreeNode<String[]>(nodeArray, null, null));
				}
				inLine.close();
			}
			in.close();
			Collections.sort(this.boggleList, new NodeComparator());
			TreeMaker();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void TreeMaker() {
		this.root = this.boggleList.get(this.boggleList.size() / 2);
		TreeMakerLeft(this.root, 0, (this.boggleList.size()/2) - 1);
		TreeMakerRight(this.root, (this.boggleList.size()/2) + 1, this.boggleList.size() - 1);
	}
	private void TreeMakerLeft(BinaryTreeNode<String[]> currentNode, int startInd, int endInd) {
		if(endInd - startInd == 0) {
			currentNode.setLeft(this.boggleList.get(startInd));
		}else if(endInd - startInd == 1) {
			BinaryTreeNode<String[]> left = this.boggleList.get(startInd);
			currentNode.setLeft(left);
			TreeMakerRight(left, endInd, endInd);
		}else {
			BinaryTreeNode<String[]> left = this.boggleList.get(startInd + ((endInd - startInd) / 2));
			currentNode.setLeft(left);
			TreeMakerLeft(left, startInd, startInd + ((endInd - startInd) / 2) - 1);
			TreeMakerRight(left, startInd + ((endInd - startInd) / 2) + 1, endInd);
		}
	}
	private void TreeMakerRight(BinaryTreeNode<String[]> currentNode, int startInd, int endInd) {
		if(endInd - startInd == 0) {
			currentNode.setRight(this.boggleList.get(startInd));
		}else if(endInd - startInd == 1) {
			BinaryTreeNode<String[]> right = this.boggleList.get(startInd);
			currentNode.setRight(right);
			TreeMakerRight(right, endInd, endInd);
		}else {
			BinaryTreeNode<String[]> right = this.boggleList.get(startInd + ((endInd - startInd) / 2));
			currentNode.setRight(right);
			TreeMakerLeft(right, startInd, startInd + ((endInd - startInd) / 2) - 1);
			TreeMakerRight(right, startInd + ((endInd - startInd) / 2) + 1, endInd);
		}
	}
	class NodeComparator implements Comparator<BinaryTreeNode<String[]>> {
		@Override
		public int compare(BinaryTreeNode<String[]> arg0, BinaryTreeNode<String[]> arg1) {
			String[] arg00 = arg0.getElement();
			String[] arg11 = arg1.getElement();
			return arg00[0].compareTo(arg11[0]);
		}
	}
	public String decrypt(String codedWord) {
		if(codedWord.substring(2).equals("6")) {
			return ".";
		}else if(codedWord.substring(2).equals("7")) {
			return ",";
		}else if(codedWord.substring(2).equals("8")) {
			return "\n";
		}else if(codedWord.substring(2).equals("9")) {
			return " ";
		}
		String[] a = this.root.getElement();
		if(a[0].compareTo(codedWord) == 0) {
			return a[1];
		}else if(a[0].compareTo(codedWord) >= 1) {
			return decryptHelp(root.getLeft(), codedWord);
		}else {
			return decryptHelp(root.getRight(), codedWord);
		}
	}
	public String decryptHelp(BinaryTreeNode<String[]> currentNode, String codedWord) {
		String[] b = currentNode.getElement();
		if(b[0].compareTo(codedWord) == 0) {
			return b[1];
		}else if(b[0].compareTo(codedWord) >= 1) {
			return decryptHelp(currentNode.getLeft(), codedWord);
		}else {
			return decryptHelp(currentNode.getRight(), codedWord);
		}
	}
	public static void main(String[] args) {
		BoggleCipher test = new BoggleCipher("C:\\Users\\Conor\\Desktop\\boggle.txt");
		File data = new File("C:\\Users\\Conor\\Desktop\\boggledata.txt");
		Scanner test2;
		try {
			test2 = new Scanner(data);
			while(test2.hasNext()) {
				System.out.print(test.decrypt(test2.next()));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
