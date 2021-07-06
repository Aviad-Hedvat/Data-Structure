package ID_207115205;

import java.io.PrintWriter;

public class BinTreeNode {
	private BinTreeNode left, right;
	private String data;
	private LinkedListNode rowNumbers;

	public BinTreeNode(BinTreeNode left, BinTreeNode right, String x, int rowNum) {
		this.left = left;
		this.right = right;
		data = x;
		rowNumbers = new LinkedListNode(rowNum);
	}

	public BinTreeNode(String x, int rowNum) {
		this (null, null, x, rowNum);
	}
	
	public BinTreeNode() {
		left = null;
		right = null;
		data = null;
		rowNumbers = null;
	}

	public String getData() {
		return data;
	}

	public BinTreeNode getLeft(){
		return left;
	}

	public BinTreeNode getRight(){
		return right;
	}
	
	public LinkedListNode getRowNumbers() {
		return rowNumbers;
	}

	public void setLeft(BinTreeNode left) {
		this.left = left;
	}

	public void setRight(BinTreeNode right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return data.toString();
	}

	public boolean find(BinTreeNode node, String x) {
		if (node == null)
			return false;
		else if (node.data.toLowerCase().equals(x.toLowerCase()))
			return true;
		else {
			int result = node.data.toLowerCase().compareTo(x);
			if (result > 0) 
				return find(node.getLeft(), x);
			else
				return find(node.getRight(), x);
		}
	}

	public boolean isLeaf(BinTreeNode node) {
		if (node == null)
			return false;
		return node.getLeft() == null && node.getRight() == null;
	}

	public int numNodes(BinTreeNode node) {
		if (node == null)
			return 0;
		return (numNodes(node.getLeft()) + numNodes(node.getRight()) + 1);
	}

	public int countLeaves(BinTreeNode node) {
		if (node == null)
			return 0;
		else if (isLeaf(node)) 
			return 1;
		return (countLeaves(node.getLeft()) + countLeaves(node.getRight()));
	}

	public void insert(BinTreeNode root, BinTreeNode node, int rowNum) {
		String rootStr, nodeStr;
		rootStr = root.getData().toLowerCase();
		nodeStr = node.getData().toLowerCase();
		int result = nodeStr.compareTo(rootStr);
		if (isLeaf(root)) {
			if (result > 0) {
				root.setRight(node);
				return;
			}
			else if (result < 0) {
				root.setLeft(node);
				return;
			}
			else {
				root.insertRowNum(rowNum);
				return;
			}
		}	
		else if (result > 0) {
			if (root.getRight() != null)
				insert(root.getRight(), node, rowNum);
			else {
				root.setRight(node);
			}
			return;
		}
		else if (result < 0) {
			if (root.getLeft() != null)
				insert(root.getLeft(), node, rowNum);
			else {
				root.setLeft(node);
			}
			return;
		}
		else {
			root.insertRowNum(rowNum);
			return;
		}		
	}

	private void insertRowNum(int rowNum) {
		LinkedListNode temp = rowNumbers;
		while (temp.hasNext())
			temp = temp.getNext();
		temp.setNext(new LinkedListNode(rowNum));
	}

	public boolean isEmpty(BinTreeNode node) {
		if (node == null)
			return true;
		return false;
	}

	public void printInOrder(BinTreeNode root, PrintWriter pw) {
		if (!isEmpty(root)) {
			printInOrder(root.getLeft(), pw);
			pw.println("\"" + root.data + "\" - " + root.printLines());
			printInOrder(root.getRight(), pw);
		}
	}
	
	public void printPreOrder(BinTreeNode root, PrintWriter pw) {
		if(!isEmpty(root)) {
			pw.println("\"" + root.data + "\" - " + root.printLines());
			printPreOrder(root.getLeft(), pw);
			printPreOrder(root.getRight(), pw);
		}
	}
	
	public void printPostOrder(BinTreeNode root, PrintWriter pw) {
		if (!isEmpty(root)) {
			printPostOrder(root.getLeft(), pw);
			printPostOrder(root.getRight(), pw);
			pw.println("\"" + root.data + "\" - " + root.printLines());
		}
	}
	
	private String printLines() {
		StringBuffer sb = new StringBuffer(" ");
		LinkedListNode temp = this.rowNumbers;
		while (temp.hasNext()) {
			sb.append(temp.toString() + ", ");
			temp = temp.getNext();
		}
		sb.append(temp.getRowNum());		
		return sb.toString();
	}
	
	public String findMax(BinTreeNode root) {
		if (root.getRight() != null)
			return findMax(root.getRight());
		else
			return root.data;
	}
	
}
