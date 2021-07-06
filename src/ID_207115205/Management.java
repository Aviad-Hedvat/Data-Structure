package ID_207115205;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Management {
	private BinTreeNode root;
	private File file;
	private int rowNum;
	private String[] words;
	private boolean emptyFile;
	private String line;

	public Management(Scanner scan) throws FileNotFoundException {
		file = new File("input.txt");
		scan = new Scanner(file);
		rowNum = 0;
		buildTree(scan);
		scan.close();
	}

	public BinTreeNode getRoot() {
		return root;
	}

	public void buildTree(Scanner scan) throws FileNotFoundException {
		if (scan.hasNext()) {
			emptyFile = false;
			String first;
			do {
				first = scan.next();
				first = onlyLetters(first);
			} while (first.length() == 0);
			root = new BinTreeNode(first, 1);				
			BinTreeNode node;
			while (scan.hasNext()) {
				rowNum++;
				line = scan.nextLine();
				line = line.replaceAll("[^\\p{Alpha}\\s]", " ");
				words = line.split(" ");
				for (int i = 0; i < words.length; i++) {
					if (words[i].length() != 0) {
						node = new BinTreeNode(words[i], rowNum);
						root.insert(root, node, rowNum);
					}
				}
			}
		}
		else {
			emptyFile = true;
		}
	}

	private String onlyLetters(String str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			if (isLetter(str.charAt(i)))
				sb.append(str.charAt(i));
		}
		return sb.toString();
	}

	public void output() throws FileNotFoundException {
		File output = new File("output.txt");
		PrintWriter pw = new PrintWriter(output);
		if (!emptyFile) {
			pw.println("Welcome to the Concordance system\n");
			root.printInOrder(root, pw);			
		}
		else {
			pw.println("Input file is empty.");
		}
		pw.close();
	}

	private boolean isLetter(char c) {
		if (c > 64 && c < 91)
			return true;
		else if (c > 96 && c < 123)
			return true;
		else
			return false;
	}

	public String findMax() {
		return root.findMax(root);
	}

	public int countLeaves(BinTreeNode node) {
		return root.countLeaves(node);
	}

	public int numNodes(BinTreeNode node) {
		return root.numNodes(node);
	}

	public boolean find(String word) {
		return root.find(root, word.toLowerCase());
	}

}
