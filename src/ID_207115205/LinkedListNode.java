package ID_207115205;

public class LinkedListNode {
	private LinkedListNode next;
	private int rowNum;
	
	public LinkedListNode(LinkedListNode next, int x){
		this.next = next;
		rowNum = x;
	}
	
	public LinkedListNode(int x) {
		this(null, x);
	}
	
	public int getRowNum() {
		return rowNum;
	}
	
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	
	public LinkedListNode getNext() {
		return next;
	}

	public void setNext(LinkedListNode next) {
		this.next = next;
	}
	
	@Override
	public String toString() {
		return "" + rowNum;
	}
	
	public boolean hasNext() {
		if (next == null)
			return false;
		return true;
	}
	
	public boolean isEmpty() {
		return !hasNext();
	}
	
	public boolean find(int row) {
		if (row == rowNum)
			return true;
		LinkedListNode temp = next;
		while (next.hasNext()) {
			if (next.getRowNum() == row)
				return true;
			temp = next.getNext();
		}
		return false;
	}
	
	public int findMax() {
		int max = rowNum;
		LinkedListNode node = next;
		while (node != null) {
			if (max < node.getRowNum())
				max = node.getRowNum();
			node = node.getNext();
		}
		return max;
	}
	
}
